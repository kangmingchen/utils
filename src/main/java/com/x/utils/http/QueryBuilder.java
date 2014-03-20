package com.x.utils.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/**
 * 字符串拼接成url请求参数格式：a=xx&b=xx&c=xx
 * 
 * @author Kenmy
 * 
 */
public class QueryBuilder {

	private Map<String, NameValuePair> paramsMap = new HashMap<String, NameValuePair>();

	public QueryBuilder append(String name, String value) {
		this.paramsMap.put(name, new BasicNameValuePair(name, value));
		return this;
	}

	public QueryBuilder append(String name, int value) {
		this.paramsMap.put(name, new BasicNameValuePair(name, Integer.toString(value)));
		return this;
	}

	public QueryBuilder append(String name, long value) {
		this.paramsMap.put(name, new BasicNameValuePair(name, Long.toString(value)));
		return this;
	}

	public QueryBuilder append(String name, float value) {
		this.paramsMap.put(name, new BasicNameValuePair(name, Float.toString(value)));
		return this;
	}

	/**
	 * return encode a=xx&b=xx&c=xxx
	 */
	public String toString() {
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>(this.paramsMap.values());
		String query = null;
		try {
			query = URLEncodedUtils.format(list, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}
}