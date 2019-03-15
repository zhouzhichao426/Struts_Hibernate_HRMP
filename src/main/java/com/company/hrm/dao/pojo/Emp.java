package com.company.hrm.dao.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Emp {
    private int empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private BigDecimal sal;
    private BigDecimal comm;
    private int deptno;

    public Emp(int empno, String job,Integer mgr, Date hiredate, BigDecimal sal, BigDecimal comm, int deptno) {
    }

    public Emp(int empno, String ename, String job, Integer mgr, Date hiredate, BigDecimal sal, BigDecimal comm, int deptno) {
    }

    public Emp() {

    }

    @Id
    @Column(name = "empno")
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    @Basic
    @Column(name = "ename")
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Basic
    @Column(name = "job")
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Basic
    @Column(name = "mgr")
    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    @Basic
    @Column(name = "hiredate")
    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    @Basic
    @Column(name = "sal")
    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    @Basic
    @Column(name = "comm")
    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    @Basic
    @Column(name = "deptno")
    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return empno == emp.empno &&
                deptno == emp.deptno &&
                Objects.equals(ename, emp.ename) &&
                Objects.equals(job, emp.job) &&
                Objects.equals(mgr, emp.mgr) &&
                Objects.equals(hiredate, emp.hiredate) &&
                Objects.equals(sal, emp.sal) &&
                Objects.equals(comm, emp.comm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno, ename, job, mgr, hiredate, sal, comm, deptno);
    }
}
