import FileWorker.FileWorker;
import MainFunctions.UserManager;
import RegisteredUnits.User;
import java.util.Scanner;

public class UserRegistration {
    static Scanner scan = new Scanner(System.in);
    static UserManager userManager = new UserManager();
    static FileWorker fileWorker = new FileWorker();

    public static void main(String[] args) {
        try {
            User.setUsers(fileWorker.getUsersFromFile());
        } catch(Exception fileWorkerException) {
            System.out.println("Ошибка получения данных из файла! Попробуйте перезапустить приложение.");
        }
        String choice;
        boolean isQuit = false;
        do {
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
                    case "1" -> userManager.getAllUsers();
                    case "2" -> userManager.getSpecificUser(userManager.inputEmailForSearch());
                    case "3" -> userManager.createNewUser();
                    case "4" -> userManager.updateSpecificUser(userManager.inputEmailForSearch());
                    case "5" -> userManager.deleteSpecificUser(userManager.inputEmailForSearch());
                    case "6" -> userManager.deleteAllUsers();
                    case "7" -> {
                        System.out.println();
                        isQuit = true;
                    }
                    default -> {
                        choice = "error";
                        System.out.println("Выберите вариант из предложенного списка.");
                    }
                }
            } while(choice.equals("error"));
        } while(!isQuit);
        try {
            fileWorker.saveUsersToFile();
        } catch(Exception fileWorkerException) {
            System.out.println("Ошибка записи данных в файл!");
        }
    }
}
