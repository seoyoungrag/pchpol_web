/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : MybatisCustomDateHandler.java
 * 2. Package : com.dwebs.converter.common.handler
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 8. 23. 오후 2:15:41
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 8. 23. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : MybatisCustomDateHandler.java
 * 3. Package  : com.dwebs.converter.common.handler
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 8. 23. 오후 2:15:41
 * </PRE>
 */
public class MybatisCustomDateHandler extends BaseTypeHandler<Date>{

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
			throws SQLException {
		
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp sqlTimestamp = rs.getTimestamp(columnName);
		if (sqlTimestamp != null) {
			return new Date(sqlTimestamp.getTime());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, int)
	 */
	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement, int)
	 */
	@Override
	public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return null;
	}
	

}
