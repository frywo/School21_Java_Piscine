import java.util.Scanner;
import java.util.UUID;

public class Menu {
private final boolean DEVMenu;

    private final TransactionsService service;
    private final Scanner input;

    Menu(boolean DEVMenu){
        this.DEVMenu = DEVMenu;
        input = new Scanner(System.in);
        service = new TransactionsService();
    }

    private void showMenu(){
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if(DEVMenu){
            System.out.println("5. DEV – remove a transfer by ID");
            System.out.println("6. DEV – check transfer validity");
            System.out.println("7. Finish execution");
        } else {
            System.out.println("5. Finish execution");
        }
    }

    private void addUser(){
        int balance = 0;
        String name;
        String temp;
        while (true){
            System.out.println("Enter a user name and a balance");
            temp = input.nextLine();
            String [] args = temp.split(" ");
            if(args.length ==2 ) {
                try {
                    balance = Integer.parseInt(args[1]);
                } catch (NumberFormatException numberFormatException){
                    System.out.println("Balance NOT a number!");
                    continue;
                }
                name = args[0];
                User user = new User(name, balance);
                service.addUser(user);
                System.out.format("User with id = %d is added\n", user.getIdentifier());
                break;
            } else {
                System.out.println("Wrong data!");
            }
        }

    }

    private void viewUserBalances(){
        int id;
        String temp;
        while (true){
            try {
                System.out.println("Enter a user ID");
                temp = input.nextLine();
                String[] args = temp.split(" ");
                if(args.length==1){
                    id = Integer.parseInt(args[0]);
                } else {
                    System.out.println("Wrong format");
                    continue;
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println("Wrong data!");
                continue;
            }
            try {
                System.out.println(service.getUsersList().retrieveByID(id).getName()+" - " +
                        service.getUsersList().retrieveByID(id).getBalance());
                break;
            } catch (UserNotFoundException userNotFoundException) {
                System.out.println(userNotFoundException.toString());
            }


        }
    }

    private void performTransfer(){

        String temp;
        int senderID;
        int recipientId;
        int amount;

        while (true){
            System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
            temp = input.nextLine();
            String [] args = temp.split(" ");
            if (args.length == 3){
                try {
                    senderID = Integer.parseInt(args[0]);
                    recipientId = Integer.parseInt(args[1]);
                    amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException numberFormatException){
                    System.out.println("Wrong data!");
                    continue;
                }
            } else {
                System.out.println("wrong data!");
                continue;
            }
            try {
                service.performingTransferTransaction(senderID, recipientId, amount);
                System.out.println("The transfer is completed");
                break;
            } catch (IllegalTransactionException illegalTransactionException) {
                System.out.println(illegalTransactionException.toString());
            } catch (UserNotFoundException userNotFoundException){
                System.out.println(userNotFoundException.toString());
            }
        }
    }

    private void viewAllTransactions(){
        int IDUser;
        String temp;

        while (true) {
           System.out.println("Enter a user ID");
           temp = input.nextLine();
           String[] args = temp.split(" ");
           if (args.length == 1){
               try {
                   IDUser = Integer.parseInt(args[0]);
               } catch (NumberFormatException numberFormatException){
                   System.out.println("NOT a NUMBER!");
                   continue;
               }
           } else {
               System.out.println("Wrong format!");
               continue;
           }
           Transaction [] transactions = service.retrievingTransfersOfUser(IDUser);
           if (transactions.length == 0){
               System.out.println("No transactions");
           }
           for (Transaction transaction : transactions){
               System.out.println(transaction.toString());
           }
           break;

        }
    }

    private void DEVRemoveTransferByID(){
        UUID IDTransfer;
        int IDUser;
        String temp;
        User user;
        Transaction [] transactions;

        while (true) {
            System.out.println("Enter a user ID and a transfer ID");
            temp = input.nextLine();
            String[] args = temp.split(" ");
            if (args.length == 2){
                try {
                    IDTransfer = UUID.fromString(args[1]);
                    IDUser = Integer.parseInt(args[0]);
                } catch (NumberFormatException numberFormatException){
                    System.out.println("NOT a NUMBER!");
                    continue;
                } catch (IllegalArgumentException illegalArgumentException){
                    System.out.println(illegalArgumentException.toString());
                    continue;
                }
            } else {
                System.out.println("Wrong format!");
                continue;
            }

            try {
                transactions = service.getUsersList().retrieveByID(IDUser).getTransactionLinkedList().toArray();
            } catch (UserNotFoundException | ArrayIndexOutOfBoundsException userNotFoundException){
                System.out.println(userNotFoundException.toString());
                continue;
            }

            boolean check = false;

            try {
                service.removeTransactionFromUser(IDUser, IDTransfer);
            } catch (TransactionNotFoundException transactionNotFoundException){
                System.out.println("Transaction with this id does not exist!");
                continue;
            } catch ( UserNotFoundException userNotFoundException){
                System.out.println("User with this id does not exist!");
                continue;
            }

            for (Transaction transaction : transactions){
                 if (transaction.getIdentifier().equals(IDTransfer)){
                     user = transaction.getRecipient();
                     System.out.format("Transfer To %s (id = %d) %d removed\n", user.getName(),
                             user.getIdentifier(), transaction.getTransferAmount());
                     check = true;
                     break;
                 }
            }
            if (check){
                break;
            }
        }
    }

    private void DEVCheckTransferValidity(){
        System.out.println("Check results:");
        Transaction[] transactions = service.checkValidityOfTransactions();
        if(transactions.length == 0){
            System.out.println("NOTHING");
        } else {
            for (Transaction transaction : transactions){
                System.out.format("%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d",
                        transaction.getSender().getName(), transaction.getSender().getIdentifier(),
                        transaction.getIdentifier().toString(),
                        transaction.getRecipient().getName(), transaction.getRecipient().getIdentifier(),
                        transaction.getTransferAmount());
            }
        }
    }

    private void print(){
        System.out.println("---------------------------------------------------------");
    }

    public void start(){
        boolean end = false;
        String temp;
        int num;
        while (!end){
            showMenu();
            temp = input.nextLine();
            String [] args = temp.split(" ");
            if(args.length == 1 ) {
                try {
                    num = Integer.parseInt(args[0]);
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("Balance NOT a number!");
                    continue;
                }
                if ((DEVMenu && !(num > 7 || num < 1)) || (!DEVMenu && !(num > 5 || num < 1))) {
                    switch (num){
                        case (1): addUser(); break;
                        case (2): viewUserBalances(); break;
                        case (3): performTransfer(); break;
                        case (4): viewAllTransactions(); break;
                    }
                    if(DEVMenu){
                        switch (num){
                            case(5): DEVRemoveTransferByID(); break;
                            case(6): DEVCheckTransferValidity();break;
                            case(7): end = true;
                        }
                    } else if (num==5) {
                        end = true;
                    }
                    print();
                }
            }
        }
    }
}
