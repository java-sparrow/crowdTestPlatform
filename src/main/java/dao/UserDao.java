package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.bo.UserBo;
import main.java.util.DBUtil;

/**
 * 用户表 数据访问对象，提供对于用户表的增删改查操作
 *
 */
public class UserDao extends BaseDao {
	/**
	 * 数据库连接
	 */
	private static Connection connection = DBUtil.getConnect();
	/**
	 * 用户表名
	 */
	private static String tableName = "t_user";

	/**
	 * 将数据库结果集对象 ResultSet 中的字段 转换为 UserBo 的属性，并返回 UserBo
	 * @param resultSet 数据库结果集对象
	 * @return 用户对象
	 * @throws SQLException 当无法从 ResultSet 中获取字段值时，抛出该异常
	 */
	private static UserBo convertResultSetToUserBo(ResultSet resultSet) throws SQLException {
		UserBo userBo = new UserBo();
		
		userBo.setId(resultSet.getInt("id"));
		userBo.setUsername(resultSet.getString("username"));
		userBo.setPassword(resultSet.getString("password"));
		userBo.setCreateTime(resultSet.getTimestamp("create_time"));
		
		return userBo;
	}
	
	/**
	 * 将数据库结果集对象 ResultSet 转换为 用户对象数组 ArrayList< UserBo >
	 * @param resultSet 数据库结果集对象
	 * @return 用户对象数组
	 */
	private static ArrayList<UserBo> getListFromResultSet(ResultSet resultSet) {
		ArrayList<UserBo> userList = new ArrayList<>();
		
		try {
			while (resultSet.next()) {
				userList.add(convertResultSetToUserBo(resultSet));
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
		
		return userList;
	}

	/**
	 * 从数据库结果集对象 ResultSet 中，获取第一条记录，并返回转换后的 UserBo
	 * @param resultSet 数据库结果集对象
	 * @return 用户对象。
	 * 			<br>当发生异常时，返回 null
	 */
	private static UserBo getFirstFromResultSet(ResultSet resultSet) {
		UserBo userBo = null;
		
		try {
			if (resultSet.next()) {
				userBo = convertResultSetToUserBo(resultSet);
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
		
		return userBo;
	}
	
	/**
	 * 根据 用户名和密码 做全匹配查找
	 * @param userData 用户数据对象
	 * @return 匹配查询结果的用户对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public UserBo queryUser(UserBo userData) {
		UserBo userBo = null;
		
		String sql = "select * from " + tableName + " where username = ? and password = ?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, userData.getUsername());
			preparedStatement.setString(2, userData.getPassword());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			userBo = getFirstFromResultSet(resultSet);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userBo;
	}

	/**
	 * 根据 用户id 查找
	 * @param id 用户id
	 * @return 匹配查询结果的用户对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public UserBo queryUserById(Integer id) {
		UserBo userBo = null;
		
		String sql = "select * from " + tableName + " where id = ?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			userBo = getFirstFromResultSet(resultSet);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userBo;
	}
	
	/**
	 * 根据 用户名 查找
	 * @param username 用户名
	 * @return 匹配查询结果的用户对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public UserBo queryUserByName(String username) {
		UserBo userBo = null;
		
		String sql = "select * from " + tableName + " where username = ?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, username);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			userBo = getFirstFromResultSet(resultSet);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userBo;
	}
	
	/**
	 * 新增用户
	 * @param userData 用户数据对象
	 * @return 新增成功时，返回 true。<br>
	 * 			新增失败时，返回 false。
	 */
	public Boolean addUser(UserBo userData) {
		Boolean isInserted = false;
		String sql = "insert into " + tableName + " (username, password) values (?, ?)";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, userData.getUsername());
			preparedStatement.setString(2, userData.getPassword());
			
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
}
