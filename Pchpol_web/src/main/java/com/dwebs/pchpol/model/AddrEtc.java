package com.dwebs.pchpol.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ADDR_ETC database table.
 * 
 */
@Entity
@Table(name="PCHPOL.ADDR_ETC")
@NamedQuery(name="AddrEtc.findAll", query="SELECT a FROM AddrEtc a")
public class AddrEtc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ETC_ID")
	private String etcId;

	@Column(name="BUILDING_DOC_NM")
	private String buildingDocNm;

	@Column(name="DELEVERY_NM")
	private String deleveryNm;

	@Column(name="HAENGJUNG_CD")
	private String haengjungCd;

	@Column(name="HAENGJUNG_NM")
	private String haengjungNm;

	@Column(name="IS_COMMON")
	private String isCommon;

	@Column(name="SIGUNGU_BUILDING")
	private String sigunguBuilding;

	@Column(name="ZIP_CD")
	private String zipCd;

	@Column(name="ZIP_CD_SER")
	private String zipCdSer;

	//bi-directional one-to-one association to AddrJuso
	@OneToOne
	@JoinColumn(name="ETC_ID")
	private AddrJuso addrJuso;

	public AddrEtc() {
	}

	public String getEtcId() {
		return this.etcId;
	}

	public void setEtcId(String etcId) {
		this.etcId = etcId;
	}

	public String getBuildingDocNm() {
		return this.buildingDocNm;
	}

	public void setBuildingDocNm(String buildingDocNm) {
		this.buildingDocNm = buildingDocNm;
	}

	public String getDeleveryNm() {
		return this.deleveryNm;
	}

	public void setDeleveryNm(String deleveryNm) {
		this.deleveryNm = deleveryNm;
	}

	public String getHaengjungCd() {
		return this.haengjungCd;
	}

	public void setHaengjungCd(String haengjungCd) {
		this.haengjungCd = haengjungCd;
	}

	public String getHaengjungNm() {
		return this.haengjungNm;
	}

	public void setHaengjungNm(String haengjungNm) {
		this.haengjungNm = haengjungNm;
	}

	public String getIsCommon() {
		return this.isCommon;
	}

	public void setIsCommon(String isCommon) {
		this.isCommon = isCommon;
	}

	public String getSigunguBuilding() {
		return this.sigunguBuilding;
	}

	public void setSigunguBuilding(String sigunguBuilding) {
		this.sigunguBuilding = sigunguBuilding;
	}

	public String getZipCd() {
		return this.zipCd;
	}

	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}

	public String getZipCdSer() {
		return this.zipCdSer;
	}

	public void setZipCdSer(String zipCdSer) {
		this.zipCdSer = zipCdSer;
	}

	public AddrJuso getAddrJuso() {
		return this.addrJuso;
	}

	public void setAddrJuso(AddrJuso addrJuso) {
		this.addrJuso = addrJuso;
	}

}