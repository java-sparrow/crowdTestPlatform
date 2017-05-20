package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.bo.TaskBo;
import main.java.util.DBUtil;

/**
 * 任务表 数据访问对象，提供对于任务表的增删改查操作
 *
 */
public class TaskDao extends BaseDao {
	/**
	 * 数据库连接
	 */
	private static Connection connection = DBUtil.getConnect();
	/**
	 * 任务表名
	 */
	private static String tableName = "t_task";

	/**
	 * 将数据库结果集对象 ResultSet 中的字段 转换为 TaskBo 的属性，并返回 TaskBo
	 * @param resultSet 数据库结果集对象
	 * @return 任务对象
	 * @throws SQLException 当无法从 ResultSet 中获取字段值时，抛出该异常
	 */
	private static TaskBo convertResultSetToTaskBo(ResultSet resultSet) throws SQLException {
		TaskBo taskBo = new TaskBo();
		
		taskBo.setId(resultSet.getInt("id"));
		taskBo.setTaskName(resultSet.getString("task_name"));
		taskBo.setTaskDetail(resultSet.getString("task_detail"));
		taskBo.setTaskImgUrl(resultSet.getString("task_img_url"));
		taskBo.setProjectDescription(resultSet.getString("project_description"));
		taskBo.setProjectComment(resultSet.getString("project_comment"));
		taskBo.setTestRequirement(resultSet.getString("test_requirement"));
		taskBo.setTaskFileUrl(resultSet.getString("task_file_url"));
		taskBo.setTaskStatus(resultSet.getInt("task_status"));
		taskBo.setCreateTime(resultSet.getTimestamp("create_time"));
		taskBo.setPublishUserId(resultSet.getInt("publish_user_id"));
		taskBo.setDeadlineTime(resultSet.getTimestamp("deadline_time"));
		taskBo.setAcceptUserId(resultSet.getInt("accept_user_id"));
		taskBo.setFinishTime(resultSet.getTimestamp("finish_time"));
		taskBo.setApproveTime(resultSet.getTimestamp("approve_time"));
		
		return taskBo;
	}
	
