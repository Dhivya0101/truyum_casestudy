package com.cognizant.truyum.dao;

public class CartEmptyException extends Exception{
		public CartEmptyException() {
			System.out.println("Cart is empty");
		}
}
