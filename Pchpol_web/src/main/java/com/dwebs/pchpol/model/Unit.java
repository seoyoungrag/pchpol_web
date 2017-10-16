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
import javax.persistence.Table;


/**
 * The persistent class for the UNIT database table.
 * 
 */
@Entity
@Table(name="PCHPOL.UNIT")
@NamedQuery(name="Unit.findAll", query="SELECT u FROM Unit u")
public class Unit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UNIT_NO")
	private int unitNo;

	@Column(name="TROOPS_TYPE")
	private String troopsType;

	@Column(name="UNIT_BIRTH")
	private String unitBirth;

	@Column(name="UNIT_MOBIL_END_DT")
	private Date unitMobilEndDt;

	@Column(name="UNIT_MOBIL_START_DT")
	private Date unitMobilStartDt;

	@Column(name="UNIT_NAME")
	private String unitName;

	@Column(name="UNIT_POL_ID")
	private String unitPolId;

	//bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(name="CODE_TROOPS")
	private Code code1;

	//bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(name="CODE_RANK")
	private Code code2;

	//bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(name="CODE_MOBIL_FUNCTION")
	private Code code3;
	
	//bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(name="CODE_POSITION")
	private Code code4;

	//bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(name="CODE_MOBIL_PLACEMENT")
	private Code code5;
	
	//bi-directional many-to-one association to WorkplacePlacementDetail
	@OneToMany(mappedBy="unit")
	private List<WorkplacePlacementDetail> workplacePlacementDetails;

	public Unit() {
	}

	public int getUnitNo() {
		return this.unitNo;
	}

	public void setUnitNo(int unitNo) {
		this.unitNo = unitNo;
	}

	public String getTroopsType() {
		return this.troopsType;
	}

	public void setTroopsType(String troopsType) {
		this.troopsType = troopsType;
	}

	public String getUnitBirth() {
		return this.unitBirth;
	}

	public void setUnitBirth(String unitBirth) {
		this.unitBirth = unitBirth;
	}

	public Date getUnitMobilEndDt() {
		return this.unitMobilEndDt;
	}

	public void setUnitMobilEndDt(Date unitMobilEndDt) {
		this.unitMobilEndDt = unitMobilEndDt;
	}

	public Date getUnitMobilStartDt() {
		return this.unitMobilStartDt;
	}

	public void setUnitMobilStartDt(Date unitMobilStartDt) {
		this.unitMobilStartDt = unitMobilStartDt;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitPolId() {
		return this.unitPolId;
	}

	public void setUnitPolId(String unitPolId) {
		this.unitPolId = unitPolId;
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

	public Code getCode3() {
		return this.code3;
	}

	public void setCode3(Code code3) {
		this.code3 = code3;
	}

	public Code getCode4() {
		return code4;
	}

	public void setCode4(Code code4) {
		this.code4 = code4;
	}

	public Code getCode5() {
		return code5;
	}

	public void setCode5(Code code5) {
		this.code5 = code5;
	}

	public List<WorkplacePlacementDetail> getWorkplacePlacementDetails() {
		return this.workplacePlacementDetails;
	}

	public void setWorkplacePlacementDetails(List<WorkplacePlacementDetail> workplacePlacementDetails) {
		this.workplacePlacementDetails = workplacePlacementDetails;
	}

	public WorkplacePlacementDetail addWorkplacePlacementDetail(WorkplacePlacementDetail workplacePlacementDetail) {
		getWorkplacePlacementDetails().add(workplacePlacementDetail);
		workplacePlacementDetail.setUnit(this);

		return workplacePlacementDetail;
	}

	public WorkplacePlacementDetail removeWorkplacePlacementDetail(WorkplacePlacementDetail workplacePlacementDetail) {
		getWorkplacePlacementDetails().remove(workplacePlacementDetail);
		workplacePlacementDetail.setUnit(null);

		return workplacePlacementDetail;
	}

}