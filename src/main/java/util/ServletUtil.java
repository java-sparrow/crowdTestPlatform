package main.java.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.bo.UserBo;
import main.java.constant.SessionAttributeConst;

/**
 * 主要提供 Servlet 中常用的 输出、获取对象 等操作。<br>
 * 一般需要 HttpServletRequest/HttpServletResponse 参数
 */
public class ServletUtil {
	/**
	 * 获取 已登录的用户对象
	 * @param request doGet/doPost 中的 HttpServletRequest 对象
	 * @return 已登录的用户对象
	 */
	public static UserBo getLoginUser(HttpServletRequest request) {
		return (UserBo) request.getSession().getAttribute(SessionAttributeConst.LOGIN_USER_FIELD_NAME);
	}
	/**
	 * 获取 已登录用户的id
	 * @param request doGet/doPost 中的 HttpServletRequest 对象
	 * @return 已登录用户的id
	 */
	public static int getLoginUserId(HttpServletRequest request) {
		return (int) request.getSession().getAttribute(SessionAttributeConst.LOGIN_USER_ID_FIELD_NAME);
	}
	/**
	 * 保存 登录用户对象 和 登录用户id 到 session对象的属性中
	 * @param request doGet/doPost 中的 HttpServletRequest 对象，用来获取 session对象
	 * @param loginUser 要保存的登录用户对象
	 */
	public static void setLoginUserToSessionAttribute(HttpServletRequest request, UserBo loginUser) {
		HttpSession session = request.getSession();

		session.setAttribute(SessionAttributeConst.LOGIN_USER_FIELD_NAME, loginUser);
		session.setAttribute(SessionAttributeConst.LOGIN_USER_ID_FIELD_NAME, loginUser.getId());
	}
	/**
	 * 向 response 流输出 JSON 格式的响应数据。<br>
	 * 是 <code>response.getWriter().append(apiObject.toJSON());</code> 的快捷方式
	 * @param response 输出流
	 * @param apiObject 响应数据对象
	 * @throws IOException 来自 <code>response.getWriter()</code> 方法
	 */
	public static void outputJSON(HttpServletResponse response, APIObject apiObject) throws IOException {
		response.getWriter().append(apiObject.toJSON());
	}
	/**
	 * 向 response 流输出 JSON 格式的响应数据。<br>
	 * 是 <code>response.getWriter().append(new APIObject(errorMessage).toJSON());</code> 的快捷方式
	 * @param response 输出流
	 * @param errorMessage 错误提示信息，用来构造 响应数据对象
	 * @throws IOException 来自 <code>response.getWriter()</code> 方法
	 */
	public static void outputJSON(HttpServletResponse response, String errorMessage) throws IOException {
		outputJSON(response, new APIObject(errorMessage));
	}
}
