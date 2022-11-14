package vn.funix.fx18085.java.asm02;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<Account> accounts = new ArrayList<>();

    public Customer(Long cccd, String name) {
        super(cccd, name);
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }



    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    //kiểm tra toàn bộ account của 1 user có tài khoản nào premium ko
    public boolean isPremium() {
        for(int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).isPremium()) {
                return true;
            }
        }
        return false;
    }


    //Add 1 account mới vào Arraylist accounts
    public boolean addAccount(Account newAccount) {
        for (Account acc : accounts) {
            if(acc.getAccountNumber().equals(newAccount.getAccountNumber())){
                //newAccount da co trong he thong
                return true;
            }
        }
        accounts.add(newAccount);
        //newAccount ko co trong he thong, nen can update account
        return false;
    }

    //hiển thị đầy đủ thông tin của toàn bộ khách hàng
    public void displayInformation() {
        //Hien thi day du thong tin cua ca User, Customer va Account
        DecimalFormat pattern = new DecimalFormat("###,###,###");
        String balanceFix =  pattern.format((long) getBalance());
        //dòng 1: thông tin cá nhân và tổng các account
        String s = super.getCustomerId() + " | " + super.getName() + " | " + (isPremium()? "Premium" : "Normal") + " | " + balanceFix;
        System.out.println(s);
        //Quet array list cua Account
        int i = 0;
        for (Account acc : accounts){
            //Hien thi account number va balance cua tung Account
            String m = String.format("%d %20s", i+1, acc.toString());
            System.out.println(m);
            i++;
        }

    }

    //Tinh tong balance cua 1 Customer
    public double getBalance() {
        double total = 0;
        for(Account acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }
}
