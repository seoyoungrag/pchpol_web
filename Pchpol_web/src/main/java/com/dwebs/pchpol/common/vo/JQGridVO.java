/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : JQGridVO.java
 * 2. Package : com.dwebs.converter.convert.vo
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 8. 29. 오후 3:25:22
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 8. 29. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : JQGridVO.java
 * 3. Package  : com.dwebs.converter.convert.vo
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 8. 29. 오후 3:25:22
 * </PRE>
 */
public class JQGridVO < T extends Serializable > {

    private int page;

    private String total;

    private String records;

    private List<T> rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
