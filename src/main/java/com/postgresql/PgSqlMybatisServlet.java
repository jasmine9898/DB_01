package com.postgresql;
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
public class PgSqlMybatisServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>Postgresql Mybatis</h2>");
        stringBuffer.append("delete from test_user where id = ?<br>");
        stringBuffer.append("insert into test_user (id, name, birthday, longcontent) values (?, ?, ?, ?)<br> ");
        MybatisUtil mybatisUtil = new MybatisUtil("postgresqlMybatis-config.xml");
        SqlSession sqlSession2=mybatisUtil.getSession();
        TestUserMapper testUserMapper2=sqlSession2.getMapper(TestUserMapper.class);
        testUserMapper2.deleteByPrimaryKey(100);
        sqlSession2.commit();

        SqlSession sqlSession = mybatisUtil.getSession();
        TestUserMapper testUserMapper = sqlSession.getMapper(TestUserMapper.class);
        TestUser testUser = new TestUser();
        testUser.setId(100);
        testUser.setBirthday("abc");
        testUser.setName("nnnnn");
        testUser.setLongcontent("aaaaaa");
        testUserMapper.insert(testUser);
        sqlSession.commit();

        stringBuffer.append("<h3>save & delete execute finished</h3> </body></html>");
        response.getWriter().write(stringBuffer.toString());
    }
}
