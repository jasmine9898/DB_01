<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
    <title>DB Demo</title>
</head>
<body>
<h2>DB_1</h2>
<h3>操作类型：insert update select delete call truncate create alter drop</h3>
<p>ojdbc6 11.2.0.4.0 </p>
<p>oracel 连接方式：
    <a href="<%=contextPath%>/oracleDefault">JDBC(Statement)</a>
    <a href="<%=contextPath%>/oracleDefaultPs">JDBC(PreparedStatement)</a>
    <a href="<%=contextPath%>/oracleC3p0">C3p0</a>
    <a href="<%=contextPath%>/oracleDbcp">Dbcp</a>
    <a href="<%=contextPath%>/oracleProxool">Proxool</a>
    <a href="<%=contextPath%>/oracleHibernate">Hibernate</a>
    <a href="<%=contextPath%>/oracleMybatis">Mybatis</a>
    <a href="<%=contextPath%>/oracleJndi">Jndi</a>

<p>oracel Error：
    <a href="<%=contextPath%>/oracleConnErr">连接不到</a>
    <a href="<%=contextPath%>/oracleAuthErr">帐号不正确</a>
    <a href="<%=contextPath%>/oracleSyntaxErr">Sql语法错误</a>
    <a href="<%=contextPath%>/oracleTableErr">表不存在</a>
    <a href="<%=contextPath%>/oracleColErr">列不存在</a>
    <a href="<%=contextPath%>/oracleNotNullErr">违反非空约束</a>
</p>
<p></p>
<p>db2jcc 1.4.2</p>
<p>db2 连接方式：
    <a href="<%=contextPath%>/db2Default">JDBC(Statement)</a>
    <a href="<%=contextPath%>/db2DefaultPs">JDBC(PreparedStatement)</a>
    <a href="<%=contextPath%>/db2C3p0">C3p0</a>
    <a href="<%=contextPath%>/db2Dbcp">Dbcp</a>
    <a href="<%=contextPath%>/db2Proxool">Proxool</a>
    <a href="<%=contextPath%>/db2Hibernate">Hibernate</a>
    <a href="<%=contextPath%>/db2Mybatis">Mybatis</a>
    <a href="<%=contextPath%>/db2Jndi">Jndi</a>


</p>
<p></p>
<p>postgresql 9.4.1208.jre6</p>
<p>postgresql 连接方式：
    <a href="<%=contextPath%>/pgsqlDefault">JDBC(Statement)</a>
    <a href="<%=contextPath%>/pgsqlDefaultPs">JDBC(PreparedStatement)</a>
    <a href="<%=contextPath%>/pgsqlC3p0">C3p0</a>
    <a href="<%=contextPath%>/pgsqlDbcp">Dbcp</a>
    <a href="<%=contextPath%>/pgsqlProxool">Proxool</a>
    <a href="<%=contextPath%>/pgsqlHibernate">Hibernate</a>
    <a href="<%=contextPath%>/pgsqlMybatis">Mybatis</a>
    <a href="<%=contextPath%>/pgsqlJndi">Jndi</a>

</p>
<p></p>
<p>mysql-connector-java 5.1.16</p>
<p>mysql 连接方式：
    <a href="<%=contextPath%>/mysqlDefault">JDBC(Statement)</a>
    <a href="<%=contextPath%>/mysqlDefaultPs">JDBC(PreparedStatement)</a>
    <a href="<%=contextPath%>/mysqlC3p0">C3p0</a>
    <a href="<%=contextPath%>/mysqlDbcp">Dbcp</a>
    <a href="<%=contextPath%>/mysqlProxool">Proxool</a>
    <a href="<%=contextPath%>/mysqlHibernate">Hibernate</a>
    <a href="<%=contextPath%>/mysqlMybatis">Mybatis</a>
    <a href="<%=contextPath%>/mysqlJndi">Jndi</a>

</p>
<p>
    <a style="color: red">oracle Jndi连接池名：jdbc/oracle  (jboss java:jboss/jdbc/oracle)</a><br>
    <a style="color: red">mysql Jndi连接池名：jdbc/mysql   (jboss java:jboss/jdbc/mysql)</a><br>
    <a style="color: red">db2 Jndi连接池名：jdbc/db2     (jboss java:jboss/jdbc/db2)</a><br>
    <a style="color: red">postgresql Jndi连接池名：jdbc/postgresql   (jboss java:jboss/jdbc/postgresql)</a><br>
</p>
<%= application.getServerInfo() %>

</body>
</html>
