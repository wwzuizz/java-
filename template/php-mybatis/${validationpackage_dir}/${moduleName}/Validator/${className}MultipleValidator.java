<#include '/share.include'>
package ${validationpackage}.${moduleName}.Validator;
/**
 * created by xiaogangfan
 * on 16/10/25.
 */
import ${validationpackage}.${moduleName}.annotation.${className}MultipleParameterAnnotation;
import ${validationpackage}.${moduleName}.enums.${className}Enum;
import org.springframework.beans.factory.annotation.Autowired;
import net.sf.json.JSONObject;
import ${modepackage}.${moduleName}.condition.${className}Condition;
import ${apppackage}.service.${moduleName}.${className}Service;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
public  class ${className}MultipleValidator implements ConstraintValidator<${className}MultipleParameterAnnotation,Object>{
     @Autowired
     ${className}Service ${classNameLow}Service;

    private ${className}Enum[] ${classNameLow}Enum;
    private ${className}Enum[] ${classNameLow}EnumValidator;

    public void initialize(${className}MultipleParameterAnnotation constraintAnnotation){
        this.${classNameLow}Enum=constraintAnnotation.value();
        this.${classNameLow}EnumValidator=constraintAnnotation.valueValidator();
    }

    public boolean isValid(Object object,ConstraintValidatorContext constraintContext){
        try{
        if(object==null)
        return true;
        for(int i=0;i<${classNameLow}Enum.length;i++){
        if(${classNameLow}Enum[i]!=${className}Enum.DEFAULT&&${classNameLow}EnumValidator[i]!=${className}Enum.DEFAULT){

<#list columns as column>
<#if
        column.possibleShortJavaType=='String'||column.possibleShortJavaType=='Integer'||column.possibleShortJavaType=='Long'>
<#if
        column.possibleShortJavaType=='String'>
        if(${classNameLow}Enum[i]==${className}Enum.${column.constantName}){ // ${column.remarks!}
        String beanProperty=BeanUtils.getProperty(object,"${column.columnNameFirstLower}");
        if(${classNameLow}Service.find${className}AppSingle(new ${className}Condition().set${column.columnName}(beanProperty))!=null&&
        ${classNameLow}Service.find${className}AppSingle(new ${className}Condition().setQueryWhere(" and ${column.sqlName} !="+beanProperty+" and "+${classNameLow}EnumValidator[i].getSqlName()+"="+BeanUtils.getProperty(object,${classNameLow}EnumValidator[i].getColumnNameFirstLower())))!=null
        ){
        return false;
        }
        }
</#if>
<#if
        column.possibleShortJavaType=='Integer'>
        if(${classNameLow}Enum[i]==${className}Enum.${column.constantName}){ // ${column.remarks!}
        String beanProperty=BeanUtils.getProperty(object,"${column.columnNameFirstLower}");
        if(${classNameLow}Service.find${className}AppSingle(new ${className}Condition().set${column.columnName}(Integer.valueOf(beanProperty)))!=null&&
        ${classNameLow}Service.find${className}AppSingle(new ${className}Condition().setQueryWhere(" and ${column.sqlName} !="+beanProperty+" and "+${classNameLow}EnumValidator[i].getSqlName()+"="+BeanUtils.getProperty(object,${classNameLow}EnumValidator[i].getColumnNameFirstLower())))!=null
        ){
        return false;
        }
        }
</#if>
<#if
        column.possibleShortJavaType=='Long'>
        if(${classNameLow}Enum[i]==${className}Enum.${column.constantName}){ // ${column.remarks!}
        String beanProperty=BeanUtils.getProperty(object,"${column.columnNameFirstLower}");
        if(${classNameLow}Service.find${className}AppSingle(new ${className}Condition().set${column.columnName}(Long.valueOf(beanProperty)))!=null&&
        ${classNameLow}Service.find${className}AppSingle(new ${className}Condition().setQueryWhere(" and ${column.sqlName} !="+beanProperty+" and "+${classNameLow}EnumValidator[i].getSqlName()+"="+BeanUtils.getProperty(object,${classNameLow}EnumValidator[i].getColumnNameFirstLower())))!=null
        ){
        return false;
        }
        }
</#if>
</#if>
</#list>

        }
        }

        } catch (IllegalAccessException e) {
        e.printStackTrace();
        } catch (InvocationTargetException e) {
        e.printStackTrace();
        } catch (NoSuchMethodException e) {
        e.printStackTrace();
        }
        return true;
    }
}