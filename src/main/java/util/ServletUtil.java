package main.java.util;

import javax.servlet.http.HttpServletRequest;

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
		return (UserBo) request.getAttribute(SessionAttributeConst.LOGIN_USER_FIELD_NAME);
	}
	/**
	 * 获取 已登录用户的id
	 * @param request doGet/doPost 中的 HttpServletRequest 对象
	 * @return 已登录用户的id
	 */
	public static int getLoginUserId(HttpServletRequest request) {
		return (int) request.getAttribute(SessionAttributeConst.LOGIN_USER_ID_FIELD_NAME);
	}
}
