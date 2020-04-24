package cn.it.shop.dao;

import cn.it.shop.model.User;

/**
 * 用户的DAO接口
 * @author Hzc
 *
 */
public interface UserDao extends BaseDao<User> {
	// 用户登陆，成功返回该User
	public User login(User user);
}
