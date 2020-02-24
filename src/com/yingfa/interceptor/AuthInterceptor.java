package com.yingfa.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class AuthInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		Controller c = inv.getController();
		Object obj = c.getSessionAttr("lg_user");
		String key = inv.getActionKey();
		System.out.println(key);
		if(!("/login".equals(key) ||"/user/login".equals(key))){
			if(obj==null){
				c.redirect("/login");
				return;
			}
		}
		inv.invoke();
	}

}
