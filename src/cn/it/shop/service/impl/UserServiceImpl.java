package cn.it.shop.service.impl;

import org.springframework.stereotype.Service;
import cn.it.shop.model.User;
import cn.it.shop.service.UserService;

/**
 * 用户登录的业务层实现类
 * @author Hzc
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Override
	// 用户登陆，成功返回该User
	public User login(User user) {
		return userDao.login(user);
	}

}
