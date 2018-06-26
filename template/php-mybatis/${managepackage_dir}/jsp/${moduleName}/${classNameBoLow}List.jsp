[#ftl] [#include '/share.include']
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="/${jspProj}/admin/${classNameLow}">
        <div class="bjui-searchBar">
            &nbsp;
            <label>状态:</label>
            <select name="status" data-toggle="selectpicker">
                <option value="-1" <c:if test="vo.status==-1"> selected</c:if>>全部</option>
            </select>
            &nbsp;
            <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom"><i class="fa fa-angle-double-down"></i></button>
            &nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp; <a class="btn btn-orange" href="javascript:;"
                data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清除</a> &nbsp;
            <div class="pull-right">
                &nbsp;
                <button type="button" class="btn-blue" data-url="/${jspProj}/admin/${classNameLow}Add?show=1" data-toggle="navtab" data-icon="plus"
                    data-fresh=true data-id="${classNameLow}Add" data-reload-warn="本页已有打开的内容，确定将刷新本页内容，是否继续？" data-title="新增"}>新增</button> &nbsp;
                <button type=" button" class="btn-blue" data-url="/${jspProj}/admin/${classNameLow}Del" data-toggle="doajaxchecked" data-confirm-msg="确定要删除选中项吗？"
                    data-icon="remove" data-idname="delids" data-group="ids">删除选中行</button>&nbsp;
            </div>
        </div>
        <div class="bjui-moreSearch">
            <label>&nbsp;生成时间起:</label>
            <input type="text" data-toggle="datepicker" value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="[@el2 'vo.createTime1'/]" />' name="createTime1">
            <label>&nbsp;生成时间止:</label>
            <input type="text" data-toggle="datepicker" value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="[@el2 'vo.createTime2'/]" />' name="createTime2">
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent mylist">
    <table data-toggle="tablefixed" data-width="100%" data-nowrap="true" class="table  table-striped table-condensed" data-selected-multi="false">
        <thead>
            <tr> [#list columns as column][#if column.columnNameFirstLower ='isDel'][#else]
                <th data-order-field="${column.sqlName}">${column.remarksFirst!}</th>[/#if][/#list]
                <th width="26">
                    <input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck">
                </th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="[@el2 "list"/]">
            <tr data-id="[@el2 "i.id"/]">
            [#list columns as column] [#if column.possibleShortJavaType ='Date'||((column.possibleShortJavaType=='Integer'||column.possibleShortJavaType=='String')&&(column.columnNameFirstLower=='createTime'||column.columnNameFirstLower=='updateTime'))]
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="[@el2 'i.${column.columnNameFirstLower }' /]" /></td>
            [#elseif column.columnNameFirstLower
                ='isDel'][#elseif column.possibleShortJavaType ='List']
                [#elseif column.possibleShortJavaType ='Boolean']
                [#else]
                 <td>[@el2 '(i.${column.columnNameFirstLower })'/]</td>[/#if][/#list]
                <td><input type="checkbox" name="ids" data-toggle="icheck" value="[@el2 "i.id"/]"></td>
                <td><a href="/${jspProj}/admin/${classNameLow}Edit?id=[@el2 "i.id"/]&show=1" class="btn btn-green" data-toggle="navtab" data-fresh=true
                    data-icon="edit" data-id="${classNameLow}Edit" data-reload-warn="本页已有打开的内容，确定将刷新本页内容，是否继续？" data-title="编辑-">编辑</a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter">
    <div class="pages">
        <span>每页&nbsp;</span>
        <div class="selectPagesize">
            <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                 <option value="10" [@el2 "(vo.pageSize eq 10)?' selected':''"/]>10</option>
                <option value="20" [@el2 "(vo.pageSize eq 20)?' selected':''"/]>20</option>
                <option value="30" [@el2 "(vo.pageSize eq 30)?' selected':''"/]>30</option>
                <option value="40" [@el2 "(vo.pageSize eq 40)?' selected':''"/]>40</option>
            </select>
        </div>
        <span>,&nbsp;共 [@el2 "vo.totalCount"/] 条, 第 [@el2 "vo.pageNum"/] 页</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="[@el2 "vo.totalCount"/]" data-page-size="[@el2 "vo.pageSize"/]"
        data-page-current="[@el2 "vo.pageNum"/]"></div>
</div>