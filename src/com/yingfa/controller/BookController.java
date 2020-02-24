package com.yingfa.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.yingfa.common.model.Book;
import com.yingfa.common.model.BookCategory;

/**
 * 用户管理controller
 * 
 * @author yanjie
 *
 */
public class BookController extends Controller{

	/**
	 * 渲染list列表
	 */
	public void list(){
		render("/book/list.html");
	}
	
	public void edit(){
		Integer id = getParaToInt("id");
		Book book = Book.dao.findById(id);
		setAttr("categorys", BookCategory.dao.find("select * from book_category"));
		setAttr("book", book);
		render("/book/form.html");
	}
	
	public void save(){
		Book book = getModel(Book.class,"",true);
		Boolean result = false;
		if(book.getId()==null){
			result = book.save();
		}else{
			result = book.update();
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
		
		Page<Book> page = Book.dao.paginate(pageNumber, pageSize, "select a.*,b.name category "," from book a left join book_category b on a.category_id = b.id where a.name like concat('%', ?, '%') ", name);
		Kv kv = Kv.create();
		kv.set("code",0);
		kv.set("msg","查询成功");
		kv.set("count",page.getTotalRow());
		kv.set("data",page.getList());
		renderJson(kv);
	}
	
	public void delete(){
		Integer id = getParaToInt("id");
		Boolean result = Book.dao.deleteById(id);
		if(result){
			renderText("suc");
		}else{
			renderText("fail");
		}
	}
	
}
