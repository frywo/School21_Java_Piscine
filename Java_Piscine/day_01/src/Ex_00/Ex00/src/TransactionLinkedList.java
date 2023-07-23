import java.util.UUID;

public class TransactionLinkedList implements TransactionsList{

    private int lengthList = 0;

    private TransactionNode start = new TransactionNode(null, null, null);
    private TransactionNode end = new TransactionNode(null, null, null);

    public TransactionLinkedList() {
        end.setPrevious(start);
        start.setNext(end);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        TransactionNode transactionNode = end;
        TransactionNode prev = end.getPrevious();
        transactionNode.setTransaction(transaction);
        end = new TransactionNode(transactionNode,null,null);
        transactionNode.setNext(end);
        prev.setNext(transactionNode);
        lengthList++;
    }

    @Override
    public void removeTransactionById(UUID ID) throws TransactionNotFoundException {
        TransactionNode node = start.getNext();
        TransactionNode prev;
        TransactionNode next;
        boolean except = true;

        while (node.transaction!= null){
            if ( node.getTransaction().getIdentifier().equals(ID)) {
                prev = node.getPrevious();
                next = node.getNext();
                prev.setNext(next);
                next.setPrevious(prev);
                lengthList--;
                node.setPrevious(null);
                node.setNext(null);
                node.setTransaction(null);
                except = false;
                break;
            } else {
                node = node.getNext();
            }
        }

        if(except) {
            throw new TransactionNotFoundException();
        }


    }

    @Override
    public Transaction [] toArray(){
       Transaction [] transactions = new Transaction[lengthList];
       TransactionNode tmp = start.getNext();
       for (int i = 0; i < lengthList; ++i){
           transactions[i] = tmp.getTransaction();
           tmp = tmp.getNext();
       }
       return  transactions;
    }
}

class TransactionNode {
    Transaction transaction;
    TransactionNode next;
    TransactionNode previous;

    TransactionNode(TransactionNode previous, Transaction transaction, TransactionNode next) {
        this.next = next;
        this.previous = previous;
        this.transaction = transaction;
    }

    public TransactionNode getNext() {
        return next;
    }

    public void setNext(TransactionNode next) {
        this.next = next;
    }

    public TransactionNode getPrevious() {
        return previous;
    }

    public void setPrevious(TransactionNode previous) {
        this.previous = previous;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
