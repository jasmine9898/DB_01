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
public class OracleNotNullErrServlet extends HttpServlet {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@192.168.2.129:1521:orcl";
    String username = "root";
    String password = "nbs2o13";
    String createsql="create table test_tableB (id integer not null,testcontent VARCHAR(255), primary key ( id ))";
    String insertsql = "insert into test_tableB(testcontent) values ('abc')";
    String dropsql = "drop table test_tableB";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>oracle jdbc insert违反非空约束</h2>");
        try {
            Class.forName(driver);
            // new oracle.jdbc.driver.OracleDriver();
            Connection conn = DriverManager.getConnection(url, username, password);
            // Statement stat = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(createsql);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement(insertsql);
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement(dropsql);
            preparedStatement.executeUpdate();

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stringBuffer.append(" </body></html>");
        resp.getWriter().write(stringBuffer.toString());

    }
}
