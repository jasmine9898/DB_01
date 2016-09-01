package com.oracle;

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
public class OracleHibernateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil hibernateUtil = new HibernateUtil("oracleHibernate.cfg.xml");

        response.setContentType("text/html;charset=UTF-8");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><head><title></title></head><body><h2>Oracle Hibernate</h2>");
        stringBuffer.append("insert into ROOT.TEST_USER (BIRTHDAY, LONGCONTENT, NAME, ID) values (?, ?, ?, ?)<br>");
        stringBuffer.append("select testuseren_.ID, testuseren_.BIRTHDAY as BIRTHDAY2_0_, testuseren_.LONGCONTENT as LONGCONT3_0_, testuseren_.NAME as NAME4_0_ from ROOT.TEST_USER testuseren_ where testuseren_.ID=?<br>");
        stringBuffer.append("delete from ROOT.TEST_USER where ID=?<br>");
        TestUserEntity userEntity = new TestUserEntity();
        userEntity.setId(1);
        userEntity.setBirthday("2016");
        userEntity.setName("hibernate");

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
