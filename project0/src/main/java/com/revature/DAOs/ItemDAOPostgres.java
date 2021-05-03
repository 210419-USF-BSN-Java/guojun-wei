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
	public Integer delete(Integer id) {
		Integer result = 0;
		String sql = "delete from items where item_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
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
}
