package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Pagination;
import com.ideais.stock.domain.Product;
import com.ideais.stock.json.internal.InternalItemJSON;
import com.ideais.stock.json.internal.ResponseJSON;
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

	@Autowired
	private ProductService productService;
	@Autowired
	private ItemService itemService;

	private String id;
	private Long productId;
	private Boolean status;
	private String jtStartIndex;
	private String jtPageSize;
	private String jtSorting;
	
	private Item item;
	
	private List<Item> items = new ArrayList<Item>();
	
	private ResponseJSON<InternalItemJSON> responseOutput;
	private ResponseJSON<InternalItemJSON> inputResponseError = new ResponseJSON<InternalItemJSON>("ERROR", "Por favor, verifique os campos.");
	
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
	public String saveItem() {
		Product product = productService.findById(productId);
		Item savedItem;
		
		item.setProduct(product);
		savedItem = itemService.save(item);

		if (savedItem == null) {
			responseOutput = new ResponseJSON<InternalItemJSON>("ERROR", new InternalItemJSON(savedItem));
			return ERROR;
		}
		
		responseOutput = new ResponseJSON<InternalItemJSON>("OK", new InternalItemJSON(savedItem));
		return SUCCESS;
	}
	
	@SkipValidation
	public String listItems() {
		return SUCCESS;
	}
	
	@SkipValidation
	public String getItemsByProductId() {
		List<InternalItemJSON> itemsJSON = new ArrayList<InternalItemJSON>();
		
		Product product = new Product();
		product.setId(productId);
		
		String[] orderSettings = jtSorting.split(" ");

		Pagination pagination = new Pagination(orderSettings[0].toLowerCase(),
				orderSettings[1].toLowerCase(), jtStartIndex, jtPageSize);
		
		items = itemService.findByProductId(product, true, pagination);
		int totalRecordCount = itemService.getCount(status, product);
		
		for (Item item : items) {
			itemsJSON.add(new InternalItemJSON(item));
		}
		
		responseOutput = new ResponseJSON<InternalItemJSON>("OK", itemsJSON, totalRecordCount);
		return SUCCESS;
	}
	
	
	@SkipValidation
	public String deleteItem() {
		item = itemService.findById(Long.valueOf(id));
		Item deletedItem = itemService.delete(item);
		
		if (deletedItem == null) {
			responseOutput = new ResponseJSON<InternalItemJSON>("ERROR", new InternalItemJSON(deletedItem));
			return ERROR;
		}
		
		responseOutput = new ResponseJSON<InternalItemJSON>("OK", new InternalItemJSON(deletedItem));
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getJtStartIndex() {
		return jtStartIndex;
	}

	public void setJtStartIndex(String jtStartIndex) {
		this.jtStartIndex = jtStartIndex;
	}

	public String getJtPageSize() {
		return jtPageSize;
	}

	public void setJtPageSize(String jtPageSize) {
		this.jtPageSize = jtPageSize;
	}

	public ResponseJSON<InternalItemJSON> getResponseOutput() {
		return responseOutput;
	}

	public void setResponseOutput(ResponseJSON<InternalItemJSON> responseOutput) {
		this.responseOutput = responseOutput;
	}

	public ResponseJSON<InternalItemJSON> getInputResponseError() {
		return inputResponseError;
	}

	public void setInputResponseError(
			ResponseJSON<InternalItemJSON> inputResponseError) {
		this.inputResponseError = inputResponseError;
	}

}
