package vn.nuce.core.common.utils;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static SessionUtil sessionUtil = null;

    public static SessionUtil getInstance() {
        if (sessionUtil == null) {
            sessionUtil = new SessionUtil();
        }
        return sessionUtil;
    }

    public void putValue(HttpSession session, String key, Object value) {
        session.setAttribute(key, value);
    }

    public Object getValue(HttpSession session, String key) {
        return session.getAttribute(key);
    }

    public void removeValue(HttpSession session, String key) {
        session.removeAttribute(key);
    }
}
