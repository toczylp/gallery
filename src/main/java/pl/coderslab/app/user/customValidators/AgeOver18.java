package pl.coderslab.app.user.customValidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeOver18Validator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeOver18 {
    String message() default "{You must be over 18Y old to sign up}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
