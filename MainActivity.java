package com.example.spendingmanagerwithdbsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import java.math.BigDecimal;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText balanceheader, dateEntryText, amountText, categoryText;
    TextView historyDateDisplay, historyTextAmount, historyTextCategory, historyDepWithText;
    BigDecimal balance;
    EditText balanceText;
    Button AddDeposit, SubtractWithdraw, FilterSearch;
    IDataAccess dao;
    public class TransactionObject {
        int id;
        String date;
        double amount;
        double newBalance;
        String category;
        int deposit, withdraw; //0 = deposit and 1 = withdraw
        public String toSQL() {
            StringBuilder sb = new StringBuilder("(");
            sb.append(mId).append(",");
            sb.append("\"").append(date).append("\"").append(",");
            sb.append(amount).append(",");
            sb.append(category).append(",");
            sb.append(")");
            return sb.toString();
        }
    }
    ArrayList<TransactionObject> transactions = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("transactions.db", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE \"Transactions\" (\n" +
                "\t\"Transaction ID\"\tINTEGER NOT NULL,\n" +
                "\t\"Date\"\tTEXT,\n" +
                "\t\"Amount\"\tNUMERIC,\n" +
                "\t\"NewBalance\"\tNUMERIC,\n" +
                "\t\"Category\"\tTEXT,\n" +
                "\t\"DeptWith\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"Transaction ID\")\n" +
                ")");

        AddDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransactionObject newTransaction = new TransactionObject();
                //String id = txtProductID.getText().toString();
                //product.mId = Integer.parseInt(id);
                newTransaction.date = dateEntryText.getText().toString();
                newTransaction.amount = Double.parseDouble(amountText.getText().toString());
                newTransaction.newBalance = Double.parseDouble(balance.toString()) + newTransaction.amount;
                newTransaction.category = categoryText.getText().toString();

                String sql = "INSERT INTO Transactions VALUES " + newTransaction.toSQL();
                Log.i("SQL", sql);

//                db.execSQL(sql);
                dao.saveTransaction(newTransaction);
            }
        });

        SubtractWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransactionObject newTransaction = new TransactionObject();
                //String id = txtProductID.getText().toString();
                //product.mId = Integer.parseInt(id);
                newTransaction.date = dateEntryText.getText().toString();
                newTransaction.amount = Double.parseDouble(amountText.getText().toString());
                newTransaction.newBalance = Double.parseDouble(balance.toString()) - newTransaction.amount;
                newTransaction.category = categoryText.getText().toString();

                String sql = "INSERT INTO Transactions VALUES " + newTransaction.toSQL();
                Log.i("SQL", sql);

//                db.execSQL(sql);
                dao.saveTransaction(newTransaction);
            }
        });

        FilterSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date, amt, newBalance, category;
                String sql = "SELECT * FROM Transactions WHERE date " + date + " AND amount " + amt;
                Log.i("SQL", sql);
            }
        });

    }
}