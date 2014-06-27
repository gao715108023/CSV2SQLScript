package com.gcj.main;

import com.gcj.utils.ConfigUtils;
import com.gcj.utils.FileUtils;
import com.gcj.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfred on 13-12-14.
 */
public class GenerateSQL {

    public List<String> doProcess(String filePath, String tableName, String columnName, String columnNameType) {
        List<String> sqlList = new ArrayList<String>();
        //List<String> contentList = FileUtils.readFileByLines(filePath);
        List<String> contentList = FileUtils.getUrlListFromFile(filePath, "gbk");
        StringBuilder sb;

        for (String content : contentList) {
            //content = StringUtils.convertStr(content, columnNameType);
            String[] array = content.split(",");
            sb = new StringBuilder();
            sb.append("delete from ").append(tableName).append(" where fun_type = '").append(array[0]).append("';");
            sqlList.add(sb.toString());
        }

        for (String content : contentList) {
            content = StringUtils.convertStr(content, columnNameType);
            sb = new StringBuilder();
            sb.append("insert into " + tableName + "(").append(columnName);
            sb.append(") values(").append(content).append(");");
            sqlList.add(sb.toString());
        }
        return sqlList;
    }

    public static void main(String[] args) {
        ConfigUtils configUtils = new ConfigUtils("infoconfig.properties");
        String tableName = configUtils.getString("table_name");
        String columnName = configUtils.getString("column_name");
        String columnNameType = configUtils.getString("column_name_type");
        String csvPath = configUtils.getString("csv_path");
        String sqlPath = configUtils.getString("sql_path");
        sqlPath = sqlPath + "/" + tableName + ".sql";
        GenerateSQL generateSQL = new GenerateSQL();
        List<String> sqlList = generateSQL.doProcess(csvPath, tableName, columnName, columnNameType);
        FileUtils.writeFile(sqlPath, sqlList);
    }
}
