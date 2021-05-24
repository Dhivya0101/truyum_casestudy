
package com.cognizant.truyum.dao;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImpl implements MenuItemDao{
	
	public ArrayList<MenuItem> getMenuItemListAdmin(){
		
		ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
		Connection conn = ConnectionHandler.getConnection();
		final String QUERY = "select * from menu_item";
		ResultSet  rs = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(QUERY);
		rs = stmt.executeQuery();
		while(rs.next())
		{
			long id= rs.getLong("id");
			String name = rs.getString("name");
			float price = rs.getFloat("price");
			String active = rs.getString("active");
			Date date1 =  rs.getDate("date_of_launch");
			String category = rs.getString("category");
			String free_delivery = rs.getString("free_delivery");
			boolean active1 = false;
			boolean free1 = false;
			if(active.equalsIgnoreCase("yes"))
			{
				active1 = true;
			}
			if(free_delivery.equalsIgnoreCase("yes"))
			{
				free1 = true;
			}
			MenuItem m1 = new MenuItem(id,name,price,active1,date1,category,free1);
			menuList.add(m1);
		}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return menuList;
	}

	@Override
	public ArrayList<MenuItem> getMenuItemListCustomer() {
		ArrayList<MenuItem> menuItemListCust = new ArrayList<MenuItem>();
		Connection con = ConnectionHandler.getConnection();
		final String Query = "select * from menu_item where active='Yes' AND date_of_launch < '2018-12-02'";
		try 
		{
			PreparedStatement stmt = con.prepareStatement(Query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				long id = rs.getLong("id");
				String name  = rs.getString("name");
				float price = rs.getFloat("price");
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
				MenuItem m1 = new MenuItem(id,name,price,act,date1,category,free);
				menuItemListCust.add(m1);
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return menuItemListCust;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		
		Connection con = ConnectionHandler.getConnection();
		long id = menuItem.getId();
		String name = menuItem.getName();
		float price = menuItem.getPrice();
		boolean active = menuItem.isActive();
		Date d =new Date( menuItem.getDateOfLaunch().getTime());
		String category = menuItem.getCategory();
		boolean free = menuItem.isFreeDelivery();
		String act ="";
		if(active == true)
		{
			act = "Yes";
		}
		else
		{
			act = "No";
		}
		String fd="";
		if(free == true)
		{
			fd = "Yes";
		}
		else
		{
			fd = "No";
		}
		
		final String query = "update menu_item set name = ? , price = ? ,active = ? ,date_of_launch = ? ,category = ? ,free_delivery = ? "+" where id = ?";
		//final String query = "update menu_item" + " name = " + name + "price = " + price + " active = " + act + ",date_of_launch = "+ d +",category = "+ category +",free_delivery = "+ fd + "where id = "+id ;
		try 
		{
			//Statement stmt = con.createStatement();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setFloat(2, price);
			stmt.setString(3, act);
			stmt.setDate(4, d);
			stmt.setString(5,category);
			stmt.setString(6, fd);
			stmt.setLong(7, id);
			stmt.execute();
		//	stmt.executeUpdate(query);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		
		Connection con = ConnectionHandler.getConnection();
		MenuItem m1 = null;
		final String query = "select * from menu_item where id=?";
		try 
		{
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, menuItemId);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				long id = rs.getLong("id");
				String name  = rs.getString("name");
				float price = rs.getFloat("price");
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
				//System.out.println(id+" "+name+" "+price+" "+act+" "+date1+" "+category+" "+free);
				m1 = new MenuItem(id,name,price,act,date1,category,free);
				//System.out.println(id+" "+name+" "+price+" "+act+" "+date1+" "+category+" "+free+" "+m1);
			}
			
			
		} catch (SQLException e) 
		{
		
			e.printStackTrace();
		}
		return m1;
	}
}
