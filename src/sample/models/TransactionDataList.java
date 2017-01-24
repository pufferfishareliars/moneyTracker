package sample.models;

import java.util.List;

public class TransactionDataList {
    private List<Transaction> transactionList;

    public List<Transaction> getTransactions()
    {
        return this.transactionList;
    }

    public void setTransactions(List<Transaction> transactions)
    {
        this.transactionList = transactions;
    }
}
