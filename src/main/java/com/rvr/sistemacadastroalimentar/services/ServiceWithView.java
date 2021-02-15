package com.rvr.sistemacadastroalimentar.services;

import java.util.List;

public interface ServiceWithView<T> {
	
	public List<T> findAll();
	
	public T findById(Integer i);
	
	public void delete(Integer id);
	
	public T update(Integer id, T obj);

}
