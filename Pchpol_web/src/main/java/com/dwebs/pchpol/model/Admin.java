package com.dwebs.pchpol.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the ADMIN database table.
 * 
 */
@Entity
@Table(name = "PCHPOL.ADMIN")
@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a")
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ADMIN_NO")
	private int adminNo;

	@Column(name="ADMIN_DEPT")
	private String adminDept;

	@Column(name="ADMIN_ETC")
	private String adminEtc;

	@Column(name="ADMIN_NAME")
	private String adminName;

	@Column(name="ADMIN_ID")
	private String adminId;
	
	@Column(name="ADMIN_PASSWORD")
	private String adminPassword;

	@Column(name="ADMIN_RANK")
	private String adminRank;

	//bi-directional many-to-one association to Code
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name="CODE_ADMIN_TYPE")
	private Code code;

	//bi-directional many-to-one association to Board
	@JsonIgnore
	@OneToMany(mappedBy="admin")
	private List<Board> boards;
	
	public Admin() {
	}

	public int getAdminNo() {
		return this.adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminDept() {
		return this.adminDept;
	}

	public void setAdminDept(String adminDept) {
		this.adminDept = adminDept;
	}

	public String getAdminEtc() {
		return this.adminEtc;
	}

	public void setAdminEtc(String adminEtc) {
		this.adminEtc = adminEtc;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminRank() {
		return this.adminRank;
	}

	public void setAdminRank(String adminRank) {
		this.adminRank = adminRank;
	}

	public Code getCode() {
		return this.code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public List<Board> getBoards() {
		return this.boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public Board addBoard(Board board) {
		getBoards().add(board);
		board.setAdmin(this);

		return board;
	}

	public Board removeBoard(Board board) {
		getBoards().remove(board);
		board.setAdmin(null);

		return board;
	}
}