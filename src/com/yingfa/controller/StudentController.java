package com.yingfa.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.yingfa.common.model.Student;

/**
 * 用户管理controller
 * 
 * @author yanjie
 *
 */
public class StudentController extends Controller{

	/**
	 * 渲染list列表
	 */
	public void list(){
		render("/student/list.html");
	}
	
	public void edit(){
		Integer id = getParaToInt("id");
		Student student = Student.dao.findById(id);
		setAttr("student", student);
		render("/student/form.html");
	}
	
	public void save(){
		Student student = getModel(Student.class,"");
		Boolean result = false;
		if(student.getId()==null){
			result = student.save();
		}else{
			result = student.update();
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
		
		Page<Student> page = Student.dao.paginate(pageNumber, pageSize, "select * "," from student where name like concat('%', ?, '%') ", name);
		Kv kv = Kv.create();
		kv.set("code",0);
		kv.set("msg","查询成功");
		kv.set("count",page.getTotalRow());
		kv.set("data",page.getList());
		renderJson(kv);
	}
	
	public void delete(){
		Integer id = getParaToInt("id");
		Boolean result = Student.dao.deleteById(id);
		if(result){
			renderText("suc");
		}else{
			renderText("fail");
		}
	}
	
}
