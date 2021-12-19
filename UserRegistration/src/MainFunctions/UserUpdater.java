package MainFunctions;

import InputFunctions.DataInput;
import RegisteredUnits.User;

import java.util.Scanner;

public class UserUpdater {
    private DataInput dataInput = new DataInput();
    private Scanner scan = new Scanner(System.in);

    public boolean changeFieldData(User user) {
        String choice;
        boolean isChanged = true;
        System.out.println("""
                
                Какое поле вы хотите изменить?
                (email - уникальное поле, его изменять нельзя)
                1) Имя
                2) Фамилия
                3) Роли
                4) Телефоны
                5) Выйти в главное меню""");
        do {
            choice = scan.nextLine();
            switch (choice) {
                case "1" -> user.setName(dataInput.inputName());
                case "2" -> user.setSurname(dataInput.inputSurname());
                case "3" -> user.setRoles(dataInput.inputRoles());
                case "4" -> user.setPhones(dataInput.inputPhones());
                case "5" -> isChanged = false;
                default -> {
                    choice = "error";
                    System.out.println("Выберите вариант из предложенного списка.");
                }
            }
        } while(choice.equals("error"));
        return isChanged;
    }
}
