package kr.or.ddit.db.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory;
	static { //특징: 클래스가로드될떄 1번 실행됨 => 그래서 싱글톤  ==? sqlclient와 비슷함.
		try(
		Reader reader = Resources.getResourceAsReader("kr/or/ddit/db/mybatis/Configuration.xml");
		){
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