	/**
	 * 将数据库结果集对象 ResultSet 转换为 任务对象数组 ArrayList< TaskBo >
	 * @param resultSet 数据库结果集对象
	 * @return 任务对象数组
	 */
	private static ArrayList<TaskBo> getListFromResultSet(ResultSet resultSet) {
		ArrayList<TaskBo> taskList = new ArrayList<>();
		
		try {
			while (resultSet.next()) {
				taskList.add(convertResultSetToTaskBo(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return taskList;
	}

	/**
	 * 从数据库结果集对象 ResultSet 中，获取第一条记录，并返回转换后的 TaskBo
	 * @param resultSet 数据库结果集对象
	 * @return 任务对象。
	 * 			<br>当发生异常时，返回 null
	 */
	private static TaskBo getFirstFromResultSet(ResultSet resultSet) {
		TaskBo taskBo = null;
		
		try {
			if (resultSet.next()) {
				taskBo = convertResultSetToTaskBo(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return taskBo;
	}
	
	/**
	 * 查询任务列表
	 * <br>TODO: 支持分页
	 * @return 任务对象列表。
	 * 			<br>当无查询结果 或 发生异常时，返回 空数组
	 */
	public ArrayList<TaskBo> queryTaskList() {
		ArrayList<TaskBo> taskList = new ArrayList<>();
		
		String sql = "select * from " + tableName;
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			
			taskList = getListFromResultSet(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return taskList;
	}
	/**
	 * 根据参数 查询任务列表
	 * <br>TODO: 支持分页
	 * @param queryData 查询数据对象，根据该对象的 taskStatus、publishUserId、acceptUserId 的值（允许多个值同时存在）来查询
	 * @return 任务对象列表。
	 * 			<br>当无查询结果 或 发生异常时，返回 空数组
	 */
	public ArrayList<TaskBo> queryTaskList(TaskBo queryData) {
		Integer queryTaskStatus = queryData.getTaskStatus();
		Integer queryPublishUserId = queryData.getPublishUserId();
		Integer queryAcceptUserId = queryData.getAcceptUserId();
		
		String sql = "select * from " + tableName + " where 1 = 1";
		
		if (queryTaskStatus != null) {
			sql += " and `task_status` = ?";
		}
		if (queryPublishUserId != null) {
			sql += " and `publish_user_id` = ?";
		}
		if (queryAcceptUserId != null) {
			sql += " and `accept_user_id` = ?";
		}
		
		ArrayList<TaskBo> taskList = new ArrayList<>();
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			int queryFieldCount = 0;
			
			if (queryTaskStatus != null) {
				preparedStatement.setInt(++queryFieldCount, queryTaskStatus);
			}
			if (queryPublishUserId != null) {
				preparedStatement.setInt(++queryFieldCount, queryPublishUserId);
			}
			if (queryAcceptUserId != null) {
				preparedStatement.setInt(++queryFieldCount, queryAcceptUserId);
			}
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			taskList = getListFromResultSet(resultSet);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return taskList;
	}

	/**
	 * 根据 任务id 查找
	 * @param id 任务id
	 * @return 匹配查询结果的任务对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public TaskBo queryTaskById(Integer id) {
		TaskBo taskBo = null;
		
		String sql = "select * from " + tableName + " where id = ?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			taskBo = getFirstFromResultSet(resultSet);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return taskBo;
	}
	
	/**
	 * 新增任务
	 * @param taskData 任务数据对象
	 * @return 新增成功时，返回 true。<br>
	 * 			新增失败时，返回 false。
	 */
	public Boolean addTask(TaskBo taskData) {
		Boolean isInserted = false;
		String sql = "insert into " + tableName
				+ " (`task_name`, `task_detail`, `task_img_url`, `project_description`, `project_comment`, `test_requirement`, `task_file_url`, `publish_user_id`, `deadline_time`)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, taskData.getTaskName());
			preparedStatement.setString(2, taskData.getTaskDetail());
			preparedStatement.setString(3, taskData.getTaskImgUrl());
			preparedStatement.setString(4, taskData.getProjectDescription());
			preparedStatement.setString(5, taskData.getProjectComment());
			preparedStatement.setString(6, taskData.getTestRequirement());
			preparedStatement.setString(7, taskData.getTaskFileUrl());
			preparedStatement.setInt(8, taskData.getPublishUserId());
			preparedStatement.setObject(9, taskData.getDeadlineTime());
			
			int resultRows = preparedStatement.executeUpdate();
			
			if (resultRows == 1) {
				isInserted = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isInserted;
	}
	
	/**
	 * 更新任务
	 * @param taskData 新的任务数据对象
	 * @return 更新成功时，返回 true。<br>
	 * 			更新失败时，返回 false。
	 */
	public Boolean updateTask(TaskBo taskData) {
		Boolean isUpdated = false;
		
		String sql = "update " + tableName
				+ " set `task_name`=?, `task_detail`=?, `task_img_url`=?"
				+ ", `project_description`=?, `project_comment`=?, `test_requirement`=?, `task_file_url`=?"
				+ ", `task_status`=?, `deadline_time`=?, `accept_user_id`=?, `finish_time`=?, `approve_time`=?"
				+ " where `id`=?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, taskData.getTaskName());
			preparedStatement.setString(2, taskData.getTaskDetail());
			preparedStatement.setString(3, taskData.getTaskImgUrl());
			
			preparedStatement.setString(4, taskData.getProjectDescription());
			preparedStatement.setString(5, taskData.getProjectComment());
			preparedStatement.setString(6, taskData.getTestRequirement());
			preparedStatement.setString(7, taskData.getTaskFileUrl());
			
			preparedStatement.setInt(8, taskData.getTaskStatus());
			preparedStatement.setObject(9, taskData.getDeadlineTime());
			preparedStatement.setInt(10, taskData.getAcceptUserId());
			preparedStatement.setObject(11, taskData.getFinishTime());
			preparedStatement.setObject(12, taskData.getApproveTime());
			
			preparedStatement.setInt(13, taskData.getId());

			int resultRows = preparedStatement.executeUpdate();
			
			if (resultRows == 1) {
				isUpdated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isUpdated;
	}
}
