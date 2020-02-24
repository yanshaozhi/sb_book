package com.yingfa.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.yingfa.common.model.User;

/**
 * 用户管理controller
 * 
 * @author yanjie
 *
 */
public class UserController extends Controller{

	/**
	 * 渲染list列表
	 */
	public void list(){
		render("/user/list.html");
	}
	
	public void edit(){
		Integer id = getParaToInt("id");
		User user = User.dao.findById(id);
		setAttr("user", user);
		render("/user/form.html");
	}
	
	public void save(){
		User user = getModel(User.class,"");
		Boolean result = false;
		if(user.getId()==null){
			result = user.save();
		}else{
			result = user.update();
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
		String username = getPara("username");
		
		/*{
		  "code": 0,
		  "msg": "",
		  "count": 1000,
		  "data": [{}, {}]
		}*/
		//dao.paginate(1, 10, "select *", "from girl where age > ? and weight < ?", 18, 50);
		Page<User> page = User.dao.paginate(pageNumber, pageSize, "select * "," from user where username like concat('%', ?, '%') ",username);
		Kv kv = Kv.create();
		kv.set("code",0);
		kv.set("msg","查询成功");
		kv.set("count",page.getTotalRow());
		kv.set("data",page.getList());
		renderJson(kv);
	}
	
	public void delete(){
		Integer id = getParaToInt("id");
		Boolean result = User.dao.deleteById(id);
		if(result){
			renderText("suc");
		}else{
			renderText("fail");
		}
	}
	
	public void login(){
		String username = getPara("username");
		String password = getPara("password");
		
		User user = User.dao.findFirst("select * from user where username = ? and password = ?",username,password);
		if(null == user){
			renderText("fail");
		}else{
			setSessionAttr("lg_user", user);
			renderText("suc");
		}
	}
	
	public void logout(){
		setSessionAttr("lg_user", null);
		redirect("/login");
	}
}
