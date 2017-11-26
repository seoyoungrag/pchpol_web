package com.dwebs.pchpol.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the ATTACH database table.
 * 
 */
@Entity
@Table(name = "PCHPOL.ATTACH")
@NamedQuery(name="Attach.findAll", query="SELECT a FROM Attach a")
public class Attach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ATTACH_NO")
	private int attachNo;

	@Column(name="ATTACH_FILE_SIZE")
	private String attachFileSize;

	@Column(name="ATTACH_ORI_NAME")
	private String attachOriName;

	@Column(name="ATTACH_SERVER_NAME")
	private String attachServerName;

	//bi-directional many-to-one association to Board
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="BOARD_NO")
	private Board board;

	public Attach() {
	}

	public int getAttachNo() {
		return this.attachNo;
	}

	public void setAttachNo(int attachNo) {
		this.attachNo = attachNo;
	}

	public String getAttachFileSize() {
		return this.attachFileSize;
	}

	public void setAttachFileSize(String attachFileSize) {
		this.attachFileSize = attachFileSize;
	}

	public String getAttachOriName() {
		return this.attachOriName;
	}

	public void setAttachOriName(String attachOriName) {
		this.attachOriName = attachOriName;
	}

	public String getAttachServerName() {
		return this.attachServerName;
	}

	public void setAttachServerName(String attachServerName) {
		this.attachServerName = attachServerName;
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}