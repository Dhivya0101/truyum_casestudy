package com.cognizant.truyum.dao;

//import java.sql.Date;
import java.text.SimpleDateFormat;
//import java.sql.*;
import java.util.*;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {
	
	public static void testGetMenuItemListAdmin()
	{
		
		MenuItemDaoSqlImpl menuItemDao = null;
		try {
			menuItemDao = new MenuItemDaoSqlImpl();
			ArrayList<MenuItem> menuItems = new ArrayList <MenuItem>();
			menuItems = menuItemDao.getMenuItemListAdmin();
	        System.out.println("Menu item list for admin"); 
	        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/YYYY");
	        System.out.println("Name"+"  "+"Price"+"  "+"Active"+"  "+"Date Of Launch"+"  "+"Category"+"  "+"Free Delivery");   
			for (MenuItem items : menuItems) {
				System.out.println(items.getName()+" "+items.getPrice()+" "+items.isActive()+" "+simple.format(items.getDateOfLaunch())+" "+items.getCategory()+" "+items.isFreeDelivery());
			}	
			System.out.println(" ");
		} 
		catch (Exception e) 
		{
		
			e.printStackTrace();
		}
	}
	public static void testGetMenuItemListCustomer()
	{
		
		MenuItemDaoSqlImpl menuItemDao = null;

		try {

			menuItemDao = new MenuItemDaoSqlImpl();

			ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

			menuItems = menuItemDao.getMenuItemListCustomer();

			System.out.println("Menu item list for customer"); 
			SimpleDateFormat simple = new SimpleDateFormat("dd/MM/YYYY");
			 System.out.println("Name"+"  "+"Price"+"  "+"Active"+"  "+"Date Of Launch"+"  "+"Category"+"  "+"Free Delivery");   
			for (MenuItem items : menuItems) {
				System.out.println(items.getName()+" "+items.getPrice()+" "+items.isActive()+" "+simple.format(items.getDateOfLaunch())+" "+items.getCategory()+" "+items.isFreeDelivery());
			}
			System.out.println(" ");
		} 
		catch (Exception e) 
		{

		  e.printStackTrace();

		}
	}
	public static void testmodifyMenuItem()
	{
		MenuItemDao menuItemDao = null;
		try {
			SimpleDateFormat simple = new SimpleDateFormat("dd/MM/YYYY");
			MenuItem menuItem = new MenuItem(105, "Chocolate Brownie",222.00f,true,DateUtil.convertToDate("02/11/2022"),"Dessert",true);
			menuItemDao = new MenuItemDaoSqlImpl();
			menuItemDao.modifyMenuItem(menuItem);
			MenuItem m1= menuItemDao.getMenuItem(menuItem.getId());
			System.out.println("Name"+"  "+"Price"+"  "+"Active"+"  "+"Date Of Launch"+"  "+"Category"+"  "+"Free Delivery");   
			System.out.println(m1.getName()+" "+m1.getPrice()+" "+m1.isActive()+" "+simple.format(m1.getDateOfLaunch())+" "+m1.getCategory()+" "+m1.isFreeDelivery());
			System.out.println("Modification Done");

		}   
		catch (Exception e)       
		{

         e.printStackTrace();

		}
	}
	public static void main(String[] args) throws CartEmptyException
	{
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testmodifyMenuItem();
	}
}
  