package cn.it.shop.dao;

import cn.it.shop.model.Forder;

/**
 * 订单的DAO接口
 * @author Hzc
 *
 */
public interface ForderDao extends BaseDao<Forder> {
	// 根据订单编号，更新订单状态
	public void updateStatusById(int id, int sid);
}
