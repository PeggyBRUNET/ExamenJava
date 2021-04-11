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

import com.barbecue.barbecue.dao.MerguezDao;
import com.barbecue.barbecue.models.Merguez;

@RestController
@RequestMapping("/merguez")
public class MerguezController {

	private final MerguezDao merguezDao;

	@Autowired
	public MerguezController(MerguezDao merguezDao) {
		this.merguezDao = merguezDao;
	}

	@GetMapping
	@ElementCollection
	public ResponseEntity<List<Merguez>> getAllMerguez() {
		List<Merguez> merguez = merguezDao.findAll();
		return new ResponseEntity<>(merguez, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Merguez> getById(@PathVariable int id) {
		Merguez merguez = merguezDao.findById(id);
		if (merguez == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(merguez, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Merguez> createMerguez(@RequestBody Merguez merguez) {
		merguezDao.save(merguez);
		return new ResponseEntity<>(merguez, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMerguez(@PathVariable int id) {
		Merguez merguez = merguezDao.findById(id);
		if (merguez == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		merguezDao.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Merguez> putMerguez(@PathVariable int id, @RequestBody Merguez merguez) {
		Merguez modifiedMerguez = merguezDao.findById(id);

		if (modifiedMerguez == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		merguez.setId(id);
		modifiedMerguez = merguezDao.save(merguez);
		return new ResponseEntity<>(modifiedMerguez, HttpStatus.OK);
	}

//Si détection d'une merguez, appel à la méthode eat()
	@PutMapping("/eat/{id}")
	public ResponseEntity<?> eatMerguez(@PathVariable int id) {
		Merguez merguez = merguezDao.findById(id);
		if (merguez == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		merguez.eat();
		return new ResponseEntity<>(merguez, HttpStatus.OK);

	}

//Si détection d'une merguez, appel à la méthode cook()
	@PutMapping("/cook/{id}")
	public ResponseEntity<?> cookMerguez(@PathVariable int id) {
		Merguez merguez = merguezDao.findById(id);
		if (merguez == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		merguez.cook();
		return new ResponseEntity<>(merguez, HttpStatus.OK);

	}
}
