package com.dwebs.pchpol.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.CascadeType;


/**
 * The persistent class for the "COMMENT" database table.
 * 
 */
@Entity
@Table(name="PCHPOL.COMMENT")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COMMENT_NO")
	private int commentNo;

	@Column(name="COMMENT_CONTENT")
	private String commentContent;

	@Column(name="COMMENT_DELETE_YN")
	private String commentDeleteYn="N";

	@Column(name="COMMENT_WRITE_DT")
	private Date commentWriteDt = new Date();

	@Column(name="COMMENT_WRITER_TYPE")
	private String commentWriterType;

	//bi-directional many-to-one association to Admin
	@ManyToOne
	@JoinColumn(name="COMMENT_WRITE_ADMIN_NO")
	private Admin admin;

	//bi-directional many-to-one association to Board
	@ManyToOne
	@JoinColumn(name="BOARD_NO")
	private Board board;

	//bi-directional many-to-one association to Comment
	@ManyToOne
	@JoinColumn(name="PARENT_COMMENT_NO")
	private Comment comment;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="comment")
	@OrderBy(value = "commentWriteDt ASC")
	private List<Comment> comments;

	//bi-directional many-to-one association to Unit
	@ManyToOne
	@JoinColumn(name="COMMENT_WRITE_UNIT_NO")
	private Unit unit;

	public Comment() {
	}

	public int getCommentNo() {
		return this.commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentDeleteYn() {
		return this.commentDeleteYn;
	}

	public void setCommentDeleteYn(String commentDeleteYn) {
		this.commentDeleteYn = commentDeleteYn;
	}

	public Date getCommentWriteDt() {
		return commentWriteDt;
	}

	public void setCommentWriteDt(Date commentWriteDt) {
		this.commentWriteDt = commentWriteDt;
	}

	public String getCommentWriterType() {
		return this.commentWriterType;
	}

	public void setCommentWriterType(String commentWriterType) {
		this.commentWriterType = commentWriterType;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setComment(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setComment(null);

		return comment;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}