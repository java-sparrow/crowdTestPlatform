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
import main.java.util.ServletUtil;

/**
 * Servlet implementation class AcceptTaskServlet
 */
@WebServlet(description = "领取任务", urlPatterns = { "/api/task/accept" })
public class AcceptTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static TaskService taskService = new TaskService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskId = request.getParameter("id");
		TaskBo taskBo = taskService.queryTaskById(taskId);
		
		if (taskBo == null) {
			APIObject apiObject = new APIObject("该任务不存在，请检查任务id");
			
			response.getWriter().append(apiObject.toJSON());
			
			return;
		}
		
		if (!taskBo.isAcceptable()) {
			APIObject apiObject = new APIObject("该任务不可领取，请检查任务id");
			
			response.getWriter().append(apiObject.toJSON());
			
			return;
		}
		
		APIObject apiObject = new APIObject();
		
		if (!taskService.acceptTask(Integer.valueOf(taskId), ServletUtil.getLoginUserId(request))) {
			apiObject.setUnknowError();
		}
		else {
			apiObject.setMessage("领取任务成功");
		}
		
		response.getWriter().append(apiObject.toJSON());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
