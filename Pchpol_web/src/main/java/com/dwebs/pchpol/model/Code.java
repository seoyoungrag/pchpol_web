package com.dwebs.pchpol.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the CODE database table.
 * 
 */
@Entity
@Table(name = "PCHPOL.CODE")
@NamedQuery(name="Code.findAll", query="SELECT c FROM Code c")
public class Code implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CODE_NO")
	private int codeNo;

	@Column(name="CODE_1DEPTH")
	private String code1depth;

	@Column(name="CODE_2DEPTH")
	private String code2depth;

	@Column(name="CODE_3DEPTH")
	private String code3depth;

	@Column(name="CODE_4DEPTH")
	private String code4depth;

	@Column(name="CODE_5DEPTH")
	private String code5depth;

	@Column(name="CODE_CATEGORY")
	private String codeCategory;

	@Column(name="CODE_ORDER_NO")
	private int codeOrderNo;
	
	@JsonIgnore
	//bi-directional many-to-one association to Admin
	@OneToMany(mappedBy="code")
	private List<Admin> admins;
	
	@JsonIgnore
	//bi-directional many-to-one association to TroopsFacilityPlacement
	@OneToMany(mappedBy="code")
	private List<TroopsFacilityPlacement> troopsFacilityPlacements;

	@JsonIgnore
	//bi-directional many-to-one association to TroopsPlacement
	@OneToMany(mappedBy="code1")
	private List<TroopsPlacement> troopsPlacements1;

	@JsonIgnore
	//bi-directional many-to-one association to TroopsPlacement
	@OneToMany(mappedBy="code2")
	private List<TroopsPlacement> troopsPlacements2;

	@JsonIgnore
	//bi-directional many-to-one association to Unit
	@OneToMany(mappedBy="code1")
	private List<Unit> units1;

	@JsonIgnore
	//bi-directional many-to-one association to Unit
	@OneToMany(mappedBy="code2")
	private List<Unit> units2;

	@JsonIgnore
	//bi-directional many-to-one association to Unit
	@OneToMany(mappedBy="code3")
	private List<Unit> units3;

	@JsonIgnore
	//bi-directional many-to-one association to Unit
	@OneToMany(mappedBy="code4")
	private List<Unit> units4;
	
	@JsonIgnore
	//bi-directional many-to-one association to Unit
	@OneToMany(mappedBy="code5")
	private List<Unit> units5;
	
	@JsonIgnore
	//bi-directional many-to-one association to WorkplacePlacement
	@OneToMany(mappedBy="code1")
	private List<WorkplacePlacement> workplacePlacements1;

	@JsonIgnore
	//bi-directional many-to-one association to WorkplacePlacement
	@OneToMany(mappedBy="code2")
	private List<WorkplacePlacement> workplacePlacements2;

	public Code() {
	}

	public int getCodeNo() {
		return this.codeNo;
	}

	public void setCodeNo(int codeNo) {
		this.codeNo = codeNo;
	}

	public String getCode1depth() {
		return this.code1depth;
	}

	public void setCode1depth(String code1depth) {
		this.code1depth = code1depth;
	}

	public String getCode2depth() {
		return this.code2depth;
	}

	public void setCode2depth(String code2depth) {
		this.code2depth = code2depth;
	}

	public String getCode3depth() {
		return this.code3depth;
	}

	public void setCode3depth(String code3depth) {
		this.code3depth = code3depth;
	}

	public String getCode4depth() {
		return this.code4depth;
	}

	public void setCode4depth(String code4depth) {
		this.code4depth = code4depth;
	}

	public String getCode5depth() {
		return this.code5depth;
	}

	public void setCode5depth(String code5depth) {
		this.code5depth = code5depth;
	}

	public String getCodeCategory() {
		return this.codeCategory;
	}

	public void setCodeCategory(String codeCategory) {
		this.codeCategory = codeCategory;
	}

	public int getCodeOrderNo() {
		return this.codeOrderNo;
	}

	public void setCodeOrderNo(int codeOrderNo) {
		this.codeOrderNo = codeOrderNo;
	}

	public List<Admin> getAdmins() {
		return this.admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public Admin addAdmin(Admin admin) {
		getAdmins().add(admin);
		admin.setCode(this);

		return admin;
	}

	public Admin removeAdmin(Admin admin) {
		getAdmins().remove(admin);
		admin.setCode(null);

		return admin;
	}

	public List<TroopsFacilityPlacement> getTroopsFacilityPlacements() {
		return this.troopsFacilityPlacements;
	}

	public void setTroopsFacilityPlacements(List<TroopsFacilityPlacement> troopsFacilityPlacements) {
		this.troopsFacilityPlacements = troopsFacilityPlacements;
	}

	public TroopsFacilityPlacement addTroopsFacilityPlacement(TroopsFacilityPlacement troopsFacilityPlacement) {
		getTroopsFacilityPlacements().add(troopsFacilityPlacement);
		troopsFacilityPlacement.setCode(this);

		return troopsFacilityPlacement;
	}

	public TroopsFacilityPlacement removeTroopsFacilityPlacement(TroopsFacilityPlacement troopsFacilityPlacement) {
		getTroopsFacilityPlacements().remove(troopsFacilityPlacement);
		troopsFacilityPlacement.setCode(null);

		return troopsFacilityPlacement;
	}

	public List<TroopsPlacement> getTroopsPlacements1() {
		return this.troopsPlacements1;
	}

	public void setTroopsPlacements1(List<TroopsPlacement> troopsPlacements1) {
		this.troopsPlacements1 = troopsPlacements1;
	}

	public TroopsPlacement addTroopsPlacements1(TroopsPlacement troopsPlacements1) {
		getTroopsPlacements1().add(troopsPlacements1);
		troopsPlacements1.setCode1(this);

		return troopsPlacements1;
	}

	public TroopsPlacement removeTroopsPlacements1(TroopsPlacement troopsPlacements1) {
		getTroopsPlacements1().remove(troopsPlacements1);
		troopsPlacements1.setCode1(null);

		return troopsPlacements1;
	}

	public List<TroopsPlacement> getTroopsPlacements2() {
		return this.troopsPlacements2;
	}

	public void setTroopsPlacements2(List<TroopsPlacement> troopsPlacements2) {
		this.troopsPlacements2 = troopsPlacements2;
	}

	public TroopsPlacement addTroopsPlacements2(TroopsPlacement troopsPlacements2) {
		getTroopsPlacements2().add(troopsPlacements2);
		troopsPlacements2.setCode2(this);

		return troopsPlacements2;
	}

	public TroopsPlacement removeTroopsPlacements2(TroopsPlacement troopsPlacements2) {
		getTroopsPlacements2().remove(troopsPlacements2);
		troopsPlacements2.setCode2(null);

		return troopsPlacements2;
	}

	public List<Unit> getUnits1() {
		return this.units1;
	}

	public void setUnits1(List<Unit> units1) {
		this.units1 = units1;
	}

	public Unit addUnits1(Unit units1) {
		getUnits1().add(units1);
		units1.setCode1(this);

		return units1;
	}

	public Unit removeUnits1(Unit units1) {
		getUnits1().remove(units1);
		units1.setCode1(null);

		return units1;
	}

	public List<Unit> getUnits2() {
		return this.units2;
	}

	public void setUnits2(List<Unit> units2) {
		this.units2 = units2;
	}

	public Unit addUnits2(Unit units2) {
		getUnits2().add(units2);
		units2.setCode2(this);

		return units2;
	}

	public Unit removeUnits2(Unit units2) {
		getUnits2().remove(units2);
		units2.setCode2(null);

		return units2;
	}

	public List<Unit> getUnits3() {
		return this.units3;
	}

	public void setUnits3(List<Unit> units3) {
		this.units3 = units3;
	}

	public Unit addUnits3(Unit units3) {
		getUnits3().add(units3);
		units3.setCode3(this);

		return units3;
	}

	public Unit removeUnits3(Unit units3) {
		getUnits3().remove(units3);
		units3.setCode3(null);

		return units3;
	}

	public List<Unit> getUnits4() {
		return this.units4;
	}

	public void setUnits4(List<Unit> units4) {
		this.units4 = units4;
	}

	public Unit addUnits4(Unit units4) {
		getUnits4().add(units4);
		units4.setCode4(this);

		return units4;
	}

	public Unit removeUnits4(Unit units4) {
		getUnits4().remove(units4);
		units4.setCode4(null);

		return units4;
	}
	
	public List<WorkplacePlacement> getWorkplacePlacements1() {
		return this.workplacePlacements1;
	}

	public void setWorkplacePlacements1(List<WorkplacePlacement> workplacePlacements1) {
		this.workplacePlacements1 = workplacePlacements1;
	}

	public WorkplacePlacement addWorkplacePlacements1(WorkplacePlacement workplacePlacements1) {
		getWorkplacePlacements1().add(workplacePlacements1);
		workplacePlacements1.setCode1(this);

		return workplacePlacements1;
	}

	public WorkplacePlacement removeWorkplacePlacements1(WorkplacePlacement workplacePlacements1) {
		getWorkplacePlacements1().remove(workplacePlacements1);
		workplacePlacements1.setCode1(null);

		return workplacePlacements1;
	}

	public List<WorkplacePlacement> getWorkplacePlacements2() {
		return this.workplacePlacements2;
	}

	public void setWorkplacePlacements2(List<WorkplacePlacement> workplacePlacements2) {
		this.workplacePlacements2 = workplacePlacements2;
	}

	public WorkplacePlacement addWorkplacePlacements2(WorkplacePlacement workplacePlacements2) {
		getWorkplacePlacements2().add(workplacePlacements2);
		workplacePlacements2.setCode2(this);

		return workplacePlacements2;
	}

	public WorkplacePlacement removeWorkplacePlacements2(WorkplacePlacement workplacePlacements2) {
		getWorkplacePlacements2().remove(workplacePlacements2);
		workplacePlacements2.setCode2(null);

		return workplacePlacements2;
	}

}