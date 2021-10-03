package com.kslima.catalog.services.validations;

import com.kslima.catalog.dto.UserInsertDTO;
import com.kslima.catalog.entities.User;
import com.kslima.catalog.repositories.UserRepository;
import com.kslima.catalog.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserInsertDTO userInsertDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User user = userRepository.findByEmail(userInsertDTO.getEmail());

        if (user != null) {
            list.add(new FieldMessage("email", "Email já existe"));
        }

        list.forEach(e -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        });

        return list.isEmpty();
    }
}
