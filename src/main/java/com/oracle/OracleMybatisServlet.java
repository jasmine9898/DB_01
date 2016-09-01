package com.oracle;

import BaseUtil.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by admin on 2016/8/30.
 */
public class OracleMybatisServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>Oracle Mybabits</h2>");
        stringBuffer.append("delete from test_user where id = ?<br>");
        stringBuffer.append("insert into test_user (id, name, birthday, longcontent) values (?, ?, ?, ?)<br> ");
        MybatisUtil mybatisUtil=new MybatisUtil("oracleMybatis-config.xml");
        com.oracle.TestUser testUser=new com.oracle.TestUser();
        testUser.setId(new BigDecimal(100));
        testUser.setBirthday("2016");
        testUser.setName("mybatis");
        testUser.setLongcontent("aaaaaa");

        SqlSession sqlSession2=mybatisUtil.getSession();
        com.oracle.TestUserMapper testUserMapper2=sqlSession2.getMapper(com.oracle.TestUserMapper.class);
        testUserMapper2.deleteByPrimaryKey(new BigDecimal(100));
        sqlSession2.commit();

        SqlSession sqlSession=mybatisUtil.getSession();
        com.oracle.TestUserMapper testUserMapper=sqlSession.getMapper(com.oracle.TestUserMapper.class);
        testUserMapper.insert(testUser);
        sqlSession.commit();

        stringBuffer.append("<h3>save & delete execute finished</h3> </body></html>");
        response.getWriter().write(stringBuffer.toString());
    }
}
