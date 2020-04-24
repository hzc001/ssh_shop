package cn.it.shop.service;

import cn.it.shop.model.User;

/**
 * 用户登录的业务层接口
 * @author Hzc
 *
 */
public interface UserService extends BaseService<User> {
	// 用户登陆，成功返回该User
	public User login(User user);
}
