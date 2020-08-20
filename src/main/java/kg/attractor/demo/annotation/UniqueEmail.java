package kg.attractor.demo.annotation;


import kg.attractor.demo.annotation.validator.UniqueInnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueInnValidator.class)
public @interface UniqueEmail {
    String message() default "Этот email уже используется другим пользователем";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

