package main.java.servlet.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import main.java.bo.TaskBo;
import main.java.service.TaskService;
import main.java.util.APIObject;

/**
 * Servlet implementation class GetTaskListServlet
 */
@WebServlet(description = "获取所有任务列表", urlPatterns = { "/api/task/getList" })
public class GetTaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static TaskService taskService = new TaskService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTaskListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<TaskBo> taskList = taskService.queryTaskList();
		
		Map<String, Object> dataMap = new HashMap<>();
		JSONArray taskListJSON = new JSONArray(taskList);
		dataMap.put("list", taskListJSON);
		
		APIObject apiObject = new APIObject();
		apiObject.setData(dataMap);

		response.getWriter().append(apiObject.toJSON());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
