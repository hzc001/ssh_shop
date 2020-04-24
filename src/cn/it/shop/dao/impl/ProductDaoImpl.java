package cn.it.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.it.shop.dao.ProductDao;

import cn.it.shop.model.Product;

/**
 * 商品的DAO实现类
 * @author Hzc
 *
 */
@SuppressWarnings("unchecked")
@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

	@Override
	// 查询商品信息，级联类别
	public List<Product> queryJoinCategory(String name, int page, int size) {
		String hql = "from Product p left join fetch p.category where p.name like :name";
		List<Product> list = getSession().createQuery(hql).setString("name", "%" + name + "%")
				.setFirstResult((page - 1) * size) // 从第几个开始显示
				.setMaxResults(size) // 显示几个
				.list();
		return list;
	}

	@Override
	// 根据关键字查询总记录数
	public Long getCount(String name) {
		String hql = "select count(p) from Product p where p.name like :name";
		Long num = (Long) getSession().createQuery(hql).setString("name", "%" + name + "%").uniqueResult(); // 只返回一条记录:总记录数
		return num;
	}

	@Override
	// 根据ids删除多条记录
	public void deleteByIds(String ids) {
		String hql = "delete from Product p where p.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	// 根据热点类别查询推荐商品（仅仅查询前4个）
	public List<Product> querByCategoryId(int cid) {
		String hql = "from Product p join fetch p.category "
				+ "where p.commend=true and p.open=true and p.category.id=:cid order by p.date desc";
		List<Product> list = getSession().createQuery(hql)
				.setInteger("cid", cid).setFirstResult(0)
				.setMaxResults(4)
				.list();
		return list;
	}

}
