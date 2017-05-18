package main.java.service;

import java.util.ArrayList;

import main.java.bo.TaskBo;
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
	 * 根据 任务id 查找
	 * @param id 任务id
	 * @return 匹配查询结果的任务对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public TaskBo queryTaskById(Integer id) {
		return taskDao.queryTaskById(id);
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
}
