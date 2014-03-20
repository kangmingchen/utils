package com.x.utils.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 网络请求访问工具
 * 
 * @author Kenmy
 * 
 */
public class HttpUtil {

	private static Logger log = Logger.getLogger(HttpUtil.class);

	/**
	 * 网络请求 GET
	 * 
	 * @param uri
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return string
	 */
	public static String Get(String uri, String params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		StringBuffer sb = new StringBuffer("");
		CloseableHttpResponse response = null;
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000).build();
		try {
			String p = (params == null) ? "" : "?" + params;
			HttpGet httpGet = new HttpGet(uri + p);
			log.info("GET >>> " + httpGet.getURI());
			httpGet.setConfig(requestConfig);
			response = httpclient.execute(httpGet);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					sb.append(EntityUtils.toString(entity, "UTF-8"));
				}
			} else {
				sb.append(code);
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		log.info("GET BACK >>> " + sb.toString());
		return sb.toString();
	}

	/**
	 * 网络请求 GET
	 * 
	 * @param uri
	 *            请求地址
	 * @return string
	 */
	public static String Get(String uri) {
		return Get(uri, null);
	}

	/**
	 * 网络请求 POST
	 * 
	 * @param uri
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return string
	 */
	public static String Post(String uri, String params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		StringBuffer sb = new StringBuffer("");
		CloseableHttpResponse response = null;
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000).build();
		try {
			StringEntity strEntity = new StringEntity(params, ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8));
			strEntity.setContentEncoding("UTF-8");
			HttpPost httppost = new HttpPost(uri);
			httppost.setEntity(strEntity);
			httppost.setConfig(requestConfig);
			log.info("POST >>> " + httppost.getURI());
			log.info("POST DATA >>>" + EntityUtils.toString(strEntity));
			response = httpclient.execute(httppost);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					sb.append(EntityUtils.toString(entity, "UTF-8"));
				}
			} else {
				sb.append(code);
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		log.info("POST BACK >>> " + sb.toString());
		return sb.toString();
	}

	/**
	 * 该方法主要是给前台传送一个字符串，多用于ajax请求
	 * 
	 * @param message
	 */
	public static void SendResult(HttpServletResponse response, Object message) {
		PrintWriter io = null;
		try {
			io = response.getWriter();
			io.print(message);
			io.flush();
		} catch (Exception e) {
			log.error("response io异常", e);
		} finally {
			io.close();
		}
	}

	/**
	 * 判断是否为ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		if(request != null && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")))
			return true;
		return false;
	}

}
