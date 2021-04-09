package com.barbecue.barbecue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbecue.barbecue.models.ChildGuest;

@Repository
public interface ChildGuestDao extends JpaRepository<ChildGuest, Integer> {

	@Override
	List<ChildGuest> findAll();

	List<ChildGuest> findAllByName(String name);

	ChildGuest findById(int id);

	ChildGuest save(ChildGuest ChildGuest);

	void deleteById(int id);
}
