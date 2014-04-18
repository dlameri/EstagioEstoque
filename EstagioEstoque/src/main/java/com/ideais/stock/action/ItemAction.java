package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Item;
import com.ideais.stock.service.ItemService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;

public class ItemAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	@Autowired
	ItemService itemService;
	List<Item> items = new ArrayList<Item>();
	
	@Validations()
	public String addItem() {
		return SUCCESS;
	}
	
	@SkipValidation
	public String listItems() {
		return SUCCESS;
	}
	

}
