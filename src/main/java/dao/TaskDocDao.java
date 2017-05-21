package main.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.bo.TaskDocBo;
import main.java.util.DBUtil;

public class TaskDocDao extends BaseDao {
	/**
	 * 数据库连接
	 */
	private static Connection connection = DBUtil.getConnect();
	/**
	 * 任务表名
	 */
	private static String tableName = "t_task_doc";
	

	/**
	 * 将数据库结果集对象 ResultSet 中的字段 转换为 TaskDocBo 的属性，并返回 TaskDocBo
	 * @param resultSet 数据库结果集对象
	 * @return 任务文档对象
	 * @throws SQLException 当无法从 ResultSet 中获取字段值时，抛出该异常
	 */
	private static TaskDocBo convertResultSetToTaskDocBo(ResultSet resultSet) throws SQLException {
		TaskDocBo taskDocBo = new TaskDocBo();
		
		taskDocBo.setDocName(resultSet.getString("docName"));
		taskDocBo.setDocUrl(resultSet.getString("docUrl"));
		taskDocBo.setTaskId(resultSet.getInt("taskId"));
		
		return taskDocBo;
	}

	/**
	 * 将数据库结果集对象 ResultSet 转换为 任务文档对象数组 ArrayList< TaskDocBo >
	 * @param resultSet 数据库结果集对象
	 * @return 任务文档对象数组
	 */
	private static ArrayList<TaskDocBo> getListFromResultSet(ResultSet resultSet) {
		ArrayList<TaskDocBo> taskDocList = new ArrayList<>();
		
		try {
			while (resultSet.next()) {
				taskDocList.add(convertResultSetToTaskDocBo(resultSet));
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
		
		return taskDocList;
	}

	/**
	 * 从数据库结果集对象 ResultSet 中，获取第一条记录，并返回转换后的 TaskDocBo
	 * @param resultSet 数据库结果集对象
	 * @return 任务文档对象。
	 * 			<br>当发生异常时，返回 null
	 */
	private static TaskDocBo getFirstFromResultSet(ResultSet resultSet) {
		TaskDocBo taskDocBo = null;
		
		try {
			if (resultSet.next()) {
				taskDocBo = convertResultSetToTaskDocBo(resultSet);
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
		
		return taskDocBo;
	}
}
