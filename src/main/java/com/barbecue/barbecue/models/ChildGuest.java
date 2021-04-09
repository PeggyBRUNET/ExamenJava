package com.barbecue.barbecue.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ChildGuest extends Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@JsonBackReference
	@ManyToOne
	private Guest parent;

	public ChildGuest() {
	}

	public ChildGuest(int id, String name, Guest parent) {
		this.id = id;
		this.name = name;
		this.parent=parent;
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
	


	public Guest getParent() {
		return parent;
	}

	public void setParent(Guest parent) {
		this.parent = parent;
	}

	public String play() {
		return this.name + " joue dans le jardin";
	}

}
