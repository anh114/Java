package vn.funix.fx18085.java.Asm03;

import vn.funix.fx18085.java.asm02.Account;

import java.text.DecimalFormat;

public class LoansAccount extends Account implements ReportService, Withdraw {
    private final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private final double  LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private final double LOAN_ACCOUNT_MAX_BALANCE = 100_000_000;


    public LoansAccount(String accountNumber, double balance, boolean type) {
        super(accountNumber, balance, type);

    }


    //In ra hoá đơn nếu rút tiền thành công
    @Override
    public void log(double amount) {
        DecimalFormat pattern = new DecimalFormat("###,###,###");
        String amountFix =  pattern.format((double) amount);
        String balanceFix =  pattern.format((double) super.getBalance());
        String vatFix = pattern.format((double) (getTransactionFee(amount) * amount));
        System.out.println("+----------------+-------------------------------------+");
        System.out.println("          BIEN LAI GIAO DICH LOAN");
        System.out.println("NGAY G/D:                   " + getDateTime());
        System.out.println("ATM ID:                     DIGITAL-BANK-ATM 2022");
        System.out.println("SO Tk:                      " + getAccountNumber());
        System.out.println("SO TIEN:                    " + amountFix);
        System.out.println("SO DU:                      " + balanceFix);
        System.out.println("PHI + VAT:                  " + vatFix);
        System.out.println("+----------------+-------------------------------------+");

    }

    //Nghiệp vụ rút tiền
    @Override
    public boolean withdraw(double amount) {
        double newBalance;
        //Nếu số tiền cần rút thoả mãn điều kiện căn bản
        if(isAccepted(amount)) {
            //cap nhap so du
            newBalance = super.getBalance() - amount - (getTransactionFee(amount) * amount);
            //update số sư mới cho tài khoản
            setBalance(newBalance);
            Transaction successLoan = new Transaction(getAccountNumber(),amount, true);
            getTrans().add(successLoan);
            return true ;
            }
        System.out.println("Khong the rut tien");
        Transaction failLoan = new Transaction(getAccountNumber(),amount, false);
       getTrans().add(failLoan);
        return false;
    }

    //Kiểm tra số tiền cần rút có thoả mãn các diều kiện ko?
    @Override
    public boolean isAccepted(double amount) {
        //0 < tiền rút <= 100tr
        if (amount > 0  && amount <= LOAN_ACCOUNT_MAX_BALANCE) {
            return true;
        } else {
            System.err.println("So tien rut khong hop le !!!");
            return false;
        }
    }

    //Tiền phí giao dịch
    public double getTransactionFee(double amount) {
        //Phân trường hợp thu phí dựa vào tài khoản thường hay Premium
        if (isPremium()) return LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE;
        return LOAN_ACCOUNT_WITHDRAW_FEE;
    }
}
