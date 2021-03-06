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
import com.barbecue.barbecue.dao.HostDao;
import com.barbecue.barbecue.models.Barbecue;
import com.barbecue.barbecue.models.Host;

@RestController
@RequestMapping("/hosts")
public class HostController {

	private final HostDao hostDao;
	private final BarbecueDao barbecueDao;

	@Autowired
	public HostController(HostDao hostDao, BarbecueDao barbecueDao) {
		this.hostDao = hostDao;
		this.barbecueDao = barbecueDao;
	}

	@GetMapping
	@ElementCollection
	public ResponseEntity<List<Host>> getAllHosts() {
		List<Host> hosts = hostDao.findAll();
		return new ResponseEntity<>(hosts, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Host> getHostById(@PathVariable int id) {
		Host host = hostDao.findById(id);

		if (host == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(host, HttpStatus.OK);
	}

//renvoie les barbecues de cet host
	@GetMapping("/{id}/barbecue")
	@ElementCollection
	public ResponseEntity<List<Barbecue>> getBarbecueByHost(@PathVariable int id) {
		Host host = hostDao.findById(id);

		if (host == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<Barbecue> barbecues = barbecueDao.findAllByHost(host);

		return new ResponseEntity<>(barbecues, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Host> createHost(@RequestBody Host host) {
		Host addedHost = hostDao.save(host);
		return new ResponseEntity<>(addedHost, HttpStatus.CREATED);
	}

//associe un barbecue qui existe d??j?? ?? un h??te qui existe d??j??
	@PutMapping("/{hostId}/barbecue/{barbecueId}")
	public ResponseEntity<Host> addBarbecueInHost(@PathVariable int hostId, @PathVariable int barbecueId) {
		Host host = hostDao.findById(hostId);

		if (host == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Barbecue barbecue = barbecueDao.findById(barbecueId);

		if (barbecue == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//Quand il y'a un h??te et un barbecue, r??cup??ration du barbecue de host, on ajoute barbecue ?? l'h??te et on sauvegarde l'h??te puis envoi requ??te            
		host.addBarbecue(barbecue);
		host.setId(hostId);
		barbecue.setId(barbecueId);
		barbecueDao.save(barbecue);
		hostDao.save(host);
		return new ResponseEntity<>(host, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteHost(@PathVariable int id) {
		Host host = hostDao.findById(id);
		if (host == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		hostDao.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
