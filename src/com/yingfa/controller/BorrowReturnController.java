package com.yingfa.controller;

import java.util.Date;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.yingfa.common.model.Book;
import com.yingfa.common.model.BorrowReturn;
import com.yingfa.common.model.User;

/**
 * 用户管理controller
 * 
 * @author yanjie
 *
 */
public class BorrowReturnController extends Controller{

	/**
	 * 渲染list列表
	 */
	public void list(){
		render("/borrow_return/list.html");
	}
	
	public void edit(){
		Integer id = getParaToInt("id");
		BorrowReturn borrow_return = BorrowReturn.dao.findById(id);
		setAttr("books", Book.dao.find("select * from book"));
		setAttr("students", Book.dao.find("select * from student"));
		setAttr("borrow_return", borrow_return);
		render("/borrow_return/form.html");
	}
	
	@Before(Tx.class)
	public void save(){
		BorrowReturn borrow_return = getModel(BorrowReturn.class,"");
		Boolean result = false;
		if(borrow_return.getId()==null){
			borrow_return.setCreateTime(new Date());
			borrow_return.setUserId(getLoginUser().getId());
			result = borrow_return.save();
			Book book = Book.dao.findById(borrow_return.getBookId());
			if("归还".equals(borrow_return.getType())){
				book.setCurrentCount(book.getCurrentCount()+1);
				book.setOutCount(book.getOutCount()-1);
			}else if("借出".equals(borrow_return.getType())){
				book.setCurrentCount(book.getCurrentCount()-1);
				book.setOutCount(book.getOutCount()+1);
			}
			book.update();
		}else{
			result = borrow_return.update();
		}
		
		if(result){
			renderText("suc");
		}else{
			renderText("fail");
		}
	}

	private User getLoginUser() {
		return (User)getSessionAttr("lg_user");
	}
	
	public void query(){
		Integer pageNumber = getParaToInt("page");
		Integer pageSize = getParaToInt("limit");
		String name = getPara("name");
		
		Page<BorrowReturn> page = BorrowReturn.dao.paginate(pageNumber, pageSize, "select a.*,b.name book_name,c.name student_name "," from borrow_return a left join book b on a.book_id = b.id left join student c on a.student_id = c.id where c.name like concat('%', ?, '%') ", name);
		Kv kv = Kv.create();
		kv.set("code",0);
		kv.set("msg","查询成功");
		kv.set("count",page.getTotalRow());
		kv.set("data",page.getList());
		renderJson(kv);
	}
	
	public void delete(){
		Integer id = getParaToInt("id");
		Boolean result = BorrowReturn.dao.deleteById(id);
		if(result){
			renderText("suc");
		}else{
			renderText("fail");
		}
	}
	
}
