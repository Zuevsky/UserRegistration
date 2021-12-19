package MainFunctions;

public interface MainFunctions {
    void getAllUsers();
    void getSpecificUser(String email);
    void createNewUser();
    void updateSpecificUser(String email);
    void deleteSpecificUser(String email);
    void deleteAllUsers();
}
