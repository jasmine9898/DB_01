package com.mysql;

import BaseUtil.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by admin on 2016/8/29.
 */
public class Test {
    public static void main(String[] args) {
        MybatisUtil mybatisUtil=new MybatisUtil("mysqlMybatis-config.xml");
        TestUser testUser=new TestUser();
        testUser.setId(100);
        testUser.setBirthday("abc");
        testUser.setName("nnnnn");
        testUser.setLongcontent("aaaaaa");

        SqlSession sqlSession2=mybatisUtil.getSession();
        TestUserMapper testUserMapper2=sqlSession2.getMapper(TestUserMapper.class);
        testUserMapper2.deleteByPrimaryKey(100);
        sqlSession2.commit();

        SqlSession sqlSession=mybatisUtil.getSession();
        TestUserMapper testUserMapper=sqlSession.getMapper(TestUserMapper.class);
        testUserMapper.insert(testUser);
        sqlSession.commit();
        }
}
