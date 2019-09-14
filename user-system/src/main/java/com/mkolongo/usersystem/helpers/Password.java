package com.mkolongo.usersystem.helpers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Password {

    int minLength();

    int maxLength();

    boolean containsDigits() default false;

    boolean containsLowercase() default false;

    boolean containsUppercase() default false;

    boolean containsSpecialSymbols() default false;

    String message() default "";
}
