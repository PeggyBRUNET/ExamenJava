package com.barbecue.barbecue.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Inheritance
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;

	protected String name;

	@JsonBackReference
	@ManyToOne
	protected Barbecue barbecue;
	
	@JsonBackReference(value="child")
	@OneToOne
	private ChildGuest child;

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
	
	public ChildGuest getChild() {
		return child;
	}

	public void setChild(ChildGuest child) {
		this.child = child;
	}

	public Guest invite(Guest guest) {
		System.out.println(this.name + " a invité " + guest.getName());
		return guest;
	}

}
