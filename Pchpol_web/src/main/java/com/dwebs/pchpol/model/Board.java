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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the BOARD database table.
 * 
 */
@Entity
@Table(name = "PCHPOL.BOARD")
@NamedQuery(name="Board.findAll", query="SELECT b FROM Board b")
public class Board implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BOARD_NO")
	private int boardNo;

	@Column(name="BOARD_AREA")
	private String boardArea;

	@Column(name="BOARD_CATEGORY")
	private String boardCategory;

	@Column(name="BOARD_SUB_CATEGORY")
	private String boardSubCategory;

	@Column(name="IS_PRIVACY")
	private String isPrivacy;
	
	@Column(name="BOARD_CONTENT")
	private String boardContent;

	@Column(name="BOARD_DELETE_YN")
	private String boardDeleteYn="N";

	@Column(name="BOARD_TITLE")
	private String boardTitle;

	@Column(name="BOARD_TYPE")
	private String boardType;

	@Column(name="BOARD_WRITE_DT")
	private Date boardWriteDt = new Date();

	//bi-directional many-to-one association to Attach
	@OneToMany(mappedBy="board")
	private List<Attach> attaches;

	//bi-directional many-to-one association to Admin
	@ManyToOne
	@JoinColumn(name="BOARD_WRITE_ADMIN_NO")
	private Admin admin;

	//bi-directional many-to-one association to Unit
	@ManyToOne
	@JoinColumn(name="BOARD_WRITE_UNIT_NO")
	private Unit unit;

	//bi-directional many-to-one association to Board
	@ManyToOne
	@JoinColumn(name="PARENT_BOARD_NO")
	private Board board;

	//bi-directional many-to-one association to Board
	@OneToMany(mappedBy="board")
	private List<Board> boards;

	//bi-directional many-to-one association to Comment
	@JsonIgnore
	@OneToMany(mappedBy="board")
	@OrderBy(value = "commentWriteDt ASC")
	private List<Comment> comments;
	
	public Board() {
	}

	public int getBoardNo() {
		return this.boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardArea() {
		return this.boardArea;
	}

	public void setBoardArea(String boardArea) {
		this.boardArea = boardArea;
	}

	public String getBoardCategory() {
		return this.boardCategory;
	}

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	public String getBoardSubCategory() {
		return boardSubCategory;
	}

	public void setBoardSubCategory(String boardSubCategory) {
		this.boardSubCategory = boardSubCategory;
	}

	public String getIsPrivacy() {
		return isPrivacy;
	}

	public void setIsPrivacy(String isPrivacy) {
		this.isPrivacy = isPrivacy;
	}

	public String getBoardContent() {
		return this.boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardDeleteYn() {
		return this.boardDeleteYn;
	}

	public void setBoardDeleteYn(String boardDeleteYn) {
		this.boardDeleteYn = boardDeleteYn;
	}

	public String getBoardTitle() {
		return this.boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardType() {
		return this.boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public Date getBoardWriteDt() {
		return boardWriteDt;
	}

	public void setBoardWriteDt(Date boardWriteDt) {
		this.boardWriteDt = boardWriteDt;
	}

	public List<Attach> getAttaches() {
		return this.attaches;
	}

	public void setAttaches(List<Attach> attaches) {
		this.attaches = attaches;
	}

	public Attach addAttach(Attach attach) {
		getAttaches().add(attach);
		attach.setBoard(this);

		return attach;
	}

	public Attach removeAttach(Attach attach) {
		getAttaches().remove(attach);
		attach.setBoard(null);

		return attach;
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

	public List<Board> getBoards() {
		return this.boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public Board addBoard(Board board) {
		getBoards().add(board);
		board.setBoard(this);

		return board;
	}

	public Board removeBoard(Board board) {
		getBoards().remove(board);
		board.setBoard(null);

		return board;
	}
	
	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setBoard(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setBoard(null);

		return comment;
	}
}