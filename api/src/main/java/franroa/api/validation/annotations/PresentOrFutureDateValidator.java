package franroa.api.validation.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Timestamp;
import java.time.Instant;

public class PresentOrFutureDateValidator implements ConstraintValidator<PresentOrFutureDate, String> {
    private PresentOrFutureDate annotation;

    @Override
    public void initialize(PresentOrFutureDate annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Instant delayedInstant = Instant.now().plusSeconds(annotation.plusSeconds());
            return Timestamp.valueOf(valueForValidation).after(Timestamp.from(delayedInstant));
        } catch (Exception e) {
            return false;
        }
    }
}
