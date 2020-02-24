package com.yingfa.common.config;

import com.jfinal.config.Routes;
import com.yingfa.controller.BookCategoryController;
import com.yingfa.controller.BookController;
import com.yingfa.controller.BorrowReturnController;
import com.yingfa.controller.StudentController;
import com.yingfa.controller.UserController;

public class AdminRoutes extends Routes {

	@Override
	public void config() {
		//针对一组路由配置baseViewPath
		//this.setBaseViewPath("/_view/_admin");
		//针对一组路由配置单独的拦截器
		//this.addInterceptor(new AdminAuthInterceptor());
		//针对后台管理系统配置路由+controller
		//this.add("/admin", AdminIndexController.class,"/index");
		this.add("/user",UserController.class);
		this.add("/student",StudentController.class);
		this.add("/book_category",BookCategoryController.class);
		this.add("/book",BookController.class);
		this.add("/borrow_return",BorrowReturnController.class);
	}

}