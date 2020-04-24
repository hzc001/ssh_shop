package cn.it.shop.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Sorder;
import cn.it.shop.service.ForderService;

/**
 * 订单的业务层实现类
 * @author Hzc
 *
 */
@Service("forderService")
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements ForderService {

	@Override
	// 计算购物总价格
	public BigDecimal cluTotal(Forder forder) {

		BigDecimal total = new BigDecimal(0.00);
		for(Sorder sorder : forder.getSorders()) {
			total = total.add(sorder.getPrice().multiply(new BigDecimal(sorder.getNumber())));
		}
		return total;
	}

	@Override
	// 根据订单编号，更新订单状态
	public void updateStatusById(int id, int sid) {
		forderDao.updateStatusById(id, sid);
	}
	
}
