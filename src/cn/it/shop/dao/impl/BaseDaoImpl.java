package cn.it.shop.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import cn.it.shop.dao.BaseDao;

/**
 * @Description TODO（公共模块的DAO抽取）
 * @author Hzc
 *
 */
@SuppressWarnings("unchecked")
@Repository("baseDao")
@Lazy(true) // 延迟加载，没加注解之前主要容器启动就会实例化bean，加上@Lazy注解则必须在第一次调用的时候才会加载，@Lazy注解的作用主要是减少springIOC容器启动的加载时间
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class clazz; // clazz中存储了当前操作的类型，即泛型T

	@Resource // 放在属性上面，就不会调用set方法，使用反射注进来，所以可以把set方法干掉了
	private SessionFactory sessionFactory;

	public BaseDaoImpl() {
		System.out.println("this代表的是当前调用构造方法的对象" + this);       // 1.cn.it.shop.dao.impl.AccountDaoImpl
		System.out.println("获取当前this对象的父类信息" + this.getClass().getSuperclass());  // 2.cn.it.shop.dao.impl.BaseDaoImpl
		System.out.println("获取当前this对象的父类信息(包括泛型信息)" + this.getClass().getGenericSuperclass()); // 3.cn.it.shop.dao.impl.BaseDaoImpl<Account>
		// 拿到泛型的参数类型
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0]; // 4.cn.it.shop.model.Account
		System.out.println("clazz = " + clazz);
	}

	protected Session getSession() {
		// 从当前线程获取session，如果没有则创建一个新的session
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		System.out.println(clazz.getSimpleName());
		String hql = "delete " + clazz.getSimpleName() + " as c where c.id=:id";
		getSession().createQuery(hql) //
				.setInteger("id", id) //
				.executeUpdate();
	}

	@Override
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> query() {
		String hql = "from " + clazz.getSimpleName();
		List<T> list = getSession().createQuery(hql).list();
		return list;
	}

}
