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
@Table(name = "articles", schema = "my_blog", catalog = "")
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String author;
	private int likes;
	private int looks;
	private String title;
	private String meta;
	private String content;
	private String staticUrl;
	private String type;
	private int top;
	private String remark;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private Timestamp removeDate;
	private int userId;
	private int categoryId;

	public String staticPath() {
		return "/blog/" + getCategoryId() + "/" + getCategoryId() + "-" + id;
	}

	public ArticleEntity() {
		super();
	}

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
	@Column(name = "author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Basic
	@Column(name = "likes")
	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Basic
	@Column(name = "looks")
	public int getLooks() {
		return looks;
	}

	public void setLooks(int looks) {
		this.looks = looks;
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
	@Column(name = "meta")
	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	@Basic
	@Column(name = "content", length = Integer.MAX_VALUE)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Basic
	@Column(name = "staticUrl", length = 128)
	public String getStaticUrl() {
		if (staticUrl == null || staticUrl.length() == 0)
			return staticPath();
		return staticUrl;
	}

	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}

	@Basic
	@Column(name = "type", length = 32)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Basic
	@Column(name = "top")
	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	@Basic
	@Column(name = "remark", length = 512)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Basic
	@Column(name = "userId")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Basic
	@Column(name = "categoryId")
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
