package com.barbecue.barbecue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbecue.barbecue.models.Barbecue;
import com.barbecue.barbecue.models.Host;

@Repository
public interface BarbecueDao extends JpaRepository<Barbecue, Integer> {

	@Override
	List<Barbecue> findAll();

	List<Barbecue> findAllByName(String name);

	Barbecue findById(int id);

	List<Barbecue> findAllByHost(Host host);

	Barbecue save(Barbecue barbecue);

	void deleteById(int id);
}
