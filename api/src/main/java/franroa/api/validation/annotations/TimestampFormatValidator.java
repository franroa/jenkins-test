package franroa.api.validation.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimestampFormatValidator implements ConstraintValidator<Timestamp, String> {
    private Timestamp annotation;

    @Override
    public void initialize(Timestamp annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
        try {
            java.sql.Timestamp.valueOf(valueForValidation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
