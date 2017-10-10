package com.dwebs.pchpol.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * The persistent class for the TROOPS_FACILITY_PLACEMENT database table.
 * 
 */
@Entity
@Table(name="PCHPOL.TROOPS_FACILITY_PLACEMENT")
@NamedQuery(name="TroopsFacilityPlacement.findAll", query="SELECT t FROM TroopsFacilityPlacement t")
public class TroopsFacilityPlacement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TROOPS_FACILITY_PLACEMENT_NO")
	private int troopsFacilityPlacementNo;

	@Column(name="TROOPS_FACILITY_MOBIL_END_DT")
	private Timestamp troopsFacilityMobilEndDt;

	@Column(name="TROOPS_FACILITY_MOBIL_START_DT")
	private Timestamp troopsFacilityMobilStartDt;

	//bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(name="CODE_TROOPS")
	private Code code;

	//bi-directional many-to-one association to Facility
	@ManyToOne
	@JoinColumn(name="FACILITY_NO")
	private Facility facility;

	public TroopsFacilityPlacement() {
	}

	public int getTroopsFacilityPlacementNo() {
		return this.troopsFacilityPlacementNo;
	}

	public void setTroopsFacilityPlacementNo(int troopsFacilityPlacementNo) {
		this.troopsFacilityPlacementNo = troopsFacilityPlacementNo;
	}

	public Timestamp getTroopsFacilityMobilEndDt() {
		return this.troopsFacilityMobilEndDt;
	}

	public void setTroopsFacilityMobilEndDt(Timestamp troopsFacilityMobilEndDt) {
		this.troopsFacilityMobilEndDt = troopsFacilityMobilEndDt;
	}

	public Timestamp getTroopsFacilityMobilStartDt() {
		return this.troopsFacilityMobilStartDt;
	}

	public void setTroopsFacilityMobilStartDt(Timestamp troopsFacilityMobilStartDt) {
		this.troopsFacilityMobilStartDt = troopsFacilityMobilStartDt;
	}

	public Code getCode() {
		return this.code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public Facility getFacility() {
		return this.facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}