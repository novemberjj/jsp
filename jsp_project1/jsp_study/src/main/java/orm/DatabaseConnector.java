package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	//DB에 접속하는 구문은 mysql에서 정해 놓은 것
	//객체 생성 자체를 private으로 묶어 놓음 <싱글톤 방식> (객체의 유일성 보장)
	private static DatabaseConnector dbc = new DatabaseConnector();
	//url, user, passowrd => connection할 때 필요한 3가지
	private Connection conn= null; //DB관련 접속 규격 java.sql에서 제공하는 인터페이스임
	//jdbcDriver
	private String jdbcDriver="com.mysql.jdbc.Driver"; //규격화해서 제공
	//mysql URL
	private String jdbcUrl="jdbc:mysql://localhost:3306/javadb"; // localhost/내가 쓸 db명
	
	private DatabaseConnector() {
		try {
			Class.forName(jdbcDriver);
			conn=DriverManager.getConnection(jdbcUrl,"javauser","mysql");
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("jdbc 드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("URL 연결정보가 정확하지 않습니다.");
			e.printStackTrace();
		}
	}
	
	//싱글톤 방식
	//생성자
	public static DatabaseConnector getInstance() {
		return dbc;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
}
