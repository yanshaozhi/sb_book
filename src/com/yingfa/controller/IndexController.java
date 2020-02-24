package com.yingfa.controller;
import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.upload.UploadFile;
/**
 * IndexController 指向系统访问首页
 * @author jbolt.cn
 * @email 909854136@qq.com
 * @date 2018年11月4日 下午9:02:52
 */
public class IndexController extends Controller {
	/**
	 * 首页Action
	 */
	public void index() {
		render("index.html");
	}
	
	public void welcome(){
		render("welcome.html");
	}
	
	public void login(){
		render("login.html");
	}
	
	public void upload(){
		UploadFile file = getFile("image");
		System.out.println(file.getFileName());
		System.out.println(file.getOriginalFileName());
		System.out.println(file.getUploadPath());
		/*
		 * {
  "code": 0
  ,"msg": ""
  ,"data": {
    "src": "http://cdn.layui.com/123.jpg"
  }
}       
		 */
		Kv kv = Kv.create();
		kv.set("code",0);
		kv.set("msg","上传成功！");
		kv.set("data",file.getFileName());
		renderJson(kv);
	}
	
}