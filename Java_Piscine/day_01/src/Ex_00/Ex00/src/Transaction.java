import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private final User recipient;
    private final User sender;
    private TransferCategory transferCategory;
    private final int transferAmount;
    private ResultTransaction resultTransaction;

    private enum TransferCategory {
        DEBIT,
        CREDIT
    }
    private enum ResultTransaction {
        SUCCESS,
        FAIL
    }

    public Transaction (User sender, User recipient, int transferAmount) {
        this.sender = sender;
        this.recipient = recipient;
        this.transferAmount = transferAmount;
        setIdentifier();

        if (transferAmount<0) {
            setTransferCategory(TransferCategory.CREDIT);
        } else {
            setTransferCategory(TransferCategory.DEBIT);
        }

        if ((sender.getBalance() < 0 || sender.getBalance() < transferAmount)
                && transferCategory == TransferCategory.DEBIT) {

            setResultTransaction(ResultTransaction.FAIL);
        } else if (transferCategory == TransferCategory.CREDIT
                && (recipient.getBalance() < 0 || recipient.getBalance() < -transferAmount)) {
            setResultTransaction(ResultTransaction.FAIL);

        } else {
            setResultTransaction(ResultTransaction.SUCCESS);
        }
    }

    public void executeTransaction(){
        sender.setBalance(sender.getBalance()-transferAmount);
        recipient.setBalance(recipient.getBalance()+transferAmount);
    }

    private void setTransferCategory(TransferCategory category){
        transferCategory = category;
    }
    public TransferCategory getTransferCategory(){
        return transferCategory;
    }

    private void setResultTransaction(ResultTransaction res) {
        resultTransaction = res;
    }
    public ResultTransaction getResultTransaction() {
        return resultTransaction;
    }

    public int getTransferAmount(){
        return transferAmount;
    }

    public User getRecipient(){
        return recipient;
    }

    public User getSender(){
        return sender;
    }

    public UUID getIdentifier(){
        return identifier;
    }
    void setIdentifier(){
        identifier = UUID.randomUUID();
    }

    void setIdentifier(UUID ID) { identifier = ID; }

    @Override
    public String toString(){
        return ("Transaction { Identifier: "+ identifier+" Sender: " +
                sender +" Recipient: " + recipient + " Amount: " +
                transferAmount + " Category: " + transferCategory +
                " Result transaction: " + resultTransaction+"}");
    }
}
