package br.com.uniamerica.gpc.GPCbackend.annotations;

import br.com.uniamerica.gpc.GPCbackend.configuration.UniqueValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValueValidator.class)
public @interface UniqueValue {
    String message() default "Id Patrimonio existe.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
