/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author hantsy
 */
public class PasswordValidator implements ConstraintValidator<Password, PasswordHolder> {

  @Override
  public void initialize(Password constraintAnnotation) { }

  @Override
  public boolean isValid(PasswordHolder value, ConstraintValidatorContext context) {
    boolean result;
    
    result = value.getPassword1().equals(value.getPassword2());

    return result;
  }

}
