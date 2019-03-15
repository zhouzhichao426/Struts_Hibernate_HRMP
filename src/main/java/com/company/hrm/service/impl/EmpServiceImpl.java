package com.company.hrm.service.impl;

import com.company.hrm.common.ServiceConst;
import com.company.hrm.dao.idao.IEmpDao;
import com.company.hrm.dao.impl.EmpDaoImpl;
import com.company.hrm.dao.pojo.Emp;
import com.company.hrm.service.iService.IEmpService;


import java.util.ArrayList;
import java.util.List;


public class EmpServiceImpl implements IEmpService {
    IEmpDao empdao = new EmpDaoImpl();

    @Override
    public String save(Emp t) {
        try {
            empdao.save(t);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceConst.ERROR;
        }
        return ServiceConst.SUCCESS;
    }

    @Override
    public String delete(Emp t) {
        try {
            empdao.delete(t);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceConst.ERROR;
        }
        return ServiceConst.SUCCESS;
    }

    @Override
    public String update(Emp t) {
        try {
            empdao.update(t);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceConst.ERROR;
        }
        return ServiceConst.SUCCESS;
    }

    @Override
    public Emp findById(Integer k) {
        Emp emp = null;
        try {
            emp = empdao.findById(k);
        } catch (Exception e) {
            e.printStackTrace();
            return emp;
        }
        return emp;
    }

    @Override
    public List<Emp> findAll() {
        List<Emp> emps = new ArrayList<Emp>();
        try {
            emps = empdao.findAll();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return emps;
    }

    @Override
    public List<Emp> findByPage(int page, int size) {
        List<Emp> emps = new ArrayList<Emp>();
        try {
            emps = empdao.findByPage(page, size);
        } catch (Exception e) {
            e.printStackTrace();
            return emps;
        }
        return emps;
    }

    @Override
    public List<Emp> findByName(String ename) {
        List<Emp> emps = new ArrayList<Emp>();
        try {
            emps = empdao.findByName(ename);
        } catch (Exception e) {
            e.printStackTrace();
            return emps;
        }
        return emps;
    }
}
