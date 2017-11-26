/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AddrDaoImpl.java
 * 2. Package : com.dwebs.pchpol.addr.dao
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 23. 오전 2:32:24
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 23. :            : 신규 개발.
 */
package com.dwebs.pchpol.addr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.addr.dao.AddrDao;
import com.dwebs.pchpol.addr.vo.Addr;
import com.dwebs.pchpol.common.vo.PagingVO;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AddrDaoImpl.java
 * 3. Package  : com.dwebs.pchpol.addr.dao
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 23. 오전 2:32:24
 * </PRE>
 */
@Component("addrDao")
public class AddrDaoImpl implements AddrDao {
	@Autowired
	private EntityManagerFactory emf;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwebs.pchpol.addr.dao.AddrDao#getAddrList(com.dwebs.pchpol.addr.vo.
	 * Addr)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Addr> getAddrList(PagingVO pagingVO, Addr addr) {
		EntityManager em = emf.createEntityManager();
		String qryStr = ""
				+ "SELECT * FROM ( SELECT inner_.*, rownumber() over(order by ORDER OF inner_) AS rownumber_ FROM ( "
				+ "SELECT DISTINCT " 
				+ "LPAD(A.DEFAULT_AREA_NO,5,0) AS zipCode "
				+ ", "
				+ "B.SIDO_NM||' '||B.SIGUNGU_NM||' '||DECODE(B.EYUP_TYPE,'0',''||B.EYUP_NM,'')||' '||"
				+ "B.ROAD_NM||DECODE(A.IS_BASEMENT,0,' ',1,' 지하',2,' 공중')||"
				+ "A.BULDING_MAIN_NUM||DECODE(A.BUILDING_SUB_NUM,0,'','-'||A.BUILDING_SUB_NUM)||"
				+ "CASE WHEN (B.EYUP_TYPE = '0' AND D.IS_COMMON = '0') THEN '' "
				+ "WHEN (B.EYUP_TYPE = '0' AND D.IS_COMMON ='1') THEN Decode(D.SIGUNGU_BUILDING, '', '', CONCAT(CONCAT(' (', D.SIGUNGU_BUILDING), ')') ) "
				+ "WHEN (B.EYUP_TYPE = '1' AND D.IS_COMMON ='0') THEN CONCAT(CONCAT(' (',B.EYUP_NM),')') "
				+ "WHEN (B.EYUP_TYPE = '1' AND D.IS_COMMON ='1') THEN CONCAT(CONCAT(CONCAT(' (',B.EYUP_NM) , Decode(D.SIGUNGU_BUILDING, '', '', CONCAT(',', D.SIGUNGU_BUILDING) )) ,')') "
				+ "END AS address " 
				+ "FROM " 
				+ "PCHPOL.ADDR_ETC D, PCHPOL.ADDR_JUSO A, PCHPOL.ADDR_ROAD_CODE B "
				+ "WHERE B.ROAD_CD = A.ROAD_CD " 
				+ "AND B.EYUP_SER = A.EYUP_SER " 
				+ "AND A.JUSO_ID = D.ETC_ID ";
		if(addr.getRoadNm()!=null&&!addr.getRoadNm().equals("")){
			qryStr += "AND B.ROAD_NM LIKE '"+addr.getRoadNm()+"%' ";
		}else{
			return new ArrayList<Addr>();
		}
		if(addr.getBuildingMainNm()!=null&&!addr.getBuildingMainNm().equals("")){
			qryStr += "AND A.BULDING_MAIN_NUM LIKE '"+addr.getBuildingMainNm()+"%' ";
		}else{
			return new ArrayList<Addr>();
		}
		qryStr += "FETCH "
				+ "FIRST "+pagingVO.getPage()*10+" ROWS ONLY"
				+ ") AS inner_ ) as inner2_ "
				+ "WHERE rownumber_ > "+(pagingVO.getPage()-1)*pagingVO.getRows()+" "
				+ "ORDER BY rownumber_";
		Query q = em.createNativeQuery(qryStr);
		List<Addr> rslist = q.getResultList();
		return rslist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwebs.pchpol.addr.dao.AddrDao#getTotCntAddr(com.dwebs.pchpol.addr.vo.
	 * Addr)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int getTotCntAddr(PagingVO pagingVO, Addr addr) {
		EntityManager em = emf.createEntityManager();
		String qryStr = ""
				+ "SELECT COUNT(*) CNT FROM ( " 
				+ "SELECT DISTINCT " 
				+ "LPAD(A.DEFAULT_AREA_NO,5,0) AS zipCode "
				+ ", "
				+ "B.SIDO_NM||' '||B.SIGUNGU_NM||' '||DECODE(B.EYUP_TYPE,'0',''||B.EYUP_NM,'')||' '||"
				+ "B.ROAD_NM||DECODE(A.IS_BASEMENT,0,' ',1,' 지하',2,' 공중')||"
				+ "A.BULDING_MAIN_NUM||DECODE(A.BUILDING_SUB_NUM,0,'','-'||A.BUILDING_SUB_NUM)||"
				+ "CASE WHEN (B.EYUP_TYPE = '0' AND D.IS_COMMON = '0') THEN '' "
				+ "WHEN (B.EYUP_TYPE = '0' AND D.IS_COMMON ='1') THEN Decode(D.SIGUNGU_BUILDING, '', '', CONCAT(CONCAT(' (', D.SIGUNGU_BUILDING), ')') ) "
				+ "WHEN (B.EYUP_TYPE = '1' AND D.IS_COMMON ='0') THEN CONCAT(CONCAT(' (',B.EYUP_NM),')') "
				+ "WHEN (B.EYUP_TYPE = '1' AND D.IS_COMMON ='1') THEN CONCAT(CONCAT(CONCAT(' (',B.EYUP_NM) , Decode(D.SIGUNGU_BUILDING, '', '', CONCAT(',', D.SIGUNGU_BUILDING) )) ,')') "
				+ "END AS address " 
				+ "FROM " 
				+ "PCHPOL.ADDR_ETC D, PCHPOL.ADDR_JUSO A, PCHPOL.ADDR_ROAD_CODE B "
				+ "WHERE B.ROAD_CD = A.ROAD_CD " 
				+ "AND B.EYUP_SER = A.EYUP_SER " 
				+ "AND A.JUSO_ID = D.ETC_ID ";
		if(addr.getRoadNm()!=null&&!addr.getRoadNm().equals("")){
			qryStr += "AND B.ROAD_NM LIKE '"+addr.getRoadNm()+"%' ";
		}else{
			return 0;
		}
		if(addr.getBuildingMainNm()!=null&&!addr.getBuildingMainNm().equals("")){
			qryStr += "AND A.BULDING_MAIN_NUM LIKE '"+addr.getBuildingMainNm()+"%' ";
		}else{
			return 0;
		}
		qryStr += " )";
		Query q = em.createNativeQuery(qryStr);
		List<Integer> rslist = q.getResultList();
		int result = 0;
		if(rslist.size()>0){
			result = rslist.get(0);
		}
		return result;
	}

}
