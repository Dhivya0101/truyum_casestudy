package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {
	
	
	private static ArrayList<MenuItem> menuItemList;
	
	MenuItemDaoCollectionImpl() throws ParseException{
		menuItemList = new ArrayList<>();
		if(menuItemList == null || menuItemList.isEmpty()) {
			
										//long id, String name, float price, boolean active, Date dateOfLaunch, String category,boolean freeDelivery
			
			MenuItem mobj1 = new MenuItem(101, "Sandwich", 99.00f , true , DateUtil.convertToDate("15/03/2017"), "Main Course", true); 
			menuItemList.add(mobj1);
			MenuItem mobj2 = new MenuItem(102, "Burger", 129.00f , true , DateUtil.convertToDate("23/12/2017"), "Main Course", false); 
			menuItemList.add(mobj2);
			MenuItem mobj3 = new MenuItem(103, "Pizza", 149.00f , true , DateUtil.convertToDate("21/08/2018"), "Main Course", false); 
			menuItemList.add(mobj3);
			MenuItem mobj4 = new MenuItem(104, "French Fries", 57.00f , false , DateUtil.convertToDate("02/07/2017"), "Starters", true); 
			menuItemList.add(mobj4);
			MenuItem mobj5 = new MenuItem(105, "Chocolate Brownie", 32.00f , true , DateUtil.convertToDate("02/11/2022"), "Dessert", true); 
			menuItemList.add(mobj5);
		}
	}
	@Override
	public ArrayList<MenuItem> getMenuItemListAdmin() {
		
		return menuItemList;
	}

	@Override
	public ArrayList<MenuItem> getMenuItemListCustomer(){
		
		ArrayList<MenuItem> menuarr = new ArrayList<MenuItem>();
		try {
		Date date1 = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(date1);  
	    Date toDay = DateUtil.convertToDate(strDate);
		
		for(MenuItem x:menuItemList) {
			Date d = x.getDateOfLaunch();
		    if(d.compareTo(toDay)==0 || d.compareTo(toDay)<0) {
		    	if(x.isActive())
		    		menuarr.add(x);
		    }
		}
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return menuarr;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		for(MenuItem x: menuItemList) {
			if(x.equals(menuItem)) {
				x.setId(menuItem.getId());
				x.setPrice(menuItem.getPrice());
				x.setName(menuItem.getName());
				x.setActive(menuItem.isActive());
				x.setCategory(menuItem.getCategory());
				x.setDateOfLaunch(menuItem.getDateOfLaunch());
				x.setFreeDelivery(menuItem.isFreeDelivery());
			}
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		for(MenuItem x:menuItemList)
		{
			if(x.getId()== menuItemId) {
				return x;
			}
		}
		return null;
	}
}
