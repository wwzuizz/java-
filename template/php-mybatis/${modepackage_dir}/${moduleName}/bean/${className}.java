<#include '/share.include'>
package ${modepackage}.model.${moduleName}.bean;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import ${modepackage}.BaseBean;
import java.util.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import java.math.BigDecimal;

/**
 * ${table.remarks!}
 * @author ${author}
 * @date ${.now?datetime}
 */
public class ${className} extends BaseBean{
	<#list columns as column>
	private ${column.possibleShortJavaType    } ${column.columnNameFirstLower};  // ${column.remarks!}
	</#list>

<@generateJavaColumns/>

}

<#macro generateJavaColumns>
<#list columns as column>
		<#if column.isDateTimeColumn>

		</#if>
	public void set${column.columnName}(${column.possibleShortJavaType  } value) {
		this.${column.columnNameFirstLower} = value;
	}
	
	public ${column.possibleShortJavaType  } get${column.columnName}() {
		return this.${column.columnNameFirstLower};
	}
	</#list>
	
</#macro>

