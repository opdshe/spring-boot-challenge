package com.springboot.challenge.config;

import com.springboot.challenge.web.util.SessionManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.springboot.challenge.web.util.SessionManager.*;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        if (httpSession != null) {
            String redirectUrl = SessionManager.getPrevPageSessionAttribute(httpSession);
            Map<Long, Integer> bag = new HashMap<>();
            httpSession.setAttribute(USER_ATTRIBUTE_NAME, authentication.getPrincipal());
            httpSession.setAttribute(BAG_ATTRIBUTE_NAME, bag);

            if (redirectUrl != null) {
                httpSession.removeAttribute(PREV_PAGE_ATTRIBUTE_NAME);
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
