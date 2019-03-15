package com.company.hrm.action;

import com.company.hrm.common.ResResult;
import com.company.hrm.common.ServiceConst;
import com.company.hrm.dao.pojo.Emp;
import com.company.hrm.service.iService.IEmpService;
import com.company.hrm.service.impl.EmpServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmpAction extends ActionSupport implements ModelDriven<Emp>, RequestAware, SessionAware {

    private Emp e = new Emp();
    IEmpService empService = new EmpServiceImpl();


    @Override
    public Emp getModel() {
        return e;
    }

    Map<String, Object> requestMap;

    @Override
    public void setRequest(Map<String, Object> map) {
        requestMap = map;
    }

    Map<String, Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = map;
    }

    public String EmpDelete() {
        int empno = e.getEmpno();
        Emp emp = new Emp();
        emp.setEmpno(empno);
        String msg = empService.delete(e);
        ResResult<Emp> res = null;
        if (msg.equals(ServiceConst.SUCCESS)) {
            res = ResResult.success("delete success!");
        } else {
            res = ResResult.error(500, "delete error");
        }
        String jsonRes = null;
        try {
            jsonRes = new ObjectMapper().writeValueAsString(res);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        out.println(jsonRes);
        out.flush();
        out.close();
        return null;
    }

    public String EmpFindAll() {
        //判断用户是否登录
        ResResult<List<Emp>> result = null;
        if (null != sessionMap.get("username")) {
            //执行
            List<Emp> empList = new ArrayList<Emp>();
            empList = empService.findAll();
            if (null!=empList) {
                result = ResResult.success("find all success", empList);
            } else {
                result = ResResult.error(404, "no data");
            }
        } else {
            result = ResResult.error(301, "have not login");
        }
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String jsonResult = null;
        try {
            jsonResult = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        out.println(jsonResult);
        out.flush();
        out.close();
        return null;
    }

    public String EmpFindById() {
        int empno = e.getEmpno();
        ResResult<List<Emp>> res = null;
        Emp emp = null;
        List<Emp> empList = new ArrayList<Emp>();
        if (empService.findById(empno) != null) {
            emp = empService.findById(empno);
            empList.add(emp);
            res = ResResult.success("find success", empList);
        } else {
            res = ResResult.error(404, "no data");
        }
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String jsonRes = null;
        try {
            jsonRes = new ObjectMapper().writeValueAsString(res);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        System.out.println(jsonRes);
        out.println(jsonRes);
        out.flush();
        out.close();
        return null;
    }

    public String EmpFindByName() {
        String ename = (String) requestMap.get("ename");

        List<Emp> empList = empService.findByName(ename);
        ResResult<List<Emp>> res = null;

        if (!empList.isEmpty() && empList.size() > 0) {
            res = ResResult.success("success", empList);
            System.out.println(res);
        } else {
            res = ResResult.error(404, "no such data");
        }
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String jsonRes = null;
        try {
            jsonRes = new ObjectMapper().writeValueAsString(res);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        System.out.println(jsonRes);
        out.println(jsonRes);
        out.flush();
        out.close();
        return null;
    }

    public String EmpSave() {
        Emp emp = new Emp(e.getEmpno(),e.getJob(),e.getMgr(),e.getHiredate(),e.getSal(),e.getComm(),e.getDeptno());
        String msg = empService.save(e);
        System.out.println(msg);
        ResResult<Emp> res = null;
        if (msg.indexOf(ServiceConst.SUCCESS) != -1) {
            res = ResResult.success("success");
        } else {
            res = ResResult.error(500, "save error");
        }
        String jsonResult = null;
        try {
            jsonResult = new ObjectMapper().writeValueAsString(res);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        out.println(jsonResult);
        out.flush();
        out.close();
        return null;
    }

    public String EmpUpdate() {
        Emp emp = new Emp(e.getEmpno(),e.getEname(),e.getJob(),e.getMgr(),e.getHiredate(),e.getSal(),e.getComm(),e.getDeptno());
        String msg = empService.update(e);
        ResResult<Emp> res = null;
        if (msg.indexOf(ServiceConst.SUCCESS) != -1) {
            res = ResResult.success("update success");
        } else {
            res = ResResult.error(500, "update error");
        }
        String jsonRes = null;
        try {
            jsonRes = new ObjectMapper().writeValueAsString(res);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        out.println(jsonRes);
        out.flush();
        out.close();
        return null;
    }
}
