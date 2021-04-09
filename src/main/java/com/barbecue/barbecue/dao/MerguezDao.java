package com.barbecue.barbecue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbecue.barbecue.models.Merguez;

@Repository
public interface MerguezDao extends JpaRepository<Merguez, Integer> {

	@Override
	List<Merguez> findAll();

	List<Merguez> findAllByName(String name);

	Merguez findById(int id);

	Merguez save(Merguez Merguez);

	void deleteById(int id);
}
