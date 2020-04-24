package cn.it.shop.service;

import java.util.Map;

import cn.it.shop.model.BackData;
import cn.it.shop.model.SendData;

/**
 * 支付的业务层接口
 * @author Hzc
 *
 */
public interface PayService {

	// 把加密后的信息存储到requestMap中
	public abstract Map<String, Object> saveDataToRequest(Map<String, Object> request, SendData sendData);

	// 把返回的数据进行加密得到密文，并与传回来的密文比较
	public boolean checkBackData(BackData backData);
}