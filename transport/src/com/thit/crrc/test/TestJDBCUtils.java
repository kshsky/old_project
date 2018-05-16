package com.thit.crrc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

import com.thit.crrc.bean.Employee;
import com.thit.crrc.utils.JDBCUtils;

/**
 * @filename : TestOJdbc.java
 * @description : 原生ojdbc测试增删改查
 * 
 * @author : sf
 * @time : 2016年12月25日 下午10:50:58
 */
public class TestJDBCUtils {
	Employee emp = new Employee();

	//query
	@Test
	public void queryTest() throws Exception{
		
		Connection conn = JDBCUtils.getConnection();
		Statement st= conn.createStatement();	
		String sql = "select * from emp";
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()){
			emp.setEmpno(rs.getString("empno"));
			emp.setEname(rs.getString("ename"));
			emp.setJob(rs.getString("job"));
			emp.setMgr(rs.getString("mgr"));
			emp.setHiredate(rs.getString("hiredate"));
			emp.setSal(rs.getDouble("sal"));
			emp.setComm(rs.getDouble("comm"));
			emp.setDeptno(rs.getInt("deptno"));
			
			System.out.println(emp);
		}
		JDBCUtils.closeAll(conn, st, rs);
	}
	//add
	@Test
	public void addTest() throws Exception{
		Connection conn= JDBCUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(?,?,?,?,?,?,?,?)";
		pst = conn.prepareStatement(sql);
		
		pst.setInt(1, 8888);
		pst.setString(2, "Lisa");
		pst.setString(3, "CEO");
		pst.setInt(4, 9999);
		pst.setTimestamp(5,new Timestamp(new Date().getTime()));
		pst.setDouble(6, 500.00);
		pst.setDouble(7, 500.00);
		pst.setInt(8, 10);
		
		pst.executeUpdate();
		JDBCUtils.closeAll(conn, pst);
		System.out.println("add success...");
	}
	//update
	@Test
	public void updateTest() throws Exception{
		Connection conn= JDBCUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "update emp set mgr=? where empno=?";
		pst = conn.prepareStatement(sql);
		
		pst.setInt(1, 2222);
		pst.setInt(2, 8888);
		
		pst.executeUpdate();
		JDBCUtils.closeAll(conn, pst);
	}
	//delete
	@Test
	public void deleteTest()throws Exception{
		Connection conn= JDBCUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from emp where empno=?";
		pst = conn.prepareStatement(sql);
		
		pst.setInt(1, 8888);
		pst.executeUpdate();
		JDBCUtils.closeAll(conn, pst);
		System.out.println("delete...");
	}


}
