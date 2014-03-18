package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.dao.SubcategoryDao;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.ActionSupport;

public class SubcategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String id;

	private Category category = new Category();
	private Subcategory subcategory = new Subcategory();
	private SubcategoryDao subcategoryDao = new SubcategoryDao();
	private CategoryDao categoryDao = new CategoryDao();
	private List<Subcategory> subcategories = new ArrayList<Subcategory>();
	private List<Category> categories = new ArrayList<Category>();


	public String addSubcategory() {
		Category categoryAux;
		
		if ( (categoryAux = categoryDao.findByName(category.getName())) != null) {
			subcategory.setCategory(categoryAux);
			categoryAux.getSubcategories().add(subcategory);
			categoryAux.setSubcategories(categoryAux.getSubcategories());
		}
		else {
			subcategory.setCategory(category);
			subcategories.add(subcategory);
			category.setSubcategories(subcategories);
			
		}
		subcategoryDao.create(subcategory);
		return SUCCESS;
	}

	public List<Subcategory> getSubcategories() {
		return subcategoryDao.findAll();
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public String listSubcategories() {
		categories = categoryDao.findAll();
		Map<Category, List<Subcategory>> mapa = new HashMap<Category, List<Subcategory>>();
		
		for (Category category : categories) {
			mapa.put(category, category.getSubcategories());
		}
		
		return SUCCESS;
	}
	
	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setCategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}
	
	public String deleteSubcategory() {
		try {
			if (Validade.isValid(id)) {
				subcategoryDao.delete(subcategoryDao.findById(Long.valueOf(id)));
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
