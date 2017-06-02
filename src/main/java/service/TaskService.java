package main.java.service;

import java.util.ArrayList;
import java.util.Date;

import main.java.bo.TaskBo;
import main.java.constant.TaskStatus;
import main.java.dao.TaskDao;

public class TaskService extends BaseService {
	private TaskDao taskDao = new TaskDao();
	

	/**
	 * 查询任务列表
	 * <br>TODO: 支持分页
	 * @return 任务对象列表。
	 * 			<br>当无查询结果 或 发生异常时，返回 空数组
	 */
	public ArrayList<TaskBo> queryTaskList() {
		return taskDao.queryTaskList();
	}
	/**
	 * 根据任务状态 查询任务列表
	 * <br>TODO: 支持分页
	 * @param status 任务状态
	 * @return 符合条件的任务对象列表。
	 * 			<br>当无符合条件的结果 或 发生异常时，返回 空数组
	 */
	public ArrayList<TaskBo> queryTaskListByStatus(Integer status) {
		TaskBo queryData = new TaskBo();
		
		queryData.setTaskStatus(status);
		
		return taskDao.queryTaskList(queryData);
	}
	/**
	 * 根据任务发布者 查询任务列表
	 * <br>TODO: 支持分页
	 * @param publishUserId 任务发布者
	 * @return 符合条件的任务对象列表。
	 * 			<br>当无符合条件的结果 或 发生异常时，返回 空数组
	 */
	public ArrayList<TaskBo> queryTaskListByPublisher(Integer publishUserId) {
		TaskBo queryData = new TaskBo();
		
		queryData.setPublishUserId(publishUserId);
		
		return taskDao.queryTaskList(queryData);
	}
	/**
	 * 根据任务承接者 查询任务列表
	 * <br>TODO: 支持分页
	 * @param acceptUserId 任务承接者
	 * @return 符合条件的任务对象列表。
	 * 			<br>当无符合条件的结果 或 发生异常时，返回 空数组
	 */
	public ArrayList<TaskBo> queryTaskListByAccepter(Integer acceptUserId) {
		TaskBo queryData = new TaskBo();
		
		queryData.setAcceptUserId(acceptUserId);
		
		return taskDao.queryTaskList(queryData);
	}
	
	/**
	 * 根据 任务id 查找
	 * @param id 任务id
	 * @return 匹配查询结果的任务对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public TaskBo queryTaskById(Integer id) {
		return taskDao.queryTaskById(id);
	}
	/**
	 * 根据 任务id 查找
	 * @param id 任务id
	 * @return 匹配查询结果的任务对象。
	 * 			<br>当无查询结果 或 发生异常 或 参数为null 或 参数无法转成整型值时，返回 null
	 */
	public TaskBo queryTaskById(String id) {
		if (id == null) {
			return null;
		}
		
		int taskId;
		
		try {
			taskId = Integer.valueOf(id);
		} catch (NumberFormatException e) {
			return null;
		}
		
		return queryTaskById(taskId);
	}

	/**
	 * 新增任务
	 * @param taskData 任务数据对象
	 * @return 新增成功时，返回 true。<br>
	 * 			新增失败时，返回 false。
	 */
	public Boolean addTask(TaskBo taskData) {
		return taskDao.addTask(taskData);
	}
	
	/**
	 * 更新任务
	 * @param taskData 新的任务数据对象
	 * @return 更新成功时，返回 true。<br>
	 * 			更新失败时，返回 false。
	 */
	public Boolean updateTask(TaskBo taskData) {
		return taskDao.updateTask(taskData);
	}
	/**
	 * 领取任务
	 * @param taskId 任务id
	 * @param acceptUserId 承接任务的用户id
	 * @return 领取任务成功时，返回 true。<br>
	 * 			领取任务失败时，返回 false。
	 */
	public Boolean acceptTask(int taskId, int acceptUserId) {
		TaskBo taskBo = queryTaskById(taskId);
		
		if (taskBo == null) {
			return false;
		}
		
		taskBo.setAcceptUserId(acceptUserId);
		taskBo.setTaskStatus(TaskStatus.ACCEPTED_CODE);
		taskBo.setAcceptTime(new Date());
		
		return updateTask(taskBo);
	}

	/**
	 * 提交测试报告
	 * @param taskId 任务id
	 * @return 改变任务状态成功时，返回 true。<br>
	 * 			改变任务状态失败时，返回 false。
	 */
	public Boolean postReport(int taskId, int reportFileId) {
		TaskBo taskBo = queryTaskById(taskId);
		
		if (taskBo == null) {
			return false;
		}
		
		taskBo.setTaskStatus(TaskStatus.WAIT_APPROVE_CODE);
		taskBo.setReportFileId(reportFileId);
		taskBo.setFinishTime(new Date());
		
		return updateTask(taskBo);
	}
}
