package com.barbecue.barbecue.models;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Food {

	public String cook();

	public String eat();

	public int getId();

	public void setId(int id);

	public String getName();

	public void setName(String name);
}