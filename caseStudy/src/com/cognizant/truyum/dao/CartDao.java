package com.cognizant.truyum.dao;
import java.util.ArrayList;

import com.cognizant.truyum.model.*;

public interface CartDao {

	public void addCartItem(long userId,Long menuItemId);
	public ArrayList<MenuItem> getAllCartItems(long userId) throws CartEmptyException;
	public void removeCartItem(long userId,long menuItemId);
	
}


