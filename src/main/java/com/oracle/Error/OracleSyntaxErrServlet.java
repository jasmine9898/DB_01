package com.oracle.Error;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleSyntaxErrServlet extends HttpServlet {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.2.129:1521:orcl";
	String username = "root";
	String password = "nbs2o13";
	String updatesql = "update from test_user set name='abc'";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("<html><head><title></title></head><body><h2>oracle jdbc 语法错误</h2>");
		try {
			Class.forName(driver);
			// new oracle.jdbc.driver.OracleDriver();
			Connection conn = DriverManager.getConnection(url, username, password);
			// Statement stat = conn.createStatement();

			PreparedStatement preparedStatement = conn.prepareStatement(updatesql);
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
