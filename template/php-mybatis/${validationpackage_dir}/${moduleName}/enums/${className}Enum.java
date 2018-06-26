<#include '/share.include'>
package ${validationpackage}.${moduleName}.enums;

/**
 * Created by linwen on 18-6-22.
 */
public enum ${className}Enum {
	DEFAULT("",""),
	<#list columns as column>
	<#if column_index==columns?size-1>
	${column.constantName}("${column.sqlName}","${column.columnNameFirstLower}"); // ${column.remarks!}
	<#else>
	${column.constantName}("${column.sqlName}","${column.columnNameFirstLower}"), // ${column.remarks!}
	</#if>
	</#list>
	String columnNameFirstLower;
	String sqlName;
	${className}Enum(String sqlName,String columnNameFirstLower){
		this.columnNameFirstLower=columnNameFirstLower;
		this.columnNameFirstLower=sqlName;
	}
	public String getColumnNameFirstLower() {
		return columnNameFirstLower;
	}

	public void setColumnNameFirstLower(String columnNameFirstLower) {
		this.columnNameFirstLower = columnNameFirstLower;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}
}