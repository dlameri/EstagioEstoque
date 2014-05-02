package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Pagination;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.json.SubcategoryJSON;
import com.ideais.stock.json.internal.InternalCategoryJSON;
import com.ideais.stock.json.internal.ResponseJSON;
import com.ideais.stock.util.Validade;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class CategoryAction extends AbstractAction<Category, InternalCategoryJSON> {

	private static final long serialVersionUID = 1L;

	private Category category = new Category();

	private List<SubcategoryJSON> subcategoriesJSON = new ArrayList<SubcategoryJSON>();

	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "category.name", type = ValidatorType.FIELD, message = "Category required") }, stringLengthFields = { @StringLengthFieldValidator(fieldName = "category.name", type = ValidatorType.FIELD, minLength = "3", maxLength = "45", message = "Nome muito curto.") })
	public String saveCategory() {
		if (Validade.isValid(id)) {
			category.setId(Long.valueOf(id));
		}

		Category savedCategory = categoryService.save(category);

		if (savedCategory == null) {
			responseOutput = new ResponseJSON<InternalCategoryJSON>("ERROR",
					"Erro ao salvar categoria.");
			return ERROR;
		}

		responseOutput = new ResponseJSON<InternalCategoryJSON>("OK",
				new InternalCategoryJSON(savedCategory));
		return SUCCESS;
	}

	@SkipValidation
	public String checkCategoryBeforeDeleting() {
		if (!Validade.isValid(id)) {
			responseOutput = new ResponseJSON<InternalCategoryJSON>("ERROR",
					"Id inválido.");
			return ERROR;
		}

		category = categoryService.findById(Long.valueOf(id));

		List<Subcategory> subcategories = subcategoryService.findByCategoryId(
				category, true);

		if (subcategories == null) {
			return SUCCESS;
		}

		for (Subcategory subcategory : subcategories) {
			subcategoriesJSON.add(new SubcategoryJSON(subcategory));
		}

		return SUCCESS;
	}

	@SkipValidation
	public String deleteCategory() {
		category = categoryService.findById(Long.valueOf(id));
		Category deletedCategory = categoryService.delete(category);

		if (deletedCategory == null) {
			responseOutput = new ResponseJSON<InternalCategoryJSON>("ERROR",
					"Erro ao deletar categoria.");
			return ERROR;
		}

		responseOutput = new ResponseJSON<InternalCategoryJSON>("OK",
				new InternalCategoryJSON(deletedCategory));
		return SUCCESS;
	}

	@SkipValidation
	public String listCategories() {
		return SUCCESS;
	}

	@SkipValidation
	public String getPaginatedCategories() {
		List<Category> categories;
		List<InternalCategoryJSON> categoriesJSON = new ArrayList<InternalCategoryJSON>();

		String[] orderSettings = jtSorting.split(" ");

		Pagination pagination = new Pagination(orderSettings[0].toLowerCase(),
				orderSettings[1].toLowerCase(), jtStartIndex, jtPageSize);

		if (StringUtils.isBlank(query)) {
			categories = categoryService.findAllWithPagination(status,
					pagination);
		} else {
			categories = categoryService.search(status, pagination, query);
		}

		for (Category category : categories) {
			categoriesJSON.add(new InternalCategoryJSON(category));
		}

		responseOutput = new ResponseJSON<InternalCategoryJSON>("OK",
				categoriesJSON, categoryService.getCount(status));

		return SUCCESS;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<SubcategoryJSON> getSubcategoriesJSON() {
		return subcategoriesJSON;
	}

	public void setSubcategoriesJSON(List<SubcategoryJSON> subcategoriesJSON) {
		this.subcategoriesJSON = subcategoriesJSON;
	}

}
