package net.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.db.DAO;
import net.product.Menu;

import javax.naming.Context;
import javax.naming.InitialContext;

public class CodexCategoryDAO implements DAO{ 
	Connection con;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	public CodexCategoryDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			con = ds.getConnection();
		}catch(Exception e) {
			System.out.println("Failed connect DB: " + e);
			return;
		}
	}
	
	// Get categoryCode as categoryName in bean.
	public CodexCategoryBean getCategorycode(CodexCategoryBean bean) {
		try {
			pstmt = con.prepareStatement("select categorycode from codexcategory where categoryname=?");
			pstmt.setString(1, bean.getCategoryName());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setCategorycode(rs.getInt("categorycode"));
			}
			
			return bean;
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		return null;
	}
	
	public void close() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}