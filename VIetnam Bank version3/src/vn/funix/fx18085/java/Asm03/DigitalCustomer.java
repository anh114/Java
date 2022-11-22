package vn.funix.fx18085.java.Asm03;

import vn.funix.fx18085.java.asm02.Account;
import vn.funix.fx18085.java.asm02.Customer;

import java.text.DecimalFormat;
import java.util.List;

public class DigitalCustomer extends Customer {

    public DigitalCustomer(String cccd, String name) {
        super(cccd, name);
    }


    public boolean withdraw(String accountNumber, double amount) {

        //duyệt qua toàn bộ các account
        for (Account account : getAccounts()) {
            //nếu account trùng với account number cần tìm
//            if (account.getAccountNumber().equals(accountNumber)) {
//                //withdraw từng loại account theo Interface
//                if (((Withdraw) account).withdraw(amount))
//                    //In ra biên lai theo tung loai account
//                    ((ReportService) account).log(amount);
//                return true;
//            }
            if (account.getAccountNumber().equals(accountNumber)) {
                if(account.isType()) {
                    SavingsAccount sa = new SavingsAccount(accountNumber, account.getBalance(), true);
                    if(sa.withdraw(amount)) {
                        sa.log(amount);
                        account.setBalance(account.getBalance()- amount);
                        return true;
                    }
                }
                if(!account.isType()){
                    LoansAccount la = new LoansAccount(accountNumber, account.getBalance(), false);
                    if(la.withdraw(amount)) {
                        la.log(amount);
                        account.setBalance(account.getBalance() - amount - (la.getTransactionFee(amount) * amount));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void displayInformation() {
        //Hien thi day du thong tin cua ca User, Customer va Account
        DecimalFormat pattern = new DecimalFormat("###,###,###");
        String balanceFix =  pattern.format((double) getBalance());
        //dòng 1: thông tin cá nhân và tổng các account
        String s = super.getCustomerId() + " | " + super.getName() + " |  " + (isPremium()? "Premium" : "Normal") + " |           " + balanceFix;
        System.out.println(s);
        //Quet array list cua Account
        int i = 0;
        List<Account> accountLst = getAccounts();
        for (Account acc : accountLst){
            String accBalanceFix =  pattern.format((double) acc.getBalance());
            //Hien thi account number va balance cua tung Account
            String m = String.format("%d | %8s | %16s | %20s", i+1, acc.getAccountNumber() ,(acc.isType() ? "SAVINGS" : "LOAN"), accBalanceFix);
            System.out.println(m);

        }
    }


}
