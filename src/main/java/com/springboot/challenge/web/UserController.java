package com.springboot.challenge.web;

import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.order.Orders;
import com.springboot.challenge.service.MemberService;
import com.springboot.challenge.service.OrderService;
import com.springboot.challenge.web.dto.MyPageResponseDto;
import com.springboot.challenge.web.util.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final MemberService memberService;
    private final OrderService orderService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referer);
        return "login";
    }
    @GetMapping("/mypage")
    public String mypage(HttpSession httpSession, Model model) {
        System.out.println("=============================mypage==============================");
        String userId = SessionManager.getUserSessionAttribute(httpSession).getUsername();
        Member member = memberService.findByUserId(userId);
        List<Orders> allByMember = orderService.findAllByMember(member);
        List<MyPageResponseDto> orderList = allByMember.stream()
                .map(MyPageResponseDto::new)
                .collect(Collectors.toList());
        model.addAttribute("orderList", orderList);
        return "mypage";
    }
}
