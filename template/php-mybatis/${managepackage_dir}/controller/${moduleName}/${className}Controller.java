<#include '/share.include'>


package ${managepackage}.controller.${moduleName};

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${managepackage}.model.${moduleName}.condition.${className}Condition;
import ${managepackage}.model.${moduleName}.vo.${className}Vo;
import ${managepackage}.service.${moduleName}.${className}Service;
<@generateJavaColumns4/>
<#macro generateJavaColumns4>
<#assign check= "ControllerMapTag"?new()/>
<#assign temp=check()/>
<#assign ListMap= "ListMapTag"?new()/>
<#assign mapStr=ListMap()/>
<#assign JsonMaps =mapStr?eval>
<#list columns as column>
	<#if  temp?contains(column.columnNameFirstLower)>
	<#elseif mapStr?contains(column.columnNameFirstLower)>
	import ${managepackage}.service.${JsonMaps[column.columnNameFirstLower]?uncap_first}.${JsonMaps[column.columnNameFirstLower]}Service;
	import ${managepackage}.model.${JsonMaps[column.columnNameFirstLower]?uncap_first}.condition.${JsonMaps[column.columnNameFirstLower]}Condition;
			<#else>
			</#if>
  	</#list>
</#macro>
import com.bei_en.shixi.management.controller.BaseController;
import com.bei_en.shixi.management.web.MyJsonFailResult;
import com.bei_en.shixi.management.web.MyJsonResult;
import com.bei_en.shixi.management.web.MyJsonSuccessResult;

/**
 * ${table.remarks!}
 * @author ${author}
 * @date ${.now?datetime}
 */
@Controller
@RequestMapping("/admin")

public class ${className}Controller extends BaseController {
    @Autowired
    ${className}Service ${classNameLow}Service;
	<@generateJavaColumns3/>
    <#macro generateJavaColumns3>
    <#assign check= "ControllerMapTag"?new()/>
    <#assign temp=check()/>
    <#assign ListMap= "ListMapTag"?new()/>
    <#assign mapStr=ListMap()/>
    <#assign JsonMaps =mapStr?eval>
    <#list columns as column>
   	<#if  temp?contains(column.columnNameFirstLower)>
  	<#elseif mapStr?contains(column.columnNameFirstLower)>
  	@Autowired
  	${JsonMaps[column.columnNameFirstLower]}Service  ${JsonMaps[column.columnNameFirstLower]?uncap_first}Service;	
  			<#else>
  			</#if>
      	</#list>
    </#macro>
	<@generateJavaColumns2/>
    <#macro generateJavaColumns2>
    <#assign check= "ControllerMapTag"?new()/>
    <#assign temp=check()/>
    <#assign ListMap= "ListMapTag"?new()/>
    <#assign mapStr=ListMap()/>
    <#assign JsonMaps =mapStr?eval>
    <#list columns as column>
   	<#if  temp?contains(column.columnNameFirstLower)>
  	<#elseif mapStr?contains(column.columnNameFirstLower)>
  	private Map<String, String> ${JsonMaps[column.columnNameFirstLower]}Map = new HashMap<String, String>();
  			<#else>
  			</#if>
      	</#list>
    </#macro>
    private void addSharedAttr(Model model) {
    	<#include '/shareController.include'>
    	<@generateJavaColumns/>
    }
    <#macro generateJavaColumns>
    <#assign check= "ControllerMapTag"?new()/>
    <#assign temp=check()/>
    <#assign ListMap= "ListMapTag"?new()/>
    <#assign mapStr=ListMap()/>
    <#assign JsonMaps =mapStr?eval>
    <#list columns as column>
 	<#if  temp?contains(column.columnNameFirstLower)>
			<#elseif mapStr?contains(column.columnNameFirstLower)>
			${JsonMaps[column.columnNameFirstLower]}Condition ${JsonMaps[column.columnNameFirstLower]}condition = new ${JsonMaps[column.columnNameFirstLower]}Condition();
			${JsonMaps[column.columnNameFirstLower]}condition.setPageSize(Integer.MAX_VALUE);
			${JsonMaps[column.columnNameFirstLower]}Map = ${JsonMaps[column.columnNameFirstLower]?uncap_first}Service.find${JsonMaps[column.columnNameFirstLower]}MapPage( ${JsonMaps[column.columnNameFirstLower]}condition);
			model.addAttribute("${JsonMaps[column.columnNameFirstLower]}Map", ${JsonMaps[column.columnNameFirstLower]}Map);
			<#else>
			</#if>
    	</#list>
    </#macro>

