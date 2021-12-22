package zuevsky.userregistration.application.services;

import java.util.Scanner;

public class InputUtils {
    private static final String ERROR = "error";
    private static final String NEXT = "next";

    private static Scanner scan = new Scanner(System.in);

    public static String getError() {
        return ERROR;
    }

    public static String getNext() {
        return NEXT;
    }

    public static boolean yesOrNoChoice() {
        System.out.println("""
                1) ДА
                2) НЕТ""");
        String choice;
        do {
            choice = scan.nextLine();
            switch (choice) {
                case "1" -> choice = "YES";
                case "2" -> choice = "NO";
                default -> {
                    choice = ERROR;
                    System.out.println("Выберите вариант из " +
                            "предложенного списка.");
                }
            }
        } while (choice.equals(ERROR));
        return choice.equals("YES");
    }
}
