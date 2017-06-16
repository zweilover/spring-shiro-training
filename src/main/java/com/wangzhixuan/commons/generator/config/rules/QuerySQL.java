/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wangzhixuan.commons.generator.config.rules;

/**
 * <p>
 * 表数据查询
 * </p>
 *
 * @author hubin, tangguo
 * @since 2016-04-25
 */
public enum QuerySQL {
    MYSQL("mysql", "show tables", "show table status",
            "show full fields from %s",
            "NAME", "COMMENT", "FIELD", "TYPE", "COMMENT", "KEY"),

    ORACLE("oracle", "SELECT * FROM USER_TABLES", "SELECT * FROM USER_TAB_COMMENTS",
            "SELECT A.COLUMN_NAME, CASE WHEN A.DATA_TYPE='NUMBER' THEN " +
            "(CASE WHEN A.DATA_PRECISION IS NULL THEN A.DATA_TYPE " +
            "WHEN NVL(A.DATA_SCALE, 0) > 0 THEN A.DATA_TYPE||'('||A.DATA_PRECISION||','||A.DATA_SCALE||')' " +
            "ELSE A.DATA_TYPE||'('||A.DATA_PRECISION||')' END) " +
            "ELSE A.DATA_TYPE END DATA_TYPE, B.COMMENTS,DECODE(C.POSITION, '1', 'PRI') KEY " +
            "FROM USER_TAB_COLUMNS A INNER JOIN USER_COL_COMMENTS B ON A.TABLE_NAME = B.TABLE_NAME" +
            " AND A.COLUMN_NAME = B.COLUMN_NAME LEFT JOIN USER_CONSTRAINTS D " +
            "ON D.TABLE_NAME = A.TABLE_NAME AND D.CONSTRAINT_TYPE = 'P' " +
            "LEFT JOIN USER_CONS_COLUMNS C ON C.CONSTRAINT_NAME = D.CONSTRAINT_NAME " +
            "AND C.COLUMN_NAME=A.COLUMN_NAME WHERE A.TABLE_NAME = '%s' ",
            "TABLE_NAME", "COMMENTS", "COLUMN_NAME", "DATA_TYPE", "COMMENTS", "KEY"),

    SQLSERVER("sqlserver","select cast(name as varchar(500)) as TABLE_NAME from sysObjects where xtype='U' order by name",
            "select name as TABLE_NAME,(select cast(value as varchar(500)) from sys.extended_properties where major_id=id and minor_id = 0) as COMMENTS from sysobjects where xtype='U'",
            "SELECT  cast(a.name as varchar(500)) AS TABLE_NAME, cast(b.name as varchar(500)) AS COLUMN_NAME, " +
                    "cast(c.value as varchar(500)) AS COMMENTS,cast(sys.types.name as varchar(500)) as DATA_TYPE," +
                    "(" +
                    " SELECT case count(1) when 1 then 'PRI' else '' end" +
                    " FROM syscolumns,sysobjects,sysindexes,sysindexkeys,systypes " +
                    " WHERE syscolumns.xusertype = systypes.xusertype and syscolumns.id = object_id(A.name) AND sysobjects.xtype = 'PK'" +
                    " AND sysobjects.parent_obj = syscolumns.id " +
                    " AND sysindexes.id = syscolumns.id " +
                    " AND sysobjects.name = sysindexes.name AND sysindexkeys.id = syscolumns.id " +
                    " AND sysindexkeys.indid = sysindexes.indid " +
                    " AND syscolumns.colid = sysindexkeys.colid and syscolumns.name = B.name) as 'KEY'" +
                    " FROM sys.tables a " +
                    " INNER JOIN sys.columns b ON b.object_id = a.object_id" +
                    " left join sys.types on b.system_type_id=sys.types.system_type_id   " +
                    " LEFT JOIN sys.extended_properties c ON c.major_id = b.object_id AND c.minor_id = b.column_id " +
                    " WHERE a.name = '%s'","TABLE_NAME","COMMENTS","COLUMN_NAME","DATA_TYPE","COMMENTS","KEY");

    private final String dbType;
    private final String tablesSql;
    private final String tableCommentsSql;
    private final String tableFieldsSql;
    private final String tableName;
    private final String tableComment;
    private final String fieldName;
    private final String fieldType;
    private final String fieldComment;
    private final String fieldKey;


    QuerySQL(final String dbType, final String tablesSql, final String tableCommentsSql,
             final String tableFieldsSql, final String tableName, final String tableComment, final String fieldName,
             final String fieldType, final String fieldComment, final String fieldKey) {
        this.dbType = dbType;
        this.tablesSql = tablesSql;
        this.tableCommentsSql = tableCommentsSql;
        this.tableFieldsSql = tableFieldsSql;
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldComment = fieldComment;
        this.fieldKey = fieldKey;
    }

    public String getDbType() {
        return dbType;
    }

    public String getTablesSql() {
        return tablesSql;
    }

    public String getTableCommentsSql() {
        return tableCommentsSql;
    }

    public String getTableFieldsSql() {
        return tableFieldsSql;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public String getFieldKey() {
        return fieldKey;
    }

}
