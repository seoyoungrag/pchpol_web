/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : CustoValid.java
 * 2. Package : com.dwebs.converter.convert.vo
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 5. 오후 1:17:17
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 5. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CustomNotBlank.java
 * 3. Package  : com.dwebs.converter.convert.vo
 * 4. Comment  : 별도 valid 처리를 위한 어노테이션
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 5. 오후 1:13:32
 * </PRE>
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValidField {

    String type() default "text";
    String name();

}	

