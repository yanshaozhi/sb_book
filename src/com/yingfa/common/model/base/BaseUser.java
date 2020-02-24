package com.yingfa.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	/**
	 * 标识
	 */
	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	/**
	 * 标识
	 */
	public java.lang.Integer getId() {
		return getInt("id");
	}

	/**
	 * 用户名
	 */
	public M setUsername(java.lang.String username) {
		set("username", username);
		return (M)this;
	}
	
	/**
	 * 用户名
	 */
	public java.lang.String getUsername() {
		return getStr("username");
	}

	/**
	 * 密码
	 */
	public M setPassword(java.lang.String password) {
		set("password", password);
		return (M)this;
	}
	
	/**
	 * 密码
	 */
	public java.lang.String getPassword() {
		return getStr("password");
	}

	/**
	 * 姓名
	 */
	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	/**
	 * 姓名
	 */
	public java.lang.String getName() {
		return getStr("name");
	}

	/**
	 * 备注
	 */
	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	/**
	 * 备注
	 */
	public java.lang.String getRemark() {
		return getStr("remark");
	}

}
