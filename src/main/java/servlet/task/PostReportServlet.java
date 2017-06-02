package main.java.servlet.task;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import main.java.bo.TaskBo;
import main.java.bo.UploadFileBo;
import main.java.service.TaskService;
import main.java.service.UploadFileService;
import main.java.util.APIObject;
import main.java.util.MultipartDataObject;
import main.java.util.MyUtil;
import main.java.util.ServletUtil;

/**
 * Servlet implementation class PostReportServlet
 */
@WebServlet(description = "提交测试报告", urlPatterns = { "/api/task/postReport" })
public class PostReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static TaskService taskService = new TaskService();
	private static UploadFileService uploadFileService = new UploadFileService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostReportServlet() {
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
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart) {
			ServletUtil.outputJSON(response, "无法接收文件，请将<form>表单的enctype属性设置为[multipart/form-data]");
			
			return;
		}
		
		// 解析上传数据
		ServletContext servletContext = this.getServletConfig().getServletContext();
		MultipartDataObject multipartDataObject = ServletUtil.parseMultipartRequest(request, servletContext);
		
		String taskId = multipartDataObject.getFormField("id");
		
		if (MyUtil.isEmptyString(taskId)) {
			ServletUtil.outputJSON(response, "任务id不能为空");
			
			return;
		}
		
		TaskBo taskBo = taskService.queryTaskById(taskId);
		
		if (taskBo == null) {
			ServletUtil.outputJSON(response, "该任务不存在，请检查任务id");
			
			return;
		}
		
		if (!taskBo.isCanBeSubmitted()) {
			ServletUtil.outputJSON(response, "该任务不可提交测试报告，请检查任务状态");
			
			return;
		}
		
		int loginUserId = ServletUtil.getLoginUserId(request);
		
		if (loginUserId != taskBo.getAcceptUserId()) {
			ServletUtil.outputJSON(response, "只有任务领取者才能提交测试报告，请重新选择任务");
			
			return;
		}
		
		UploadFileBo uploadFileData = multipartDataObject.getUploadFile("reportFile");
		
		if (uploadFileData == null) {
			ServletUtil.outputJSON(response, "测试报告文件不能为空");
			
			return;
		}
		
		// 保存 上传文件对象 记录
		uploadFileData.setUploadUserId(loginUserId);
		Integer uploadFileId = uploadFileService.addUploadFile(uploadFileData);
		
		APIObject apiObject = new APIObject();
		if (uploadFileId == null) {
			apiObject.setUnknowError();
		}
		else if (!taskService.postReport(Integer.valueOf(taskId), uploadFileId)) {
			apiObject.setUnknowError();
		}
		else {
			apiObject.setMessage("提交测试报告成功");
		}
	
		ServletUtil.outputJSON(response, apiObject);
	}

}
