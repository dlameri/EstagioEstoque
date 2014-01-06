package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.domain.Category;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Category category = new Category();
	private CategoryDao categoryDao = new CategoryDao();
	private List<Category> categories = new ArrayList<Category>();

	public String addCategory() {

		categoryDao.create(category);
		return SUCCESS;
	}

	public Category listCategory() {
		categoryDao.findAll();
		return category;
	}

	public String listCategories() {
		categories = categoryDao.findAll();
		return "success";
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> Categories) {
		this.categories = categories;
	}

}
