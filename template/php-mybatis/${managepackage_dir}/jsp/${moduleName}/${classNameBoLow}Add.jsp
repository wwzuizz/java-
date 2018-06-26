[#ftl] [#include '/share.include'] 
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="bjui-pageContent">
    <form action="/${jspProj}/admin/${classNameLow}Add" id="form1" data-toggle="validate" data-alertmsg="false">
        <table class="table table-condensed table-hover" width="100%">
            <tbody> [#list table.notPkColumns as column] [#if column.columnNameFirstLower != 'isDel' && column.columnNameFirstLower != 'createTime'&&
                column.columnNameFirstLower != 'updateTime' && column.columnNameFirstLower != 'createId'&& column.columnNameFirstLower !=
                'updateId'&& column.columnNameFirstLower !='status']
                <tr>
                    <td><label for="${column.columnNameFirstLower}" class="control-label x85">${column.remarksFirst!}：</label> 
                    <input type="text" name="${column.columnNameFirstLower}" id="${column.columnNameFirstLower}"
                     value="" data-rule="[#if !column.nullable]required [/#if][#if column.isNumberColumn]number [/#if]"></td>
                    <td>
                </tr>[/#if] [/#list]
                <tr>
                    <td><label for="status" class="control-label x85">状态：</label> <select name="status" id="status" data-toggle="selectpicker">
                            <option value="1">正常</option>
                        </select></td>
                    <td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
    </ul>
</div>
