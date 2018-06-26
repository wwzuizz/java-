<#include '/share.include'>
package ${validationpackage}.${moduleName}.annotation;

import ${validationpackage}.${moduleName}.Validator.${className}MultipleValidator;
import ${validationpackage}.${moduleName}.enums.${className}Enum;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ${className}MultipleValidator.class)
@Documented
public @interface ${className}MultipleParameterAnnotation {

	String message() default "{${className}MultipleParameterAnnotation}}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	${className}Enum[] value() ;
	${className}Enum[] valueValidator() ;
}