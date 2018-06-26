<#include '/share.include'>

package ${modepackage}.model.${moduleName}.vo;

import java.util.*;
import ${modepackage}.${moduleName}.bean.${className};
import ${modepackage}.BaseVo;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import java.math.BigDecimal;

/**
 * 用于前端页面数据传递
 * ${table.remarks!}
 * @author ${author}
 * @date ${.now?datetime}
 */
public class ${className}Vo extends BaseVo {

    private ${className} ${classNameLow};

    public ${className}Vo() {

    this.${classNameLow} = new ${className}();
    }

    public ${className}Vo(${className} ${classNameLow}) {

    this.${classNameLow} = ${classNameLow};
    }
    
    public ${className} get${className}() {

    return this.${classNameLow};
    }
    
    public void set${className}(${className} ${classNameLow}) {

    this.${classNameLow}=${classNameLow};
    }

<@generateJavaColumns/>
<#macro generateJavaColumns>
<#list columns as column>
        <#if column.isDateTimeColumn>

        </#if>
//        <#if column.pk>
//        public Integer getId() {
//            return this.${classNameLow}.get${column.columnName}();
//        }
//        public void setId(Integer id) {
//            this.${classNameLow}.set${column.columnName}(id);
//        }
//        </#if>
        
        <#if (column.possibleShortJavaType=='Integer')&&(column.columnNameFirstLower=='createTime'||column.columnNameFirstLower=='updateTime')>
    public void set${column.columnName}(Date value) {
        this.${classNameLow}.set${column.columnName}(value!=null ? (int) (value.getTime()/1000) : 0);
    }
    <#elseif (column.possibleShortJavaType=='String')&&(column.columnNameFirstLower=='createTime'||column.columnNameFirstLower=='updateTime')>
    public void set${column.columnName}(Date value) {
	this.${classNameLow}.set${column.columnName}(String.valueOf(value!=null ? (int) (value.getTime()/1000) : 0));
    }
    <#else>
        public void set${column.columnName}(${column.possibleShortJavaType    } value) {
    	this.${classNameLow}.set${column.columnName}(value);
        }
    </#if>
    <#if validateBack='true'&&column.columnNameFirstLower!='createTime'&&column.columnNameFirstLower!='updateTime'>
	${column.hibernateValidatorExprssion}</#if>
	<#if (column.possibleShortJavaType=='Integer'&&(column.columnNameFirstLower=='createTime'||column.columnNameFirstLower=='updateTime'))>
	 public Date get${column.columnName}() {
	     Integer time=this.${classNameLow}.get${column.columnName}();
	     if (time!=null&&time!=0)
		    return new Date(Long.valueOf(time)*1000);
		else
		    return null;
	    }
	 <#elseif (column.possibleShortJavaType=='String'&&(column.columnNameFirstLower=='createTime'||column.columnNameFirstLower=='updateTime'))>
	 public Date get${column.columnName}() {
	 String time=this.${classNameLow}.get${column.columnName}();
	     if (time!=null&&time!=""&&time!="0")
		    return new Date(Long.valueOf(time)*1000);
		else
		    return null;
	    }
	<#else>
    public ${column.possibleShortJavaType} get${column.columnName}() {
        return this.${classNameLow}.get${column.columnName}();
    }
    </#if>
    </#list>
    
</#macro>


}