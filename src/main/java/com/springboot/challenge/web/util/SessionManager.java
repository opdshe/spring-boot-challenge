package com.springboot.challenge.web.util;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SessionManager {
    public static final String BAG_ATTRIBUTE_NAME = "bag";
    public static final String USER_ATTRIBUTE_NAME = "user";
    public static final String PREV_PAGE_ATTRIBUTE_NAME = "prevPage";

    public static void setSessionAttribute(HttpSession httpSession, String attributeName, Object target){
        if(!isAlreadyExist(httpSession, attributeName)) {
            httpSession.setAttribute(attributeName, target);
        }
    }

    public static Optional<Object> getSessionAttribute(HttpSession httpSession, String attributeName) {
        return Optional.of(httpSession.getAttribute(attributeName));
    }
    private static boolean isAlreadyExist(HttpSession httpSession, String attributeName) {
        return httpSession.getAttribute(attributeName) != null;
    }

    public static void sessionReset(HttpSession httpSession, String attributeName, Object target){
        httpSession.setAttribute(attributeName, target);
    }
}
