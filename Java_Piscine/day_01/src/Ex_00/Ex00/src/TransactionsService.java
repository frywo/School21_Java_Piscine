import java.util.Arrays;
import java.util.UUID;

public class TransactionsService {

    private final UsersList usersList;

    TransactionsService() {
        usersList = new UsersArrayList();
    }

    public void  addUser(User user){
        usersList.addUser(user);
    }

    public int retrieveUsersBalance(User user) {
        return user.getBalance();
    }

    public void performingTransferTransaction(int senderID, int recipientID, int amount) {
        User sender  = usersList.retrieveByID(senderID);
        User recipient = usersList.retrieveByID(recipientID);

        Transaction transactionSender = new Transaction(sender, recipient, amount);
        Transaction transactionRecipient = new Transaction(recipient, sender, -amount);
        transactionSender.setIdentifier(transactionRecipient.getIdentifier());
        sender.getTransactionLinkedList().addTransaction(transactionSender);
        recipient.getTransactionLinkedList().addTransaction(transactionRecipient);

        if(sender.getBalance()<amount && amount>0){
            throw new IllegalTransactionException();
        } else if (recipient.getBalance()<-amount && amount<0) {
            throw new IllegalTransactionException();
        }


        transactionSender.executeTransaction();


    }

    public Transaction[] retrievingTransfersOfUser(int ID){
        User user = usersList.retrieveByID(ID);
        return user.getTransactionLinkedList().toArray();
    }

    public void removeTransactionFromUser(int userID, UUID transactionID) throws TransactionNotFoundException, UserNotFoundException {
        User user = usersList.retrieveByID(userID);
        user.getTransactionLinkedList().removeTransactionById(transactionID);

    }

    public Transaction[] checkValidityOfTransactions(){
        Transaction [] res = new Transaction[0];
        for (int i = 0; i< usersList.retrieveNumberUsers(); ++i) {
            User user = usersList.retrieveByIndex(i);
            Transaction[] userTransaction = user.getTransactionLinkedList().toArray();
            for (Transaction transaction : userTransaction) {
                byte check = 0;
                UUID transactionID = transaction.getIdentifier();
                User firstUser = transaction.getSender();
                User secondUser = transaction.getRecipient();
                Transaction[] firstUserTransactions = firstUser.getTransactionLinkedList().toArray();
                Transaction[] secondUserTransactions = secondUser.getTransactionLinkedList().toArray();

                for (Transaction firstUserTransaction : firstUserTransactions) {
                    if (firstUserTransaction.getIdentifier().equals(transactionID)) {
                        check++;
                    }
                }

                for (int z = 0; z < secondUserTransactions.length; ++z) {
                    if (firstUserTransactions[z].getIdentifier().equals(transactionID)) {
                        check++;
                    }
                }

                if (check != 2) {
                    res = Arrays.copyOf(res, res.length + 1);
                    res[res.length - 1] = transaction;

                }
            }
        }
        return res;
    }

    public UsersList getUsersList(){ return usersList; }


}
