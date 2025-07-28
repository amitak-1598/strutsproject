package com.project.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Proxy(lazy = false)
@Table(name="ticket_comment")
public class TicketComment implements Serializable {

	private static final long serialVersionUID = 1146493226757874239L;
	@Id
	@Column(name = "comment_id",nullable = false, unique = true)
	private String commentId;
	@CreationTimestamp
	@Column(name = "create_date")
	private Date createDate;
	@UpdateTimestamp
	@Column(name = "update_date")
	private Date updateDate;
	@Column(name = "comment_sender_email_id")
	private String commentSenderEmailId;
	@Column(name = "comment_body")
	private String commentBody;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCommentSenderEmailId() {
		return commentSenderEmailId;
	}

	public void setCommentSenderEmailId(String commentSenderEmailId) {
		this.commentSenderEmailId = commentSenderEmailId;
	}

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

}

