package com.ideais.stock.json;

public class Link {

	private String href;
	private String a;
	
	public Link(String href, String a) {
		this.href = href;
		this.a = a;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
}
