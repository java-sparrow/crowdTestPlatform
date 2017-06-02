package main.java.bo;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 文件上传表 记录映射类
 *
 */
public class UploadFileBo extends BaseBo {
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 上传文件的用户id
	 */
	private int uploadUserId;
	/**
	 * 上传的时间
	 */
	private LocalDateTime uploadFileTime;
	/**
	 * 上传的文件名
	 */
	private String uploadFileName;
	/**
	 * 上传文件的大小（单位：字节）
	 */
	private long uploadFileSize;
	/**
	 * 保存的文件名（由系统自动生成）
	 */
	private String saveFileName;
	/**
	 * 保存路径
	 */
	private String saveFilePath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(int uploadUserId) {
		this.uploadUserId = uploadUserId;
	}

	public LocalDateTime getUploadFileTime() {
		return uploadFileTime;
	}

	public void setUploadFileTime(LocalDateTime uploadFileTime) {
		this.uploadFileTime = uploadFileTime;
	}
	public void setUploadFileTime(Date uploadFileTime) {
		this.uploadFileTime = toLocalDateTime(uploadFileTime);
	}
	public void setUploadFileTime(long uploadFileTime) {
		this.uploadFileTime = toLocalDateTime(uploadFileTime);
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public long getUploadFileSize() {
		return uploadFileSize;
	}

	public void setUploadFileSize(long uploadFileSize) {
		this.uploadFileSize = uploadFileSize;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getSaveFilePath() {
		return saveFilePath;
	}

	public void setSaveFilePath(String saveFilePath) {
		this.saveFilePath = saveFilePath;
	}
}
