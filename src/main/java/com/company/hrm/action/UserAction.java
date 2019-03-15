package com.company.hrm.action;

import com.company.hrm.common.ResResult;
import com.company.hrm.common.ServiceConst;
import com.company.hrm.dao.pojo.User;
import com.company.hrm.service.iService.IUserService;
import com.company.hrm.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class UserAction extends ActionSupport implements ModelDriven<User>, RequestAware, SessionAware {

    IUserService userService;
    User u = new User();
    private String userpassword1;
    private String userpassword2;

    public String getUserpassword1() {
        return userpassword1;
    }

    public void setUserpassword1(String userpassword1) {
        this.userpassword1 = userpassword1;
    }

    public String getUserpassword2() {
        return userpassword2;
    }

    public void setUserpassword2(String userpassword2) {
        this.userpassword2 = userpassword2;
    }

    public UserAction(){
        userService = new UserServiceImpl();
    }

    @Override
    public User getModel() {
        return u;
    }
    Map<String,Object> requestMap;
    @Override
    public void setRequest(Map<String, Object> map) {
        requestMap = map;
    }
    Map<String,Object> sessionMap;
    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = map;
    }

    public String UserLogin() {
        User user = userService.login(u.getUsername(),u.getUserpassword());
        ResResult<User> result = null;
        if(user != null){
            sessionMap.put("username", user.getUsername());
            result = ResResult.success("login success",user);
        }else{
            result = ResResult.error(500,"password error");
        }
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonResult = null;
        try {
            jsonResult = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        out.println(jsonResult);
        out.flush();
        out.close();
        return null;
    }
    public String UserRegist() throws Exception {
        ResResult result = null;
        String msg = userService.regist(u);
        if(msg.equals("success")){
            result = ResResult.success("regist success");
        }else{
            result = ResResult.error(500,"error");
        }
        String jsonResult = new ObjectMapper().writeValueAsString(result);

        PrintWriter out = ServletActionContext.getResponse().getWriter();

        out.write(jsonResult);
        out.flush();
        out.close();
        return null;
    }
    public String UserExist() {
        boolean flag = userService.isExist(u.getUsername());

        ResResult result = flag?ResResult.success():ResResult.error(404, "no such user");

        String resultJson = null;
        try {
            resultJson = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(resultJson);
        out.flush();
        out.close();
        return null;
    }
}
