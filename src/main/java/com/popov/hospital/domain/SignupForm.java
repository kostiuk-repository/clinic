package com.popov.hospital.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignupForm {
    @NotEmpty
    @Size(min=3, max=30)
    private String username = "";

    @NotEmpty
    @Size(min=4, max=30)
    private String password = "";

    @NotEmpty
    @Size(min=4, max=30)
    private String passwordCheck = "";

    @NotEmpty
    private String role = "DOCTOR";

	@Column(name = "post")
	private String post;

	@Column(name = "address")
	private String address;

	@Column(name = "fio")
	private String fio;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "telephone_number", unique = true)
	private String telephoneNumber;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
}
