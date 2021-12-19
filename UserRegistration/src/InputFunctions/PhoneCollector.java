package InputFunctions;

import RegisteredUnits.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class PhoneCollector {
    private final String ERROR = "error";

    private InputChecker check = new InputChecker();
    private Scanner scan = new Scanner(System.in);

    public ArrayList<String> collectPhones() {
        System.out.println("\nУ каждого пользователя может быть не более 3-ех уникальных номеров телефона.");
        ArrayList<String> phones = new ArrayList<>();
        String phone = addPhone(phones);
        phones.add(phone);
        if(oneMorePhone()) {
            phone = addPhone(phones);
            phones.add(phone);
            if(oneMorePhone()) {
                phone = addPhone(phones);
                phones.add(phone);
            }
        }
        return phones;
    }

    private String addPhone(ArrayList<String> phones) {
        System.out.println("\nВведите номер мобильного телефона в формате 375XXXXXXXXX:");
        String phone;
        do {
            phone = scan.nextLine();
            if(check.phoneCheck(phone)) {
                if(isUnique(phone, phones)) {
                    System.out.println("Номер принят!");
                } else {
                    System.out.println("Введенный номер телефона уже указан другим пользователем, используйте другой.");
                    phone = ERROR;
                }
            } else {
                System.out.println("Перепроверьте введенный номер и попробуйте еще раз.");
                phone = ERROR;
            }
        } while(phone.equals(ERROR));
        return phone;
    }

    private boolean isUnique(String phone, ArrayList<String> phones) {
        Collection<User> userCollection = User.getUsers().values();
        for(User user : userCollection) {
            for(String userPhone : user.getPhones()) {
                if(userPhone.equals(phone)) {
                    return false;
                }
            }
        }
        for(String addedPhone : phones) {
            if(addedPhone.equals(phone)) {
                return false;
            }
        }
        return true;
    }

    private boolean oneMorePhone() {
        String choice;
        System.out.println("""
                
                Добавить еще один номер телефона?
                1) ДА
                2) НЕТ""");
        do {
            choice = scan.nextLine();
            switch (choice) {
                case "1" -> choice = "YES";
                case "2" -> choice = "NO";
                default -> {
                    choice = ERROR;
                    System.out.println("Выберите вариант ДА или НЕТ.");
                }
            }
        } while(choice.equals(ERROR));
        return choice.equals("YES");
    }
}
