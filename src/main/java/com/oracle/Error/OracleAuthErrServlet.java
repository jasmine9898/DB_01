package com.oracle.Error;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by admin on 2016/8/27.
 */
public class OracleAuthErrServlet extends HttpServlet {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@192.168.2.129:1521:orcl";
    String username = "root1";
    String password = "nbs2o13";
    Connection conn=null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>oracle jdbc 用户名不正确</h2>");
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            if(conn!=null){
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stringBuffer.append(" </body></html>");
        resp.getWriter().write(stringBuffer.toString());

    }
}
