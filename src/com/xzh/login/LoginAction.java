package com.xzh.login;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ģ���¼��������json����
 * 
 * @author xiangzhihong
 *
 */
public class LoginAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;

	HttpServletResponse response;

	private String userName;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * ģ���û���¼��ҵ��
	 */
	public Object login() {
		JSONObject json = new JSONObject();
		try {
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			// Map<String,String> json=new HashMap<String,String>();
			if ("admin".equals(userName) && "123456".equals(password)) {
				json.put("message", "��ӭ����Ա��½");
			} else if ((!"admin".equals(userName)) && "123456".equals(password)) {
				json.put("message", "��ӭ" + userName + "��½��");
			} else {
				json.put("message", "�Ƿ���½��Ϣ��");
			}
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
			response.getOutputStream().write(jsonBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}