package vn.funix.fx18085.java.Asm03;

import vn.funix.fx18085.java.asm02.Account;
import vn.funix.fx18085.java.asm02.Customer;

import java.util.Scanner;

public class Asm03 {
    private static final Scanner sc = new Scanner(System.in);

    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "FUNIX";





    public static void main(String[] args) {
        //Cập nhập thông tin khách hàng
        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
        menu();
        sc.nextLine();

    }

    //Giao diện hiển thị các chức năng
    public static void menu() {
        final String AUTHOR = "FX18085";
        final String VERSION = "@v3.0.0";
        System.out.println("+----------+----------------------+---------------+");
        System.out.println("| NGAN HANG SO | "+ AUTHOR.concat(VERSION) + "                   |");
        System.out.println("+----------+----------------------+---------------+");
        System.out.println("| 1. Thong tin khach hang                         |");
        System.out.println("| 2. Them tai khoan ATM                           |");
        System.out.println("| 3. Them tai khoan tin dung                      |");
        System.out.println("| 4. Rut tien                                     |");
        System.out.println("| 5. Lich su giao dich                            |");
        System.out.println("| 0. Thoat                                        |");
        System.out.println("+----------+----------------------+---------------+");
        System.out.println("Chuc nang: ");
        chucNang();
    }


    //Các chức năng theo yêu cầu
    public static void chucNang() {
        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    thongTinKhachHang();
                    break;
                case 2:
                    themTaiKhoanATM();
                    break;
                case 3:
                    themTaiKhoanTinDung();
                    break;
                case 4:
                    rutTien();
                    break;
                case 5:
                    lichSuGiaoDich();
                    break;
                case 0:
                    exit();
                    break;
                //trường hợp user input ngoài 0-5
                default:
                    System.out.println("Please enter from 0 to 5");
                    menu();
            }
        } catch (Exception e) {
            System.out.println(EXIT_ERROR_CODE );
        }


    }


    //Chức năng 1: Thông tin khách hàng
    public static void thongTinKhachHang() {
        //Kiẻm tra customer có tồn tại trong hệ thống không
        Customer cus =  activeBank.getCustomerId(CUSTOMER_ID);
        //Customer có tồn tại
        if(cus != null) {
            //Hiển thị thông tin
            cus.displayInformation();
        } else {
            System.out.println("Khong co thong tin cua khach hang nay");
        }
        menu();
    }

    //Chức năng 2: Thêm tài khoản Savings
    public static void themTaiKhoanATM() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma so tai khoan 6 chu so: ");
        //nhập Account number
        String accountNumber = scanner.nextLine();
        //Nạp tiền vào tài khoản
        System.out.println("Xin nhap so tien: ");
        double balance = scanner.nextDouble();
        //Kiểm tra Account number
        if(accountNumber.length() == 6 && !activeBank.checkAccount(new Account(accountNumber, balance, true))) {
            //Nếu account chưa có trong hệ thống, update
            System.out.println("Tai khoan hop le");
            //add new saving account vao he thong Account
            SavingsAccount savingsAccount = new SavingsAccount(accountNumber, balance, true);
            activeBank.addAccount(CUSTOMER_ID, savingsAccount);
        } else {
            System.out.println("Account number is not valid");
        }
        menu();
    }

    //Chức năng 3: Thêm tài khoản Loan
    public static void themTaiKhoanTinDung() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma so tai khoan 6 chu so: ");
        String accNum = scanner.nextLine();
        //Nạp tiền vào tài khoản Tín dụng
        System.out.println("Xin nhap so tien can nap: ");
        double balance = scanner.nextDouble();
        if(accNum.length() == 6 && !activeBank.checkAccount(new Account(accNum, balance, false))) {
            System.out.println("Tai khoan hop le");
            //Add new Loan Account vaò hệ thống bạnk
            LoansAccount loansAccount = new LoansAccount(accNum,balance, false);
            activeBank.addAccount(CUSTOMER_ID, loansAccount);
        } else {
            System.out.println("Account number is not valid");
        }
        menu();
    }

    //Chức năng 4: Rút tiền
    public static void rutTien() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so tai khoan can rut tien: ");
        String accNum = scanner.nextLine();
        System.out.println("Nhap so tien can rut: ");
        double amount = scanner.nextDouble();
        //Rút tiền dựa theo tài khoản dc nhập
        activeBank.withdraw(CUSTOMER_ID, accNum, amount);
        menu();

    }
    //Chức năng 5: Lịch sử giao dịch
    public static void lichSuGiaoDich() {
        System.out.println("+----------------+-----------------+---------------+");
        System.out.println("| LICH SU GIAO DICH                                |");
        System.out.println("+----------------+-----------------+---------------+");
        //Kiẻm tra customer có tồn tại trong hệ thống không
        Customer cus =  activeBank.getCustomerId(CUSTOMER_ID);
        //Customer có tồn tại
        if(cus != null) {
            //Hiển thị thông tin
            cus.displayInformation();
            for(Account acc: cus.getAccounts()) {
                acc.displayTrans();
            }
        } else {
            System.out.println("Khong co thong tin cua khach hang nay");
        }
        System.out.println("+----------------+-------------------+---------------+");
        menu();

    }

    public static void exit () {
        System.exit(EXIT_COMMAND_CODE);
    }

}
