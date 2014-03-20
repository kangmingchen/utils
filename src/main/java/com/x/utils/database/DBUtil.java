package com.x.utils.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title:DBUtil
 * </p>
 * <p>
 * Description:数据库工具类
 * </p>
 * 
 * @Author Chenkangming @Date 2014-2-24
 * @version 1.0
 */
public class DBUtil {

	private static Logger log = LoggerFactory.getLogger(DBUtil.class);

	public static boolean checkDBValidate(String servanddb, String user, String pwd) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(servanddb, user, pwd);
		} catch (Exception e) {
			log.error("{}", e);
		}
		boolean result = false;
		if (con != null) {
			result = true;
			try {
				con.close();
			} catch (SQLException e) {
				log.error("{}", e);
			} finally {
				con = null;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(checkDBValidate("jdbc:mysql://localhost:3306", "root", "root"));
	}

}
