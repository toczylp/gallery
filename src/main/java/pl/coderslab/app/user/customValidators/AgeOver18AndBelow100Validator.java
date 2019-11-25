package pl.coderslab.app.user.customValidators;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
public class AgeOver18AndBelow100Validator implements ConstraintValidator<AgeOver18AndBelow100, LocalDate> {

    private final int MIN_AGE = 18;
    private final int MAX_AGE = 100;

    @Override
    public void initialize(AgeOver18AndBelow100 constraint) {
    }

    @Override
    public boolean isValid(LocalDate yearOfBirthCandidate, ConstraintValidatorContext constraintValidatorContext) {

        Period period = Period.between(yearOfBirthCandidate ,LocalDate.now());

        return period.getYears() > MIN_AGE && period.getYears() < MAX_AGE;

    }
}
