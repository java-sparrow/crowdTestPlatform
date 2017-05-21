package main.java.servlet.task;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bo.TaskBo;
import main.java.bo.TaskDocBo;
import main.java.service.TaskDocService;
import main.java.service.TaskService;
import main.java.util.APIObject;
import main.java.util.ServletUtil;

/**
 * Servlet implementation class AddTaskDocServlet
 */
@WebServlet(description = "新增任务文档", urlPatterns = { "/api/taskDoc/add" })
public class AddTaskDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static TaskService taskService = new TaskService();
	private static TaskDocService taskDocService = new TaskDocService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskDocServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtil.outputJSON(response, "请使用 POST 方式提交");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskId = request.getParameter("taskId");
		
		TaskBo taskBo = taskService.queryTaskById(taskId);
		
		if (taskBo == null) {
			ServletUtil.outputJSON(response, "该任务不存在，请检查任务id");
			
			return;
		}
		
		String docName = request.getParameter("docName");
		String docUrl = request.getParameter("docUrl");
		
		TaskDocBo taskDocBo = new TaskDocBo();
		taskDocBo.setDocName(docName);
		taskDocBo.setDocUrl(docUrl);
		taskDocBo.setTaskId(Integer.valueOf(taskId));
		
		APIObject apiObject = new APIObject();
		
		Integer taskDocId = taskDocService.addTaskDocBo(taskDocBo);
		
		if (taskDocId == null) {
			apiObject.setUnknowError();
		}
		else {
			apiObject.setMessage("新增任务文档成功");
			
			TaskDocBo taskDocData = new TaskDocBo();
			taskDocData.setId(taskDocId);
			
			apiObject.setData(taskDocData);
		}
		
		ServletUtil.outputJSON(response, apiObject);
	}

}
