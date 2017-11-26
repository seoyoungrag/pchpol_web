package com.dwebs.pchpol.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ADDR_ROAD_CODE database table.
 * 
 */
@Embeddable
public class AddrRoadCodePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ROAD_CD")
	private String roadCd;

	@Column(name="EYUP_SER")
	private String eyupSer;

	public AddrRoadCodePK() {
	}
	public String getRoadCd() {
		return this.roadCd;
	}
	public void setRoadCd(String roadCd) {
		this.roadCd = roadCd;
	}
	public String getEyupSer() {
		return this.eyupSer;
	}
	public void setEyupSer(String eyupSer) {
		this.eyupSer = eyupSer;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AddrRoadCodePK)) {
			return false;
		}
		AddrRoadCodePK castOther = (AddrRoadCodePK)other;
		return 
			this.roadCd.equals(castOther.roadCd)
			&& this.eyupSer.equals(castOther.eyupSer);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roadCd.hashCode();
		hash = hash * prime + this.eyupSer.hashCode();
		
		return hash;
	}
}