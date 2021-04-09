package com.barbecue.barbecue.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Barbecue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String date;
	private String address;
	private String city;
	private String country;

	@JsonBackReference
	@ManyToOne
	private Host host;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ElementCollection
	private List<Merguez> merguez;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ElementCollection
	private List<Guest> guests;

	public Barbecue() {
	}

	public Barbecue(String name, String date, String address, String city, String country, Host host) {
		this.name = name;
		this.date = date;
		this.address = address;
		this.city = city;
		this.country = country;
		this.merguez = new ArrayList<>();
		this.guests = new ArrayList<>();
		this.host = host;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public List<Merguez> getMerguez() {
		return merguez;
	}

	public void setMerguez(List<Merguez> merguez) {
		this.merguez = merguez;
	}

	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

// Permet d'inviter une personne
	public void addGuest(Guest guest) {
		guest.setBarbecue(this);
		this.guests.add(guest);
		System.out.println(guest.getName() + " a rejoint le barbecue : \"" + this.name + "\"");
	}
// Permet de donner le nom spécifique de la merguez qui appartient à un barbecue
	public void addMerguez(Merguez merguez) {
		merguez.setBarbecue(this);
		this.merguez.add(merguez);
		System.out.println(merguez.getName() + " fait partie du barbecue de " + this.name);
	}

}