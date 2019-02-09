package franroa.validation.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<Enum, String> {
    private Enum annotation;

    @Override
    public void initialize(Enum annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();

        if (annotation.allowEmpty() && (valueForValidation == null || valueForValidation.equals(""))) {
            return true;
        }

        if (valueForValidation == null || enumValues == null) {
            return true;
        }

        for (Object enumValue : enumValues) {
            if (valuesMatch(valueForValidation, enumValue)) {
                return true;
            }
        }

        return false;
    }

    private boolean valuesMatch(String valueForValidation, Object valueInList) {
        if (annotation.ignoreCase()) {
            return valueForValidation.equalsIgnoreCase(valueInList.toString());
        }

        return valueForValidation.equals(valueInList.toString());
    }
}
