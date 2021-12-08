/**
 * 
 */
package com.example.airfrance.challenge.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.airfrance.challenge.validator.FrenchAdultValidation;

/**
 * User Entity to define attributes
 * @author zhaimi
 * @Date 05/12/2021
 */

@Entity
@FrenchAdultValidation
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@NotNull(message = "validation.message.userName.mandatory")
	@Size(min = 2, message = "{validation.message.userName.min.char}")
	@Column(name="userName")
	private String userName;
	
	@NotNull(message = "{validation.message.birthDay.mandatory}")
        @Past(message = "{validation.message.birthDay.past}")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dateOfBirth")
	private LocalDate dateOfBirth;
	
	
	@Column(name="country")
	private String country;
	
	@Pattern(regexp="(^$|[0-9]{10})", message = "{validation.message.phone.valid}")
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	@Column(name="gender")
	private String gender;

	public User() {
		super();
	}

	public User(Integer id, String userName, LocalDate dateOfBirth, String country) {
		super();
		this.id = id;
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.country = country;
	}

	
	
	public User(Integer id, String userName, LocalDate dateOfBirth, String country, String phoneNumber, String gender) {
		super();
		this.id = id;
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
