package br.com.uniamerica.gpc.GPCbackend.configuration;

import br.com.uniamerica.gpc.GPCbackend.annotations.UniqueValue;
import br.com.uniamerica.gpc.GPCbackend.repository.AtivoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    private AtivoRepository ativoRepository;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!isPostRequest()) {
            return true;
        }

        if (ativoRepository == null) {
            return true;
        }

        // Perform uniqueness check
        return !ativoRepository.existsByIdPatrimonio(value);
    }

    private boolean isPostRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return "POST".equals(request.getMethod());
    }
}
