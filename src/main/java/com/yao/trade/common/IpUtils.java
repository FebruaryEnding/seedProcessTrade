package com.yao.trade.common;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: caomin
 * @Date: 2019年01月25日
 * @Description: 获取ip地址
 */
public class IpUtils {

    private final static String IP_SEPARATOR = ",";

    /**
     * 获取真实Ip
     *
     * @param request
     * @return
     */
    public static String getRealIpAddress(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String ip = request.getHeader("x-forwarded-for");

        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (ip.indexOf(IP_SEPARATOR) != -1) {
                ip = ip.substring(0, ip.indexOf(IP_SEPARATOR));
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        } else {
            // 当有多级反向代理时，x-forwarded-for值为多个时取第一个ip地址
            if (ip.indexOf(IP_SEPARATOR) != -1) {
                ip = ip.substring(0, ip.indexOf(IP_SEPARATOR));
            }
            return ip;
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        } else {
            return ip;
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else {
            return ip;
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = "";
        }
        return ip;
    }

    public static String getRealIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (request == null) {
            return "";
        }
        String ip = request.getHeader("x-forwarded-for");

        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (ip.indexOf(IP_SEPARATOR) != -1) {
                ip = ip.substring(0, ip.indexOf(IP_SEPARATOR));
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        } else {
            // 当有多级反向代理时，x-forwarded-for值为多个时取第一个ip地址
            if (ip.indexOf(IP_SEPARATOR) != -1) {
                ip = ip.substring(0, ip.indexOf(IP_SEPARATOR));
            }
            return ip;
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        } else {
            return ip;
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else {
            return ip;
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = "";
        }
        return ip;
    }
}
