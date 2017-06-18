package com.xianyue.util.database;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

/**
 * @author Xianyue 将sql解析为原始的参数结构
 */
public class SqlResolve {

//    private final String sql =
//     "select ErrorRate as errorrate, ErrorCount as errorcount from ONEAPM_ALERT_AI_9_1482396275636_webTransaction_1_1 where ErrorRate>(1+?ErrorRate_0_avg_7_5_count)* period(ErrorRate, 60 min, 15 day) or ErrorCount > (1 + ?ErrorCount_0_avg_7_5_count)*period(ErrorCount, 60 min, 15 day) for last 3 min";
    String sql =
            "SELECT ID FROM MY_TABLE1, MY_TABLE2, (SELECT * FROM MY_TABLE3) LEFT OUTER JOIN MY_TABLE4 WHERE ID = (SELECT MAX(ID) FROM MY_TABLE5) AND ID2 IN (SELECT * FROM MY_TABLE6)";

    public void sqlParser() throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Statement statement = (Select) parserManager.parse(new StringReader(sql));
        if (statement instanceof Select) {
            Select selectStatement = (Select) statement;
            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
            List tableList = tablesNamesFinder.getTableList(selectStatement);
            for (Iterator iter = tableList.iterator(); iter.hasNext();) {
                System.out.println(iter.next().toString());
            }
        }

    }

    public static void main(String[] args) {
        SqlResolve resolve = new SqlResolve();
        try {
            resolve.sqlParser();
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }
}
