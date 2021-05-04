package com.revature.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Item;


import util.ConnectionUtil;

public class ItemDAOPostgres implements ItemDAO{

	@Override
	public List<Item> getAvailability(boolean available) {
		List<Item> items = new ArrayList<>();
		String sql = "select * from items where availability = ?;";

		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setBoolean(1, available);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				items.add(new Item(rs.getInt("item_id"), 
						rs.getString("item_name"), 
						rs.getBoolean("availability"),
						rs.getTimestamp("item_time")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}
	
	@Override
	public Item add(Item it) {
		Item item = null;
		String sql = "insert into items (item_name, availability) values (?, ?) returning item_id;";
		try(Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, it.getItemName());
			ps.setBoolean(2, true);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				item = it;
				item.setItemId(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public Boolean delete(Integer id) {
		Boolean result = false;
		String sql = "delete from items where item_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<Item> getAvailableOfferList(Integer id){
		List<Item> items = new ArrayList<>();
		String sql = "select * \r\n"
				+ "from items\r\n"
				+ "where availability \r\n"
				+ "and items.item_id \r\n"
				+ "not in \r\n"
				+ "(select items.item_id \r\n"
				+ "from items \r\n"
				+ "join offer_info \r\n"
				+ "on items.item_id = offer_info.item_id \r\n"
				+ "where offer_info.user_id = ?);";
		
		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
//				Item item = null;
//				item.setItemId(rs.getInt("item_id"));
//				item.setItemName(rs.getString("item_name"));
//				items.add(item);
				items.add(new Item(rs.getInt("item_id"), 
						rs.getString("item_name"), 
						rs.getBoolean("availability"),
						rs.getTimestamp("item_time")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}
	
	@Override
	public Boolean update(Boolean bool, Integer itemID) {
		Boolean result = false;
		String sql = "update items set availability = ? where item_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setBoolean(1, bool);
			ps.setInt(2, itemID);
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Item> getOwnedItems(Integer status, Integer userID) {
		List<Item> items = new ArrayList<>();
		String sql = "select items.item_id, items.item_name, items.availability, items.item_time \r\n"
				+ "from items \r\n"
				+ "join offer_info\r\n"
				+ "on items.item_id = offer_info.item_id \r\n"
				+ "where offer_info.payment_status = ? and offer_info.user_id = ?;";

		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, userID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				items.add(new Item(rs.getInt("item_id"), 
						rs.getString("item_name"), 
						rs.getBoolean("availability"),
						rs.getTimestamp("item_time")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
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
}
