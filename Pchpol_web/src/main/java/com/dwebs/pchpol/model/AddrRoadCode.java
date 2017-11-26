package com.dwebs.pchpol.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ADDR_ROAD_CODE database table.
 * 
 */
@Entity
@Table(name="ADDR_ROAD_CODE")
@NamedQuery(name="AddrRoadCode.findAll", query="SELECT a FROM AddrRoadCode a")
public class AddrRoadCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AddrRoadCodePK id;

	@Column(name="END_DT")
	private String endDt;

	@Column(name="EYUP_CD")
	private String eyupCd;

	@Column(name="EYUP_NM")
	private String eyupNm;

	@Column(name="EYUP_NM_ENG")
	private String eyupNmEng;

	@Column(name="EYUP_TYPE")
	private String eyupType;

	@Column(name="IS_USE")
	private String isUse;

	@Column(name="MODIFEID_REASON")
	private String modifeidReason;

	@Column(name="MODIFIED_HIS")
	private String modifiedHis;

	@Column(name="ROAD_NM")
	private String roadNm;

	@Column(name="ROAD_NM_ENG")
	private String roadNmEng;

	@Column(name="SIDO_NM")
	private String sidoNm;

	@Column(name="SIDO_NM_ENG")
	private String sidoNmEng;

	@Column(name="SIGUNGU_NM")
	private String sigunguNm;

	@Column(name="SIGUNGU_NM_ENG")
	private String sigunguNmEng;

	@Column(name="START_DT")
	private String startDt;

	//bi-directional many-to-one association to AddrJuso
	@OneToMany(mappedBy="addrRoadCode")
	private List<AddrJuso> addrJusos;

	public AddrRoadCode() {
	}

	public AddrRoadCodePK getId() {
		return this.id;
	}

	public void setId(AddrRoadCodePK id) {
		this.id = id;
	}

	public String getEndDt() {
		return this.endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getEyupCd() {
		return this.eyupCd;
	}

	public void setEyupCd(String eyupCd) {
		this.eyupCd = eyupCd;
	}

	public String getEyupNm() {
		return this.eyupNm;
	}

	public void setEyupNm(String eyupNm) {
		this.eyupNm = eyupNm;
	}

	public String getEyupNmEng() {
		return this.eyupNmEng;
	}

	public void setEyupNmEng(String eyupNmEng) {
		this.eyupNmEng = eyupNmEng;
	}

	public String getEyupType() {
		return this.eyupType;
	}

	public void setEyupType(String eyupType) {
		this.eyupType = eyupType;
	}

	public String getIsUse() {
		return this.isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getModifeidReason() {
		return this.modifeidReason;
	}

	public void setModifeidReason(String modifeidReason) {
		this.modifeidReason = modifeidReason;
	}

	public String getModifiedHis() {
		return this.modifiedHis;
	}

	public void setModifiedHis(String modifiedHis) {
		this.modifiedHis = modifiedHis;
	}

	public String getRoadNm() {
		return this.roadNm;
	}

	public void setRoadNm(String roadNm) {
		this.roadNm = roadNm;
	}

	public String getRoadNmEng() {
		return this.roadNmEng;
	}

	public void setRoadNmEng(String roadNmEng) {
		this.roadNmEng = roadNmEng;
	}

	public String getSidoNm() {
		return this.sidoNm;
	}

	public void setSidoNm(String sidoNm) {
		this.sidoNm = sidoNm;
	}

	public String getSidoNmEng() {
		return this.sidoNmEng;
	}

	public void setSidoNmEng(String sidoNmEng) {
		this.sidoNmEng = sidoNmEng;
	}

	public String getSigunguNm() {
		return this.sigunguNm;
	}

	public void setSigunguNm(String sigunguNm) {
		this.sigunguNm = sigunguNm;
	}

	public String getSigunguNmEng() {
		return this.sigunguNmEng;
	}

	public void setSigunguNmEng(String sigunguNmEng) {
		this.sigunguNmEng = sigunguNmEng;
	}

	public String getStartDt() {
		return this.startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public List<AddrJuso> getAddrJusos() {
		return this.addrJusos;
	}

	public void setAddrJusos(List<AddrJuso> addrJusos) {
		this.addrJusos = addrJusos;
	}

	public AddrJuso addAddrJuso(AddrJuso addrJuso) {
		getAddrJusos().add(addrJuso);
		addrJuso.setAddrRoadCode(this);

		return addrJuso;
	}

	public AddrJuso removeAddrJuso(AddrJuso addrJuso) {
		getAddrJusos().remove(addrJuso);
		addrJuso.setAddrRoadCode(null);

		return addrJuso;
	}

}