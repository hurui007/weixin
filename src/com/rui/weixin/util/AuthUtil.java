package com.rui.weixin.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @title 微信接口校验工具类
 * @author ruihu
 * @date 2018-06-28
 */
public class AuthUtil {
	
	private static final String TOKEN = "imooc";
	
	public static boolean checkSignature(String signature, String timestamp, String nonce){
		String[] arr = {TOKEN, timestamp, nonce};
		//1、排序
		Arrays.sort(arr);
		
		//生成字符串
		StringBuffer buffer = new StringBuffer();
		for(String value : arr){
			buffer.append(value);
		}
		
		//字符串sha1加密
		String temp = getSha1(buffer.toString());
		return temp.equals(signature);
		
	}
	
	/**
	 * @title sha1加密方法
	 * @param str
	 * @date 2018-06-28
	 * @return
	 */
	public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];      
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

}
