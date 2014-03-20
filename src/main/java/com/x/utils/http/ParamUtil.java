package com.x.utils.http;

import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 
 * 参数工具类
 * @author kenmy
 *
 */
public class ParamUtil {
	
	private static Logger log=Logger.getLogger(ParamUtil.class);
	
	/**
	 * 获取得到排序好的查询字符串
	 * @param params 请求参数
	 * @param isContainSignature 是否包含signature参数
	 * @return
	 */
	public static  String getSortQueryString(List<ParamEntry> params) throws Exception {
		Collections.sort( params, new Comparator<ParamEntry>(){
			public int compare( ParamEntry object1, ParamEntry object2 ){
					return object1.getKey().compareTo( object2.getKey());
			}
		});
		return toQueryString(params);
	}
	
	/**
	 * list to string 
	 * @param params
	 * @return string 格式：a=xx&b=xx&c=xx&d=xx
	 */
	public static String toQueryString(List<ParamEntry> params){
		StringBuffer sb = new StringBuffer();
		for(ParamEntry pe:params){
			sb.append(pe.getKey()).append("=").append(pe.getValue()).append("&");
		}
		
		String text = sb.toString();
		if(text.endsWith("&")) {
			text=text.substring(0,text.length()-1);
		}
		return text;
	}
	
	/**
	 * params转MAP工具
	 * params格式为：commandId=20003&gameId=10010&sn=0&version=0&adultFlag=0&errorMessage=帐号密码错误&code=0001&customerId=
	 * @param params
	 * @return
	 */
	public static Map<String,String> queryStringToMap(String params){
		String[] rs=params.split("&");
		Map<String,String> map=new HashMap<String, String>(2);
		for(String ss:rs){
			String[] s1=ss.split("=");
			if(s1.length<2){
				map.put(s1[0], "");
			}else{
				map.put(s1[0], s1[1]);
			}
		}
		return map;
	}
	
	/**
	 * 打印接收到的Post值
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public static void PrintParams(HttpServletRequest request,String name) {
		Enumeration<String> e = request.getParameterNames();
		String paramName, paramValue;
		StringBuilder sb=new StringBuilder();
		sb.append(name+" recevie data>>>");
		while (e.hasMoreElements()) {
			paramName = e.nextElement();
			paramValue = request.getParameter(paramName);
			sb.append(paramName).append("=").append(paramValue).append("&");
		}
		log.info(sb.toString());
	}
}
