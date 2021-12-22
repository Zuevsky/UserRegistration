package zuevsky.userregistration.application.services;

import zuevsky.userregistration.application.validators.UserInputValidator;
import zuevsky.userregistration.data.repository.UsersRepository;
import zuevsky.userregistration.domen.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager implements MainFunctions<String, User> {
    private DataInput dataInput = new DataInput();
    private UserInputValidator validator = new UserInputValidator();
    private Scanner scan = new Scanner(System.in);
    private UserUpdater userUpdater = new UserUpdater();

    public boolean mainMenuControl() {
        boolean isQuit = false;
        String choice;
        System.out.println("""
                                
                Главное меню:
                1) Показать всех пользователей
                2) Показать определенного пользователя
                3) Добавить нового пользователя
                4) Изменить определенного пользователя
                5) Удалить определенного пользователя
                6) Удалить всех пользователей
                7) ЗАКРЫТЬ ПРИЛОЖЕНИЕ""");
        do {
            choice = scan.nextLine();
            switch (choice) {
                case "1" -> getAll(UsersRepository.getUsers());
                case "2" -> getSpecific(UsersRepository.getUsers(),
                        inputEmailForSearch(UsersRepository.getUsers()));
                case "3" -> createNew(UsersRepository.getUsers());
                case "4" -> updateSpecific(UsersRepository.getUsers(),
                        inputEmailForSearch(UsersRepository.getUsers()));
                case "5" -> deleteSpecific(UsersRepository.getUsers(),
                        inputEmailForSearch(UsersRepository.getUsers()));
                case "6" -> deleteAll(UsersRepository.getUsers());
                case "7" -> {
                    System.out.println();
                    isQuit = true;
                }
                default -> {
                    choice = "error";
                    System.out.println("Выберите вариант из " +
                            "предложенного списка.");
                }
            }
        } while (choice.equals("error"));
        return isQuit;
    }

    @Override
    public void getAll(HashMap<String, User> userCollection) {
        System.out.println("\nСписок всех пользователей:");
        if (userCollection.values().size() == 0) {
            System.out.println("Список пуст");
        }
        for (User user : userCollection.values()) {
            System.out.println(User.toString(user));
        }
    }

    @Override
    public void getSpecific(HashMap<String, User> userCollection, String email) {
        System.out.println(User.toString(userCollection.get(email)));
    }

    @Override
    public void createNew(HashMap<String, User> userCollection) {
        User user = new User(dataInput.inputEmail(),
                dataInput.inputName(), dataInput.inputSurname(),
                dataInput.inputRoles(), dataInput.inputPhones());
        userCollection.put(user.getEmail(), user);
        System.out.println("Новый пользователь успешно добавлен!\n"
                + User.toString(user));
    }

    @Override
    public void updateSpecific(HashMap<String, User> userCollection, String email) {
        User user = userCollection.get(email);
        System.out.println(User.toString(user));
        do {
            if (userUpdater.changeFieldData(user)) {
                System.out.println("Желаете изменить что-то еще?");
            }
        } while (InputUtils.yesOrNoChoice());
        System.out.println("Изменения сохранены!\n" + User.toString(user));
    }

    @Override
    public void deleteSpecific(HashMap<String, User> userCollection, String email) {
        System.out.println("\nВы уверены, что хотите удалить пользователя "
                + email + " ?");
        if (InputUtils.yesOrNoChoice()) {
            userCollection.remove(email);
            System.out.println("Пользователь " + email + " успешно удален!");
        }
    }

    @Override
    public void deleteAll(HashMap<String, User> userCollection) {
        System.out.println("\nВы уверены, что хотите удалить всех пользователей?");
        if (InputUtils.yesOrNoChoice()) {
            userCollection.clear();
            System.out.println("Все пользователи успешно удалены!");
        }
    }

    private String inputEmailForSearch(HashMap<String, User> userCollection) {
        Collection<User> users = userCollection.values();
        String email;
        System.out.println("\nВведите email требуемого пользователя:");
        do {
            email = scan.nextLine();
            if (!validator.validateRegex(email, UserInputValidator.getEmailRegex())) {
                System.out.println("Проверьте правильность написания " +
                        "адреса электронной почты.");
                email = InputUtils.getError();
            } else {
                boolean isExist = false;
                for (User user : users) {
                    if (user.getEmail().equals(email)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    System.out.println("Указанного пользователя " +
                            "не существует!");
                    email = InputUtils.getError();
                }
            }
        } while (email.equals(InputUtils.getError()));
        return email;
    }
}
