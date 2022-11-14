package vn.funix.fx18085.java.asm02;

import java.text.DecimalFormat;

public class Account {
    private String accountNumber;
    private double balance;


    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
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
        return String.format("%10s | %25s", accountNumber, balanceFix);
    }
}
