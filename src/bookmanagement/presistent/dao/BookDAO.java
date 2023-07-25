package bookmanagement.presistent.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import bookmanagement.presistent.dto.BookRequestDTO;
import bookmanagement.presistent.dto.BookResponseDTO;
@Service("bookDao")
public class BookDAO {
	static Connection con = null;
	static {
		con = MyConnection.getConnection();
	}
	//Insert Data
	public int insertData(BookRequestDTO dto) {
		int result = 0;
		String sql = "insert into book(book_code,book_title,book_author,book_price) values(?,?,?,?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getBookCode());
			ps.setString(2, dto.getBookTitle());
			ps.setString(3, dto.getBookAuthor());
			ps.setDouble(4, dto.getBookPrice());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error1!");
		}
		return result;
	}
	//Update Data
	public int updateData(BookRequestDTO dto) {
		int result = 0;
		String sql = "update book set book_title=?,book_author=?,book_price=? where book_code=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setString(1, dto.getBookTitle());
			ps.setString(2, dto.getBookAuthor());
			ps.setDouble(3, dto.getBookPrice());
			ps.setString(4, dto.getBookCode());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database erro2r!");
		}
		return result;
	}
	//Delete Data
	public int deleteData(BookRequestDTO dto) {
		int result = 0;
		String sql = "delete from book where book_code=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setString(1, dto.getBookCode());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error3!");
		}
		return result;
	}
	//Select One
	public BookResponseDTO selectOne(BookRequestDTO dto) {
		BookResponseDTO res = new BookResponseDTO();
		String sql = "select * from book where book_code=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getBookCode());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res.setBookCode(rs.getString("book_code"));
				res.setBookTitle(rs.getString("book_title"));
				res.setBookAuthor(rs.getString("book_author"));
				res.setBookPrice(rs.getDouble("book_price"));
			}
		} catch (SQLException e) {
			System.out.println("Database error!4");
		}
		return res;
	}
	//Select All
	public ArrayList<BookResponseDTO> selectAll () {
		ArrayList<BookResponseDTO> list = new ArrayList<BookResponseDTO>();
		String sql = "select * from book";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BookResponseDTO res = new BookResponseDTO();
				res.setBookCode(rs.getString("book_code"));
				res.setBookTitle(rs.getString("book_title"));
				res.setBookAuthor(rs.getString("book_author"));
				res.setBookPrice(rs.getDouble("book_price"));
				list.add(res);
			}
		} catch (SQLException e) {
			System.out.println("Database error!5");
		}
		return list;
	}
}
