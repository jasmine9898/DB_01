package com.oracle;

import BaseUtil.DataConf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.sql.*;
import java.util.Random;

/**
 * Created by admin on 2016/8/27.
 */
public class OracleDefaultPsServlet extends HttpServlet {
    String driver = DataConf.oracle_driver;
    String url =DataConf.oracle_connectUrl;
    String username = DataConf.oracle_name;
    String password = DataConf.oracle_password;
    String insertsql = "insert into test_user values (?,?,to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),?)";
    String updatesql = "update test_user set name=? where id=1";
    String callsql = "{call proc_update(?)}";
   // String selectsql = "select * from test_user where name like ? and rownum<=?";
    String deletesql = "delete from test_user where name like ?";
    String mulselctsql="select * from (select name,birthday from test_user where name like ?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>oracle jdbc preparedStatement</h2>");
        stringBuffer.append(insertsql+"<br>"+updatesql+"<br>"+callsql+"<br>"+mulselctsql+"<br>"+deletesql+"<br>查询结果：<br>");

        try {
            Class.forName(driver);
            // new oracle.jdbc.driver.OracleDriver();
            Connection conn = DriverManager.getConnection(url, username, password);
            // Statement stat = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement(insertsql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "PreparedStatement");
            preparedStatement.setClob(3, new StringReader(getRandomString(25000)));
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement(updatesql);
            preparedStatement.setString(1, "%update_ps%");
            preparedStatement.execute();

            CallableStatement cstmt = conn.prepareCall(callsql);
            cstmt.setString(1, "call");
            cstmt.execute();
            cstmt.close();

            preparedStatement = conn.prepareStatement(mulselctsql);
            preparedStatement.setString(1, "%call%");
            //preparedStatement.setInt(2, 5);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
               // stringBuffer.append(rs.getString("name")+"- -"+rs.getString("birthday"));
                stringBuffer.append(rs.getString(1)+" - "+rs.getString(2)+"<br>");
            }

            preparedStatement = conn.prepareStatement(deletesql);
            preparedStatement.setString(1, "%call%");
            preparedStatement.execute();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
