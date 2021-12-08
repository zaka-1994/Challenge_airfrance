package com.example.airfrance.challenge.validator;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.airfrance.challenge.entities.User;

public class CountryValidator implements ConstraintValidator<FrenchAdultValidation, User> {
    
	String countryFrance = "france";
	
	/**
	 * This method check if country is France and the age is greater than 18 years 
	 */
	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
		Period period = Period.between(user.getDateOfBirth(), LocalDate.now());
		return countryFrance.equals(user.getCountry().toLowerCase()) && period.getYears() >= 18;
	}
	

}
