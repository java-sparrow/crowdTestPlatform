package main.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bo.UserBo;
import main.java.util.APIObject;
import main.java.util.ServletUtil;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(description = "用户退出", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtil.outputJSON(response, "请使用 POST 方式提交退出");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBo loginUser = ServletUtil.getLoginUser(request);
		
		if (loginUser == null) {
			ServletUtil.outputJSON(response, "你还没有登录，不需要退出");
			
			return;
		}
		
		ServletUtil.removeLoginUserFromSessionAttribute(request);

		APIObject apiObject = new APIObject();
		
		apiObject.setMessage("退出成功");
		
		ServletUtil.outputJSON(response, apiObject);
	}

}
