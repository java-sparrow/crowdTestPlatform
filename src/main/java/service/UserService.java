package main.java.service;

import main.java.bo.UserBo;
import main.java.dao.UserDao;

/**
 * 用户Service，提供部分业务逻辑处理
 *
 */
public class UserService extends BaseService {
	private UserDao userDao = new UserDao();

	/**
	 * 根据 用户id 查找
	 * @param id 用户id
	 * @return 匹配查询结果的用户对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public UserBo queryUserById(Integer id) {
		return userDao.queryUserById(id);
	}

	/**
	 * 根据 用户名 查找
	 * @param username 用户名
	 * @return 匹配查询结果的用户对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public UserBo queryUserByName(String username) {
		// TODO: 区分 无查询结果的null 和 抛异常的null
		return userDao.queryUserByName(username);
	}

	/**
	 * 根据 用户名和密码 做全匹配查找
	 * @param userData 用户数据对象
	 * @return 匹配查询结果的用户对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public UserBo queryUser(UserBo userData) {
		return userDao.queryUser(userData);
	}

	/**
	 * 新增用户
	 * @param userData 用户数据对象
	 * @return 新增成功时，返回 true。<br>
	 * 			新增失败时，返回 false。
	 */
	public Boolean addUser(UserBo userData) {
		return userDao.addUser(userData);
	}
}
