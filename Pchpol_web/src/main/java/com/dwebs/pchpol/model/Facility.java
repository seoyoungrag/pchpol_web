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


/**
 * The persistent class for the FACILITY database table.
 * 
 */
@Entity
@Table(name = "PCHPOL.Facility")
@NamedQuery(name="Facility.findAll", query="SELECT f FROM Facility f")
public class Facility implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="FACILITY_NO")
	private int facilityNo;

	@Column(name="FACILITY_ADDRESS")
	private String facilityAddress;

	@Column(name="FACILITY_AREA")
	private String facilityArea;

	@Column(name="FACILITY_CHARGER_NAME")
	private String facilityChargerName;

	@Column(name="FACILITY_CHARGER_TEL")
	private String facilityChargerTel;

	@Column(name="FACILITY_FOOD_MAIN")
	private String facilityFoodMain;

	@Column(name="FACILITY_FOOD_NEARBY")
	private String facilityFoodNearby;

	@Column(name="FACILITY_FOOD_TEL")
	private String facilityFoodTel;

	@Column(name="FACILITY_NAME")
	private String facilityName;

	@Column(name="FACILITY_SHELTER_BATH")
	private String facilityShelterBath;

	@Column(name="FACILITY_SHELTER_LAUNDRY")
	private String facilityShelterLaundry;

	@Column(name="FACILITY_SHELTER_OTHER")
	private String facilityShelterOther;

	@Column(name="FACILITY_TYPE")
	private String facilityType;

	//bi-directional many-to-one association to TroopsFacilityPlacement
	@OneToMany(mappedBy="facility")
	private List<TroopsFacilityPlacement> troopsFacilityPlacements;

	public Facility() {
	}

	public int getFacilityNo() {
		return this.facilityNo;
	}

	public void setFacilityNo(int facilityNo) {
		this.facilityNo = facilityNo;
	}

	public String getFacilityAddress() {
		return this.facilityAddress;
	}

	public void setFacilityAddress(String facilityAddress) {
		this.facilityAddress = facilityAddress;
	}

	public String getFacilityArea() {
		return this.facilityArea;
	}

	public void setFacilityArea(String facilityArea) {
		this.facilityArea = facilityArea;
	}

	public String getFacilityChargerName() {
		return this.facilityChargerName;
	}

	public void setFacilityChargerName(String facilityChargerName) {
		this.facilityChargerName = facilityChargerName;
	}

	public String getFacilityChargerTel() {
		return this.facilityChargerTel;
	}

	public void setFacilityChargerTel(String facilityChargerTel) {
		this.facilityChargerTel = facilityChargerTel;
	}

	public String getFacilityFoodMain() {
		return this.facilityFoodMain;
	}

	public void setFacilityFoodMain(String facilityFoodMain) {
		this.facilityFoodMain = facilityFoodMain;
	}

	public String getFacilityFoodNearby() {
		return this.facilityFoodNearby;
	}

	public void setFacilityFoodNearby(String facilityFoodNearby) {
		this.facilityFoodNearby = facilityFoodNearby;
	}

	public String getFacilityFoodTel() {
		return this.facilityFoodTel;
	}

	public void setFacilityFoodTel(String facilityFoodTel) {
		this.facilityFoodTel = facilityFoodTel;
	}

	public String getFacilityName() {
		return this.facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFacilityShelterBath() {
		return this.facilityShelterBath;
	}

	public void setFacilityShelterBath(String facilityShelterBath) {
		this.facilityShelterBath = facilityShelterBath;
	}

	public String getFacilityShelterLaundry() {
		return this.facilityShelterLaundry;
	}

	public void setFacilityShelterLaundry(String facilityShelterLaundry) {
		this.facilityShelterLaundry = facilityShelterLaundry;
	}

	public String getFacilityShelterOther() {
		return this.facilityShelterOther;
	}

	public void setFacilityShelterOther(String facilityShelterOther) {
		this.facilityShelterOther = facilityShelterOther;
	}

	public String getFacilityType() {
		return this.facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public List<TroopsFacilityPlacement> getTroopsFacilityPlacements() {
		return this.troopsFacilityPlacements;
	}

	public void setTroopsFacilityPlacements(List<TroopsFacilityPlacement> troopsFacilityPlacements) {
		this.troopsFacilityPlacements = troopsFacilityPlacements;
	}

	public TroopsFacilityPlacement addTroopsFacilityPlacement(TroopsFacilityPlacement troopsFacilityPlacement) {
		getTroopsFacilityPlacements().add(troopsFacilityPlacement);
		troopsFacilityPlacement.setFacility(this);

		return troopsFacilityPlacement;
	}

	public TroopsFacilityPlacement removeTroopsFacilityPlacement(TroopsFacilityPlacement troopsFacilityPlacement) {
		getTroopsFacilityPlacements().remove(troopsFacilityPlacement);
		troopsFacilityPlacement.setFacility(null);

		return troopsFacilityPlacement;
	}

}