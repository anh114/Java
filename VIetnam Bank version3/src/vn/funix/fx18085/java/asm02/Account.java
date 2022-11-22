package vn.funix.fx18085.java.asm02;

import vn.funix.fx18085.java.Asm03.Transaction;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Account {
    private String accountNumber;
    private double balance;
    private boolean type;

    //List giao dịch lịch sử
    private static final ArrayList<Transaction> trans = new ArrayList<>();



    public Account(String accountNumber, double balance, boolean type) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }


        public static ArrayList<Transaction> getTrans() {
        return trans;
    }



    //kiểm tra 1 tài khoản có phải là Premium không
    public boolean isPremium() {
        if(balance >= 10000000) {
            return true;
        } else {
            return false;
        }
    }
    //display Account number và balance của 1 tài khoản
    public String toString() {
        DecimalFormat pattern = new DecimalFormat("###,###,###");
        String balanceFix =  pattern.format(balance);
        return String.format("%10s |    %25s", accountNumber, balanceFix);
    }

    public static String getDateTime(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

    public void displayTrans(){
        DecimalFormat pattern = new DecimalFormat("###,###,###");
        for(Transaction trans: trans) {
            if(trans.getAccountNumber().equals(accountNumber)) {
                //chỉ hiện các giao dịch thành công
                if(trans.isStatus()) {
                    String amountFix = pattern.format((double) (-trans.getAmount()));
                    String n = String.format("%s | %5s | %5s",getAccountNumber(), amountFix, getDateTime());
                    System.out.println(n);
                }
//                break;
            }


        }



    }


}
