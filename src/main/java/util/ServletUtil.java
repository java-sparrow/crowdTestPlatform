package main.java.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import main.java.bo.UploadFileBo;
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
		return (UserBo) request.getSession().getAttribute(SessionAttributeConst.LOGIN_USER_FIELD_NAME);
	}
	/**
	 * 获取 已登录用户的id
	 * @param request doGet/doPost 中的 HttpServletRequest 对象
	 * @return 已登录用户的id
	 */
	public static int getLoginUserId(HttpServletRequest request) {
		return (int) request.getSession().getAttribute(SessionAttributeConst.LOGIN_USER_ID_FIELD_NAME);
	}
	/**
	 * 保存 登录用户对象 和 登录用户id 到 session对象的属性中
	 * @param request doGet/doPost 中的 HttpServletRequest 对象，用来获取 session对象
	 * @param loginUser 要保存的登录用户对象
	 */
	public static void setLoginUserToSessionAttribute(HttpServletRequest request, UserBo loginUser) {
		HttpSession session = request.getSession();

		session.setAttribute(SessionAttributeConst.LOGIN_USER_FIELD_NAME, loginUser);
		session.setAttribute(SessionAttributeConst.LOGIN_USER_ID_FIELD_NAME, loginUser.getId());
	}
	/**
	 * 从 session对象的属性中 删除 登录用户对象 和 登录用户id
	 * @param request doGet/doPost 中的 HttpServletRequest 对象，用来获取 session对象
	 */
	public static void removeLoginUserFromSessionAttribute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.removeAttribute(SessionAttributeConst.LOGIN_USER_FIELD_NAME);
		session.removeAttribute(SessionAttributeConst.LOGIN_USER_ID_FIELD_NAME);
	}
	
	/**
	 * 向 response 流输出 JSON 格式的响应数据。<br>
	 * 是 <code>response.getWriter().append(apiObject.toJSON());</code> 的快捷方式
	 * @param response 输出流
	 * @param apiObject 响应数据对象
	 * @throws IOException 来自 <code>response.getWriter()</code> 方法
	 */
	public static void outputJSON(HttpServletResponse response, APIObject apiObject) throws IOException {
		response.getWriter().append(apiObject.toJSON());
	}
	/**
	 * 向 response 流输出 JSON 格式的响应数据。<br>
	 * 是 <code>response.getWriter().append(new APIObject(errorMessage).toJSON());</code> 的快捷方式
	 * @param response 输出流
	 * @param errorMessage 错误提示信息，用来构造 响应数据对象
	 * @throws IOException 来自 <code>response.getWriter()</code> 方法
	 */
	public static void outputJSON(HttpServletResponse response, String errorMessage) throws IOException {
		outputJSON(response, new APIObject(errorMessage));
	}
	

	/**
	 * 解析 表单二进制式上传数据，并且会将上传数据中的文件保存到 WebContent 的 upload 文件夹中。
	 * @param request doGet/doPost 中的 HttpServletRequest 对象，用来获取上传数据
	 * @param servletContext Servlet环境
	 * @return 解析后的数据对象
	 */
	public static MultipartDataObject parseMultipartRequest(HttpServletRequest request, ServletContext servletContext) {
		MultipartDataObject multipartDataObject = new MultipartDataObject();
		
		// 1.为基于硬盘文件的项目集创建一个工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 2.配置一个仓库（确保有安全的临时空间可用）
		File tempFileRepository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(tempFileRepository);

		// 3.创建一个新的文件上传处理器
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 4.解析请求
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			String uploadFileRepository = request.getServletContext().getRealPath("/") + "upload\\";
			
			for (FileItem item : items) {
				String fieldName = item.getFieldName();
				
				if (item.isFormField()) {
					String fieldValue = item.getString("utf-8");
					
					// 保存文本域的值
					multipartDataObject.putFormField(fieldName, fieldValue);
					
					System.out.println("文本型：" + fieldName + "->" + fieldValue);
				}
				else {
					String fileName = item.getName();
					long fileSize = item.getSize();
					
					// 上传文件有效时才保存
					if (!MyUtil.isEmptyString(fileName) && (fileSize > 0)) {
						// 保存文件域的值
						multipartDataObject.putFileField(fieldName, item);
						System.out.println("文件型：文件名[" + fileName + "] 大小[" + fileSize + "]");
						
						// 自动生成文件名
						String saveFileName = MyUtil.createUUID() + MyUtil.getFileSuffixName(fileName);
						// 文件保存路径（WebContent 的 upload 文件夹）
						String saveFileAbsolutePath = uploadFileRepository + saveFileName;
						
						// 保存文件
						File saveFile = new File(saveFileAbsolutePath);
						item.write(saveFile);
						
						// 将其它详情信息保存到文件对象中
						UploadFileBo uploadFileData = new UploadFileBo();
						uploadFileData.setUploadFileName(item.getName());
						uploadFileData.setUploadFileSize(item.getSize());
						uploadFileData.setSaveFileName(saveFileName);
						// 保存路径使用 WebContent 下的路径
						uploadFileData.setSaveFilePath("/upload/" + saveFileName);
						multipartDataObject.putUploadFile(fieldName, uploadFileData);
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return multipartDataObject;
	}
}
