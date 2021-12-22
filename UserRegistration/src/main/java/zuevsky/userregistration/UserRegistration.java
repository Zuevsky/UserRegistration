package zuevsky.userregistration;


import zuevsky.userregistration.application.services.UserManager;
import zuevsky.userregistration.data.UsersFileWorker;
import zuevsky.userregistration.data.repository.UsersRepository;


public class UserRegistration {
    static UserManager userManager = new UserManager();
    static UsersFileWorker fileWorker = new UsersFileWorker();

    public static void main(String[] args) {
        try {
            UsersRepository.setUsers(fileWorker.getDataFromFile());
        } catch (Exception fileWorkerException) {
            System.out.println("Ошибка получения данных из файла!" +
                    " Попробуйте перезапустить приложение.");
        }
        boolean isQuit = false;
        while (!isQuit) {
            isQuit = userManager.mainMenuControl();
        }
        try {
            fileWorker.saveDataToFile();
        } catch (Exception fileWorkerException) {
            System.out.println("Ошибка записи данных в файл!");
        }
    }
}
