package com.ideais.stock.domain;

public class CartItemWS {
	private Long cartItemId;
	private Integer quantity;
	
	public CartItemWS(Item item, Integer quantity) {
		cartItemId = item.getId();
		this.quantity = quantity;
	}
	
	public Long getCartItemId() {
		return cartItemId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
}