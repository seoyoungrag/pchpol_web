package com.dwebs.pchpol.model;

import java.io.Serializable;
import javax.persistence.*;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FACILITY_NO")
	private int facilityNo;

	@Column(name="FACILITY_ADDR_JUSO")
	private String facilityAddrJuso;

	@Column(name="FACILITY_ADDR_SEBU")
	private String facilityAddrSebu;

	@Column(name="FACILITY_ADDR_ZIP")
	private String facilityAddrZip;

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

	public Facility() {
	}

	public int getFacilityNo() {
		return this.facilityNo;
	}

	public void setFacilityNo(int facilityNo) {
		this.facilityNo = facilityNo;
	}

	public String getFacilityAddrJuso() {
		return this.facilityAddrJuso;
	}

	public void setFacilityAddrJuso(String facilityAddrJuso) {
		this.facilityAddrJuso = facilityAddrJuso;
	}

	public String getFacilityAddrSebu() {
		return this.facilityAddrSebu;
	}

	public void setFacilityAddrSebu(String facilityAddrSebu) {
		this.facilityAddrSebu = facilityAddrSebu;
	}

	public String getFacilityAddrZip() {
		return this.facilityAddrZip;
	}

	public void setFacilityAddrZip(String facilityAddrZip) {
		this.facilityAddrZip = facilityAddrZip;
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

}