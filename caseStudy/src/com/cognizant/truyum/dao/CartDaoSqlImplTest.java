package com.cognizant.truyum.dao;

import java.util.ArrayList;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImplTest {

	public static void testAddCartItem()
	{
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.addCartItem((long)1, (long) 101);
		cartDao.addCartItem((long)1, (long) 103);
		try
		{
			ArrayList<MenuItem> cartList = cartDao.getAllCartItems((long)1);
			System.out.println("items added to cart successfuly");
			System.out.println("Name  Price  Active  Date Of Launch  Category  Free Delivery");

			for(MenuItem x: cartList) {
				System.out.println(x.getName()+" "+x.getPrice()+" "+x.isActive()+" "+x.getDateOfLaunch()+" "+x.getCategory()+" "+x.isFreeDelivery());
			}
			System.out.println(" ");
		}
		catch(CartEmptyException e)
		{
			e.printStackTrace();
		}
	}
	public static void testGetAllCartItems()
	{
		CartDao cartDao = new CartDaoSqlImpl();
		try
		{
			ArrayList<MenuItem> cartlist = cartDao.getAllCartItems(1);
			System.out.println("Name  Price  Active  Date Of Launch  Category  Free Delivery");
			for(MenuItem x: cartlist) {
				System.out.println(x.getName()+" "+x.getPrice()+" "+x.isActive()+" "+x.getDateOfLaunch()+" "+x.getCategory()+" "+x.isFreeDelivery());
			}
			System.out.println(" ");
		}
		catch(CartEmptyException e)
		{
			e.printStackTrace();
		}
		
	}
	public static void testRemoveCartItem()
	{
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.removeCartItem((long)1,(long)1);
		try
		{
			ArrayList<MenuItem> cartleft = cartDao.getAllCartItems((long)1);
			if(cartleft.isEmpty()) {
				throw new CartEmptyException();
			}
			else
			{
				for(MenuItem x:cartleft) {
					System.out.println(x.getName()+" "+x.getPrice()+" "+x.isActive()+" "+x.getDateOfLaunch()+" "+x.getCategory()+" "+x.isFreeDelivery());
				}
			}
		}
		catch(CartEmptyException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		testAddCartItem();
		testGetAllCartItems();
		testRemoveCartItem();
		
	}
}
