package com.ideais.stock.webservice.domain;

import java.util.ArrayList;
import java.util.List;

public class CartWS {
	private List<CartItemWS> cartItems;
	private Integer totalQuantity = 0;
	
	public CartWS() {
		cartItems = new ArrayList<CartItemWS>();
	}
		
	public Integer getTotalQuantity() {
		return totalQuantity;
	}
	
	public List<CartItemWS> getCartItems() {
		return cartItems;
	}
	
}