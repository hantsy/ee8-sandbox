/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;

/**
 *
 * @author hantsy
 */
@Constraint(validatedBy=PasswordValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
@interface Password {

    String message() default "Password fields must match";
    Class[] groups() default {};
    Class[] payload() default {};
}
