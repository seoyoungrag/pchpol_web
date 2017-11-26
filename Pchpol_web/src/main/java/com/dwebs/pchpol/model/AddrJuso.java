package com.dwebs.pchpol.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ADDR_JUSO database table.
 * 
 */
@Entity
@Table(name="ADDR_JUSO")
@NamedQuery(name="AddrJuso.findAll", query="SELECT a FROM AddrJuso a")
public class AddrJuso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="JUSO_ID")
	private String jusoId;

	@Column(name="BEFORE_ROAD_ADDR")
	private String beforeRoadAddr;

	@Column(name="BUILDING_SUB_NUM")
	private int buildingSubNum;

	@Column(name="BULDING_MAIN_NUM")
	private int buldingMainNum;

	@Column(name="DEFAULT_AREA_NO")
	private int defaultAreaNo;

	@Column(name="IS_BASEMENT")
	private String isBasement;

	@Column(name="IS_DETAIL")
	private String isDetail;

	@Column(name="MODIFEID_REASON")
	private String modifeidReason;

	@Column(name="START_DT")
	private String startDt;

	//bi-directional one-to-one association to AddrEtc
	@OneToOne(mappedBy="addrJuso")
	private AddrEtc addrEtc;

	//bi-directional many-to-one association to AddrRoadCode
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="EYUP_SER", referencedColumnName="EYUP_SER"),
		@JoinColumn(name="ROAD_CD", referencedColumnName="ROAD_CD")
		})
	private AddrRoadCode addrRoadCode;

	public AddrJuso() {
	}

	public String getJusoId() {
		return this.jusoId;
	}

	public void setJusoId(String jusoId) {
		this.jusoId = jusoId;
	}

	public String getBeforeRoadAddr() {
		return this.beforeRoadAddr;
	}

	public void setBeforeRoadAddr(String beforeRoadAddr) {
		this.beforeRoadAddr = beforeRoadAddr;
	}

	public int getBuildingSubNum() {
		return this.buildingSubNum;
	}

	public void setBuildingSubNum(int buildingSubNum) {
		this.buildingSubNum = buildingSubNum;
	}

	public int getBuldingMainNum() {
		return this.buldingMainNum;
	}

	public void setBuldingMainNum(int buldingMainNum) {
		this.buldingMainNum = buldingMainNum;
	}

	public int getDefaultAreaNo() {
		return this.defaultAreaNo;
	}

	public void setDefaultAreaNo(int defaultAreaNo) {
		this.defaultAreaNo = defaultAreaNo;
	}

	public String getIsBasement() {
		return this.isBasement;
	}

	public void setIsBasement(String isBasement) {
		this.isBasement = isBasement;
	}

	public String getIsDetail() {
		return this.isDetail;
	}

	public void setIsDetail(String isDetail) {
		this.isDetail = isDetail;
	}

	public String getModifeidReason() {
		return this.modifeidReason;
	}

	public void setModifeidReason(String modifeidReason) {
		this.modifeidReason = modifeidReason;
	}

	public String getStartDt() {
		return this.startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public AddrEtc getAddrEtc() {
		return this.addrEtc;
	}

	public void setAddrEtc(AddrEtc addrEtc) {
		this.addrEtc = addrEtc;
	}

	public AddrRoadCode getAddrRoadCode() {
		return this.addrRoadCode;
	}

	public void setAddrRoadCode(AddrRoadCode addrRoadCode) {
		this.addrRoadCode = addrRoadCode;
	}

}