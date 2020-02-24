package com.yingfa.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.yingfa.common.model.BookCategory;

/**
 * 用户管理controller
 * 
 * @author yanjie
 *
 */
public class BookCategoryController extends Controller{

	/**
	 * 渲染list列表
	 */
	public void list(){
		render("/book_category/list.html");
	}
	
	public void edit(){
		Integer id = getParaToInt("id");
		BookCategory book_category = BookCategory.dao.findById(id);
		setAttr("book_category", book_category);
		render("/book_category/form.html");
	}
	
	public void save(){
		BookCategory book_category = getModel(BookCategory.class,"");
		Boolean result = false;
		if(book_category.getId()==null){
			result = book_category.save();
		}else{
			result = book_category.update();
		}
		
		if(result){
			renderText("suc");
		}else{
			renderText("fail");
		}
	}
	
	public void query(){
		Integer pageNumber = getParaToInt("page");
		Integer pageSize = getParaToInt("limit");
		String name = getPara("name");
		
		Page<BookCategory> page = BookCategory.dao.paginate(pageNumber, pageSize, "select * "," from book_category where name like concat('%', ?, '%') ", name);
		Kv kv = Kv.create();
		kv.set("code",0);
		kv.set("msg","查询成功");
		kv.set("count",page.getTotalRow());
		kv.set("data",page.getList());
		renderJson(kv);
	}
	
	public void delete(){
		Integer id = getParaToInt("id");
		Boolean result = BookCategory.dao.deleteById(id);
		if(result){
			renderText("suc");
		}else{
			renderText("fail");
		}
	}
	
}
