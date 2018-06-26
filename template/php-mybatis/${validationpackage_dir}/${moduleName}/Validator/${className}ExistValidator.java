<#include '/share.include'>
package ${validationpackage}.${moduleName}.Validator;
/**
 * created by xiaogangfan
 * on 16/10/25.
 */
import ${validationpackage}.${moduleName}.annotation.${className}ParameterExistAnnotation;
import ${validationpackage}.${moduleName}.enums.${className}Enum;
import org.springframework.beans.factory.annotation.Autowired;
import net.sf.json.JSONObject;
import ${modepackage}.${moduleName}.condition.${className}Condition;
import ${apppackage}.service.${moduleName}.${className}Service;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ${className}ExistValidator{
<#assign columnX=''>
<#list columns as column>
<#if
        column.possibleShortJavaType=='String'||column.possibleShortJavaType=='Integer'||column.possibleShortJavaType=='Long'>
<#if
        column.possibleShortJavaType=='String'&&!columnX?contains(column.possibleShortJavaType)>
<#assign columnX=columnX+'String-'>
<#elseif column.possibleShortJavaType=='Integer'&&!columnX?contains(column.possibleShortJavaType)>
<#assign columnX=columnX+'Integer-'>
<#elseif column.possibleShortJavaType=='Long'&&!columnX?contains(column.possibleShortJavaType)>
<#assign columnX=columnX+'Long-'>
</#if>
</#if>
</#list>
<#list columnX?split("-")as x>
<#if x!="">

    public static class ${className}ExistValidator${x} implements ConstraintValidator<${className}ParameterExistAnnotation,${x}>{
        @Autowired
        ${className}Service ${classNameLow}Service;

        private ${className}Enum ${classNameLow}Enum;

        public void initialize(${className}ParameterExistAnnotation constraintAnnotation){
            this.${classNameLow}Enum=constraintAnnotation.value();
        }

        public boolean isValid(${x} object,ConstraintValidatorContext constraintContext){
            if(object==null)
                return true;
    <#list columns as column>
    <#if
            column.possibleShortJavaType=='String'||column.possibleShortJavaType=='Integer'||column.possibleShortJavaType=='Long'>
    <#if
            column.possibleShortJavaType==x>
         if(${classNameLow}Enum==${className}Enum.${column.constantName}){ // ${column.remarks!}
                if(${classNameLow}Service.find${className}AppSingle(new ${className}Condition().set${column.columnName}(object))!=null){
                    return false;
                }
          }
    <#elseif column.possibleShortJavaType==x>
         if(${classNameLow}Enum==${className}Enum.${column.constantName}){ // ${column.remarks!}
                if(${classNameLow}Service.find${className}AppSingle(new ${className}Condition().set${column.columnName}(Integer.valueOf(object)))!=null){
                    return false;
                }
         }
    <#elseif column.possibleShortJavaType==x>
          if(${classNameLow}Enum==${className}Enum.${column.constantName}){ // ${column.remarks!}
                if(${classNameLow}Service.find${className}AppSingle(new ${className}Condition().set${column.columnName}(Long.valueOf(object)))!=null){
                    return false;
                }
          }
    </#if>
    </#if>
    </#list>
                return true;
       }
    }
    </#if>
    </#list>

}