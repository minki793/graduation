package ru.graduation.web.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.graduation.HasIdAndEmail;
import ru.graduation.model.User;
import ru.graduation.repository.UserRepository;
import ru.graduation.web.ExceptionInfoHandler;

@Component
public class UniqueMailValidator implements org.springframework.validation.Validator {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return HasIdAndEmail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HasIdAndEmail user = ((HasIdAndEmail) target);
        User dbUser = repository.getByEmail(user.getEmail().toLowerCase());
        if (dbUser != null && !dbUser.getId().equals(user.getId())) {
            errors.rejectValue("email", "Duplicate", ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL);
        }
    }
}

