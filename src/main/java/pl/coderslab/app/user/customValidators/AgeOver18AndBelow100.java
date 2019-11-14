package pl.coderslab.app.user.customValidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeOver18AndBelow100Validator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeOver18AndBelow100 {
    String message() default "{You must be over 18Y old and below 100Y to sign up}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
