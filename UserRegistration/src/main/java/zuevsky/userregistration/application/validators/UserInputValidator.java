package zuevsky.userregistration.application.validators;

import zuevsky.userregistration.data.repository.UsersRepository;
import zuevsky.userregistration.domen.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputValidator implements RegexValidator {
    private static final String EMAIL_REGEX =
            "^([a-z0-9_\\.-]{3,20})@([a-z0-9_\\.-]{2,10})\\.([a-z\\.]{2,6})$";
    private static final String NAME_REGEX = "^[A-Za-zА-Яа-я]{2,20}$";
    private static final String SURNAME_REGEX = "^[A-Za-zА-Яа-я]{2,25}$";

    public static String getEmailRegex() {
        return EMAIL_REGEX;
    }

    private static final String PHONE_REGEX = "^375[0-9]{9}$";

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean validateRegex(String field, String regex) {
        switch (regex) {
            case EMAIL_REGEX -> pattern = Pattern.compile(EMAIL_REGEX);
            case NAME_REGEX -> pattern = Pattern.compile(NAME_REGEX);
            case SURNAME_REGEX -> pattern = Pattern.compile(SURNAME_REGEX);
            case PHONE_REGEX -> pattern = Pattern.compile(PHONE_REGEX);
        }
        matcher = pattern.matcher(field);
        return matcher.find();
    }

    public boolean validateEmail(String email, Collection<User> userCollection) {
        boolean success = true;
        if (validateRegex(email, UserInputValidator.EMAIL_REGEX)) {
            for (User user : userCollection) {
                if (user.getEmail().equals(email)) {
                    System.out.println("Введенный адрес электронной" +
                            " почты уже указан другим пользователем.");
                    success = false;
                    break;
                }
            }
        } else {
            System.out.println("Проверьте правильность написания адреса" +
                    " электронной почты.");
            success = false;
        }
        return success;
    }

    public boolean validateName(String name) {
        boolean success = true;
        if (!validateRegex(name, UserInputValidator.NAME_REGEX)) {
            System.out.println("Проверьте правильность написания имени.");
            success = false;
        }
        return success;
    }

    public boolean validateSurname(String surname) {
        boolean success = true;
        if (!validateRegex(surname, UserInputValidator.SURNAME_REGEX)) {
            System.out.println("Проверьте правильность написания фамилии.");
            success = false;
        }
        return success;
    }

    public boolean validatePhone(String phone, ArrayList<String> phones) {
        boolean success = true;
        if (validateRegex(phone, UserInputValidator.PHONE_REGEX)) {
            if (phoneUniqueValidate(phone, phones)) {
                System.out.println("Номер принят!");
            } else {
                System.out.println("Введенный номер телефона " +
                        "уже указан другим пользователем, используйте другой.");
                success = false;
            }
        } else {
            System.out.println("Перепроверьте введенный номер" +
                    " и попробуйте еще раз.");
            success = false;
        }
        return success;
    }

    private boolean phoneUniqueValidate(String phone, ArrayList<String> phones) {
        Collection<User> userCollection = UsersRepository.getUsers().values();
        for (User user : userCollection) {
            for (String userPhone : user.getPhones()) {
                if (userPhone.equals(phone)) {
                    return false;
                }
            }
        }
        for (String addedPhone : phones) {
            if (addedPhone.equals(phone)) {
                return false;
            }
        }
        return true;
    }
}
