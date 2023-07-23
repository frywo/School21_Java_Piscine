
public class UserIdsGenerator{
    private static final UserIdsGenerator instance = new UserIdsGenerator();

    private static int id = 0;
    private UserIdsGenerator() {}

    public static UserIdsGenerator getInstance(){
     return instance;
    }

    public int getID() {
     ++id;
     return id;
    }
}
