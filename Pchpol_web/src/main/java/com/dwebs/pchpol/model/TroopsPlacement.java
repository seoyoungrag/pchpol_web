package com.dwebs.pchpol.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the TROOPS_PLACEMENT database table.
 * 
 */
@Entity
@Table(name="PCHPOL.TROOPS_PLACEMENT")
@NamedQuery(name="TroopsPlacement.findAll", query="SELECT t FROM TroopsPlacement t")
public class TroopsPlacement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TROOPS_PLACEMENT_NO")
	private int troopsPlacementNo;

	//bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(name="CODE_TROOPS")
	private Code code1;

	//bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(name="CODE_WORKPLACE")
	private Code code2;

	public TroopsPlacement() {
	}

	public int getTroopsPlacementNo() {
		return this.troopsPlacementNo;
	}

	public void setTroopsPlacementNo(int troopsPlacementNo) {
		this.troopsPlacementNo = troopsPlacementNo;
	}

	public Code getCode1() {
		return this.code1;
	}

	public void setCode1(Code code1) {
		this.code1 = code1;
	}

	public Code getCode2() {
		return this.code2;
	}

	public void setCode2(Code code2) {
		this.code2 = code2;
	}

}