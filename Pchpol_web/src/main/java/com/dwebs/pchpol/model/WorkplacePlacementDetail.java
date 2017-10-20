package com.dwebs.pchpol.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the WORKPLACE_PLACEMENT_DETAIL database table.
 * 
 */
@Entity
@Table(name="PCHPOL.WORKPLACE_PLACEMENT_DETAIL")
@NamedQuery(name="WorkplacePlacementDetail.findAll", query="SELECT w FROM WorkplacePlacementDetail w")
public class WorkplacePlacementDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WORKPLACE_PLACEMENT_DETAIL_NO")
	private int workplacePlacementDetailNo;

	@Column(name="WORKPLACE_PLACEMENT_DETAIL_WORK_TIME")
	private String workplacePlacementDetailWorkTime;

	//bi-directional one-to-one association to WorkplacePlacement
	@ManyToOne
	@JoinColumn(name="WORKPLACE_PLACEMENT_NO")
	private WorkplacePlacement workplacePlacement;

	//bi-directional many-to-one association to Unit
	@ManyToOne
	@JoinColumn(name="UNIT_NO")
	private Unit unit;

	public WorkplacePlacementDetail() {
	}

	public int getWorkplacePlacementDetailNo() {
		return this.workplacePlacementDetailNo;
	}

	public void setWorkplacePlacementDetailNo(int workplacePlacementDetailNo) {
		this.workplacePlacementDetailNo = workplacePlacementDetailNo;
	}

	public String getWorkplacePlacementDetailWorkTime() {
		return this.workplacePlacementDetailWorkTime;
	}

	public void setWorkplacePlacementDetailWorkTime(String workplacePlacementDetailWorkTime) {
		this.workplacePlacementDetailWorkTime = workplacePlacementDetailWorkTime;
	}

	public WorkplacePlacement getWorkplacePlacement() {
		return this.workplacePlacement;
	}

	public void setWorkplacePlacement(WorkplacePlacement workplacePlacement) {
		this.workplacePlacement = workplacePlacement;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}