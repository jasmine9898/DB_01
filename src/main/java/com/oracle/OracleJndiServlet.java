package com.oracle;

import BaseUtil.DatabaseJndiConn;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by admin on 2016/8/30.
 */
public class OracleJndiServlet extends HttpServlet {

    String insertsql = "insert into test_user(id,name,birthday) values (?,?,to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'))";
    String updatesql = "update test_user set name=? where id=1";
    String callsql = "{call proc_update(?)}";
    // String selectsql = "select * from test_user where name like ? and rownum<=?";
    String deletesql = "delete from test_user where name like ?";
    String mulselctsql="select * from (select name,birthday from test_user where name like ?)";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String serverInfo=this.getServletContext().getServerInfo();
        resp.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>oracle jndi preparedStatement</h2>");
        String queryResult="<a style=\"color: red\">error</a>";
        String jndiName="jdbc/oracle";
        if(serverInfo.toLowerCase().contains("jboss")){
            jndiName="java:jboss/jdbc/oracle";
        }
        stringBuffer.append("JNDI name :"+jndiName+"<br>");
        try {
            Connection conn = DatabaseJndiConn.getConnection(jndiName);
            // Statement stat = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement(insertsql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "PreparedStatement");
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement(updatesql);
            preparedStatement.setString(1, "update_ps");
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
                queryResult=rs.getString(1)+" - "+rs.getString(2)+"<br>";
            }

            preparedStatement = conn.prepareStatement(deletesql);
            preparedStatement.setString(1, "%call%");
            preparedStatement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(queryResult.contains("error")){
            stringBuffer.append(queryResult);
        }else  stringBuffer.append(insertsql+"<br>"+updatesql+"<br>"+callsql+"<br>"+mulselctsql+"<br>"+deletesql+"<br>查询结果：<br>"+queryResult);
        stringBuffer.append(" </body></html>");
        resp.getWriter().write(stringBuffer.toString());

    }
}
