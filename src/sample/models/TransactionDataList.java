package sample.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "transactions")
public class TransactionDataList {
    private List<Transaction> transactionList;

    @XmlElement(name = "transaction")
    public List<Transaction> getTransactions()
    {
        return this.transactionList;
    }

    public void setTransactions(List<Transaction> transactions)
    {
        this.transactionList = transactions;
    }
}
