public interface UsersList {

    int DEFAULT_SIZE = 10;
    void addUser(User user);
    User  retrieveByID(int ID) throws UserNotFoundException;
    User  retrieveByIndex(int index);
    int retrieveNumberUsers();

}
