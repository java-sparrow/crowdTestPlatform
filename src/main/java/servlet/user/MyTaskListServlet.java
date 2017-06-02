package main.java.servlet.user;

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
import main.java.bo.UploadFileBo;
import main.java.service.TaskService;
import main.java.service.UploadFileService;
import main.java.util.APIObject;
import main.java.util.ServletUtil;
import main.java.vo.UserTaskVo;

/**
 * Servlet implementation class MyTaskListServlet
 */
@WebServlet(description = "获取登录用户的任务列表", urlPatterns = { "/api/user/myTaskList" })
public class MyTaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String PUBLISH_TYPE = "1";
	private static String ACCEPT_TYPE = "2";

	private static TaskService taskService = new TaskService();
	private static UploadFileService uploadFileService = new UploadFileService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyTaskListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<TaskBo> taskList = new ArrayList<>();
		int loginId = ServletUtil.getLoginUserId(request);
		String listType = request.getParameter("type");
		
		// 仅当listType等于1时，才认为是发布的任务
		if (PUBLISH_TYPE.equals(listType)) {
			// 发布的任务列表
			taskList = taskService.queryTaskListByPublisher(loginId);
		}
		// listType的其它值视为 领取的任务
		else {
			// 领取的任务列表
			taskList = taskService.queryTaskListByAccepter(loginId);
		}
		
		ArrayList<UserTaskVo> userTaskVoList = UserTaskVo.convertBoListToVoList(taskList);
		
		// TODO: 通过新的联表查询语句方式实现
		for (UserTaskVo userTaskVo : userTaskVoList) {
			Integer reportFileId = userTaskVo.getReportFileId();
			
			if (reportFileId == null) {
				// 有的任务并未提交测试报告，所以不用执行下面的查询语句
				continue;
			}
			
			UploadFileBo uploadFileBo = uploadFileService.queryUploadFileById(reportFileId);
			
			if (uploadFileBo == null) {
				// TODO: 找不到对应id的文件上传对象 
				continue;
			}
			
			userTaskVo.setReportFileName(uploadFileBo.getUploadFileName());
			userTaskVo.setReportFileUrl(uploadFileBo.getSaveFilePath());
		}
		
		Map<String, Object> dataMap = new HashMap<>();
		JSONArray taskListJSON = new JSONArray(userTaskVoList);
		dataMap.put("list", taskListJSON);
		
		APIObject apiObject = new APIObject();
		apiObject.setData(dataMap);

		ServletUtil.outputJSON(response, apiObject);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
