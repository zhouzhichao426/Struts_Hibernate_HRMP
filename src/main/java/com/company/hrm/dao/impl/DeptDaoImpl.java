package com.company.hrm.dao.impl;

import com.company.hrm.dao.idao.IDeptDao;
import com.company.hrm.dao.pojo.Dept;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptDaoImpl implements IDeptDao {
    SessionFactory sessionFactory;
    public DeptDaoImpl() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public void save(Dept t) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Dept t) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Dept t) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    @Override
    public Dept findById(Integer k) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Dept dept = session.get(Dept.class, k);// 通过类里的id
        transaction.commit();
        session.close();
        return dept;
    }

    @Override
    public List<Dept> findAll() throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Dept";
        Query<Dept> query = session.createQuery(hql);
        List<Dept> depts = query.list();
        transaction.commit();
        session.close();
        return depts;
    }

    @Override
    public List<Dept> findByPage(int page, int size) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Dept";//通过查对象
        Query<Dept> query = session.createQuery(hql).setFirstResult((page - size) * size).setMaxResults(size);
        List<Dept> depts = query.list();
        transaction.commit();
        session.close();
        return depts;
    }

}
