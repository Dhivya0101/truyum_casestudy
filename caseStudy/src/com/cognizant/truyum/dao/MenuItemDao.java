package com.cognizant.truyum.dao;

import java.util.*;
import com.cognizant.truyum.model.*;
//import java.sql.PreparedStatement;

public interface MenuItemDao {
	public ArrayList<MenuItem> getMenuItemListAdmin();
	 public ArrayList<MenuItem> getMenuItemListCustomer();
	 public void modifyMenuItem(MenuItem menuItem);
	 public MenuItem getMenuItem(long menuItemId);
	 
}
