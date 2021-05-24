package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.ArrayList;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImplTest {
	

	public static void testGetMenuItemListAdmin() throws ParseException
	{
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		ArrayList<MenuItem> menuItems = new ArrayList <MenuItem>();
		menuItems = menuItemDao.getMenuItemListAdmin();
		System.out.println("Menu Items : ");
		for(MenuItem items: menuItems) {
			System.out.println(items.getName()+" "+items.getPrice()+" "+items.isActive()+" "+items.getDateOfLaunch()+" "+items.getCategory()+" "+items.isFreeDelivery());
		}
		System.out.println("\n");
	}
	
	public static void testGetMenuItemListCustomer() throws ParseException {
		
		MenuItemDao  menuItemDao = new MenuItemDaoCollectionImpl();
		ArrayList<MenuItem> menuItems = menuItemDao.getMenuItemListCustomer();
	    System.out.println("Menu Items for customer");
	    for(MenuItem items: menuItems) {
	    	System.out.println(items.getName()+" "+items.getPrice()+" "+items.isActive()+" "+items.getDateOfLaunch()+" "+items.getCategory()+" "+items.isFreeDelivery());
	    }
	    System.out.println("\n");
	}
	
	public static void testmodifyMenuItem() throws ParseException {
		
		MenuItem menuItem = new MenuItem(105, "Chocolate Brownie", 32.00f , true , DateUtil.convertToDate("02/11/2022"), "Dessert", true);
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		menuItemDao.modifyMenuItem(menuItem); 
		if(menuItemDao.getMenuItem(menuItem.getId()).equals(menuItem)) {
			System.out.println("Modification going on");
			System.out.println("Name"+"  "+"Price"+"  "+"Active"+"  "+"Date Of Launch"+"  "+"Category"+"  "+"Free Delivery"); 
			System.out.println(menuItem.getName()+" "+menuItem.getPrice()+" "+menuItem.isActive()+" "+menuItem.getDateOfLaunch()+" "+menuItem.getCategory()+" "+menuItem.isFreeDelivery());
			System.out.println("Modification Done");
		}
		
	}  
	public static void main(String[] args) throws ParseException {
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testmodifyMenuItem();
	}
}

