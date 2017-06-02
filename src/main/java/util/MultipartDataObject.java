package main.java.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import main.java.bo.UploadFileBo;

/**
 * 存储 表单二进制式上传数据 解析后的数据对象。<br>
 * 包含3个Map子数据对象，分别为 表单文本域Map、表单文件域Map、上传文件Bo对象Map。
 *
 */
public class MultipartDataObject {
	/**
	 * 表单文本域Map，可根据属性名获取属性值
	 */
	private Map<String, String> formFieldMap = new HashMap<>();
	/**
	 * 表单文件域Map，可根据属性名获取上传组件封装的文件对象
	 */
	private Map<String, FileItem> fileFieldMap = new HashMap<>();
	/**
	 * 上传文件Bo对象Map，可根据属性名获取上传文件对象
	 */
	private Map<String, UploadFileBo> uploadFileBoMap = new HashMap<>();
	
	public String putFormField(String fieldName, String fieldData) {
		return formFieldMap.put(fieldName, fieldData);
	}
	public String getFormField(String fieldName) {
		return formFieldMap.get(fieldName);
	}
	
	public Object putFileField(String fieldName, FileItem fileItem) {
		return fileFieldMap.put(fieldName, fileItem);
	}
	public FileItem getFileField(String fieldName) {
		return fileFieldMap.get(fieldName);
	}
	
	public UploadFileBo putUploadFile(String fieldName, UploadFileBo uploadFileData) {
		return uploadFileBoMap.put(fieldName, uploadFileData);
	}
	public UploadFileBo getUploadFile(String fieldName) {
		return uploadFileBoMap.get(fieldName);
	}
}
