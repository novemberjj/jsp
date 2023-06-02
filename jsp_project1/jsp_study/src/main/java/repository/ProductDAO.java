package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.ProductVO;
import orm.DatabaseConnector;

public class ProductDAO implements DAO {
	//DB Connector
	private Connection conn;
	//DB 연결 후 sql 전송 객체
	private PreparedStatement pst; //cntrl+shift+o => import
	//쿼리문 작성 변수
	private String query=""; 
	
	public ProductDAO() {
		//싱글톤 방식의 객체 생성 방식
		DatabaseConnector dbc= DatabaseConnector.getInstance();
		conn=dbc.getConnection();
	}

	@Override
	public int insert(ProductVO pvo) {
		System.out.println(">>> DAO 접근 완료");
		query="insert into product(pname, price, madeby) values(?,?,?)";
		try {
			pst=conn.prepareStatement(query);
			pst.setString(1, pvo.getPname());
			pst.setInt(2, pvo.getPrice());
			pst.setString(3, pvo.getMadeby());
			
			//executeUpdate : insert, update, delete
			//excuteQuery : select에서 사용
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ProductVO> selectList() {
		System.out.println(">>> DAO 접근 완료");
		query="select * from product order by pno desc"; //pno기준 내림차순 최신데이터부터
		List<ProductVO> list= new ArrayList<ProductVO>();
		
		try {
			pst=conn.prepareStatement(query);
			ResultSet rs= pst.executeQuery(); //select는 executeQuery
			while(rs.next()) {
				list.add(new ProductVO(rs.getInt("pno"), rs.getString("pname"), rs.getString("regdate"))); //여기서 쓰이는 이름 DB필드명
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductVO selectOne(int pno) {
		System.out.println(">>> DAO 접근 완료");
		query="select * from product where pno=?";
		try {
			pst=conn.prepareStatement(query);
			pst.setInt(1, pno);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				return new ProductVO(rs.getInt("pno"), rs.getString("pname"), rs.getInt("price"), rs.getString("regdate"), rs.getString("madeby"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(ProductVO pvo) {
		System.out.println(">>> DAO 접근 완료");
		query="update product set pname=?, price=?, madeby=? where pno=?";
		try {
			pst=conn.prepareStatement(query);
			pst.setString(1, pvo.getPname());
			pst.setInt(2, pvo.getPrice());
			pst.setString(3, pvo.getMadeby());
			pst.setInt(4, pvo.getPno());
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int pno) {
		System.out.println(">>> DAO 접근 완료");
		query="delete from product where pno=?";
		try {
			pst=conn.prepareStatement(query);
			pst.setInt(1, pno);
			return pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}