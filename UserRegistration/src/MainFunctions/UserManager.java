package MainFunctions;

import InputFunctions.DataInput;
import InputFunctions.InputChecker;
import RegisteredUnits.User;

import java.util.Collection;
import java.util.Scanner;

public class UserManager implements MainFunctions{
    private final String ERROR = "error";

    private DataInput dataInput = new DataInput();
    private InputChecker check = new InputChecker();
    private Scanner scan = new Scanner(System.in);
    private UserUpdater userUpdater = new UserUpdater();

    @Override
    public void getAllUsers() {
        System.out.println("\nСписок всех пользователей:");
        if(User.getUsers().values().size() == 0) {
            System.out.println("Список пуст");
        }
        for(User user : User.getUsers().values()) {
            System.out.println(User.toString(user));
        }
    }

    @Override
    public void getSpecificUser(String email) {
        System.out.println(User.toString(User.getUsers().get(email)));
    }

    @Override
    public void createNewUser() {
        User user = new User(dataInput.inputEmail(), dataInput.inputName(), dataInput.inputSurname(), dataInput.inputRoles(), dataInput.inputPhones());
        User.addNewUser(user);
        System.out.println("Новый пользователь успешно добавлен!\n" + User.toString(user));
    }

    @Override
    public void updateSpecificUser(String email) {
        User user = User.getUsers().get(email);
        System.out.println(User.toString(user));
        do {
            if(userUpdater.changeFieldData(user)) {
                System.out.println("""
                    
                    Желаете изменить что-то еще?
                    1) ДА
                    2) НЕТ""");
            }
        } while(yesOrNoChoice());
        System.out.println("Изменения сохранены!\n" + User.toString(user));
    }

    @Override
    public void deleteSpecificUser(String email) {
        System.out.println("\nВы уверены, что хотите удалить пользователя " + email + " ?\n" +
                "1) ДА\n" +
                "2) НЕТ");
        if(yesOrNoChoice()) {
            User.getUsers().remove(email);
            System.out.println("Пользователь " + email + " успешно удален!");
        }
    }

    @Override
    public void deleteAllUsers() {
        System.out.println("""
                
                Вы уверены, что хотите удалить всех пользователей?
                1) ДА
                2) НЕТ""");
        if(yesOrNoChoice()) {
            User.getUsers().clear();
            System.out.println("Все пользователи успешно удалены!");
        }
    }

    public String inputEmailForSearch() {
        Collection<User> userCollection = User.getUsers().values();
        String email;
        System.out.println("\nВведите email требуемого пользователя:");
        do {
            email = scan.nextLine();
            if (!check.emailCheck(email)) {
                System.out.println("Проверьте правильность написания адреса электронной почты.");
                email = ERROR;
            } else {
                boolean isExist = false;
                for(User user : userCollection) {
                    if (user.getEmail().equals(email)) {
                        isExist = true;
                        break;
                    }
                }
                if(!isExist) {
                    System.out.println("Указанного пользователя не существует!");
                    email = ERROR;
                }
            }
        } while(email.equals(ERROR));
        return email;
    }

    private boolean yesOrNoChoice() {
        String choice;
        do {
            choice = scan.nextLine();
            switch(choice) {
                case "1" -> choice = "YES";
                case "2" -> choice = "NO";
                default -> {
                    choice = ERROR;
                    System.out.println("Выберите вариант из предложенного списка.");
                }
            }
        } while(choice.equals(ERROR));
        return choice.equals("YES");
    }
}
