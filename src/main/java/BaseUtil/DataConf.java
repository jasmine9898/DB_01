package BaseUtil;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class DataConf {

    public static String mysql_driver = "com.mysql.jdbc.Driver";
    public static String mysql_connectUrl = "jdbc:mysql://192.168.2.129:3306/javatest";
    public static String mysql_name = "root";
    public static String mysql_password = "tingyun2o13";

    public static String oracle_driver = "oracle.jdbc.driver.OracleDriver";
    public static String oracle_connectUrl = "jdbc:oracle:thin:@192.168.2.129:1521:orcl";
    public static String oracle_name = "root";
    public static String oracle_password = "nbs2o13";

    public static String sqlserver_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String sqlserver_connectUrl = "jdbc:sqlserver://192.168.2.200:1433;databaseName=Test";
    public static String sqlserver_name = "sa";
    public static String sqlserver_password = "123456";

    public static String postgresql_driver = "org.postgresql.Driver";
    public static String postgresql_connectUrl = "jdbc:postgresql://192.168.2.129:5432/javatest";
    public static String postgresql_name = "postgres";
    public static String postgresql_password = "nbs2o13";

    public static String db2_driver = "com.ibm.db2.jcc.DB2Driver";
    public static String db2_connectUrl = "jdbc:db2://192.168.2.129:50000/test1";
    public static String db2_name = "db2inst1";
    public static String db2_password = "nbs2o13";

    public static String derby_driver = "org.apache.derby.jdbc.ClientDriver";
    public static String derby_connectUrl = "jdbc:derby://192.168.2.130:1527/test";
    public static String derby_name = "app";
    public static String derby_password = "nbs2o13";

    public static String mysql_Proxool = "proxool_mysql.xml";
    public static String oracle_Proxool = "proxool_oracle.xml";
    public static String postgresql_Proxool = "proxool_postgresql.xml";
    public static String sqlserver_Proxool = "proxool_sqlserver.xml";
    public static String db2_Proxool = "proxool_db2.xml";
    public static String derby_Proxool = "proxool_derby.xml";

    public static String optkey;
    private static String dateString;
    public static HashSet dbtype = new HashSet();

    static {
        dbtype.add("mysql");
        dbtype.add("oracle");
        dbtype.add("postgresql");
        dbtype.add("sqlserver");
        dbtype.add("db2");
        dbtype.add("derby");
    }

    public static HashMap getSqlMap() {

        LinkedHashMap<String, String> sqlMap = new LinkedHashMap<String, String>();
        //	sqlMap.put("insert", "insert into test_user (id,name,birthday) values (1, '"+optkey+"_insert',"+dateString+"),(2, '"+optkey+"_insert',"+dateString+")");
        sqlMap.put("insert", "insert into test_user (id,name,birthday) values (1, '" + optkey + "_insert'," + dateString + ")");
        // sqlMap.put("insert0", "insert into test_user (id,name,birthday) values (2, '" + optkey + "_insert'," + dateString + ")");
        sqlMap.put("update", "update test_user set name='" + optkey + "_update' where name = '" + optkey + "_insert'");
        sqlMap.put("call", "{call proc_update(?)}");
        sqlMap.put("call", "{call proc_update('"+optkey+"')}");


        sqlMap.put("select", "select id,name,birthday from test_user where name like '%" + optkey + "%'");
        sqlMap.put("delete", "delete from test_user where name like '%" + optkey + "%' and id=1");
        sqlMap.put("truncate", "truncate table test_user");
        sqlMap.put("drop", "drop table test_tableA");
        sqlMap.put("create", "create table test_tableA (id INTEGER not NULL,  first VARCHAR(255), last VARCHAR(255), age INTEGER, PRIMARY KEY ( id ))");
        sqlMap.put("alter", "alter table test_tableA DROP COLUMN age");
        return sqlMap;
            /*
             *
			DROP PROCEDURE IF EXISTS `proc_count`;
			 DELIMITER $$
				CREATE PROCEDURE `proc_count`(IN param_id INT,OUT resultCount INT)
					BEGIN
						SELECT COUNT(*) INTO resultCount FROM test_user WHERE id =param_id;
					END
						$$
			DELIMITER ;
				
					
			DROP PROCEDURE IF EXISTS `proc_sourceTypeCount`;
			DELIMITER $$
			CREATE PROCEDURE `proc_sourceTypeCount`(IN sourcetype VARCHAR(30))
			BEGIN
			 SET @sqlstr=CONCAT("SELECT count(*) FROM test_user WHERE NAME LIKE '%",sourcetype,"%'");
			 PREPARE stmt FROM @sqlstr;
			 EXECUTE stmt;
			END
			$$
			DELIMITER ;
			
						
			DROP PROCEDURE IF EXISTS `proc_TypeUpdate`;
			DELIMITER $$
			CREATE PROCEDURE `proc_Update`(IN sourcetype VARCHAR(30))
			BEGIN
			 SET @sqlstr=CONCAT("update test_user set name = 'proc_",sourcetype,"' WHERE NAME LIKE '%",sourcetype,"%'");
			 PREPARE stmt FROM @sqlstr;
			 EXECUTE stmt;
			END
			$$
DELIMITER ;
			*/
    }

    public static DataSource getDataSource(String dataType, String sourceType) {
        if (dataType.equalsIgnoreCase("mysql")) {
            dateString = "now()";
        } else if (dataType.equalsIgnoreCase("oracle")) {
            dateString = "to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')";
        } else if (dataType.equalsIgnoreCase("postgresql") || dataType.equalsIgnoreCase("db2") || dataType.equalsIgnoreCase("derby")) {
            dateString = "current_timestamp";
        } else if (dataType.equalsIgnoreCase("sqlserver")) {
            dateString = "getdate()";
        } else {
            dateString = "'2020-1-1'";
        }
        DataSource dataSource = null;
        if (sourceType.equalsIgnoreCase("c3p0")) {
            dataSource = new C3p0DataSource(getDataConfFieldVal(dataType + "_driver"), getDataConfFieldVal(dataType + "_connectUrl"), getDataConfFieldVal(dataType + "_name"), getDataConfFieldVal(dataType + "_password"));
        } else if (sourceType.equalsIgnoreCase("dbcp")) {
            dataSource = new DbcpDataSource(getDataConfFieldVal(dataType + "_driver"), getDataConfFieldVal(dataType + "_connectUrl"), getDataConfFieldVal(dataType + "_name"), getDataConfFieldVal(dataType + "_password"));
        } else if (sourceType.equalsIgnoreCase("proxool")) {
            try {
                JAXPConfigurator.configure(DataConf.class.getClassLoader().getResource(getDataConfFieldVal(dataType + "_Proxool")).getFile(), false);
            } catch (ProxoolException e) {
                System.out.println("#########################   JAXPConfigurator.configure 执行错误");
                e.printStackTrace();
            }
            dataSource = new ProxoolDataSource(dataType);
        } else if (sourceType.equalsIgnoreCase("default")) {
            dataSource = new DefaultDatasource(getDataConfFieldVal(dataType + "_driver"), getDataConfFieldVal(dataType + "_connectUrl"), getDataConfFieldVal(dataType + "_name"), getDataConfFieldVal(dataType + "_password"));

        } else {
            System.out.println("DataSourec未成功初始化。。。");
        }
        // optkey = dataType + "_" + sourceType;
        optkey = sourceType;
        return dataSource;
    }

    private static String getDataConfFieldVal(String fieldName) {
        String fieldVal = "";
        try {
            Class<DataConf> cc = DataConf.class;
            Field fieldObj = cc.getDeclaredField(fieldName);
            fieldVal = fieldObj.get(null).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("#########################  未读取到配置信息  " + fieldName);
        }
        return fieldVal;
    }
}

