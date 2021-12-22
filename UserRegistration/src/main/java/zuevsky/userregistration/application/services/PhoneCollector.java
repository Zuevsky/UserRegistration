package zuevsky.userregistration.application.services;

import zuevsky.userregistration.application.validators.UserInputValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneCollector {
    private UserInputValidator validator = new UserInputValidator();
    private Scanner scan = new Scanner(System.in);

    public ArrayList<String> collectPhones(int phoneNumbers) {
        ArrayList<String> phones = new ArrayList<>();
        String phone;
        for (int i = 1; i <= phoneNumbers; i++) {
            phone = addPhone(phones);
            phones.add(phone);
            if (!oneMorePhone()) {
                break;
            }
        }
        return phones;
    }

    private String addPhone(ArrayList<String> phones) {
        System.out.println("\nВведите номер мобильного телефона " +
                "в формате 375XXXXXXXXX:");
        String phone;
        do {
            phone = scan.nextLine();
        } while (!validator.validatePhone(phone, phones));
        return phone;
    }

    private boolean oneMorePhone() {
        System.out.println("Добавить еще один номер телефона?");
        return InputUtils.yesOrNoChoice();
    }
}
