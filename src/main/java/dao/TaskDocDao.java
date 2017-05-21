package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	/**
	 * 新增 任务文档
	 * @param taskDocBo 任务文档数据对象
	 * @return 新增成功时，返回 新记录的id。<br>
	 * 			新增失败时，返回 null。
	 */
	public Integer addTaskDocBo(TaskDocBo taskDocBo) {
		String sql = "insert into " + tableName
				+ " (`doc_name`, `doc_url`, `task_id`) values (?, ?, ?)";
		
		// 特别注意：如果 connection.prepareStatement 没有加第二个参数，则会报错 Generated keys not requested. 
		// You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate(), Statement.executeLargeUpdate() or Connection.prepareStatement().
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, taskDocBo.getDocName());
			preparedStatement.setString(2, taskDocBo.getDocUrl());
			preparedStatement.setInt(3, taskDocBo.getTaskId());
			
			int resultRows = preparedStatement.executeUpdate();
			
			if (resultRows != 1) {
				System.out.println("新增 任务文档 失败！");
				
				return null;
			}
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			// 获取新记录返回的id
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
