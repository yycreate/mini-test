package com.yxkj.app.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class WxUtil {

	public static JSONObject getOpenIdTool(String appId,String code,String appSecret){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
		String result =HttpUtil.sendGet(url);
		return JSONObject.parseObject(result); 
	}
	
	
	public static JSONObject getTokenTool(String appId,String appSecret){
		String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
		String result =HttpUtil.sendGet(url);
		return JSONObject.parseObject(result); 
	}
	
	public static JSONObject getTicketTool(String access_token){
		String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		String result =HttpUtil.sendGet(url);
		return JSONObject.parseObject(result);
	}
	
	/**
	 * 通过openid获取微信用户信息
	 * @param access_token  微信token
	 * @param openid		微信openid
	 * @return
	 */
	public static JSONObject getUserInfoTool(String access_token, String openid){
		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ access_token +"&openid="+ openid +"&lang=zh_CN";
		String result = HttpUtil.sendGet(url);
		return JSONObject.parseObject(result);
	}
	
	/**
	 * 创建微信自定义菜单
	 * @param access_token微信token
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject createMenu(String access_token, String menu) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, menu);
		return JSONObject.parseObject(result);
	}
	
	
	/**
	 * 创建微信卡券
	 * @param access_token  微信token
	 * @param card  Post Json数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject createCard(String access_token, String card) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/card/create?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, card);
		return JSONObject.parseObject(result);
	}
	
	/**
	 * 获取微信卡券二维码
	 * @param access_token  微信token
	 * @param card  Post Json数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject createCardQrcode(String access_token, String card) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/card/qrcode/create?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, card);
		return JSONObject.parseObject(result);
	}
	
	
	/**
	 * 修改微信卡券库存
	 * @param access_token  微信token
	 * @param reduceCardStock  Post Json数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject updateCardStock(String access_token, String reduceCardStock) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/card/modifystock?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, reduceCardStock);
		return JSONObject.parseObject(result);
	}
	
	/**
	 * 获取微信图片
	 * @param access_token  微信token
	 * @param media_id  Get String媒体id
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject createWxImgUrl(String access_token, String media_id) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+media_id;
		String result = HttpUtil.sendGet2(url);
		return JSONObject.parseObject(result);
		
	}
	
	public static String createWxImgUrl2(String access_token, String media_id) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+media_id;
		//String result = HttpUtil.sendGet2(url);
		//return JSONObject.parseObject(result);
		return url;
	}
	
	/**
	 * 增加微信临时素材
	 * @param access_token  微信token
	 * @param card  Post Json数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject createTemporaryImg(String access_token, String card) throws UnsupportedEncodingException{
		String url = "https://api.weixin.qq.com/card/qrcode/create?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, card);
		return JSONObject.parseObject(result);
	}
	
	/**
	 * 删除微信自定义菜单
	 * @param access_token
	 * @return
	 * @throws Exception
	 */
	public static JSONObject deleteMenu(String access_token) throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+ access_token;
		String result = HttpUtil.sendGet(url);
		return JSONObject.parseObject(result);
	}
	
	/**
	 * 发送模板消息
	 * @param access_token
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static JSONObject sendMessage(String access_token, String message) throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ access_token;
		String result = HttpUtil.sendPost(url, message);
		return JSONObject.parseObject(result);
	}
	
	/**
     * 加密接口
     */
    public static String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1"); // 如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
}
