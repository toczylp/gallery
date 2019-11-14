package pl.coderslab.app.user.customValidators;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
public class AgeOver18AndBelow100Validator implements ConstraintValidator<AgeOver18AndBelow100, LocalDate> {

    @Override
    public void initialize(AgeOver18AndBelow100 constraint) {
    }

    @Override
    public boolean isValid(LocalDate yearOfBirthCandidate, ConstraintValidatorContext constraintValidatorContext) {

        Period period = Period.between(yearOfBirthCandidate ,LocalDate.now());

        return period.getYears() > 18 && period.getYears() < 100;

    }
}
