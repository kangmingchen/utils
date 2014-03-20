package com.x.utils.http;

import java.util.Map.Entry;

/**
 * 请求参数entry类
 */
public class ParamEntry implements Entry<String, String> {

	String key;
	String val;
	public ParamEntry( String key, String val )
	{
		this.key = key;
		this.val = val;
	}
	public String getKey()
	{
		return key;
	}
	public String getValue()
	{
		return val;
	}
	public String setValue( String object )
	{
		this.val = object;
		return this.val;
	}

}