    @RequestMapping("/${classNameLow}")
    public String ${classNameLow}(Model model,${className}Condition vo, HttpServletResponse resp ) {

		List<${className}Vo> list =new ArrayList<${className}Vo>();
		try {
		    list = ${classNameLow}Service.find${className}Page(vo);
		} catch (Exception e) {
		    e.printStackTrace();
		    writeJson(resp, new MyJsonFailResult(e.getMessage()));
		    return null;
		}
	
		model.addAttribute("list", list);
		addSharedAttr(model);
		model.addAttribute("vo", vo);
	
		return "/admin/${moduleName}/${classNameBoLow}List.html";
    }

    @RequestMapping("/${classNameLow}Add")
    public String ${classNameLow}Add(Model model, ${className}Vo vo, boolean show, HttpSession session,
	    HttpServletResponse resp) {

	if (show) {
	    addSharedAttr(model);
	    return "/admin/${moduleName}/${classNameBoLow}Add.html";
	}
	try {
	   /* SysUserVo obj = getLoginUser(session);
	    if (obj!=null)
		vo.setCreateId(obj.getId());
	    else
		vo.setCreateId(0);*/
	    vo.setCreateTime(new Date());
	${classNameLow}Service.insert(vo);
	} catch (Exception e) {
	    e.printStackTrace();
	    writeJson(resp, new MyJsonFailResult(e.getMessage()));
	    return null;
	}
	MyJsonSuccessResult re = new MyJsonSuccessResult();
	re.setCloseCurrent(true);
	re.setTabid("${classNameLow}List");
	writeJson(resp, re);
	return null;
    }

    @RequestMapping("/${classNameLow}Edit")
    public String ${classNameLow}Edit(Model model,boolean show, ${className}Vo vo, HttpSession session, HttpServletResponse resp) {
	
		if (show) {
		    ${className}Vo vo1 = ${classNameLow}Service.findById(vo.getId());
		    addSharedAttr(model);
		    model.addAttribute("vo", vo1);
		    return "/admin/${moduleName}/${classNameBoLow}Edit.html";
		}
		try {
		   /* SysUserVo obj = getLoginUser(session);
		    if (obj!=null)
			vo.setUpdateId(obj.getId());
		    else
			vo.setUpdateId(0);*/
		    vo.setUpdateTime(new Date());
		${classNameLow}Service.updateSelective(vo);
		} catch (Exception e) {
		    e.printStackTrace();
		    writeJson(resp, new MyJsonFailResult(e.getMessage()));
		    return null;
		}
		MyJsonSuccessResult re = new MyJsonSuccessResult();
		re.setCloseCurrent(true);
		re.setTabid("${classNameLow}List");
		writeJson(resp, re);
		return null;
    }

    @RequestMapping("/${classNameLow}Del")
    @ResponseBody
    public MyJsonResult ${classNameLow}Del(Model model, int[] delids, HttpSession session) {

		try {
		    for (int i : delids) {
			${className}Vo vo1 = new ${className}Vo();
			vo1.setId(i);
//			vo1.setStatus(0);
			/*SysUserVo obj = getLoginUser(session);
			if (obj!=null)
			    vo1.setUpdateId(obj.getId());
			else
			    vo1.setUpdateId(0);*/
			vo1.setUpdateTime(new Date());
			${classNameLow}Service.updateSelective(vo1);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    return new MyJsonFailResult(e.getMessage());
		}
		return new MyJsonSuccessResult();
	}
    
}
