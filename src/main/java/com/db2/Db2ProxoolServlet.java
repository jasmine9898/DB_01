package com.db2;

import BaseUtil.SqlExcuteUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Db2ProxoolServlet extends HttpServlet {
	SqlExcuteUtil sqlExcute = new SqlExcuteUtil();
	String classname=this.getClass().getSimpleName();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.delete(0, stringBuffer.length());
		stringBuffer.append("<html><head><title></title></head><body><h2>DB2 Proxool</h2>");

		try {
			stringBuffer.append(sqlExcute.excuteSql("db2","proxool",""));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stringBuffer.append(" </body></html>");
		resp.getWriter().write(stringBuffer.toString());
	}
}