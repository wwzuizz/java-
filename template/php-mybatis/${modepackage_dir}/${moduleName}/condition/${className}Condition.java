<#include '/share.include'>
package ${managepackage}.model.${moduleName}.condition;

import java.util.*;

import ${managepackage}.model.BaseCondition;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;
import com.bei_en.common.validation.GroupSelect;
import ${validationpackage}.${moduleName}.annotation.${className}ParameterExistAnnotation;
import ${validationpackage}.${moduleName}.enums.${className}Enum;
import ${validationpackage}.${moduleName}.annotation.${className}ParameterNotExistAnnotation;
import java.math.BigDecimal;
import com.bei_en.common.validation.GroupDelete;
import com.bei_en.common.validation.GroupEdit;
import com.bei_en.common.validation.GroupInsert;
import com.bei_en.common.validation.GroupSelect;
/**
 * 用于list页面的查询条件
 * ${table.remarks!}
 * @author ${author}
 * @date ${.now?datetime}
 */
public class ${className}Condition extends BaseCondition {

    private Date createTime1;   //生成时间起
    private Date createTime2;   //止
	private String queryWhere;
	private String returnColumn;
	public String getReturnColumn() {
		return returnColumn;
	}

	public ${className}Condition setReturnColumn(String returnColumn) {
		this.returnColumn = returnColumn;
		return this;
	}
	public String getQueryWhere() {
		return queryWhere;
	}

	public ${className}Condition setQueryWhere(String queryWhere) {
		this.queryWhere = queryWhere;
			return this;
	}
	<#list columns as column>
<#if
		column.possibleShortJavaType=='String' >
	@NotEmpty(groups = {GroupInsert.class,GroupDelete.class,GroupEdit.class,GroupSelect.class},message = "${column.remarks!}不能为空")
<#else>
	@NotNull(groups = {GroupInsert.class,GroupDelete.class,GroupEdit.class,GroupSelect.class},message = "${column.remarks!}不能为空")
</#if>
<#if
		column.possibleShortJavaType=='String'||column.possibleShortJavaType=='Integer'|| column.possibleShortJavaType=='Long'>
	@${className}ParameterExistAnnotation(value =${className}Enum.${column.constantName}, groups = {GroupInsert.class,GroupDelete.class,GroupEdit.class,GroupSelect.class},message = "${column.remarks!}已经存在")
	@${className}ParameterNotExistAnnotation(value =${className}Enum.${column.constantName}, groups = {GroupInsert.class,GroupDelete.class,GroupEdit.class,GroupSelect.class},message = "${column.remarks!}不存在")
</#if>
	private ${column.possibleShortJavaType    } ${column.columnNameFirstLower};  // ${column.remarks!}
	</#list>

<@generateJavaColumns/>



<#macro generateJavaColumns>
<#list columns as column>
		<#if column.isDateTimeColumn>

		</#if>
	public ${className}Condition set${column.columnName}(${column.possibleShortJavaType  } value) {
		this.${column.columnNameFirstLower} = value;
		return this;
	}
	
	public ${column.possibleShortJavaType  } get${column.columnName}() {
		return this.${column.columnNameFirstLower};
	}
	</#list>
	
</#macro>
    public Date getCreateTime1() {

	return createTime1;
    }

    public void setCreateTime1(Date createTime1) {

	this.createTime1 = createTime1;
    }

    public Date getCreateTime2() {

	return createTime2;
    }

    public void setCreateTime2(Date createTime2) {

	this.createTime2 = createTime2;
    }



}