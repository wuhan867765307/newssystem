package cn.news.entity;

import java.io.Serializable;

/*
 * 用户 实体类
 * @author
 */
public class User implements Serializable {
	
    private static final long serialVersionUID = 435894070589975762L;
    private int uid;
	private String uname;
	private String upwd;

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public int getUid() {
		return uid;
	}

	public String getUname() {
		return uname;
	}

	public String getUpwd() {
		return upwd;
	}
	
	public User() {
		super();
	}

	public User(String uname, String upwd) {
		this.uname = uname;
		this.upwd = upwd;
	}

	public User(int uid, String uname, String upwd) {
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
	}
	
	
}
