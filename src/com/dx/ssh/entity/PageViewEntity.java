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
 * 访问站点人员的信息 <br/>
 * {"Ip":"115.236.60.114","Isp":"中国浙江省杭州市 电信","Browser":
 * "Google Chrome 50.0.2661.102","OS":"Windows 7","QueryResult":1}
 * 
 * @version 1.0 *
 */
@Entity
@Table(name = "pageviews", schema = "my_blog", catalog = "")
public class PageViewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String ip;
	private String isp;
	private String browser;
	private Timestamp createDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Basic
	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Basic
	@Column(name = "isp")
	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	@Basic
	@Column(name = "browser")
	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Basic
	@Column(name = "createDate")
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "PageViewEntity [id=" + id + ", ip=" + ip + ", isp=" + isp + ", browser=" + browser + ", createDate="
				+ createDate + "]";
	}
}
