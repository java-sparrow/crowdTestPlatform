package main.java.bo;

import java.sql.Timestamp;

/**
 * 任务表 记录映射类
 *
 */
public class TaskBo extends BaseBo {
	/**
	 * 主键id
	 */
	private int id;
	/**
	 * 任务名
	 */
	private String taskName;
	/**
	 * 任务详情
	 */
	private String taskDetail;
	/**
	 * 任务图片
	 */
	private String taskImgUrl;
	/**
	 * 项目描述
	 */
	private String projectDescription;
	/**
	 * 项目备注
	 */
	private String projectComment;
	/**
	 * 测试需求
	 */
	private String testRequirement;
	/**
	 * 文件地址（供下载）
	 */
	private String taskFileUrl;
	/**
	 * 任务状态
	 */
	private int taskStatus;
	/**
	 * 创建日期
	 */
	private Timestamp createTime;
	/**
	 * 发布任务的用户id
	 */
	private int publishUserId;
	/**
	 * 任务截止的交付时间
	 */
	private Timestamp deadlineTime;
	/**
	 * 承接任务的用户id
	 */
	private int acceptUserId;
	/**
	 * 任务完成时的时间
	 */
	private Timestamp finishTime;
	/**
	 * 任务审批的时间
	 */
	private Timestamp approveTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDetail() {
		return taskDetail;
	}
	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}
	public String getTaskImgUrl() {
		return taskImgUrl;
	}
	public void setTaskImgUrl(String taskImgUrl) {
		this.taskImgUrl = taskImgUrl;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getProjectComment() {
		return projectComment;
	}
	public void setProjectComment(String projectComment) {
		this.projectComment = projectComment;
	}
	public String getTestRequirement() {
		return testRequirement;
	}
	public void setTestRequirement(String testRequirement) {
		this.testRequirement = testRequirement;
	}
	public String getTaskFileUrl() {
		return taskFileUrl;
	}
	public void setTaskFileUrl(String taskFileUrl) {
		this.taskFileUrl = taskFileUrl;
	}
	public int getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getPublishUserId() {
		return publishUserId;
	}
	public void setPublishUserId(int publishUserId) {
		this.publishUserId = publishUserId;
	}
	public Timestamp getDeadlineTime() {
		return deadlineTime;
	}
	public void setDeadlineTime(Timestamp deadlineTime) {
		this.deadlineTime = deadlineTime;
	}
	public int getAcceptUserId() {
		return acceptUserId;
	}
	public void setAcceptUserId(int acceptUserId) {
		this.acceptUserId = acceptUserId;
	}
	public Timestamp getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}
	public Timestamp getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Timestamp approveTime) {
		this.approveTime = approveTime;
	}
}
