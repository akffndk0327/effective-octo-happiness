package kr.or.ddit.ibatis.config;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactory {
	static SqlMapClient smc;
	static {

		try {
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml"); 
			// config파일로 buld가 builder한다.
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SQlMapClient 생성 실패");
			e.printStackTrace();
		}
	}

	public static SqlMapClient getInstance(){
		return smc;
	}
	
	
}
