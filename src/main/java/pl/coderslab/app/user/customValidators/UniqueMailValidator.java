package pl.coderslab.app.user.customValidators;

import pl.coderslab.app.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueMailValidator implements ConstraintValidator<UniqueMail, String> {

    //dlaczego nie można użyc adnotacji Lombok RequiredArgParam...

    private UserService userService;

    public UniqueMailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueMail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String mailCandidate, ConstraintValidatorContext constraintValidatorContext) {

        return mailCandidate != null && (userService.findByMail(mailCandidate).isEmpty());
    }
}
