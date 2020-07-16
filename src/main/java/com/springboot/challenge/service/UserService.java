package com.springboot.challenge.service;

import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.domain.order.OrderRepository;
import com.springboot.challenge.domain.order.Orders;
import com.springboot.challenge.exceptions.AlreadyExistUserIdException;
import com.springboot.challenge.exceptions.DoNotHaveUserSessionAttributeException;
import com.springboot.challenge.exceptions.MemberMismatchException;
import com.springboot.challenge.web.dto.MemberRegisterRequestDto;
import com.springboot.challenge.web.dto.OrderDtoForMyPage;
import com.springboot.challenge.web.util.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.springboot.challenge.web.util.SessionManager.*;

@RequiredArgsConstructor
@Service
public class UserService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long register(MemberRegisterRequestDto memberRegisterRequestDto) throws AlreadyExistUserIdException {
        if (!isAvailableUserId(memberRegisterRequestDto.getUserId())) {
            throw new AlreadyExistUserIdException(memberRegisterRequestDto.getUserId());
        }
        return memberRepository.save(memberRegisterRequestDto.toEntity()).getId();
    }

    @Transactional
    public List<OrderDtoForMyPage> getOrderDtosForMyPage(HttpSession httpSession){
        UserDetails user = (UserDetails) getSessionAttribute(httpSession, USER_ATTRIBUTE_NAME)
                .orElseThrow(DoNotHaveUserSessionAttributeException::new);
        String userId = user.getUsername();
        Member member = this.findByUserId(userId);
        List<Orders> ordersFindByMember = this.findAllByMember(member);
        return ordersFindByMember.stream()
                .map(OrderDtoForMyPage::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId)
                .orElseThrow(() -> new MemberMismatchException(userId));
    }

    @Transactional
    public List<Orders> findAllByMember(Member member) {
        return orderRepository.findAllByMember(member);
    }

    @Transactional
    public boolean isAvailableUserId(String userId) {
        Optional<Member> memberFindByUserId = memberRepository.findByUserId(userId);
        return !memberFindByUserId.isPresent();
    }
}
