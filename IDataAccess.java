package com.example.spendingmanagerwithdbsearch;

public interface IDataAccess {
    public MainActivity.TransactionObject loadTransaction(int transactionID);
    public int saveTransactionObject (MainActivity.TransactionObject transactionObject);
}
