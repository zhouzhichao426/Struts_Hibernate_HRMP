package com.company.hrm.dao.impl;

import com.company.hrm.dao.idao.IUserDao;
import com.company.hrm.dao.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;




public class UserDaoImpl implements IUserDao {
    SessionFactory sessionFactory;

    public UserDaoImpl() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public String regist(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String msg = "error";
        try {
            session.save(user);
            msg = "success";
        } catch (Exception e) {
            throw e;
        }
        transaction.commit();
        session.close();
        return msg;
    }

    @Override
    public User login(String username, String password) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from User where username=? and userpassword=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,username);
        query.setParameter(1,password);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public boolean isExist(String username) throws Exception {
       Session session = sessionFactory.openSession();
       Transaction transaction = session.beginTransaction();
       String hql = "from User where username=?";
       Query query = session.createQuery(hql);
       query.setParameter(0,username);
       User user = (User) query.uniqueResult();
       transaction.commit();
       session.close();
        return user!=null?true:false;
    }

}
