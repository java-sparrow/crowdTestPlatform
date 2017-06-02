package main.java.vo;

import java.util.ArrayList;

import main.java.bo.TaskBo;
import main.java.constant.TaskStatus;

public class UserTaskVo extends BaseVo {
	/**
	 * 任务id
	 */
	private int taskId;
	/**
	 * 任务名
	 */
	private String taskName;
	/**
	 * 任务状态
	 */
	private Integer taskStatus;
	/**
	 * 任务状态文本
	 */
	private String taskStatusText;
	/**
	 * 创建日期
	 */
	private String createTime;
	/**
	 * 任务截止的交付时间
	 */
	private String deadlineTime;
	/**
	 * 测试报告文件id
	 */
	private Integer reportFileId;
	/**
	 * 测试报告名
	 */
	private String reportFileName;
	/**
	 * 测试报告下载地址
	 */
	private String reportFileUrl;

	/**
	 * 构造器（根据 任务对象Bo 创建Vo）
	 */
	public UserTaskVo() {
		super();
	}
	public UserTaskVo(TaskBo taskBo) {
		this.taskId = taskBo.getId();
		this.taskName = taskBo.getTaskName();
		this.taskStatus = taskBo.getTaskStatus();
		this.taskStatusText = TaskStatus.getTextByCode(this.taskStatus);
		this.createTime = taskBo.getCreateTime().toLocalDate().toString();
		this.deadlineTime = taskBo.getDeadlineTime().toLocalDate().toString();
		this.reportFileId = taskBo.getReportFileId();
	}
	
	/**
	 * 将 TaskBo 列表 批量转换为 UserTaskVo 列表
	 * @param taskBos 任务Bo列表
	 * @return 转换后的 UserTaskVo 列表
	 */
	public static ArrayList<UserTaskVo> convertBoListToVoList(ArrayList<TaskBo> taskBos) {
		ArrayList<UserTaskVo> userTaskVos = new ArrayList<>();
		
		for (TaskBo taskBo : taskBos) {
			userTaskVos.add(new UserTaskVo(taskBo));
		}
		
		return userTaskVos;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskStatusText() {
		return taskStatusText;
	}

	public void setTaskStatusText(String taskStatusText) {
		this.taskStatusText = taskStatusText;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDeadlineTime() {
		return deadlineTime;
	}

	public void setDeadlineTime(String deadlineTime) {
		this.deadlineTime = deadlineTime;
	}

	public Integer getReportFileId() {
		return reportFileId;
	}
	
	public void setReportFileId(Integer reportFileId) {
		this.reportFileId = reportFileId;
	}
	
	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public String getReportFileUrl() {
		return reportFileUrl;
	}

	public void setReportFileUrl(String reportFileUrl) {
		this.reportFileUrl = reportFileUrl;
	}
}
