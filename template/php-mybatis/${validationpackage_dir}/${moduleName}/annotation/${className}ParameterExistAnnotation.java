<#include '/share.include'>
package ${validationpackage}.${moduleName}.annotation;

import ${validationpackage}.${moduleName}.Validator.${className}ExistValidator;
import ${validationpackage}.${moduleName}.enums.${className}Enum;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
<#assign columnX=''>
<#list columns as column><#if	column.possibleShortJavaType=='String'||column.possibleShortJavaType=='Integer'||column.possibleShortJavaType=='Long'>
<#if column.possibleShortJavaType=='String'&&!columnX?contains(column.possibleShortJavaType)>
<#assign columnX=columnX+'String-'>
<#elseif column.possibleShortJavaType=='Integer'&&!columnX?contains(column.possibleShortJavaType)>
<#assign columnX=columnX+'Integer-'>
<#elseif column.possibleShortJavaType=='Long'&&!columnX?contains(column.possibleShortJavaType)>
<#assign columnX=columnX+'Long-'>
</#if>
</#if>
</#list>
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy ={<#list columnX?split("-")as x><#if x!="">${className}ExistValidator.${className}ExistValidator${x}.class,</#if></#list>})
@Documented
public @interface ${className}ParameterExistAnnotation {

	String message() default "{${className}ParameterExistAnnotation}}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	${className}Enum value() ;

}