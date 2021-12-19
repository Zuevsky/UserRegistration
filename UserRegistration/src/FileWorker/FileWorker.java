package FileWorker;

import RegisteredUnits.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileWorker implements FileWorkFunctions{

    @Override
    public HashMap<String, User> getUsersFromFile() throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader("resources/UsersDataBase.txt"));
        HashMap<String, User> users = new HashMap<>();
        User user;
        String userString;
        String email = "";
        String name = "";
        String surname = "";
        while((userString = buffer.readLine()) != null) {
            ArrayList<String> roles = new ArrayList<>();
            ArrayList<String> phones = new ArrayList<>();
            String[] fields = userString.split(";");
            for(int i = 0; i < fields.length; i++) {
                switch(i){
                    case 0 -> email = fields[i];
                    case 1 -> name = fields[i];
                    case 2 -> surname = fields[i];
                    case 3 -> {
                        String[] rolesString = fields[i].split(",");
                        roles.addAll(Arrays.asList(rolesString));
                    }
                    case 4 -> {
                        String[] phonesString = fields[i].split(",");
                        phones.addAll(Arrays.asList(phonesString));
                    }
                }
            }
            user = new User(email, name, surname, roles, phones);
            users.put(user.getEmail(), user);
        }
        buffer.close();
        return users;
    }

    @Override
    public void saveUsersToFile() throws IOException {
        ArrayList<User> userCollection = new ArrayList<>(User.getUsers().values());
        BufferedWriter buffer = new BufferedWriter(new FileWriter("resources/UsersDataBase.txt", false));
        String userString;
        for(int i = 0; i < userCollection.size(); i++) {
            User user = userCollection.get(i);
            userString = user.getEmail() + ";" + user.getName() + ";" + user.getSurname() + ";" + collectionToString(user.getRoles()) + ";" + collectionToString(user.getPhones()) + ";";
            if(i != 0) {
                buffer.write("\n" + userString);
            } else {
                buffer.write(userString);
            }
        }
        buffer.close();
    }

    private String collectionToString(ArrayList<String> collection) {
        String result = "";
        for(int i = 0; i < collection.size(); i++) {
            if(i == collection.size() - 1) {
                result = result.concat(collection.get(i));
            } else {
                result = result.concat(collection.get(i) + ",");
            }
        }
        return result;
    }
}
