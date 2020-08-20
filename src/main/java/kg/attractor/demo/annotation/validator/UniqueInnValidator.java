package kg.attractor.demo.annotation.validator;

import kg.attractor.demo.annotation.UniqueEmail;
import kg.attractor.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueInnValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepo userRepo;

    public void initialize(UniqueEmail constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        try {
            return !userRepo.existsByEmail(email);
        }catch (NumberFormatException ignored){}

        return true;
    }
}