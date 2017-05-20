package main.java.servlet.task;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bo.TaskBo;
import main.java.service.TaskService;
import main.java.util.APIObject;
import main.java.util.MyUtil;
import main.java.util.ServletUtil;

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
		ServletUtil.outputJSON(response, "请使用 POST 方式操作");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskName = request.getParameter("taskName");
		
		if (MyUtil.isEmptyString(taskName)) {
			ServletUtil.outputJSON(response, "任务名不能为空");
			
			return;
		}
		
		String deadlineTime = request.getParameter("deadlineTime");

		if (MyUtil.isEmptyString(deadlineTime)) {
			ServletUtil.outputJSON(response, "截止时间不能为空");
			
			return;
		}
		
		long deadlineTimestamp;
		
		try {
			deadlineTimestamp = Long.valueOf(deadlineTime);
		} catch (NumberFormatException e) {
			ServletUtil.outputJSON(response, "截止时间格式不正确");
			
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
		taskBo.setDeadlineTime(deadlineTimestamp);
		// 登录用户的id从request的属性中获取
		taskBo.setPublishUserId(ServletUtil.getLoginUserId(request));
		
		APIObject apiObject = new APIObject();
		
		if (!taskService.addTask(taskBo)) {
			apiObject.setUnknowError();
		}
		else {
			apiObject.setMessage("新增任务成功");
		}
		
		ServletUtil.outputJSON(response, apiObject);
	}

}
