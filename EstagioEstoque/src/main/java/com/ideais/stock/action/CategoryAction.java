package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Category;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.ActionSupport;


public class CategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private Category category = new Category();
	
	@Autowired
	private CategoryService categoryService;
	private List<Category> categories = new ArrayList<Category>();
	
	public String saveCategory() {
		try {
			if (category.getId() != null) {
				categoryService.save(category);
				return SUCCESS;
			}
			categoryService.save(category);
			return SUCCESS;
			
		} catch (Exception e) {
			return ERROR;
		}
	}

	public List<Category> getCategories() {
		return categoryService.findAll();
	}

	public String listCategories() {
		if (Validade.isValid(id)) {
			category = categoryService.findById(Long.valueOf(id));
			return SUCCESS;
		}
		categories = categoryService.findAll();
		return SUCCESS;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public String deleteCategory() {
		categoryService.delete(categoryService.findById(Long.valueOf(id)));
		return SUCCESS;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
