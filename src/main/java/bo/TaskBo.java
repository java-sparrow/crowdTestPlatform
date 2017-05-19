package main.java.bo;

import java.time.LocalDateTime;
import java.util.Date;

import main.java.util.MyUtil;

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
	private LocalDateTime createTime;
	/**
	 * 发布任务的用户id
	 */
	private int publishUserId;
	/**
	 * 任务截止的交付时间
	 */
	private LocalDateTime deadlineTime;
	/**
	 * 承接任务的用户id
	 */
	private Integer acceptUserId;
	/**
	 * 任务完成时的时间
	 */
	private LocalDateTime finishTime;
	/**
	 * 任务审批的时间
	 */
	private LocalDateTime approveTime;
	
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
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = MyUtil.toLocalDateTime(createTime);
	}
	public void setCreateTime(long createTime) {
		this.createTime = MyUtil.toLocalDateTime(createTime);
	}
	public int getPublishUserId() {
		return publishUserId;
	}
	public void setPublishUserId(int publishUserId) {
		this.publishUserId = publishUserId;
	}
	public LocalDateTime getDeadlineTime() {
		return deadlineTime;
	}
	public void setDeadlineTime(LocalDateTime deadlineTime) {
		this.deadlineTime = deadlineTime;
	}
	public void setDeadlineTime(Date deadlineTime) {
		this.deadlineTime = MyUtil.toLocalDateTime(deadlineTime);
	}
	public void setDeadlineTime(long deadlineTime) {
		this.deadlineTime = MyUtil.toLocalDateTime(deadlineTime);
	}
	public Integer getAcceptUserId() {
		return acceptUserId;
	}
	public void setAcceptUserId(Integer acceptUserId) {
		this.acceptUserId = acceptUserId;
	}
	public LocalDateTime getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = MyUtil.toLocalDateTime(finishTime);
	}
	public void setFinishTime(long finishTime) {
		this.finishTime = MyUtil.toLocalDateTime(finishTime);
	}
	public LocalDateTime getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(LocalDateTime approveTime) {
		this.approveTime = approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = MyUtil.toLocalDateTime(approveTime);
	}
	public void setApproveTime(long approveTime) {
		this.approveTime = MyUtil.toLocalDateTime(approveTime);
	}
}
