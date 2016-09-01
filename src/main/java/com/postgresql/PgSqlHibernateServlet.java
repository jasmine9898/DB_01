package com.postgresql;

import BaseUtil.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2016/8/29.
 */
public class PgSqlHibernateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil hibernateUtil=new HibernateUtil("postgresqlHibernate.cfg.xml");

        response.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>Postgresql hibernate</h2>");
        stringBuffer.append("insert into javatest.public.test_user (birthday, longcontent, name, id) values (?, ?, ?, ?)<br>");
        stringBuffer.append("select testuseren_.id, testuseren_.birthday as birthday2_0_, testuseren_.longcontent as longcont3_0_, testuseren_.name as name4_0_ from javatest.public.test_user testuseren_ where testuseren_.id=?<br>");
        stringBuffer.append("delete from javatest.public.test_user where id=?");
        TestUserEntity userEntity = new TestUserEntity();
        userEntity.setId(1);
        userEntity.setBirthday("2016");
        userEntity.setName("hibernate");
        userEntity.setLongcontent("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Session session = hibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();

        Session session2 = hibernateUtil.getSessionFactory().openSession();
        session2.beginTransaction();
        session2.delete(userEntity);
        session2.getTransaction().commit();
        stringBuffer.append("<h3>save & delete execute finished</h3> </body></html>");
        response.getWriter().write(stringBuffer.toString());
    }
}
