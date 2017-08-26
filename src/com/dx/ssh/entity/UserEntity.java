package com.dx.ssh.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户信息
 * 
 * @version 1.0
 */
@Entity
@Table(name = "users", schema = "my_blog", catalog = "")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String acount;
	private String pwd;
	private String name;
	private String sex;
	private String phone;
	private String email;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private Timestamp removeDate;
	private String state;
	private String type;
	private String remark;
	private String qq;
	private String weibo;
	private String intro;
	private String avatar;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "account")
	public String getAcount() {
		return acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}

	@Basic
	@Column(name = "pwd")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Basic
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Basic
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "createDate")
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Basic
	@Column(name = "moidfyDate")
	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Basic
	@Column(name = "removeDate")
	public Timestamp getRemoveDate() {
		return removeDate;
	}

	public void setRemoveDate(Timestamp removeDate) {
		this.removeDate = removeDate;
	}

	@Basic
	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Basic
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Basic
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Basic
	@Column(name = "qq")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Basic
	@Column(name = "weibo")
	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	@Basic
	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Basic
	@Column(name = "avatar")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", acount=" + acount + ", pwd=" + pwd + ", name=" + name + ", sex=" + sex
				+ ", phone=" + phone + ", email=" + email + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", removeDate=" + removeDate + ", state=" + state + ", type=" + type + ", remark=" + remark + ", qq="
				+ qq + ", weibo=" + weibo + ", intro=" + intro + ", avatar=" + avatar + "]";
	}	
}
