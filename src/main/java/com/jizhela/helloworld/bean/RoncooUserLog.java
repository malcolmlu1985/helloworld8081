/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.jizhela.helloworld.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wujing
 */

public class RoncooUserLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer id;


	private Date createTime;

	private String userName;

	private String userIp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@Override
	public String toString() {
		return "RoncooUserLog [id=" + id + ", createTime=" + createTime + ", userName=" + userName + ", userIp=" + userIp + "]";
	}

}
