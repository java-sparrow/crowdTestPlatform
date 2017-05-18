package main.java.servlet.task;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bo.TaskBo;
import main.java.constant.SessionAttributeConst;
import main.java.service.TaskService;
import main.java.util.APIObject;
import main.java.util.MyUtil;

/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet(description = "新增任务", urlPatterns = { "/api/task/add" })
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static TaskService taskService = new TaskService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		APIObject apiObject = new APIObject("请使用 POST 方式操作");
		
		response.getWriter().append(apiObject.toJSON());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskName = request.getParameter("taskName");
		
		if (MyUtil.isEmptyString(taskName)) {
			APIObject apiObject = new APIObject("任务名不能为空");
			
			response.getWriter().append(apiObject.toJSON());
			return;
		}
		
		TaskBo taskBo = new TaskBo();
		taskBo.setTaskName(taskName);
		taskBo.setTaskDetail(request.getParameter("taskDetail"));
		taskBo.setTaskImgUrl(request.getParameter("taskImgUrl"));
		taskBo.setProjectDescription(request.getParameter("projectDescription"));
		taskBo.setProjectComment(request.getParameter("projectComment"));
		taskBo.setTestRequirement(request.getParameter("testRequirement"));
		taskBo.setTaskFileUrl(request.getParameter("taskFileUrl"));
		// 登录用户的id从request的属性中获取
		taskBo.setPublishUserId((int) request.getAttribute(SessionAttributeConst.LOGIN_USER_ID_FIELD_NAME));
		
		APIObject apiObject = new APIObject();
		
		if (!taskService.addTask(taskBo)) {
			apiObject.setUnknowError();
		}
		else {
			apiObject.setMessage("新增任务成功");
		}
		
		response.getWriter().append(apiObject.toJSON());
	}

}
