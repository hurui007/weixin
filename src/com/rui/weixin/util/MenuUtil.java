package com.rui.weixin.util;

import com.rui.weixin.menu.Button;
import com.rui.weixin.menu.ClientButton;
import com.rui.weixin.menu.Menu;
import com.rui.weixin.menu.ViewButton;

/**
 * @Title: 菜单工具       
 * @author 胡锐
 * @date:   2018年7月13日 下午11:31:19
 */
public class MenuUtil {
	//菜单接口调用地址
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	
	/**
	 * @Title: 菜单初始化    
	 * @author 胡锐
	 * @date:   2018年7月13日 下午11:47:14
	 * @return: Menu      
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		ClientButton clientButton11 = new ClientButton();
		clientButton11.setName("client菜单");
		clientButton11.setKey("11");
		clientButton11.setType("click");
		
		
		ViewButton viewButton21 = new ViewButton();
		viewButton21.setName("view菜单");
		viewButton21.setType("view");
		viewButton21.setUrl("http://www.youku.com");
		
		ClientButton clientButton31 = new ClientButton();
		clientButton31.setName("扫码推事件");
		clientButton31.setType("scancode_push");
		clientButton31.setKey("31");
		
		ClientButton clientButton41 = new ClientButton();
		clientButton41.setName("发送位置");
		clientButton41.setKey("41");
		clientButton41.setType("location_select");
		
		
		Button button = new Button();
		button.setName("菜单");
		button.setSub_button(new Button[]{clientButton31,viewButton21});
		button.setType("click");
		menu.setButton(new Button[]{button,clientButton11,clientButton41});
		
		return menu;
	}

}
