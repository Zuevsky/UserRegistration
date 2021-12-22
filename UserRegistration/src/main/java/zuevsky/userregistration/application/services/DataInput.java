package zuevsky.userregistration.application.services;

import zuevsky.userregistration.application.validators.UserInputValidator;
import zuevsky.userregistration.data.repository.UsersRepository;
import zuevsky.userregistration.domen.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class DataInput {
    private Scanner scan = new Scanner(System.in);
    private UserInputValidator validator = new UserInputValidator();
    private RoleSwitcher roleSwitcher = new RoleSwitcher();
    private PhoneCollector phoneCollector = new PhoneCollector();

    public String inputEmail() {
        Collection<User> userCollection = UsersRepository.getUsers().values();
        String email;
        System.out.println("\nВведите адрес электронной почты:");
        do {
            email = scan.nextLine();
        } while (!validator.validateEmail(email, userCollection));
        System.out.println("Email " + email + " принят!");
        return email;
    }

    public String inputName() {
        String name;
        System.out.println("\nВведите имя (только буквы от 2 до 20 смв):");
        do {
            name = scan.nextLine();

        } while (!validator.validateName(name));
        System.out.println("Имя " + name + " принято!");
        return name;
    }

    public String inputSurname() {
        String surname;
        System.out.println("\nВведите фамилию (только буквы от 2 до 25 смв):");
        do {
            surname = scan.nextLine();
        } while (!validator.validateSurname(surname));
        System.out.println("Фамилия " + surname + " принята!");
        return surname;
    }

    public ArrayList<String> inputRoles() {
        ArrayList<String> roles = roleSwitcher.roleConstructor();
        String stringRoles = "";
        for (int i = 0; i < roles.size(); i++) {
            if (i == roles.size() - 1) {
                stringRoles = stringRoles.concat(roles.get(i) + ".");
            } else {
                stringRoles = stringRoles.concat(roles.get(i) + ", ");
            }
        }
        System.out.println("Вы выбрали следующие роли: " + stringRoles);
        return roles;
    }

    public ArrayList<String> inputPhones() {
        System.out.println("\nУ каждого пользователя может быть" +
                " не более 3-ех уникальных номеров телефона.");
        ArrayList<String> phones = phoneCollector.collectPhones(3);
        String stringPhones = "";
        for (int i = 0; i < phones.size(); i++) {
            if (i == phones.size() - 1) {
                stringPhones = stringPhones.concat(phones.get(i) + ".");
            } else {
                stringPhones = stringPhones.concat(phones.get(i) + ", ");
            }
        }
        System.out.println("Вы добавили следующие номера телефонов: "
                + stringPhones);
        Collections.sort(phones);
        return phones;
    }
}
