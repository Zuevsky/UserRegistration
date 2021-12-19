package RegisteredUnits;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private static HashMap<String, User> users = new HashMap<>();

    private final String email;
    private String name;
    private String surname;
    private ArrayList<String> roles;
    private ArrayList<String> phones;

    public User(String email, String name, String surname, ArrayList<String> roles, ArrayList<String> phones) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
        this.phones = phones;
    }

    public static HashMap<String, User> getUsers() {
        return users;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public String getRolesToString() {
        String stringRoles = "";
        for(int i = 0; i < roles.size(); i++) {
            if(i == roles.size() - 1) {
                stringRoles = stringRoles.concat(roles.get(i));
            } else {
                stringRoles = stringRoles.concat(roles.get(i) + ", ");
            }
        }
        return stringRoles;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public String getPhonesToString() {
        String stringPhones = "";
        for(int i = 0; i < phones.size(); i++) {
            if(i == phones.size() - 1) {
                stringPhones = stringPhones.concat(phones.get(i));
            } else {
                stringPhones = stringPhones.concat(phones.get(i) + ", ");
            }
        }
        return stringPhones;
    }

    public static void setUsers(HashMap<String, User> users) {
        User.users = users;
    }

    public static void addNewUser(User user) {
        User.users.put(user.getEmail(), user);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    public static String toString(User user) {
        return "EMAIL: " + user.getEmail() + "; ИМЯ: " + user.getName() + "; ФАМИЛИЯ: " +
                user.getSurname() + "; РОЛИ: " + user.getRolesToString() + "; ТЕЛЕФОНЫ: " + user.getPhonesToString() + ".";
    }
}
