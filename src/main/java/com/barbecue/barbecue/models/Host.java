package com.barbecue.barbecue.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Host {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Barbecue> barbecues = new ArrayList<Barbecue>();

	public Host() {
	}

	public Host(int id, String name) {
		this.id = id;
		this.name = name;

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

	public List<Barbecue> getBarbecues() {
		return barbecues;
	}

	public void setBarbecues(List<Barbecue> barbecues) {
		this.barbecues = barbecues;
	}

	public void addBarbecue(Barbecue barbecue) {
		barbecue.setHost(this);
		this.barbecues.add(barbecue);
	};
}
