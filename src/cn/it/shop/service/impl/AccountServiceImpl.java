package cn.it.shop.service.impl;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Account;
import cn.it.shop.service.AccountService;

/**
 * 管理员的业务层实现类
 * @author Hzc
 *
 */
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

	/*
	 * 只需实现AccountService接口中新增的方法即可，公共方法已经在BaseServiceImpl中实现了
	 */

	// 管理登陆功能
}
