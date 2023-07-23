public class UsersArrayList implements UsersList{

    private int capacity = DEFAULT_SIZE;
    private int size = 0;
    User [] users = new User[capacity];


    @Override
    public void addUser(User user) {
        if(capacity-1 == size){
            users = increaseArray(users);
        }
        if (users[size] == null) {
            users[size] = user;
            size++;
        }
    }

    @Override
    public User retrieveByID(int ID) throws UserNotFoundException{
        for (int i = 0; i < size; ++i) {
           if( users[i].getIdentifier() == ID) {
               return users[i];
           }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User retrieveByIndex(int index) throws UserNotFoundException, ArrayIndexOutOfBoundsException {
        if(index<0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (users[index] != null && index<size){
            return users[index];
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public int retrieveNumberUsers(){
        return size;
    }

    private User[] increaseArray (User[] arr){
        this.capacity *= 2;
        User [] res = new User[capacity];
        System.arraycopy(arr, 0,res, 0, arr.length);
        return res;
    }
}
