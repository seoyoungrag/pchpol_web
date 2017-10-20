package com.dwebs.pchpol.model;

import java.io.Serializable;
import java.util.Date;
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


/**
 * The persistent class for the WORKPLACE_PLACEMENT database table.
 * 
 */
@Entity
@Table(name="PCHPOL.WORKPLACE_PLACEMENT")
@NamedQuery(name="WorkplacePlacement.findAll", query="SELECT w FROM WorkplacePlacement w")
public class WorkplacePlacement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WORKPLACE_PLACEMENT_NO")
	private int workplacePlacementNo;

	@Column(name="WORKPLACE_PLACEMENT_WORK_DT")
	private Date workplacePlacementWorkDt;

	//bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(name="CODE_TROOPS")
	private Code code1;

	//bi-directional many-to-one association to Code
	@ManyToOne
	@JoinColumn(name="CODE_WORKPLACE")
	private Code code2;

	//bi-directional one-to-one association to WorkplacePlacementDetail
	@OneToMany(mappedBy="workplacePlacement", fetch=FetchType.EAGER)
	private List<WorkplacePlacementDetail> workplacePlacementDetail;

	public WorkplacePlacement() {
	}

	public int getWorkplacePlacementNo() {
		return this.workplacePlacementNo;
	}

	public void setWorkplacePlacementNo(int workplacePlacementNo) {
		this.workplacePlacementNo = workplacePlacementNo;
	}

	public Date getWorkplacePlacementWorkDt() {
		return workplacePlacementWorkDt;
	}

	public void setWorkplacePlacementWorkDt(Date workplacePlacementWorkDt) {
		this.workplacePlacementWorkDt = workplacePlacementWorkDt;
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

	public List<WorkplacePlacementDetail> getWorkplacePlacementDetail() {
		return workplacePlacementDetail;
	}

	public void setWorkplacePlacementDetail(List<WorkplacePlacementDetail> workplacePlacementDetail) {
		this.workplacePlacementDetail = workplacePlacementDetail;
	}


}