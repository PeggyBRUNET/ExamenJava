package com.barbecue.barbecue.controllers;

import java.util.List;

import javax.persistence.ElementCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbecue.barbecue.dao.ChildGuestDao;
import com.barbecue.barbecue.models.ChildGuest;

@RestController
@RequestMapping("/childguests")
public class ChildGuestController {

	private final ChildGuestDao childGuestDao;

	@Autowired
	public ChildGuestController(ChildGuestDao childGuestDao) {
		this.childGuestDao = childGuestDao;
	}

	@GetMapping
	@ElementCollection
	public ResponseEntity<List<ChildGuest>> getAllChildGuests() {
		List<ChildGuest> childGuests = childGuestDao.findAll();
		return new ResponseEntity<>(childGuests, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ChildGuest> getById(@PathVariable int id) {
		ChildGuest childGuest = childGuestDao.findById(id);
		if (childGuest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(childGuest, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ChildGuest> createChildGuest(@RequestBody ChildGuest ChildGuest) {
		childGuestDao.save(ChildGuest);
		return new ResponseEntity<>(ChildGuest, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteChildGuest(@PathVariable int id) {
		ChildGuest ChildGuest = childGuestDao.findById(id);
		if (ChildGuest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		childGuestDao.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ChildGuest> putChildGuest(@PathVariable int id, @RequestBody ChildGuest childGuest) {
		ChildGuest modifiedChildGuest = childGuestDao.findById(id);

		if (modifiedChildGuest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		childGuest.setId(id);
		modifiedChildGuest = childGuestDao.save(childGuest);
		return new ResponseEntity<>(modifiedChildGuest, HttpStatus.OK);
	}

	@PutMapping("/play/{id}")
	public ResponseEntity<?> playChild(@PathVariable int id) {
		ChildGuest childguest = childGuestDao.findById(id);
		if (childguest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		childguest.play();
		return new ResponseEntity<>(childguest, HttpStatus.OK);

	}
}
