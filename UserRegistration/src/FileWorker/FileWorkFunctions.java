package FileWorker;

import RegisteredUnits.User;

import java.io.IOException;
import java.util.Map;

public interface FileWorkFunctions {
    Map<String, User> getUsersFromFile() throws IOException;
    void saveUsersToFile() throws IOException;
}
