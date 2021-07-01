package com.app.carritodecompras.generics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public class GenericServiceImpl<E, R extends PagingAndSortingRepository<E, Key>, Key>
		implements GenericService<E, Key> {

	@Autowired
	protected R dao;

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {
		// TODO Auto-generated method stub 
		return dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Key id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	@Transactional
	public E save(E alumno) {
		// TODO Auto-generated method stub
		return dao.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Key id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public Page<E> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

}
