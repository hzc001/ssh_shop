package cn.it.shop.utils.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import cn.it.shop.model.Category;
import cn.it.shop.model.Product;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ProductService;

/**
 * 商品的线程任务
 * @author Hzc
 *
 */
@Component
public class ProductTimerTask extends TimerTask {

	@Resource
	private ProductService productService = null;
	@Resource
	private CategoryService categoryService = null;

	private ServletContext application = null; // 定义一个ServletContext对象，因为我们更新了后台数据后，需要存入application域里面

	public void setApplication(ServletContext application) {
		this.application = application; // 通过监听器将这个application对象set进来，因为这里是无法拿application对象的
	}

	@Override
	// 和监听器在项目启动的时候数据初始化的逻辑一样
	public void run() {
		System.out.println("----run----");
		List<List<Product>> bigList = new ArrayList<List<Product>>(); // bigList中存放一个装有Category类的list
		// 1. 查询出热点类别
		for (Category category : categoryService.queryByHot(true)) {
			// 根据热点类别id获取推荐商品信息
			List<Product> lst = productService.querByCategoryId(category.getId());
			bigList.add(lst); // 将装有category的list放到bigList中
		}
		// 2. 把查询的bigList交给application内置对象
		application.setAttribute("bigList", bigList);
	}

}
