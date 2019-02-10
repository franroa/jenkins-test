package franroa.api.validation.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = {PresentOrFutureDateValidator.class})
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PresentOrFutureDate
{
    String message() default " Date has to be placed in the present or in the future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int plusSeconds();

    boolean ignoreCase() default false;

    boolean allowEmpty() default true;
}
