package com.mobigen.loans.utils;

import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static String getRequestHeaderInfos(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========= Request Header Info =========");
        sb.append("\nRequest IP: " + getClientIp(request));
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                sb.append("\n" + headerName + " = " + headerValue);
            }
        } else {
            sb.append("\nNo headers found in the request.");
        }
        sb.append("\n=======================================");
        return sb.toString();
    }

    // 클라이언트 IP 가져오는 메서드
    private static String getClientIp(HttpServletRequest request) {
        
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
