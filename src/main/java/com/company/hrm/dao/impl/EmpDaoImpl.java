package com.company.hrm.dao.impl;

import com.company.hrm.dao.idao.IEmpDao;
import com.company.hrm.dao.pojo.Emp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class EmpDaoImpl implements IEmpDao {

    SessionFactory sessionFactory;

    public EmpDaoImpl() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public void save(Emp t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Emp t) {
       Session session = sessionFactory.openSession();
       Transaction transaction = session.beginTransaction();
       session.delete(t);
       transaction.commit();
       session.close();
    }

    @Override
    public void update(Emp t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();
    }

    @Override
    public Emp findById(Integer k) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Emp emp = session.get(Emp.class,k);
        transaction.commit();
        session.close();
        return emp;
    }

    @Override
    public List<Emp> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Emp";
        Query query = session.createQuery(hql);
        List<Emp> emps = query.list();
        transaction.commit();
        session.close();
        return emps;
    }

    @Override
    public List<Emp> findByPage(int page, int size) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Emp";
        Query<Emp> query = session.createQuery(hql).setFirstResult((page-1)*size).setMaxResults(size);
        List<Emp> emps = query.list();
        transaction.commit();
        session.close();
        return emps;
    }

    @Override
    public List<Emp> findByName(String ename) {
       Session session = sessionFactory.openSession();
       Transaction transaction = session.beginTransaction();
       String hql = "from Emp where ename like ?";
       Query<Emp> query = session.createQuery(hql);
       query.setParameter(0,"%"+ename+"%");
       List<Emp> emps = query.list();
       transaction.commit();
       session.close();
       return emps;
    }

}
