package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.service.ItemService;
import com.ideais.stock.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;

public class ItemAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String deleted;
	private String confirmation;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ItemService itemService;
	
	private Product product;
	private Item item;
	
	private List<Image> images = new ArrayList<Image>();
	private List<Item> items = new ArrayList<Item>();
	
	@Validations()
	public String addItem() {
		product = productService.findById(product.getId());
		
		for (Image image : images) {
			image.setItem(item);
		}
		
		item.setProduct(product);
		item.setImages(images);
		itemService.save(item);
		
		return SUCCESS;
	}
	
	@SkipValidation
	public String listItems() {
		return SUCCESS;
	}
	
	@SkipValidation
	public String deleteItem() {
		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
}
