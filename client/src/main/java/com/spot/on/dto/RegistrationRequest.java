package com.spot.on.dto;

import javax.validation.constraints.NotBlank;

public class RegistrationRequest {

	@NotBlank(message = "Name cannot be blank")
	private String name;
	@NotBlank(message = "Surname cannot be blank")
	private String surname;
	@NotBlank(message = "Type is required")
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
