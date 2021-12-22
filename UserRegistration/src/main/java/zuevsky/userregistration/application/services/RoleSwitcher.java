package zuevsky.userregistration.application.services;

import java.util.ArrayList;
import java.util.Scanner;

public class RoleSwitcher {
    private Scanner scan = new Scanner(System.in);

    private enum rolesLvl1 {
        USER,
        CUSTOMER
    }

    private enum rolesLvl2 {
        ADMIN,
        PROVIDER
    }

    private enum rolesLvl3 {
        SUPER_ADMIN
    }

    public ArrayList<String> roleConstructor() {
        ArrayList<String> roles = new ArrayList<>();
        System.out.println("\nДля выбора доступны роли 3-ех уровней. " +
                "По одной роли с 1 и 2 уровня, ЛИБО одна роль 3-го уровня.");
        String role = switchRoleLvl1();
        if (!role.equals(InputUtils.getNext())) {
            roles.add(role);
        }
        role = switchRoleLvl2();
        if (!role.equals(InputUtils.getNext())) {
            roles.add(role);
        }
        if (roles.size() == 0) {
            roles.add(switchRoleLvl3());
        }
        return roles;
    }

    private String switchRoleLvl1() {
        String role1Lvl;
        String choice;
        System.out.println("""
                                
                Выберите роль первого уровня(1шт), либо перейдите к ролям следующего уровня:
                1) USER
                2) CUSTOMER
                3) роли второго уровня""");
        do {
            choice = scan.nextLine();
            switch (choice) {
                case "1" -> role1Lvl = rolesLvl1.USER.toString();
                case "2" -> role1Lvl = rolesLvl1.CUSTOMER.toString();
                case "3" -> role1Lvl = InputUtils.getNext();
                default -> {
                    role1Lvl = InputUtils.getError();
                    System.out.println("Выберите вариант из предложенного списка.");
                }
            }
        } while (role1Lvl.equals(InputUtils.getError()));
        return role1Lvl;
    }

    private String switchRoleLvl2() {
        String role2Lvl;
        String choice;
        System.out.println("""
                                
                Выберите роль второго уровня(1шт), либо перейдите к ролям следующего уровня:
                1) ADMIN
                2) PROVIDER
                3) роли третьего уровня""");
        do {
            choice = scan.nextLine();
            switch (choice) {
                case "1" -> role2Lvl = rolesLvl2.ADMIN.toString();
                case "2" -> role2Lvl = rolesLvl2.PROVIDER.toString();
                case "3" -> role2Lvl = InputUtils.getNext();
                default -> {
                    role2Lvl = InputUtils.getError();
                    System.out.println("Выберите вариант из предложенного списка.");
                }
            }
        } while (role2Lvl.equals(InputUtils.getError()));
        return role2Lvl;
    }

    private String switchRoleLvl3() {
        String role3Lvl;
        String choice;
        System.out.println("""
                Выберите роль третьего уровня:
                1) SUPER_ADMIN""");
        do {
            choice = scan.nextLine();
            if (choice.equals("1")) {
                role3Lvl = rolesLvl3.SUPER_ADMIN.toString();
            } else {
                role3Lvl = InputUtils.getError();
                System.out.println("Выберите вариант из предложенного списка.");
            }
        } while (role3Lvl.equals(InputUtils.getError()));
        return role3Lvl;
    }
}
