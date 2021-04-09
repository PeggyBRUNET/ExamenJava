package com.barbecue.barbecue.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Merguez implements Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@JsonBackReference
	@ManyToOne
	private Barbecue barbecue;

	public Merguez() {
	}

	public Merguez(int id, String name, Barbecue barbecue) {
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

	public String cook() {
		return "La merguez grille!";
	}

	public String eat() {
		return "Cette merguez est délicieuse avec de la moutarde.";
	}

}
