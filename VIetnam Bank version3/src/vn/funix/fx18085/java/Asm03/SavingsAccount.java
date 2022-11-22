package vn.funix.fx18085.java.Asm03;

import vn.funix.fx18085.java.asm02.Account;

import java.text.DecimalFormat;

public class SavingsAccount extends Account implements ReportService, Withdraw {
    private final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;

    public SavingsAccount(String accountNumber, double balance, boolean type) {
        super(accountNumber, balance, type);
    }


    //In ra hoá đơn nếu rút tiền thành công
    @Override
    public void log(double amount) {
        DecimalFormat pattern = new DecimalFormat("###,###,###");
        String amountFix =  pattern.format((double) amount);
        String balanceFix =  pattern.format((double) (super.getBalance()));

        System.out.println("+----------------+-------------------------------------+");
        System.out.println("          BIEN LAI GIAO DICH SAVINGS");
        System.out.println("NGAY G/D:                 " + getDateTime());
        System.out.println("ATM ID:                   DIGITAL-BANK-ATM 2022");
        System.out.println("SO Tk:                    " + getAccountNumber());
        System.out.println("SO TIEN:                  " + amountFix);
        System.out.println("SO DU:                    " + balanceFix);
        System.out.println("PHI + VAT:                0d");
        System.out.println("+----------------+-------------------------------------+");



    }

    //xử lý nghiệp vụ rút tiền
    @Override
    public boolean withdraw(double amount) {
        double newBalance;
        //Nếu số tiền rút thoả mãn điều kiên
        if(isAccepted(amount)) {
            //Số tiền còn lại sau khi rút
            newBalance = getBalance() - amount;
            //Số tiền còn lại trong tài khoản phải >= 50k
                //update số dư mới
                setBalance(newBalance);
                Transaction successSaving = new Transaction(getAccountNumber(),amount, true);
                getTrans().add(successSaving);
                return true;
            }

        System.out.println("Khong the rut tien tu tai khoan tiet kiem");
        Transaction failSaving = new Transaction(getAccountNumber(), amount, false);
        getTrans().add(failSaving);
        return false;
    }


    //Kiểm tra số tiền cần rút có thoả mãn các diều kiện ko?
    @Override
    public boolean isAccepted(double amount) {
        //Tài khoản là Premium
        if (isPremium()) {
            //tiền rút > 50k, số dư > 50k, tiền rút là bội số của 10k
            if (amount > 50000 && amount < (getBalance() - 50000) && amount % 10000 == 0) return true;
        } else {
            //Tài khoản thường: tiền rút <= 5tr và > 50k, số dư >50k và là bội số của 10k
            if (amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW && amount > 50000 && amount < (getBalance() - 50000) && amount % 10000 == 0)
                return true;
        }
        System.out.println("So tien rut khong hop le !!!");
        return false;
    }

}
