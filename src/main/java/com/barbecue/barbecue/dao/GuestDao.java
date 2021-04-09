package com.barbecue.barbecue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbecue.barbecue.models.Barbecue;
import com.barbecue.barbecue.models.Guest;

@Repository
public interface GuestDao extends JpaRepository<Guest, Integer> {

	@Override
	List<Guest> findAll();
	
	List<Guest> findAllByName(String name);

	Guest findById(int id);

	Guest save(Guest guest);

	void deleteById(int id);
}
