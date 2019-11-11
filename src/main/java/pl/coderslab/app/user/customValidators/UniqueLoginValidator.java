package pl.coderslab.app.user.customValidators;

import pl.coderslab.app.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private UserService userService;

    public UniqueLoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueLogin constraint) {

    }

    @Override
    public boolean isValid(String loginCandidate, ConstraintValidatorContext constraintValidatorContext) {

        return loginCandidate != null && (userService.findByUserName(loginCandidate) == null || userService.findByUserName(loginCandidate).getLogin() == loginCandidate);

    }
}
