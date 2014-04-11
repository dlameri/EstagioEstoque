	package com.ideais.stock.service;

import java.util.List;

public interface BaseService<T> {
	
	public T save(T entity);
	
	public T findById(Long id);
	
	public List<T> findAll();
	
	public void delete(T entity);

}
