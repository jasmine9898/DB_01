package com.db2;

import BaseUtil.DataConf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

/**
 * Created by admin on 2016/8/27.
 */
public class Db2DefaultPsServlet extends HttpServlet {
    String driver = DataConf.db2_driver;
    String url = DataConf.db2_connectUrl;
    String username = DataConf.db2_name;
    String password = DataConf.db2_password;
    String insertsql = "insert into test_user(id,name,birthday) values (?,?,current_timestamp)";
    String updatesql = "update test_user set name=? where id=1";
    String callsql = "{call proc_update(?)}";
   // String selectsql = "select * from test_user where name like ? and rownum<=?";
    String deletesql = "delete from test_user where name like ?";
    String mulselctsql="select * from (select name,birthday from test_user where name like ?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>DB2 jdbc preparedStatement</h2>");
        String queryResult="<a style=\"color: red\">error</a>";

        try {
            Class.forName(driver);
            // new oracle.jdbc.driver.OracleDriver();
            Connection conn = DriverManager.getConnection(url, username, password);
            // Statement stat = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement(insertsql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "PreparedStatement");
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement(updatesql);
            preparedStatement.setString(1, "%update_ps%");
            preparedStatement.execute();

            CallableStatement cstmt = conn.prepareCall(callsql);
            cstmt.setString(1, "ps");
            cstmt.execute();
            cstmt.close();

            preparedStatement = conn.prepareStatement(mulselctsql);
            preparedStatement.setString(1, "%ps%");
            //preparedStatement.setInt(2, 5);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
               // stringBuffer.append(rs.getString("name")+"- -"+rs.getString("birthday"));
                queryResult=rs.getString(1)+" - "+rs.getString(2)+"<br>";
            }

            preparedStatement = conn.prepareStatement(deletesql);
            preparedStatement.setString(1, "%ps%");
            preparedStatement.execute();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(queryResult.contains("error")){
            stringBuffer.append(queryResult);
        }else  stringBuffer.append(insertsql+"<br>"+updatesql+"<br>"+callsql+"<br>"+mulselctsql+"<br>"+deletesql+"<br>查询结果：<br>"+queryResult);
        stringBuffer.append(" </body></html>");
        resp.getWriter().write(stringBuffer.toString());
    }

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "你我他的地のabcxyz0123789~!@#$%^&*();/<>|\\][{}（），。、《》？；‘：“”’";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            stringBuffer.append(base.charAt(number));
        }
        return stringBuffer.toString();
    }
}
