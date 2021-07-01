package com.app.carritodecompras.generics.service;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GenericService<T, Key> {
	
	public Iterable<T> findAll();
	
	public Page<T> findAll(Pageable pageable);
	
	public Optional<T> findById(Key id);
	
	public T save(T entity);
	
	public void deleteById(Key id);
	
}
