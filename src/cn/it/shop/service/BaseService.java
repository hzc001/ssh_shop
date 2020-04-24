package cn.it.shop.service;

import java.util.List;

/**
 * 增删查改模板抽取的业务层接口
 * @author Hzc
 *
 * @param <T>
 */
public interface BaseService<T> {
	public void save(T t);      // 插入

	public void update(T t);    // 修改
	
	public void delete(int id); // 删除
	
	public T get(int id);       // 获取一个T
	
	public List<T> query();     // 获取全部的T

}
