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
import com.barbecue.barbecue.dao.ChildGuestDao;
import com.barbecue.barbecue.dao.GuestDao;
import com.barbecue.barbecue.dao.MerguezDao;
import com.barbecue.barbecue.models.Barbecue;
import com.barbecue.barbecue.models.ChildGuest;
import com.barbecue.barbecue.models.Guest;
import com.barbecue.barbecue.models.Merguez;

@RestController
@RequestMapping("/guests")
public class GuestController {

	private final GuestDao guestDao;
	private final BarbecueDao barbecueDao;
	private final MerguezDao merguezDao;
	private final ChildGuestDao childGuestDao;

	@Autowired
	public GuestController(GuestDao guestDao, BarbecueDao barbecueDao, MerguezDao merguezDao,
			ChildGuestDao childGuestDao) {
		this.guestDao = guestDao;
		this.barbecueDao = barbecueDao;
		this.merguezDao = merguezDao;
		this.childGuestDao = childGuestDao;
	}

	@GetMapping
	@ElementCollection
	public ResponseEntity<List<Guest>> getAllGuests() {
		List<Guest> guests = guestDao.findAll();
		return new ResponseEntity<>(guests, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Guest> getById(@PathVariable int id) {
		Guest guest = guestDao.findById(id);
		if (guest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(guest, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
		guestDao.save(guest);
		return new ResponseEntity<>(guest, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteGuest(@PathVariable int id) {
		Guest guest = guestDao.findById(id);
		if (guest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		guestDao.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

// Association id guests
	@PutMapping("/{guestId}/invite/{guestId2}")
	public ResponseEntity<Barbecue> inviteGuestToBarbecue(@PathVariable int guestId, @PathVariable int guestId2) {
		Guest guest = guestDao.findById(guestId);

		if (guest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Guest guest2 = guestDao.findById(guestId2);

		if (guest2 == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//Quand guest invite guest2, récupération du barbecue de guest, on ajoute guest et on sauvegarde le barbecue puis envoi requête
		Barbecue barbecue = guest.getBarbecue();
		System.out.println(guest.getName() + " invite" + guest2.getName() + " au barbecue de " + barbecue.getName());
		barbecue.addGuest(guest2);
		barbecueDao.save(barbecue);
		return new ResponseEntity<Barbecue>(barbecue, HttpStatus.OK);
	}

//Association merguez et invité
	@PutMapping("/{guestId}/bring/{merguezId}")
	public ResponseEntity<Barbecue> bringMerguezToBarbecue(@PathVariable int guestId, @PathVariable int merguezId) {
		Guest guest = guestDao.findById(guestId);

		if (guest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Merguez merguez = merguezDao.findById(merguezId);

		if (merguez == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

// Quand guest apporte une merguez, récupération du barbecue de guest, on ajoute
// une merguez au barbecue et on sauvegarde
// le barbecue,on dit quel invité ajoute une merguez puis envoi requête
		Barbecue barbecue = guest.getBarbecue();
		barbecue.addMerguez(merguez);
		barbecueDao.save(barbecue);
		System.out.println(guest.getName() + " ajoute une merguez. ");
		return new ResponseEntity<Barbecue>(barbecue, HttpStatus.OK);
	}

	@PutMapping("/{guestId}/child/{childId}")
	public ResponseEntity<Guest> bringChildToBarbecue(@PathVariable int guestId, @PathVariable int childId) {
		Guest guest = guestDao.findById(guestId);

		if (guest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		ChildGuest child = childGuestDao.findById(childId);

		if (child == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

// Quand guest apporte une merguez, récupération du barbecue de guest, on ajoute
// une merguez au barbecue et on sauvegarde
// le barbecue,on dit quel invité ajoute une merguez puis envoi requête
		child.setParent(guest);
		guest.setChild(child);
		child.play();
		childGuestDao.save(child);
		guestDao.save(guest);
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}

}
