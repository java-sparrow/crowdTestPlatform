package main.java.service;

import main.java.bo.TaskDocBo;
import main.java.dao.TaskDocDao;

public class TaskDocService extends BaseService {
	private TaskDocDao taskDocDao = new TaskDocDao();

	/**
	 * 新增 任务文档
	 * @param taskDocBo 任务文档数据对象
	 * @return 新增成功时，返回 新记录的id。<br>
	 * 			新增失败时，返回 null。
	 */
	public Integer addTaskDocBo(TaskDocBo taskDocBo) {
		return taskDocDao.addTaskDocBo(taskDocBo);
	}
}
