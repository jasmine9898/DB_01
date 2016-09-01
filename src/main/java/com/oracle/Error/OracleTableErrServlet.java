package com.oracle.Error;

import BaseUtil.SqlExcuteUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class OracleTableErrServlet extends HttpServlet {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.2.129:1521:orcl";
	String username = "root";
	String password = "nbs2o13";
	String insertsql = "insert into test_user1 values (?,?,to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'))";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("<html><head><title></title></head><body><h2>oracle jdbc 表不存在</h2>");
		try {
			Class.forName(driver);
			// new oracle.jdbc.driver.OracleDriver();
			Connection conn = DriverManager.getConnection(url, username, password);
			// Statement stat = conn.createStatement();

			PreparedStatement preparedStatement = conn.prepareStatement(insertsql);
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, "PreparedStatement");
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
