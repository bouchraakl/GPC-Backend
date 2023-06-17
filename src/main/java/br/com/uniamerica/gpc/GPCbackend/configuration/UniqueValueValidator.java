package br.com.uniamerica.gpc.GPCbackend.configuration;

import br.com.uniamerica.gpc.GPCbackend.annotations.UniqueValue;
import br.com.uniamerica.gpc.GPCbackend.repository.AtivoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    private AtivoRepository ativoRepository;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ativoRepository == null) {
            return true;
        }

        // Perform uniqueness check
        return !ativoRepository.existsByIdPatrimonio(value);
    }
}
