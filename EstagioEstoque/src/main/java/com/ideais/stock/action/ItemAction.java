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
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

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
	
	@Validations(
		requiredStrings={
	    	@RequiredStringValidator(fieldName="item.optionName", type= ValidatorType.FIELD, message="Nome não pode ser nulo."),
	    	@RequiredStringValidator(fieldName="item.optionValue", type= ValidatorType.FIELD, message="Descrição longa não pode ser nulo."),
	    },
	    stringLengthFields={
	    	@StringLengthFieldValidator(fieldName="item.optionName", type= ValidatorType.FIELD, minLength="3", maxLength="45", message="Nome muito curto."),
	    	@StringLengthFieldValidator(fieldName="item.optionValue", type= ValidatorType.FIELD, minLength="3", maxLength="45", message="Nome muito curto.")
	    },
	    requiredFields={
			@RequiredFieldValidator(fieldName="item.sku", type= ValidatorType.FIELD, message="SKU não pode ser nulo."),
			@RequiredFieldValidator(fieldName="item.priceFrom", type= ValidatorType.FIELD, message="Preço De não pode ser nulo."),
			@RequiredFieldValidator(fieldName="item.priceFor", type= ValidatorType.FIELD, message="Preço Por não pode ser nulo."),
			@RequiredFieldValidator(fieldName="item.stock", type= ValidatorType.FIELD, message="Largura não pode ser nulo."),
		},
		fieldExpressions={
			@FieldExpressionValidator(fieldName="item.priceFor", expression="item.priceFrom > item.priceFor", message="Preço Por deve ser maior que Preço De.", shortCircuit=true)
		}, 
		conversionErrorFields={
			@ConversionErrorFieldValidator(message = "Deve ser um número", shortCircuit = true)
		}
	)
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
		items = itemService.findAll();
		return SUCCESS;
	}
	
	@SkipValidation
	public String deleteItem() {
		item = itemService.findById(Long.valueOf(id));
		itemService.delete(item);
		
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
