package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Factory Object[Method] Pattern
 * 이전에는 필요한거 일일이 만든엇는데 힘드닌까
 * 자동으로 만들기.
 */
public class ConnectionFactory {
	static {
		ResourceBundle bundle=  ResourceBundle.getBundle("kr.or.ddit.db.dbInfo"); //퀄러파이드? 클레스패스이후부터, 확장자 필요없어 
		// 2.
		try {
			String driverClassName = bundle.getString("driverClassName");
			Class.forName(driverClassName); // 메모리에 ""을 로딩하라 ->dbInfo.properties 로 옮겨가 그리고 다시 가져오기
			url = bundle.getString("url");
			user = bundle.getString("user");
			password = bundle.getString("password");
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException(e1);
		}
	}

	static String url;
	static String user ;
	static String password ;
	public static Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
		
	}
}
