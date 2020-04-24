package cn.it.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.it.shop.dao.CategoryDao;
import cn.it.shop.model.Category;

/**
 * 商品类别的DAO实现类
 * @author Hzc
 *
 */
@SuppressWarnings("unchecked")
@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

	@Override
	// 查询类别信息，级联管理员
	public List<Category> queryJoinAccount(String type, int page, int size) {
		// left join表示关联Account一起查询，fetch表示将Account对象加到Category中，fetch相当于在程序里设置了lazy=false
		String hql = "from Category c left join fetch c.account where c.type like :type";
		List<Category> list = getSession().createQuery(hql).setString("type", "%" + type + "%")
				.setFirstResult((page - 1) * size) // 从第几个开始显示
				.setMaxResults(size) // 显示几个
				.list();
		return list;
	}

	@Override
	// 根据关键字查询总记录数
	public Long getCount(String type) {
		String hql = "select count(c) from Category c where c.type like :type";
		Long num = (Long) getSession().createQuery(hql).setString("type", "%" + type + "%").uniqueResult(); // 只返回一条记录:总记录数
		return num;
	}

	@Override
	// 根据ids删除多条记录
	public void deleteByIds(String ids) {
		String hql = "delete from Category c where c.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	// 根据boelen值查询热点或非热点类别
	public List<Category> queryByHot(boolean hot) {
		String hql = "from Category c where c.hot=:hot";
		List<Category> list = getSession().createQuery(hql).setBoolean("hot", hot).list();
		return list;
	}
}
