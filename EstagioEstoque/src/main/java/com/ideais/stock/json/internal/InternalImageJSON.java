package com.ideais.stock.json.internal;

import java.io.Serializable;

import com.ideais.stock.domain.Image;

public class InternalImageJSON implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String showcaseUrl;
	private String productUrl;
	private String superzoomUrl;
	private String shoppingCartUrl;
	private String androidShowcaseUrl;
	private String androidProductUrl;
	private String promo;
	private Boolean main;
	
	public InternalImageJSON(Image image) {
		this.id = image.getId();
		this.showcaseUrl = image.getShowcaseUrl();
		this.productUrl = image.getProductUrl();
		this.superzoomUrl = image.getSuperzoomUrl();
		this.shoppingCartUrl = image.getShoppingCartUrl();
		this.androidShowcaseUrl = image.getAndroidShowcaseUrl();
		this.androidProductUrl = image.getAndroidProductUrl();
		this.promo = image.getPromo();
		this.main = image.getMain();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShowcaseUrl() {
		return showcaseUrl;
	}

	public void setShowcaseUrl(String showcaseUrl) {
		this.showcaseUrl = showcaseUrl;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getSuperzoomUrl() {
		return superzoomUrl;
	}

	public void setSuperzoomUrl(String superzoomUrl) {
		this.superzoomUrl = superzoomUrl;
	}

	public String getShoppingCartUrl() {
		return shoppingCartUrl;
	}

	public void setShoppingCartUrl(String shoppingCartUrl) {
		this.shoppingCartUrl = shoppingCartUrl;
	}

	public String getAndroidShowcaseUrl() {
		return androidShowcaseUrl;
	}

	public void setAndroidShowcaseUrl(String androidShowcaseUrl) {
		this.androidShowcaseUrl = androidShowcaseUrl;
	}

	public String getAndroidProductUrl() {
		return androidProductUrl;
	}

	public void setAndroidProductUrl(String androidProductUrl) {
		this.androidProductUrl = androidProductUrl;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}
}
