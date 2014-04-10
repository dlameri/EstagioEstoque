package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.dao.ProductDao;
import com.ideais.stock.dao.SubcategoryDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String id;

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SubcategoryDao subcategoryDao;
	@Autowired
	private ProductDao productDao;
	
	private Subcategory subcategory = new Subcategory();
	private Product product = new Product();
	private Dimensions dimensions = new Dimensions();
	private List<Category> categories = new ArrayList<Category>();
	private List<Subcategory> subcategories = new ArrayList<Subcategory>();
	private List<Product> products = new ArrayList<Product>();
	
	
	public String addProduct() {
		subcategory = subcategoryDao.findById(subcategory.getId());
		product.setDimensions(dimensions);
		product.setCategory(subcategory.getCategory());
		product.setSubcategory(subcategory);
		productDao.save(product);
		
		return SUCCESS;
	}
	
	public String deleteProduct() {
		product = productDao.findById(Long.valueOf(id));
		product.softDelete();
		productDao.save(product);
		
		return SUCCESS;
	}

	public String listProducts() {
		return SUCCESS;
	}
	
	public String execute() {
		try {
			Category category = categoryDao.findById(Long.valueOf(id));
			subcategories = subcategoryDao.findByCategoryId(category);
		} catch (Exception e) {
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public List<Category> getCategories() {
		return categoryDao.findAll();
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}
	
	public List<Product> getProducts() {
		return productDao.findAll();
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}