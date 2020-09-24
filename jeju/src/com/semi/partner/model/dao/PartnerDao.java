package com.semi.partner.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.semi.common.JDBCTemplate.close;
import com.semi.product.model.dao.ProductDao;
import com.semi.product.model.vo.Product;

public class PartnerDao {
	
	private Properties prop = new Properties();
	
	public PartnerDao() {
		try {
			String path = ProductDao.class.getResource("/sql/partner/partner_sql.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> selectPartnerProductList(Connection conn,int cPage,int numPerPage,String userId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Product> list=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectPartnerProductList"));
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Product p=new Product();
				p.setpNum(rs.getInt("p_num"));
				p.setpName(rs.getString("p_name"));
				p.setpBigNameEng(rs.getString("p_big_name_eng"));
				p.setpBigNameKor(rs.getString("p_big_name_kor"));
				p.setpMap(rs.getString("p_map_address"));
				p.setTitleImgPath(rs.getString("title_img_path"));
				p.setpPriceC(rs.getInt("p_price_c"));
				list.add(p);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectPartnerProductCount(Connection conn,String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectPartnerProductCount"));
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
		
			if(rs.next()) {
				result=rs.getInt(1);
			}
			System.out.println("파트너 전체 상품 몇개?"+ result);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteProduct(Connection conn, int pNum) {
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteProduct"));
			pstmt.setInt(1, pNum);
			
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
		
	}
	
	public Product selectProduct(Connection conn,int pNum) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Product p=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectOneProduct"));
			pstmt.setInt(1, pNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				p=new Product();
				p.setpNum(rs.getInt("p_num"));
				p.setpBigNameEng(rs.getString("p_big_name_eng"));
				p.setpBigNameKor(rs.getString("p_big_name_kor"));
				p.setpName(rs.getString("p_name"));
				p.setpPriceA(rs.getInt("p_price_a"));
				p.setpPriceC(rs.getInt("p_price_c"));
				p.setpDateStart(rs.getDate("p_date_start"));
				p.setpDateFinish(rs.getDate("p_date_finish"));
				p.setpTime(rs.getString("p_time"));
				p.setTitleImgPath(rs.getString("title_img_path"));
				p.setpImgPath(rs.getString("p_img_path"));
				p.setpInfo(rs.getString("p_info"));
				p.setpIntd(rs.getString("p_intd"));
				p.setpPointInfo(rs.getString("p_point_info"));
				p.setRuntime(rs.getString("runtime"));
				p.setSchedule(rs.getString("schedule_img_path"));
				p.setPrecaution(rs.getString("precaution"));
				p.setpMapName(rs.getString("p_map_address"));
				p.setpMap(rs.getString("p_map"));
				p.setpScore(rs.getInt("p_score"));
				p.setPartnerId(rs.getString("partner_id"));
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return p;
	}
	
	public int addProduct(Connection conn, Product p) {
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("addProduct"));
			pstmt.setString(1, p.getpBigNameEng());
			pstmt.setString(2, p.getpBigNameKor());
			pstmt.setString(3, p.getpName());
			pstmt.setInt(4, p.getpPriceA());
			pstmt.setInt(5, p.getpPriceC());
			pstmt.setDate(6, p.getpDateStart());
			pstmt.setDate(7, p.getpDateFinish());
			pstmt.setString(8, p.getpTime());
			pstmt.setString(9, p.getTitleImgPath());
			pstmt.setString(10, p.getpImgPath());
			pstmt.setString(11, p.getpInfo());
			pstmt.setString(12, p.getpPointInfo());
			pstmt.setString(13, p.getpIntd());
			pstmt.setString(14, p.getRuntime());
			pstmt.setString(15, p.getSchedule());
			pstmt.setString(16, p.getPrecaution());
			pstmt.setString(17, p.getpMapName());
			pstmt.setString(18, p.getpMap());
//			pstmt.setString(19, p.getPartnerId());
			pstmt.setString(19, "user01");
			
			
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateProduct(Connection conn,Product p) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			
//			updateProduct=UPDATE PRODUCT SET P_BIG_NAME_ENG=?, p_big_name_kor=?,p_name=?,
//			p_price_a=?,p_price_c=?,p_date_start=?,p_date_finish=?,p_time=?,
//			title_img_path=?,p_img_path=?,p_info=?,p_intd=?,p_point_info=?,runtime=?,
//			SChedule_img_path=?,precaution=?,p_map_address=?,p_map=? WHERE P_NUM=? AND PARTNER_ID=?
			
			pstmt=conn.prepareStatement(prop.getProperty("updateProduct"));
			pstmt.setString(1, p.getpBigNameEng());
			pstmt.setString(2, p.getpBigNameKor());
			pstmt.setString(3, p.getpName());
			pstmt.setInt(4, p.getpPriceA());
			pstmt.setInt(5, p.getpPriceC());
			pstmt.setDate(6, p.getpDateStart());
			pstmt.setDate(7, p.getpDateFinish());
			pstmt.setString(8, p.getpTime());
			pstmt.setString(9, p.getTitleImgPath());
			pstmt.setString(10, p.getpImgPath());
			pstmt.setString(11, p.getpInfo());
			pstmt.setString(12, p.getpIntd());
			pstmt.setString(13, p.getpPointInfo());
			pstmt.setString(14, p.getRuntime());
			pstmt.setString(15, p.getSchedule());
			pstmt.setString(16, p.getPrecaution());
			pstmt.setString(17, p.getpMapName());
			pstmt.setString(18, p.getpMap());
			pstmt.setInt(19, p.getpNum());
//			pstmt.setString(19, p.getPartnerId());
			pstmt.setString(20, "user01");
			
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
}
