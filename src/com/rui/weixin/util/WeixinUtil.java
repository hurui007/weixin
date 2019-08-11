package com.rui.weixin.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.rui.weixin.bean.AccessToken;
import com.rui.weixin.menu.Menu;

import net.sf.json.JSONObject;

/**
 * @title 微信接口常量
 * @author ruihu
 * @date 2018年07月4日
 */
public class WeixinUtil {
	//s上传了一个图片素材,最后得到了一个media_id:
	//   1uCk24azOFgOgwJd9pwMVkmhGjlRfJIbLdYWJoq8qZR0wvCt-hU1GCnqhieA3K02
	//开发者id(测试号)
	private static String AppID = "wxca551e7b17c3d833";
	//开发者密码（测试号）
	private static String AppSecret = "ffd0d81fa5170b62365dd854518be35a";
	
	private static String ASSESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	private static String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";
	//调用接口生成的token字符串，每次请求有效时间为2小时
	private static String TOKEN = "11_9W8nMX_oUHdTX7ayOJJG8r8-1Q2oK5WPoyDkU45Lpe-PYQ6-TMRWc2Sap5vYdOYJX8tRABjCxgr8yQGUNJszLNDUapD23d1v-AYT7tLY1pSpNHmw2DsuZ_wmtT8AHXcABAWDT";
	
	//媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	private static String IMG_TYPE = "image";
	private static String VOICE_TYPE = "voice";
	private static String VIDEO_TYPE = "video";
	private static String THUMB_TYPE = "thumb";
	
	/**
	 * @Title: 创建菜单  
	 * @param: @param token
	 * @param: @param menu
	 * @author 胡锐
	 * @date:   2018年7月13日 下午11:52:17
	 * @return: int      
	 */
	public static int createMenu(String token, String menu){
		int result = 0;
		String createMenuUrl = String.format(MenuUtil.CREATE_MENU_URL, TOKEN);
		
		JSONObject obj = doPostStr(createMenuUrl, menu);
		if(obj != null){
			result = obj.getInt("errcode");
		}
		
		return result;
	}
	
	/**
	 * @Title: 将素材上传到微信服务器   
	 * @param: @param filePath
	 * @param: @param accessToken
	 * @param: @param type
	 * @param: @throws IOException      
	 * @author 胡锐
	 * @date:   2018年7月4日 下午11:40:33
	 * @return: void      
	 * @throws
	 */
	public static String upload(String filePath, String accessToken, String type) throws IOException{
		File file = new File(filePath);
		if(!file.exists() || !file.isFile()){
			throw new IOException("文件不存在!");
		}
		
		String uploadUrl = String.format(UPLOAD_URL, accessToken, type);
		URL urlObj = new URL(uploadUrl);
		HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();
		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);
		
		
		//设置请求头信息
		con.setRequestProperty("Connection","Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		//设置边界
		String BOUNDARY = "--------"  + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition:form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		
		byte[] head = sb.toString().getBytes("utf-8");
		
		//获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);
		
		//文件正文部分
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while((bytes = in.read(bufferOut)) != -1){
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		
		//结尾部分
		//定义最后数据分割线
		byte[] foot = ("\r\n--" + BOUNDARY + "\r\n").getBytes("utf-8");
		out.write(foot);
		
		out.flush();
		out.close();
		
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String line = null;
			while((line = reader.readLine()) != null){
				buffer.append(line);
			}
			if(result == null){
				result = buffer.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(reader != null){
				reader.close();
			}
		}
		JSONObject obj = JSONObject.fromObject(result);
		System.out.println(obj);
		String mediaId = obj.getString("thumb_media_id");
		return mediaId;
	}
	
	public static AccessToken getAccessToken(){
		AccessToken accessToken = new AccessToken();
		String url = String.format(ASSESS_TOKEN_URL, AppID, AppSecret);
		JSONObject obj = doGetStr(url);
		if(obj != null){
			accessToken.setAccessToken(obj.getString("access_token"));
			accessToken.setExpiresIn(obj.getInt("expires_in"));
		}
		return accessToken; 
	}
	
	/**
	 * @Title: 获取token
	 * @param: @param url，接口方法请求路径
	 * @param: @return      
	 * @author 胡锐
	 * @date:   2018年7月4日 下午10:19:18
	 * @return: JSONObject      
	 * @throws
	 */
	public static JSONObject doGetStr(String url){
		HttpClient httpclient = HttpClients.createDefault(); 
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		try {
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				String result = EntityUtils.toString(entity, "UTF-8");
				System.out.println("result :" + result);
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	} 
	
	/**
	 * @Title: post
	 * @param: @param url
	 * @param: @param outStr
	 * @param: @return      
	 * @author 胡锐
	 * @date:   2018年7月4日 下午10:25:25
	 * @return: String      
	 * @throws
	 */
	public static JSONObject doPostStr(String url, String outStr){
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
		try {
			HttpResponse response = httpclient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
		
	}
	
	public static void main(String[] args) {
		
		/**
		 * 上传素材文件
		 * String path = "/Users/ruihu/Desktop/cup_thrumb.jpg";
		String path = "/Users/ruihu/Desktop/9527.mp4";
		String mediaId = "0";
		try {
			mediaId = upload(path, TOKEN, "video");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("最后得到的media_id:" + mediaId);**/
		
		/**
		 * 获取token，每两个小时就需要获取一次
		 * AccessToken accessToken = getAccessToken();
			if(accessToken != null){
			System.out.println("token:" + accessToken.getAccessToken());
			System.out.println("time:" + accessToken.getExpiresIn());
		}
		 */
		
		/**
		 * 推送菜单事件
		 */
		Menu menu = MenuUtil.initMenu();
		String menuUrl = JSONObject.fromObject(menu).toString();
		System.out.println(menuUrl);
		//推送菜单事件
		int result = createMenu(TOKEN, menuUrl);
		System.out.println(result);
		
		
	}

}
