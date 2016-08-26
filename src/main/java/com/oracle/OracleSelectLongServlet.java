package com.oracle;

import BaseUtil.SqlExcuteUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class OracleSelectLongServlet extends HttpServlet {
	SqlExcuteUtil sqlExcute = new SqlExcuteUtil();
	String classname=this.getClass().getSimpleName();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("<html><body><h2>UrlConnection Download File</h2>");
		stringBuffer.delete(0, stringBuffer.length());
		try {
			stringBuffer.append(sqlExcute.excuteSql("oracle","dbcp","select","select * from test_user"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stringBuffer.append(" </body></html>");
		resp.getWriter().write(stringBuffer.toString());
	}

}

