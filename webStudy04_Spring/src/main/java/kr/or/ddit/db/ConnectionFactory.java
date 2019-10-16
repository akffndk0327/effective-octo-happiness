package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Factory Object[Method] Pattern
 * 객체 생성하는 공장객체
 * 이전에는 필요한거 일일이 만든엇는데 힘드닌까
 * 자동으로 만들기.
 */
public class ConnectionFactory {
	static String url;
	static String user ;
	static String password ;
	static BasicDataSource dataSource;
	static { //클래스 로딩되면 1번 실행됨
		ResourceBundle bundle=  ResourceBundle.getBundle("kr.or.ddit.db.dbInfo"); //퀄러파이드? 클레스패스이후부터, 확장자 필요없어 
		// 2.
//		try {
//			Class.forName(driverClassName); // 메모리에 ""을 로딩하라 ->dbInfo.properties 로 옮겨가 그리고 다시 가져오기
//			//커넥션 미리 만들기
//			//컨슈머에서 사용함.
//		} catch (ClassNotFoundException e1) {
//			throw new RuntimeException(e1);
//		}
		String driverClassName = bundle.getString("driverClassName");
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		int initiaSize = Integer.parseInt(bundle.getString("initialSize"));
		long maxWait = Long.parseLong(bundle.getString("maxWait"));
		int maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
			
		
		dataSource= new BasicDataSource(); // 커넥션 정보 보내기 
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		//pooling운영 방법 세움.
		dataSource.setInitialSize(initiaSize); //최초의 몇개의 커넥션 생성할건지 정하는 메서드
		dataSource.setMaxWaitMillis(maxWait); //5인분 만들엇는데 6명일때의 대기시간. pool안에 커넷션 없어 기다리고 반납한후에 반응없으면 exception 띄워
		dataSource.setMaxTotal(maxTotal); //전체 커넥션의 갯수 : 처음엔 5개 만드는데 6번째손님올떈 나눠줄게 없은니까 2초기다리고 예외 띄우는게 아니라 5개를 또 만들어 total까지 
		//11번째 손님은 더이상 생성안하고 예외 띄워
		
	}

	//pooling 은 커넥션 만들어진곳에서 해야함.
	//미리 커넥션 만들어놔야함. => 언제?static에서 만들어와냐함
	public static Connection getConnection() throws SQLException{
//		Connection conn = DriverManager.getConnection(url, user, password); //getConnection : 매번 커넥션 생성함. => 비효율적
		Connection conn = dataSource.getConnection(); 
		return conn;
		
	}
}
