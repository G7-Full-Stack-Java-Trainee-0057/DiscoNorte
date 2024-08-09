package cl.playground.disconorte.utility.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ValidatorManager {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

        /**
     * Validates the given entity using the JSR-303 Bean Validation API.
     *
     * @param  entity  the entity to be validated
     * @param  <T>     the type of the entity
     * @throws IllegalArgumentException  if the entity is not valid
     */
    public static <T> void validateEntity(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<T> violation : violations) {
                sb.append(violation.getPropertyPath()).append(" ").append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException("Entity validation failed:\n" + sb.toString());
        }
    }
}
