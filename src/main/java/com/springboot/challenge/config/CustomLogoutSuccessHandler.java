package com.springboot.challenge.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.springboot.challenge.web.util.SessionManager.*;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        if (authentication != null && authentication.getDetails() != null) {
            request.getSession().invalidate();
        }
        setSessionAttribute(request.getSession(), BAG_ATTRIBUTE_NAME, new HashMap<Long, Integer>());
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/products");
    }
}
