<#include '/share.include'>


package ${apppackage}.controller.${moduleName};

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ${modepackage}.${moduleName}.vo.${className}Vo;
import ${modepackage}.${moduleName}.bean.${className};
import ${modepackage}.${moduleName}.condition.${className}Condition;
import ${apppackage}.service.${moduleName}.${className}Service;
import ${apppackage}.controller.BaseController;
import com.bei_en.common.web.JsonFailResult;
import com.bei_en.common.web.JsonResult;
import com.bei_en.common.web.JsonSuccessResult;
import com.mysql.fabric.xmlrpc.base.Array;
import com.bei_en.common.validation.GroupDelete;
import com.bei_en.common.validation.GroupEdit;
import com.bei_en.common.validation.GroupInsert;
import com.bei_en.common.validation.GroupSelect;
/**
 * ${table.remarks!}
 * @author ${author}
 * @date ${.now?datetime}
 */
@Controller
@RequestMapping("/${className}")
public class ${className}APPController extends BaseController {

    @Autowired
    ${className}Service ${classNameLow}Service;


    @RequestMapping("/${classNameLow}List")
	@ResponseBody
    public JsonResult ${classNameLow}List(Model model,@Validated({GroupSelect.class})  ${className}Condition vo, BindingResult br, HttpSession session ) {
    	if (br.hasErrors())
       		return new JsonFailResult(session.getId(), br.getAllErrors().get(0)
					.getDefaultMessage());
    	
     	try {
     		return new JsonSuccessResult(session.getId(), ${classNameLow}Service.find${className}AppPage(vo));
		
     	} catch (Exception e) {
			e.printStackTrace();
			return new JsonFailResult(session.getId(), 1, "后台报错",
					e.getMessage());
		}
    }
	@RequestMapping("/get${className}Single")
	@ResponseBody
	public JsonResult ${classNameLow}Single(Model model,@Valid  ${className}Condition vo, BindingResult br, HttpSession session ) {
		if (br.hasErrors())
		return new JsonFailResult(session.getId(), br.getAllErrors().get(0)
		.getDefaultMessage());

		try {
		return new JsonSuccessResult(session.getId(), ${classNameLow}Service.find${className}AppSingle(vo));

		} catch (Exception e) {
		e.printStackTrace();
		return new JsonFailResult(session.getId(), 1, "后台报错",
		e.getMessage());
		}
	}


	   <#macro generateJavaColumns>
	   <#list columns as column>
	   seetingJsonObject(object,"${column.columnNameFirstLower}",  ${classNameLow}Vo.get${column.columnName}());
	   </#list>
	   </#macro>
    
    @RequestMapping("/${classNameLow}Add")
    @ResponseBody
    public JsonResult ${classNameLow}Add(Model model,@Validated({GroupInsert.class})  ${className}Condition vo, BindingResult br, HttpSession session) {
      	if (br.hasErrors())
       		return new JsonFailResult(session.getId(), br.getAllErrors().get(0)
					.getDefaultMessage());

      	try {
      		
      	
		    ${className}Vo ${classNameLow}=new ${className}Vo();
		    
		    <@generateJavaColumns2/>
		    
			${classNameLow}Service.insert(${classNameLow});
			return new JsonSuccessResult(session.getId(), "成功");
      	} catch (Exception e) {
			e.printStackTrace();
			return new JsonFailResult(session.getId(), 1, "后台报错",
					e.getMessage());
		}
    }
	   <#macro generateJavaColumns2>
	   <#list columns as column>
	   ${classNameLow}.set${column.columnName}(vo.get${column.columnName}());
	   </#list>
	   </#macro>
    @RequestMapping("/${classNameLow}Edit")
	@ResponseBody
    public JsonResult ${classNameLow}Edit(Model model,@Validated({GroupEdit.class})  ${className}Condition vo, BindingResult br, HttpSession session) {
      	if (br.hasErrors())
       		return new JsonFailResult(session.getId(), br.getAllErrors().get(0)
					.getDefaultMessage());

    	try {
    	${className}Vo ${classNameLow}=new ${className}Vo();
		<@generateJavaColumns2/>
		${classNameLow}Service.updateSelective(${classNameLow});
	
		return new JsonSuccessResult(session.getId(), "修改成功");
		
     	} catch (Exception e) {
			e.printStackTrace();
			return new JsonFailResult(session.getId(), 1, "后台报错",
					e.getMessage());
		}
    }
	<#if pkColumns??>
	@RequestMapping("/${classNameLow}delete")
	public JsonResult ${classNameLow}delete(Model model,@Valid  ${className}Condition vo, BindingResult br, HttpSession session) {
		if (br.hasErrors())
		return new JsonFailResult(session.getId(), br.getAllErrors().get(0)
		.getDefaultMessage());

		try {

		<#list pkColumns as column>

		${classNameLow}Service.delete( vo.get${column.columnName}());
		</#list>

		return new JsonSuccessResult(session.getId(), "删除成功");

		} catch (Exception e) {
		e.printStackTrace();
		return new JsonFailResult(session.getId(), 1, "后台报错",
		e.getMessage());
		}
	}
	@RequestMapping("/${classNameLow}ByID")
	@ResponseBody
	public JsonResult ${classNameLow}ByID(Model model,@Valid  ${className}Condition vo, BindingResult br, HttpSession session) {
		if (br.hasErrors())
		return new JsonFailResult(session.getId(), br.getAllErrors().get(0)
		.getDefaultMessage());

		try {

		<#list pkColumns as column>

		${className}Vo ${classNameLow}=	${classNameLow}Service.findById( vo.get${column.columnName}());
		</#list>

		return new JsonSuccessResult(session.getId(), "获取成功",model);

		} catch (Exception e) {
		e.printStackTrace();
		return new JsonFailResult(session.getId(), 1, "后台报错",
		e.getMessage());
		}
		}
	</#if>
  
    
}
