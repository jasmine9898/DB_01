package com.mysql;

import BaseUtil.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2016/8/30.
 */
public class MysqlMybatisServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>Mysql Mybabits</h2>");
        stringBuffer.append("delete from test_user where id = ?<br>");
        stringBuffer.append("insert into test_user (id, name, birthday, longcontent) values (?, ?, ?, ?)<br> ");
        MybatisUtil mybatisUtil=new MybatisUtil("mysqlMybatis-config.xml");

        SqlSession sqlSession2=mybatisUtil.getSession();
        TestUserMapper testUserMapper2=sqlSession2.getMapper(TestUserMapper.class);
        testUserMapper2.deleteByPrimaryKey(100);
        sqlSession2.commit();
        TestUser testUser=new TestUser();
        testUser.setId(100);
        testUser.setBirthday("2016");
        testUser.setName("mybatis");
        testUser.setLongcontent("aaaaaa");

        SqlSession sqlSession=mybatisUtil.getSession();
        TestUserMapper testUserMapper=sqlSession.getMapper(TestUserMapper.class);
        testUserMapper.insert(testUser);
        sqlSession.commit();

        stringBuffer.append("<h3>save & delete execute finished</h3> </body></html>");
        response.getWriter().write(stringBuffer.toString());
    }
}
