package com.barbecue.barbecue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbecue.barbecue.models.Host;

@Repository
public interface HostDao extends JpaRepository<Host, Integer> {

	@Override
	List<Host> findAll();

	List<Host> findAllByName(String name);

	Host findById(int id);

	Host save(Host Host);

	void deleteById(int id);
}
