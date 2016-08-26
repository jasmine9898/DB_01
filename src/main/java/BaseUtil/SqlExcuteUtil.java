package BaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Iterator;
public class SqlExcuteUtil {
    private String dataType, sourceType, sqlType;
    private StringBuffer resultbuffer = new StringBuffer();
    private DataSource dataSource;
    private Connection connection;
    private Statement stmt;
    public StringBuffer excuteSql(String dataType, String sourceType) throws SQLException {
        getConn(dataType, sourceType);
        Iterator iterator = DataConf.getSqlMap().keySet().iterator();
        while (iterator.hasNext()) {
            sqlType = (String) iterator.next();
            if (sqlType.equals("select")) {
                executeQuery(toSql(sqlType));
            } else if (sqlType.equals("call")) {
                executeProc(toSql(sqlType));
            } else {
                executeUpdate(toSql(sqlType));
            }
        }
        disConn();
        return resultbuffer;
    }

    public StringBuffer excuteSql(String dataType, String sourceType, String sqlType) throws SQLException {
        if (sqlType.length() == 0) {
            excuteSql(dataType, sourceType);
        } else {
            getConn(dataType, sourceType);
            if (sqlType.equals("select")) {
                executeQuery(toSql(sqlType));
            } else if (sqlType.equals("call")) {
                executeProc(toSql(sqlType));
            } else
                executeUpdate(toSql(sqlType));
        }
        disConn();
        return resultbuffer;
    }

    public StringBuffer excuteSql(String dataType, String sourceType, String sqlType, String sqlString) throws SQLException {
        getConn(dataType, sourceType);
        if (sqlType.equals("select")) {
            executeQuery(sqlString);
        } else
            executeUpdate(sqlString);
        disConn();
        return resultbuffer;
    }

    private void getConn(String dataType, String sourceType) throws SQLException {
        this.resultbuffer.delete(0, resultbuffer.length());
        this.dataSource = DataConf.getDataSource(dataType, sourceType);
        this.connection = dataSource.getConnection();

        this.stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultbuffer.append("<br>***************  getConnection success  **************<br>");
        this.dataType = dataType;
        this.sourceType = sourceType;
    }

    private void disConn() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                resultbuffer.append("<font color=\"red\">" + dataType + "_" + sourceType + "_" + sqlType + "</font>" + "  disconnection - statement close error , ErrorCode is " + e.getErrorCode() + "  . msg is " + e.getMessage() + "<br>");
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                resultbuffer.append("<font color=\"red\">" + dataType + "_" + sourceType + "_" + sqlType + "</font>" + "  disconnection - connection close error , ErrorCode is " + e.getErrorCode() + "  . msg is " + e.getMessage() + "<br>");
                e.printStackTrace();
            }
        }
        resultbuffer.append("***********************  disConnection success  ***********************<br>");
    }

    private void executeProc(String sqlString) {
        try {
            CallableStatement cstmt = connection.prepareCall(sqlString);
            //	PreparedStatement pstmt=connection.prepareStatement(toSql(sqlType));
            //cstmt.setString("source", sourceType);
            // cstmt.setString(1, sourceType);
            int result = cstmt.executeUpdate();
            cstmt.close();
            resultbuffer.append("<p>" + sqlString + ";<br>" + dataType + "_" + sourceType + "--execute finished, </p><br>");// " + result + "  rows .</p><br>");
        } catch (SQLException e) {
            resultbuffer.append("<font color=\"red\">" + sqlString + "</font>" + "  " + dataType + "_" + sourceType + "  SQL Execute Failed , ErrorCode is " + e.getErrorCode() + "  . msg is " + e.getMessage() + "<br>");
            e.printStackTrace();
        }
    }

    private void executeUpdate(String sqlString) {
        if (!(dataType == "db2" && sqlType == "truncate")) {
            try {
                int result = stmt.executeUpdate(sqlString);
                if (sqlType == "insert" || sqlType == "update" || sqlType == "delete") {
                    System.out.println("aaaaaaaaa");
                    resultbuffer.append("<p>" + sqlString + ";<br>" + dataType + "_" + sourceType + "--execute finished,  " + result + "  rows affected.</p><br>");
                } else {
                    System.out.println("bbbbbb");
                    resultbuffer.append("<p>" + sqlString + ";<br>" + dataType + "_" + sourceType + "--execute finished, </p><br>");
                }
            } catch (SQLException e) {
                resultbuffer.append("<font color=\"red\">" + sqlString + "</font>" + "  " + dataType + "_" + sourceType + "  SQL Execute Failed , ErrorCode is " + e.getErrorCode() + "  . msg is " + e.getMessage() + "<br>");
                e.printStackTrace();
            }
        }
    }

    private void executeQuery(String sqlString) {
        ResultSet resultSet;
        try {
            resultSet = stmt.executeQuery(sqlString);
            int rowcount = 0;
            resultSet.last();
            rowcount = resultSet.getRow();
            resultbuffer.append("<p>" + sqlString + ";<br>" + dataType + "_" + sourceType + "--execute finished,  " + rowcount + "  rows in ResultSet.</p><br>");
            resultSet.close();
        } catch (SQLException e) {
            resultbuffer.append("<font color=\"red\">" + sqlString + "</font>" + "  " + dataType + "_" + sourceType + "  SQL Execute Failed , ErrorCode is " + e.getErrorCode() + "  . msg is " + e.getMessage() + "<br>");
            e.printStackTrace();
        }
    }

    private String toSql(String sqlType) {
        String sql = (String) DataConf.getSqlMap().get(sqlType);
        return sql;
    }
}

