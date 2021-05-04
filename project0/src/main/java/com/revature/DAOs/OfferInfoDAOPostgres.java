package com.revature.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.models.Item;
import com.revature.models.OfferInfo;

import util.ConnectionUtil;

public class OfferInfoDAOPostgres implements OfferInfoDAO{
	
	
	@Override
	public List<OfferInfo> getAll(){
		List<OfferInfo> offerInfos = new ArrayList<>();
		String sql = "select * from offer_info;";
		try {
			Connection c = ConnectionUtil.getConnectionFromEnv();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				int offerID = rs.getInt("offer_id");
				int userID = rs.getInt("user_id");
				int itemID = rs.getInt("item_id");
				double price = rs.getDouble("price");
				int paymentStatus = rs.getInt("payment_status");
				Date date = rs.getDate("offer_time");
				offerInfos.add(new OfferInfo(offerID, userID, itemID, price, paymentStatus,date));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return offerInfos;
	}

	@Override
	public OfferInfo add(OfferInfo o) {
		OfferInfo offer = null;
		String sql = "insert into offer_info \r\n" 
				+ "(user_id, item_id, price, payment_status) \r\n"
				+ "values (?, ?, ?, 1) returning offer_id;";
		try(Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, o.getUserId());
			ps.setInt(2, o.getItemId());
			ps.setDouble(3, o.getPrice());
			//ps.setInt(4, o.getPaymentStatus());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				offer = o;
				offer.setOfferId(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return offer;
	}

	@Override
	public OfferInfo getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Integer offerID, Integer itemID) {
		Boolean result = false;
		String sql = "update offer_info set payment_status = 3 where item_id = ?;\r\n"
				+ "update offer_info set payment_status = 2 where offer_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, itemID);
			ps.setInt(2, offerID);
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer delete(OfferInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfferInfo getByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfferInfo addEmployee(OfferInfo t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<OfferInfo> getOfferByStatus(Integer status){
		List<OfferInfo> offers = new ArrayList<>();
		String sql = "select * from offer_info where payment_status = ?;";
		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
//				Item item = null;
//				item.setItemId(rs.getInt("item_id"));
//				item.setItemName(rs.getString("item_name"));
//				items.add(item);
				offers.add(new OfferInfo(rs.getInt("offer_id"), 
						rs.getInt("user_id"), 
						rs.getInt("item_id"),
						rs.getDouble("price"),
						rs.getInt("payment_status"),
						rs.getTimestamp("offer_time")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public Integer update(OfferInfo t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Boolean updatePayment(Integer status, Integer userID, Integer itemID) {
		Boolean result = false;
		String sql = "update offer_info set payment_status = ? where user_id = ? and item_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, userID);
			ps.setInt(3, itemID);
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer calWeeklyPayment() {
		Integer result = 0;
		String sql = "select sum(price) as weekly_payment\r\n"
				+ "from offer_info\r\n"
				+ "where offer_time > current_date - interval '7 days' \r\n"
				+ "and payment_status = 4;";
		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, status);
//			ps.setInt(2, userID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("weekly_payment");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
