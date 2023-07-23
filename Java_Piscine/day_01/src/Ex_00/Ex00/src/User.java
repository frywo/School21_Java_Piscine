public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    private TransactionLinkedList transactionLinkedList;

    public User (String name, Integer balance) {
        setName(name);
        setBalance(balance);
        this.identifier = UserIdsGenerator.getInstance().getID();
        transactionLinkedList = new TransactionLinkedList();
    }

    public void setName (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBalance (Integer balance){
        if (balance >= 0){
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }

    public Integer getBalance() {
        return balance;
    }

    private void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setTransactionLinkedList(TransactionLinkedList transactionLinkedList){
        this.transactionLinkedList = transactionLinkedList;
    }

    public TransactionLinkedList getTransactionLinkedList() {
        return transactionLinkedList;
    }

    @Override
    public String toString(){
        return ("User { Name: " + name + " | Balance: " + balance + " | ID: " + identifier+ "}");
    }

}
