package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.bo.UploadFileBo;
import main.java.util.DBUtil;

public class UploadFileDao extends BaseDao {
	/**
	 * 数据库连接
	 */
	private static Connection connection = DBUtil.getConnect();
	/**
	 * 文件上传表名
	 */
	private static String tableName = "t_upload_file";

	/**
	 * 将数据库结果集对象 ResultSet 中的字段 转换为 UploadFileBo 的属性，并返回 UploadFileBo
	 * @param resultSet 数据库结果集对象
	 * @return 上传文件对象
	 * @throws SQLException 当无法从 ResultSet 中获取字段值时，抛出该异常
	 */
	private static UploadFileBo convertResultSetToUploadFileBo(ResultSet resultSet) throws SQLException {
		UploadFileBo uploadFileBo = new UploadFileBo();
		
		uploadFileBo.setId(resultSet.getInt("id"));
		uploadFileBo.setUploadUserId(resultSet.getInt("upload_user_id"));
		uploadFileBo.setUploadFileTime(resultSet.getTimestamp("upload_file_time"));
		uploadFileBo.setUploadFileName(resultSet.getString("upload_file_name"));
		uploadFileBo.setUploadFileSize(resultSet.getInt("upload_file_size"));
		uploadFileBo.setSaveFileName(resultSet.getString("save_file_name"));
		uploadFileBo.setSaveFilePath(resultSet.getString("save_file_path"));
		
		return uploadFileBo;
	}
	
	/**
	 * 将数据库结果集对象 ResultSet 转换为 上传文件对象数组 ArrayList< UploadFileBo >
	 * @param resultSet 数据库结果集对象
	 * @return 上传文件对象数组
	 */
	private static ArrayList<UploadFileBo> getListFromResultSet(ResultSet resultSet) {
		ArrayList<UploadFileBo> uploadFileList = new ArrayList<>();
		
		try {
			while (resultSet.next()) {
				uploadFileList.add(convertResultSetToUploadFileBo(resultSet));
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
		
		return uploadFileList;
	}

	/**
	 * 从数据库结果集对象 ResultSet 中，获取第一条记录，并返回转换后的 UploadFileBo
	 * @param resultSet 数据库结果集对象
	 * @return 上传文件对象。
	 * 			<br>当发生异常时，返回 null
	 */
	private static UploadFileBo getFirstFromResultSet(ResultSet resultSet) {
		UploadFileBo uploadFileBo = null;
		
		try {
			if (resultSet.next()) {
				uploadFileBo = convertResultSetToUploadFileBo(resultSet);
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
		
		return uploadFileBo;
	}


	/**
	 * 根据 上传文件id 查找
	 * @param id 上传文件id
	 * @return 匹配查询结果的上传文件对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public UploadFileBo queryUploadFileById(Integer id) {
		UploadFileBo uploadFileBo = null;
		
		String sql = "select * from " + tableName + " where id = ?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			uploadFileBo = getFirstFromResultSet(resultSet);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uploadFileBo;
	}
	
	/**
	 * 新增上传文件
	 * @param uploadFileData 上传文件数据对象
	 * @return 新增成功时，返回 新记录的id。<br>
	 * 			新增失败时，返回 null。
	 */
	public Integer addUploadFile(UploadFileBo uploadFileData) {
		String sql = "insert into " + tableName
				+ " (`upload_user_id`, `upload_file_time`, `upload_file_name`, `upload_file_size`, `save_file_name`, `save_file_path`)"
				+ " values (?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, uploadFileData.getUploadUserId());
			preparedStatement.setObject(2, uploadFileData.getUploadFileTime());
			preparedStatement.setString(3, uploadFileData.getUploadFileName());
			preparedStatement.setLong(4, uploadFileData.getUploadFileSize());
			preparedStatement.setString(5, uploadFileData.getSaveFileName());
			preparedStatement.setString(6, uploadFileData.getSaveFilePath());
			
			int resultRows = preparedStatement.executeUpdate();
			
			if (resultRows != 1) {
				System.out.println("新增 上传文件 失败！");
				
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
