package zuevsky.userregistration.application.validators;

public interface RegexValidator {
    boolean validateRegex(String field, String regex);
}