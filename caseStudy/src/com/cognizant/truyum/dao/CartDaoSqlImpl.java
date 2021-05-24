package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImpl implements CartDao{
	
	public void addCartItem(long userId, Long menuItemId) 
	{
		Connection con = ConnectionHandler.getConnection();
		String Query = "insert into cart(user_id,menu_id)"+"values (?,?) ";
	//	long cartid = 1;
		try 
		{
			PreparedStatement stmt = con.prepareStatement(Query);
			stmt.setLong(1, userId);
			stmt.setLong(2, menuItemId);
			stmt.execute();
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		Connection con = ConnectionHandler.getConnection();
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		
		Cart cart = new Cart(0,menuItemList);
		String query = "select * from menu_item JOIN cart ON menu_item.id = cart.menu_id where cart.user_id = ?";
		try
		{
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, userId);
			ResultSet rs = stmt.executeQuery();
			float value  = 0.00f;
			while(rs.next())
			{
				long id = rs.getLong("id");
				String name  = rs.getString("name");
				float price = rs.getFloat("price");
				value = value + price;
				String active = rs.getString("active");
				Date date1 = rs.getDate("date_of_launch");
				String category = rs.getString("category");
				String free_delivery = rs.getString("free_delivery");
				boolean act = false;
				if(active.equalsIgnoreCase("yes"))
				{
					act = true;
				}
				boolean free=false;
				if(free_delivery.equalsIgnoreCase("yes"))
				{
					free = true;
				}
				MenuItem m1  = new MenuItem(id,name,price,act,date1,category,free);
				menuItemList.add(m1);
				
			}
			//total =  value;
			cart.setTotal(value);
			cart.setMenuItemList(menuItemList);
		} 
		catch (SQLException e) 
		{
		
			e.printStackTrace();
		}
		
	
		return menuItemList;
	}
	

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		Connection con = ConnectionHandler.getConnection();
		String query = "delete from  cart where user_id = ? AND menu_id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1,userId);
			stmt.setLong(2, menuItemId);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
