package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java1234.model.Book;
import com.java1234.util.StringUtil;

/**
 * ͼ��DAO��
 * @author С����
 *
 */
public class BookDao {
	/**
	 * ͼ������
	 * @param con
	 * @param book
	 * @return
	 */
	public int add(Connection con,Book book) throws Exception {
		String sql = "insert into t_book values(null,?,?,?,?,?,?)" ;
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setInt(5, book.getBookTypeId());
		pstmt.setString(6, book.getBookDesc());
		return pstmt.executeUpdate();
	}
	/**
	 * ͼ���ѯ
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,Book book) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_book b,t_booktype bt where b.bookTypId=bt.id");
		if(StringUtil.isNotEmpty(book.getBookName())) {
			sb.append(" and b.bookName like '%" + book.getBookName() + "%'");
		}
		if(StringUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and b.author like '%" + book.getAuthor() + "%'");
		}
		if(book.getBookTypeId() != null && book.getBookTypeId() != -1) {
			sb.append(" and b.bookTypId="+book.getBookTypeId());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	/**
	 * ɾ��ͼ��
	 * @param con
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(Connection con,String id) throws SQLException {
		String sql = "delete from t_book where id=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, id);
		return pst.executeUpdate();
	}
	/**
	 * ͼ����Ϣ�޸�
	 * @param con
	 * @param book
	 * @return
	 * @throws SQLException
	 */
	public int update(Connection con,Book book) throws SQLException {
		String sql = "update t_book set bookName=?,author=?,sex=?,price=?,bookDesc=?��bookTypId=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3,book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setString(5, book.getBookDesc());
		pstmt.setInt(6, book.getBookTypeId());
		pstmt.setInt(7, book.getId());
		return pstmt.executeUpdate();
	}
	/**
	 * ָ��ͼ��������Ƿ����ͼ��
	 * @param con
	 * @param bookTypeId
	 * @return
	 * @throws Exception
	 */
	public boolean existBookByBookTypeId(Connection con,String bookTypeId) throws Exception {
		String sql = "select * from t_book where bookTypId=?"; {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookTypeId);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		}
	}
}