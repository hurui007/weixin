package com.rui.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rui.weixin.util.AuthUtil;
import com.rui.weixin.util.MessageUtil;

/**
 * Servlet implementation class wei
 */
@WebServlet("/wei")
public class wei extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public wei() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//首先根据get请求验证参数，判断消息确实来自微信
		//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		System.out.println("--");
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		
		if(AuthUtil.checkSignature(signature, timestamp, nonce)){
			out.println(echostr);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		try {
			//根据消息类型获取消息，已经返回操作
			String message = MessageUtil.message(request);
			System.out.println("返回的消息：\r\n" + message);
			writer.write(message);
			
		} finally{
			writer.close();
		}
		
		doGet(request, response);
	}
	

}
