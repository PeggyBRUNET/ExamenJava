package com.barbecue.barbecue.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Inheritance
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@JsonBackReference
	@ManyToOne
	private Barbecue barbecue;

	public Guest() {
	}

	public Guest(int id, String name, Barbecue barbecue) {
		this.id = id;
		this.name = name;
		this.barbecue = barbecue;

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

	public Barbecue getBarbecue() {
		return barbecue;
	}

	public void setBarbecue(Barbecue barbecue) {
		this.barbecue = barbecue;
	}

	public Guest invite(Guest guest) {
		System.out.println(this.name + " a invité " + guest.getName());
		return guest;
	}

}
