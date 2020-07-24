/*******************************************************************************
 * Copyright (c) 2017-2027 nd Corporation and others.
 * All rights reserved. 
 *
 * http://www.nd.com.cn/
 *******************************************************************************/
package com.example.oracle.rdbs;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Package: com.example.demo.jsqlparser
 * @Description: ${todo} (Describe the role of the class)
 * @author: 198530 kidchild@163.com
 * @date: 2020/7/20  11:40
 * @version: V1.0
 * <p>
 * Modification History:
 * Date                       Author         Version          Description(summary)
 * ------------------------------------------------------------------------------------
 * 2020/7/20  11:40       198530         1.0             Create class.
 * <p>
 * Why & What is modified(detail): <give me a reason>
 */
public class OracleParser {

    public static String getStringList(List<?> list, boolean useComma, boolean useBrackets) {
        StringBuilder ans = new StringBuilder();
        String comma = ",";
        if (!useComma) {
            comma = "";
        }
        if (list != null) {
            if (useBrackets) {
                ans.append("(");
            }
            for(int i = 0; i < list.size(); ++i) {
                ans.append(list.get(i)).append(i < list.size() - 1 ? comma + " " : "");
            }
            if (useBrackets) {
                ans.append(")");
            }
        }

        return ans.toString();
    }


    /**
     * 查询字段
     * @param sql
     * @return
     * @throws JSQLParserException
     */
    public static List<SelectItem> selectItems(String sql) throws Exception {
        //解析SQL
        Statement stmt;
        try {
            stmt = CCJSqlParserUtil.parse(sql);
        } catch (Throwable e) {
            throw new Exception("不支持该SQL转换为分页查询!", e);
        }
        if (!(stmt instanceof Select)) {
            throw new Exception("分页语句必须是Select查询!");
        }
        //获取分页查询的select
        return getPageSelect((Select) stmt);
    }

    public static String getColumns(String sql) {
        try {
            List<SelectItem> items = selectItems(sql);
            return getStringList(items, true, false);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String sql = "select name, sum(age) as age from liliangbin group by name";

        Statement stmt;
        try {
            stmt = CCJSqlParserUtil.parse(sql);
        } catch (Throwable e) {
            throw new Exception("不支持该SQL转换为分页查询!", e);
        }
        if (!(stmt instanceof Select)) {
            throw new Exception("分页语句必须是Select查询!");
        }
        //获取分页查询的select
        List<SelectItem> pageSelect = getPageSelect((Select) stmt);
        System.out.println(getStringList(pageSelect, true, false));
    }

    protected static List<SelectItem> getPageSelect(Select select) {
        SelectBody selectBody = select.getSelectBody();
        //获取查询列
        List<SelectItem> selectItems = getSelectItems((PlainSelect) selectBody);
        return selectItems;
    }

    /**
     * 获取查询列
     *
     * @param plainSelect
     * @return
     */
    protected static List<SelectItem> getSelectItems(PlainSelect plainSelect) {
        //设置selectItems
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (SelectItem selectItem : plainSelect.getSelectItems()) {
            //别名需要特殊处理
            if (selectItem instanceof SelectExpressionItem) {
                SelectExpressionItem selectExpressionItem = (SelectExpressionItem) selectItem;
                if (selectExpressionItem.getAlias() != null) {
                    //直接使用别名
                    Column column = new Column(selectExpressionItem.getAlias().getName());
                    SelectExpressionItem expressionItem = new SelectExpressionItem(column);
                    selectItems.add(expressionItem);
                } else if (selectExpressionItem.getExpression() instanceof Column) {
                    Column column = (Column) selectExpressionItem.getExpression();
                    SelectExpressionItem item = null;
                    if (column.getTable() != null) {
                        Column newColumn = new Column(column.getColumnName());
                        item = new SelectExpressionItem(newColumn);
                        selectItems.add(item);
                    } else {
                        selectItems.add(selectItem);
                    }
                } else {
                    selectItems.add(selectItem);
                }
            } else if (selectItem instanceof AllTableColumns) {
                selectItems.add(new AllColumns());
            } else {
                selectItems.add(selectItem);
            }
        }
        // SELECT *, 1 AS alias FROM TEST
        // 应该为
        // SELECT * FROM (SELECT *, 1 AS alias FROM TEST)
        // 不应该为
        // SELECT *, alias FROM (SELECT *, 1 AS alias FROM TEST)
        for (SelectItem selectItem : selectItems) {
            if (selectItem instanceof AllColumns) {
                return Collections.singletonList(selectItem);
            }
        }
        return selectItems;
    }
}
