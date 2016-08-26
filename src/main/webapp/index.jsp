<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String contextPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<html>
<body>
<h2>DB_1</h2>
<p>oracel c3p0：<a href="<%=contextPath%>/oracleC3p0"><%=contextPath%>/oracleC3p0</a></p>
<p>oracel dbcp：<a href="<%=contextPath%>/oracleDbcp"><%=contextPath%>/oracleDbcp</a></p>
<p>oracel proxool：<a href="<%=contextPath%>/oracleProxool"><%=contextPath%>/oracleProxool</a></p>
<p>oracel selectLong：<a href="<%=contextPath%>/oracleSelectLong"><%=contextPath%>/oracleSelectLong</a></p>

<p>db2 ：<a href="<%=contextPath%>/db2"><%=contextPath%>/db2</a></p>
<p>postgresql ：<a href="<%=contextPath%>/postgresql"><%=contextPath%>/postgresql</a></p>
<p>mysql ：<a href="<%=contextPath%>/mysql"><%=contextPath%>/mysql</a></p>


</body>
</html>
