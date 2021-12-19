package InputFunctions;

import RegisteredUnits.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class DataInput implements DataInputFunctions{
    private final String ERROR = "error";

    private Scanner scan = new Scanner(System.in);
    private InputChecker check = new InputChecker();
    private RoleSwitcher roleSwitcher = new RoleSwitcher();
    private PhoneCollector phoneCollector = new PhoneCollector();

    @Override
    public String inputEmail() {
        Collection<User> userCollection = User.getUsers().values();
        String email;
        System.out.println("\nВведите адрес электронной почты:");
        do {
            email = scan.nextLine();
            if (check.emailCheck(email)) {
                for(User user : userCollection) {
                    if(user.getEmail().equals(email)) {
                        System.out.println("Введенный адрес электронной почты уже указан другим пользователем.");
                        email = ERROR;
                    }
                }
            } else {
                System.out.println("Проверьте правильность написания адреса электронной почты.");
                email = ERROR;
            }
        } while(email.equals(ERROR));
        System.out.println("Email " + email + " принят!");
        return email;
    }

    @Override
    public String inputName() {
        String name;
        System.out.println("\nВведите имя (только буквы от 2 до 20 смв):");
        do {
            name = scan.nextLine();
            if (!check.nameCheck(name)) {
                System.out.println("Проверьте правильность написания имени.");
                name = ERROR;
            }
        } while(name.equals(ERROR));
        System.out.println("Имя " + name + " принято!");
        return name;
    }

    @Override
    public String inputSurname() {
        String surname;
        System.out.println("\nВведите фамилию (только буквы от 2 до 25 смв):");
        do {
            surname = scan.nextLine();
            if (!check.surnameCheck(surname)) {
                System.out.println("Проверьте правильность написания фамилии.");
                surname = ERROR;
            }
        } while(surname.equals(ERROR));
        System.out.println("Фамилия " + surname + " принята!");
        return surname;
    }

    @Override
    public ArrayList<String> inputRoles() {
        ArrayList<String> roles = roleSwitcher.roleConstructor();
        String stringRoles = "";
        for(int i = 0; i < roles.size(); i++) {
            if(i == roles.size() - 1) {
                stringRoles = stringRoles.concat(roles.get(i) + ".");
            } else {
                stringRoles = stringRoles.concat(roles.get(i) + ", ");
            }
        }
        System.out.println("Вы выбрали следующие роли: " + stringRoles);
        return roles;
    }

    @Override
    public ArrayList<String> inputPhones() {
        ArrayList<String> phones = phoneCollector.collectPhones();
        String stringPhones = "";
        for(int i = 0; i < phones.size(); i++) {
            if(i == phones.size() - 1) {
                stringPhones = stringPhones.concat(phones.get(i) + ".");
            } else {
                stringPhones = stringPhones.concat(phones.get(i) + ", ");
            }
        }
        System.out.println("Вы добавили следующие номера телефонов: " + stringPhones);
        return phones;
    }
}
