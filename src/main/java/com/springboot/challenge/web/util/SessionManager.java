package com.springboot.challenge.web.util;

import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    public static final String BAG_ATTRIBUTE_NAME = "bag";
    public static final String USER_ATTRIBUTE_NAME = "user";
    public static final String PREV_PAGE_ATTRIBUTE_NAME = "prevPage";

    public static void setBagAttributeName (HttpSession httpSession) {
        httpSession.setAttribute(BAG_ATTRIBUTE_NAME, new HashMap<Long,String>());
    }

    public static UserDetails getUserSessionAttribute (HttpSession httpSession) {
        return (UserDetails) httpSession.getAttribute(USER_ATTRIBUTE_NAME);
    }

    public static Map<Long, Integer> getBagSessionAttribute(HttpSession httpSession) {
        return (Map<Long, Integer>) httpSession.getAttribute(BAG_ATTRIBUTE_NAME);
    }
    public static String getPrevPageSessionAttribute(HttpSession httpSession) {
        return (String) httpSession.getAttribute(PREV_PAGE_ATTRIBUTE_NAME);
    }
}
