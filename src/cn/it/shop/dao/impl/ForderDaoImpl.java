package cn.it.shop.dao.impl;

import org.springframework.stereotype.Repository;

import cn.it.shop.dao.ForderDao;
import cn.it.shop.model.Forder;

/**
 * 订单的DAO实现类
 * @author Hzc
 *
 */
@Repository("forderDao")
public class ForderDaoImpl extends BaseDaoImpl<Forder> implements ForderDao {

	@Override
	// 根据订单编号，更新订单状态
	public void updateStatusById(int id, int sid) {
		String hql = "update Forder f set f.status.id=:sid where f.id=:id";
		getSession().createQuery(hql).setInteger("sid", sid).setInteger("id", id).executeUpdate();
	}

}
