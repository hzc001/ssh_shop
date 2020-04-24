package cn.it.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Product;
import cn.it.shop.service.ProductService;

/**
 * 商品的业务层实现类
 * @author Hzc
 *
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

	@Override
	// 查询商品信息，级联类别
	public List<Product> queryJoinCategory(String name, int page, int size) {
		return productDao.queryJoinCategory(name, page, size);
	}

	@Override
	// 根据关键字查询总记录数
	public Long getCount(String name) {
		return productDao.getCount(name);
	}

	@Override
	// 根据ids删除多条记录
	public void deleteByIds(String ids) {
		productDao.deleteByIds(ids);
	}

	@Override
	// 根据热点类别查询推荐商品（仅仅查询前4个）
	public List<Product> querByCategoryId(int cid) {
		return productDao.querByCategoryId(cid);
	}

}
