package com.x.utils.http;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>Title:IPUtil</p>
 * <p>Description:获取IP工具类</p>
 * @Author Chenkangming @Date 2013-9-5
 * @version 1.0
 */
public class IPUtil {
	/**
	 * 获取网络中真实的IP地址
	 * @param request
	 * @return ip
	 */
	public static String getIPFromRequest(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String[] arrIp=ip.split(",");
		if(arrIp.length>1){
			ip=arrIp[1];
		}
		return ip.trim();
	}
}
