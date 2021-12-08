/**
 * 
 */
package com.example.airfrance.challenge.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CountryValidator.class)
/**
 * @author zhaimi
 *
 */
public @interface FrenchAdultValidation {

	String message() default "{validation.message.adult.french}";

	Class<?>[] groups() default {};

	public abstract Class<? extends Payload>[] payload() default {};

}
