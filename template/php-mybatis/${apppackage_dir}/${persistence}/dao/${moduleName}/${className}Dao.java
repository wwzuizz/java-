
<#include '/share.include'>
<#if idColumn??>
<#assign idJavaType = idColumn.javaType>
<#else>
<#assign idJavaType = "Integer">
</#if>

package ${apppackage}.${persistence}.dao.${moduleName};

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import ${modepackage}.${moduleName}.bean.${className};
import ${modepackage}.${moduleName}.condition.${className}Condition;
import ${apppackage}.persistence.BaseDao;

/**
 * ${table.remarks!}
 * @author ${author}
 * @date ${.now?datetime}
 */
@Repository
public interface ${className}Dao extends BaseDao<${className}, ${idJavaType!}> {

    ${idJavaType!} findNumberByCondition(${className}Condition vo);
    void deleteMap(${className}Condition vo);
    List<${className}> findPageBreakByCondition(${className}Condition vo, RowBounds rb);

    /**
     * 返回上次插入的id，用于merge 自增长有效
     * @return
     */
     ${idJavaType!} findLastInsertedId();
}
