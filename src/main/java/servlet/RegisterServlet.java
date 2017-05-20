package main.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bo.UserBo;
import main.java.service.UserService;
import main.java.util.APIObject;
import main.java.util.MyUtil;
import main.java.util.ServletUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "用户注册", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static UserService userService = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtil.outputJSON(response, "请使用 POST 方式注册");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserBo userData = new UserBo();
		
		userData.setUsername(username);
		userData.setPassword(password);
		
		System.out.printf("注册请求： 用户名 [%s]，密码 [%s] \n", username, password);
		
		APIObject apiObject = null;
		
		if (MyUtil.isEmptyString(username) || MyUtil.isEmptyString(password)) {
			apiObject = new APIObject("用户名或密码不能为空");
		}
		else if (userService.queryUserByName(username) != null) {
			apiObject = new APIObject("用户名已存在");
		}
		else if (!userService.addUser(userData)) {
			apiObject = new APIObject();
			apiObject.setUnknowError();
		}
		else {
			apiObject = new APIObject();
			apiObject.setMessage("注册成功");
		}

		ServletUtil.outputJSON(response, apiObject);
	}

}
