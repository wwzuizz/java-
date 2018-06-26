<#include '/share.include'>
<#if idColumn??>
<#assign idJavaType = idColumn.javaType>
<#else>
<#assign idJavaType = "Integer">
</#if>

package ${apppackage}.service.${moduleName};

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ${apppackage}.service.BaseService;
import ${modepackage}.${moduleName}.bean.${className};
import ${modepackage}.${moduleName}.vo.${className}Vo;
import ${modepackage}.${moduleName}.condition.${className}Condition;
import ${apppackage}.${persistence}.dao.${moduleName}.${className}Dao;

/**
 * ${table.remarks!}
 * @author ${author}
 * @date ${.now?datetime}
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ${className}Service extends BaseService{

    @Autowired
    private ${className}Dao ${classNameLow}Dao;

    public void insert(${className}Vo model) {

    	 ${classNameLow}Dao.insert(model.get${className}());
    }

    /**
     * 插入并返回生成的id  合适余自增长
     * @param model
     * @return
     */
    public Integer merge(${className}Vo model) {

	${classNameLow}Dao.insert(model.get${className}());
	return ${classNameLow}Dao.findLastInsertedId();

    }

    public void delete(${idJavaType!} modelInteger) {

    	${classNameLow}Dao.delete(modelInteger);
    }
	public void deleteMap(${className}Condition vo) {

		${classNameLow}Dao.deleteMap(vo);
	}
    public ${className}Vo findById(Integer modelInteger) {

    	${className}Vo model = new ${className}Vo(${classNameLow}Dao.findById(modelInteger));
    	return model;
    }

    public void update(${className}Vo model) {

    	${classNameLow}Dao.update(model.get${className}());
    }

    /**
     * 动态更新,只更新非null的值
     * @param model
     */
    public void updateSelective(${className}Vo model) {

    	${classNameLow}Dao.updateSelective(model.get${className}());
    }

    public int countAll() {

    	return ${classNameLow}Dao.countAll();
    }

    public List<${className}Vo> findAll() {

		List<${className}Vo> list = new ArrayList<${className}Vo>();
		List<${className}> list1 = ${classNameLow}Dao.findAll();
		for (${className} model : list1) {
		    list.add(new ${className}Vo(model));
		}
		return list;
    }

    public List<${idJavaType!}> findAllIds() {

    	return ${classNameLow}Dao.findAllIds();
    }


    /**
     * 分页查询，包括总数和内容
     * @param Vo  返回总数
     * @return  返回内容
     */
    public JSONArray  find${className}AppPage(${className}Condition vo) {
		   JSONArray array=new JSONArray();
		    vo.setOrderField(vo.getOrderField()!=null ? vo.getOrderField() : "<#if pkColumn??>${pkColumn.sqlName}</#if>");
		    vo.setOrderDirection(vo.getOrderDirection()!=null ? vo.getOrderDirection() : "desc");
			RowBounds rb = new RowBounds(vo.startPage(), vo.endPage());
		    List<${className}>     list = ${classNameLow}Dao.findPageBreakByCondition(vo, rb);
			   JSONObject object=null;
			   ${className} ${classNameLow}Vo=null;
			   for (int i = 0; i < list.size(); i++) {
				   object=new JSONObject();
				   ${classNameLow}Vo=list.get(i);
				   <@generateJavaColumns/>
				   array.add(object);
			}

		return array;
    }
	public JSONObject  find${className}AppSingle(${className}Condition vo) {
		vo.setOrderField(vo.getOrderField()!=null ? vo.getOrderField() : "<#if pkColumn??>${pkColumn.sqlName}</#if>");
		vo.setOrderDirection(vo.getOrderDirection()!=null ? vo.getOrderDirection() : "desc");
		RowBounds rb = new RowBounds(vo.startPage(), vo.endPage());
		List<${className}>     list = ${classNameLow}Dao.findPageBreakByCondition(vo, rb);
		JSONObject object=null;
		if(list!=null&&list.size()>0){
		${className} ${classNameLow}Vo=null;
			for (int i = 0; i < 1; i++) {
			object=new JSONObject();
			${classNameLow}Vo=list.get(i);
				<@generateJavaColumns/>
			}
		}
		return object;
	}
    <#macro generateJavaColumns>
	   <#list columns as column>
	   seetingJsonObject(object,"${column.columnNameFirstLower}",  ${classNameLow}Vo.get${column.columnName}());
	   </#list>
	   </#macro>
    public Integer search${className}Num(${className}Condition Vo) {

    	return ${classNameLow}Dao.findNumberByCondition(Vo);
    }

}
