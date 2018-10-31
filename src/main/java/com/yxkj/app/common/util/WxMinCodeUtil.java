package com.yxkj.app.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class WxMinCodeUtil {

	
	/**
	 * 微信小程序获取OPENID
	 * @param APPID  微信APPID
	 * @param SECRET 微信SECRET
	 * @param JSCODE 小程序登陆JSCODE
	 * @return JSONObject
	 */
	public static JSONObject getUserInfoTool(String APPID,String SECRET, String JSCODE){
		String url="https://api.weixin.qq.com/sns/jscode2session"
				+"?appid="+APPID
				+"&secret="+SECRET
				+"&js_code="+JSCODE
				+"&grant_type=authorization_code";
		String result = HttpUtil.sendGet(url);
		return JSONObject.parseObject(result);
	}
	
	/**
	 * 
	 * */
    
    
    
}
