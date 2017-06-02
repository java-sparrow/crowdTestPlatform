package main.java.bo;

import java.time.LocalDateTime;
import java.util.Date;

import main.java.constant.TaskStatus;

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
	private Integer taskStatus;
	/**
	 * 创建日期
	 */
	private LocalDateTime createTime;
	/**
	 * 发布任务的用户id
	 */
	private Integer publishUserId;
	/**
	 * 任务截止的交付时间
	 */
	private LocalDateTime deadlineTime;
	/**
	 * 承接任务的用户id
	 */
	private Integer acceptUserId;
	/**
	 * 任务被领取的时间
	 */
	private LocalDateTime acceptTime;
	/**
	 * 测试报告文件id
	 */
	private Integer reportFileId;
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
	public Integer getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = toLocalDateTime(createTime);
	}
	public void setCreateTime(long createTime) {
		this.createTime = toLocalDateTime(createTime);
	}
	public Integer getPublishUserId() {
		return publishUserId;
	}
	public void setPublishUserId(Integer publishUserId) {
		this.publishUserId = publishUserId;
	}
	public LocalDateTime getDeadlineTime() {
		return deadlineTime;
	}
	public void setDeadlineTime(LocalDateTime deadlineTime) {
		this.deadlineTime = deadlineTime;
	}
	public void setDeadlineTime(Date deadlineTime) {
		this.deadlineTime = toLocalDateTime(deadlineTime);
	}
	public void setDeadlineTime(long deadlineTime) {
		this.deadlineTime = toLocalDateTime(deadlineTime);
	}
	public Integer getAcceptUserId() {
		return acceptUserId;
	}
	public void setAcceptUserId(Integer acceptUserId) {
		this.acceptUserId = acceptUserId;
	}
	public LocalDateTime getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(LocalDateTime acceptTime) {
		this.acceptTime = acceptTime;
	}
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = toLocalDateTime(acceptTime);
	}
	public void setAcceptTime(long acceptTime) {
		this.acceptTime = toLocalDateTime(acceptTime);
	}
	public Integer getReportFileId() {
		return reportFileId;
	}
	public void setReportFileId(Integer reportFileId) {
		// 仅当有效值时 才赋值
		if (reportFileId > 0) {
			this.reportFileId = reportFileId;
		}
	}
	public LocalDateTime getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = toLocalDateTime(finishTime);
	}
	public void setFinishTime(long finishTime) {
		this.finishTime = toLocalDateTime(finishTime);
	}
	public LocalDateTime getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(LocalDateTime approveTime) {
		this.approveTime = approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = toLocalDateTime(approveTime);
	}
	public void setApproveTime(long approveTime) {
		this.approveTime = toLocalDateTime(approveTime);
	}
	
	// 一些业务方法

	/**
	 * 任务是否 可领取
	 * @return true：可领取。false 不可再次领取
	 */
	public Boolean isAcceptable() {
		return taskStatus == TaskStatus.CREATED_CODE;
	}
	/**
	 * 任务是否 可提交（测试）
	 * @return true：可提交（测试）。false 不可提交（测试）
	 */
	public Boolean isCanBeSubmitted() {
		return (taskStatus == TaskStatus.ACCEPTED_CODE) || (taskStatus == TaskStatus.APPROVED_REJECT_CODE);
	}
	/**
	 * 任务是否 可审批
	 * @return true：可审批。false 不可审批
	 */
	public Boolean isApprovable() {
		return taskStatus  == TaskStatus.WAIT_APPROVE_CODE;
	}
	/**
	 * 任务是否 不可改变
	 * @return true：不可改变。false 可以改变
	 */
	public Boolean isImmutable() {
		return taskStatus == TaskStatus.APPROVED_PASS_CODE;
	}
}
