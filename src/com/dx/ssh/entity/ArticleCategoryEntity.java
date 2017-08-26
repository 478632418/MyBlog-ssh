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

@Entity
@Table(name = "articlecategories", schema = "my_blog", catalog = "")
public class ArticleCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String remarks;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private Timestamp removeDate;

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
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Basic
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	@Column(name = "modifyDate")
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

	@Override
	public String toString() {
		return "ArticleCategoryEntity [id=" + id + ", title=" + title + ", remarks=" + remarks + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", removeDate=" + removeDate + "]";
	}

}
