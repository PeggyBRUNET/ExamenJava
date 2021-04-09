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

import com.barbecue.barbecue.dao.BarbecueDao;
import com.barbecue.barbecue.dao.GuestDao;
import com.barbecue.barbecue.dao.MerguezDao;
import com.barbecue.barbecue.models.Barbecue;
import com.barbecue.barbecue.models.Guest;
import com.barbecue.barbecue.models.Merguez;

//On crée un barbecue

@RestController
@RequestMapping("/barbecues")
public class BarbecueController {

	private final BarbecueDao barbecueDao;
	private final MerguezDao merguezDao;
	private final GuestDao guestDao;

	@Autowired
	public BarbecueController(BarbecueDao barbecueDao, MerguezDao merguezDao, GuestDao guestDao) {
		this.barbecueDao = barbecueDao;
		this.merguezDao = merguezDao;
		this.guestDao = guestDao;
	}

//On récupère les barbecues
	@GetMapping
	@ElementCollection
	public ResponseEntity<List<Barbecue>> getAllBarbecues() {
		List<Barbecue> barbecues = barbecueDao.findAll();
		return new ResponseEntity<>(barbecues, HttpStatus.OK);
	}

//Par nom
	@GetMapping("/?name={name}")
	@ElementCollection
	public ResponseEntity<List<Barbecue>> getAllBarbecuesByName(@PathVariable String name) {
		List<Barbecue> barbecues = barbecueDao.findAllByName(name);
		return new ResponseEntity<>(barbecues, HttpStatus.OK);
	}

//par id
	@GetMapping("/?id={id}")
	public ResponseEntity<Barbecue> getBarbecueById(@PathVariable int id) {
		Barbecue barbecue = barbecueDao.findById(id);
		if (barbecue == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(barbecue, HttpStatus.OK);
	}

//On crée un barbecue
	@PostMapping
	public ResponseEntity<Barbecue> createBarbecue(@RequestBody Barbecue barbecue) {
		barbecueDao.save(barbecue);
		return new ResponseEntity<>(barbecue, HttpStatus.CREATED);

	}

//On associe un guest et un barbecue
	@PutMapping("/{barbecueId}/guest/{guestId}")
	public ResponseEntity<Barbecue> addGuestInBarbecue(@PathVariable int barbecueId, @PathVariable int guestId) {
		Barbecue barbecue = barbecueDao.findById(barbecueId);

		if (barbecue == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Guest guest = guestDao.findById(guestId);

		if (guest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		barbecue.addGuest(guest);
		guest.setId(guestId);
		guestDao.save(guest);
		barbecueDao.save(barbecue);
		return new ResponseEntity<>(barbecue, HttpStatus.OK);
	}

//On associe un barbecue et une merguez
	@PutMapping("/{barbecueId}/merguez/{merguezId}")
	public ResponseEntity<Barbecue> addMerguezInBarbecue(@PathVariable int barbecueId, @PathVariable int merguezId) {
		Barbecue barbecue = barbecueDao.findById(barbecueId);

		if (barbecue == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Merguez merguez = merguezDao.findById(merguezId);

		if (merguez == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//Quand barbecue et merguez, on ajoute la merguez au barbecue et on sauvegarde le barbecue puis envoi requête
		barbecue.addMerguez(merguez);
		merguez.setId(merguezId);
		merguezDao.save(merguez);
		barbecue.setId(barbecueId);
		barbecueDao.save(barbecue);
		return new ResponseEntity<>(barbecue, HttpStatus.OK);
	}

//Supprime le barbecue s'il existe
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBarbecue(@PathVariable int id) {
		Barbecue barbecue = barbecueDao.findById(id);
		if (barbecue == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		barbecueDao.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}