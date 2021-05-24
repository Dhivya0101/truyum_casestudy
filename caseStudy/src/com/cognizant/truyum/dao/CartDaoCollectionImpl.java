package com.cognizant.truyum.dao;

import java.text.ParseException;
//import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao{

	private static HashMap<Long,Cart> userCarts;
	public CartDaoCollectionImpl() {
		super();
		if(userCarts == null)
		{
			userCarts = new HashMap<Long,Cart>();    //key - userId, value - arrayList in menuItem
		}
	}

	@Override
	public void addCartItem(long userId, Long menuItemId) {
		
		MenuItemDao menuItemDao;
		try {
			menuItemDao = new MenuItemDaoCollectionImpl();
			MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
			if(userCarts.containsKey(userId))
			{
				ArrayList<MenuItem> cartItems = userCarts.get(userId).getMenuItemList();
				cartItems.add(menuItem);
			}
			else
			{
				ArrayList<MenuItem> cart1 = new ArrayList<MenuItem>();
				cart1.add(menuItem);
				Cart newCart = new Cart(0,cart1);
				userCarts.put(userId, newCart);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		
		ArrayList<MenuItem> getall = userCarts.get(userId).getMenuItemList();
		if(getall.isEmpty()) {
			throw new CartEmptyException();
		}
		else
		{
				float value =0.00f;
				for(MenuItem x : getall)
				{
					value = value + x.getPrice();
				}
				userCarts.get(userId).setTotal(value);
		}
		return getall;    
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		
		ArrayList<MenuItem> removeList = userCarts.get(userId).getMenuItemList();
		for(MenuItem x:removeList) {
			if(x.getId() == menuItemId) {
				removeList.remove(removeList.indexOf(x));
				break;
			}
		}
		userCarts.get(userId).setMenuItemList(removeList);
	}

}
