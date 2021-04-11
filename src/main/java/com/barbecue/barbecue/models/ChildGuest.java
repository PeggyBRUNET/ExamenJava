package com.barbecue.barbecue.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ChildGuest extends Guest {

	@JsonManagedReference(value = "child")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Guest parent;

	public ChildGuest() {
	}

	public ChildGuest(int id, String name, Guest parent) {
		this.parent = parent;
	}

	public Guest getParent() {
		return parent;
	}

	public void setParent(Guest parent) {
		this.parent = parent;
	}

	public void play() {
		System.out.println(this.name + " joue dans le jardin sous l'oeil attendri de " + this.parent.getName());
	}

}
