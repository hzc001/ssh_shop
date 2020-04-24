package cn.it.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Category;
import cn.it.shop.service.CategoryService;

/**
 * 商品类别的业务层实现类
 * @author Hzc
 *
 */
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	@Override
	// 查询类别信息，级联管理员
	public List<Category> queryJoinAccount(String type, int page, int size) {
		return categoryDao.queryJoinAccount(type, page, size);
	}

	@Override
	// 根据关键字查询总记录数
	public Long getCount(String type) {
		return categoryDao.getCount(type);
	}

	@Override
	// 根据ids删除多条记录
	public void deleteByIds(String ids) {
		categoryDao.deleteByIds(ids);
	}

	@Override
	// 根据boelen值查询热点或非热点类别
	public List<Category> queryByHot(boolean hot) {
		return categoryDao.queryByHot(hot);
	}
}
