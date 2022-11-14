package vn.funix.fx18085.java.asm02;

import java.util.Scanner;

public class Asm02 {
    private static final Bank bank = new Bank();

    private static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        menu();
        sc.nextLine();
    }

    //menu chính
    public static void menu() {
        final String AUTHOR = "FX18085";
        final String VERSION = "@v2.0.0";
        System.out.println("+----------+----------------------+--------------+");
        System.out.println("| NGAN HANG SO | "+ AUTHOR.concat(VERSION) + "                  |");
        System.out.println("+----------+----------------------+--------------+");
        System.out.println("| 1. Them khach Hang                             |");
        System.out.println("| 2. Them tai khoan cho khach hang               |");
        System.out.println("| 3. Hien thi danh sach khach hang               |");
        System.out.println("| 4. Tim theo CCCD                               |");
        System.out.println("| 5. Tim theo ten khach hang                     |");
        System.out.println("| 0. Thoat                                       |");
        System.out.println("+----------+----------------------+--------------+");
        System.out.println("Chuc nang: ");
        chucNang();
    }


    //các chức năng lựa chọn vào từng mục
    public static void chucNang() {
        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    themKhachHang();
                    break;
                case 2:
                    themTaiKhoan();
                    break;
                case 3:
                    hienThiDanhSachKhachHang();
                    break;
                case 4:
                    timTheoCCCD();
                    break;
                case 5:
                    timTheoTenKhachHang();
                    break;
                case 0:
                    exit();
                    break;

                //trường hợp user input ngoài 0-5
                default:
                    System.out.println("Please enter from 0 to 5");
                    menu();

            }
            //trường hợp user không input number
        } catch (Exception e) {
            System.out.println("Xin dien chu so hop le");
        }
    }

    //Chuc nang 1: Them khach hang
    public static void themKhachHang() {
                System.out.println("Nhap so CCCD: ");
                sc.nextLine();
                    String cccd = sc.nextLine();
                    System.out.println("Nhap ten khach hang: ");
                    String name = sc.nextLine();
                    //kiem tra cccd nay co trong he thong bank chua
                    Customer cus = getCustomerById(Long.parseLong(cccd));
                    if (cus == null && cus.checkIdAvailable(cccd)) {
                        //add CCCD moi vao
                        Customer c = new Customer(Long.parseLong(cccd), name);
                        bank.setCustomers(c);
                    } else {
                        System.out.println("Khach hang nay khong hop le");
                        System.out.println(cccd);
                    }
                    menu();


    }

    //Chuc nang 2: Them tai khoan moi
    public static void themTaiKhoan() {
        //xac nhan lai CCCD
        System.out.println("Nhap CCCC khach hang:");
        sc.nextLine();
        String xacMinhCCCD = sc.nextLine();
        //Truong hop cccd nay da co trong bank
        if (!bank.isCustomerExisted(xacMinhCCCD)) {
            //tiep tuc buoc tao account moi
            System.out.println("Nhap ma STK gom 6 chu so:");
            String accNumber = sc.nextLine();
            //nap tien cho account nay
            System.out.println("Nhap so du:");
            int soDu = sc.nextInt();
            //trường hợp nạp dưới 50k
            if (soDu < 50000) {
                System.out.println("So du khong du, yeu cau nhap lai");
                //trường hợp account không đủ 6 chữ số
            } else if (accNumber.length() != 6) {
                System.out.println("Account number co 6 chu so");
            } else {
                //update Account moi
                Account newAcc = new Account(accNumber, soDu);
                Customer cus = getCustomerById(Long.parseLong(xacMinhCCCD));
                //CCCD co ton tai trong Bank va Kiem tra account da co trong account list chua
                if (cus != null) {
                    if(!cus.addAccount(newAcc)) {
                        //add account moi vao trong Bank
                        bank.addAccount(xacMinhCCCD, newAcc);
                        System.out.println("Add new account successfully");

                    } else {
                        System.out.println("Account is already exist");
                    }
                }
            }
        } else {
            System.out.println("CCCD nay khong ton tai");
        }
        menu();
    }
    //Kiem tra CCCD da co trong Bank chua
    public static Customer getCustomerById (long cccd){
        for(Customer customer : bank.getCustomers()){
            //trường họp cccd dã có trong hệ thống
            if(customer.getCustomerId()== cccd)
                return customer;
        }
        return null;
    }


            //chuc nang 3: hien thi danh sach customer
            public static void hienThiDanhSachKhachHang () {
                //quet toan bo Array list Customer
                for (Customer cus : bank.getCustomers()) {
                    cus.displayInformation();
                }
                menu();
            }

            //chuc nang 4: tim theo CCCD
            public static void timTheoCCCD () {
                Scanner scanner = new Scanner(System.in);
                System.out.println("CCCD can tim: ");
                long currentCccd = scanner.nextLong();
                //duyệt toàn bộ Arraylist customer
                for (Customer cus : bank.getCustomers()) {
                    if (cus.getCustomerId()== currentCccd) {
                        //hiển thị tài khoản theo ID
                        cus.displayInformation();
                    }
                }
                menu();
            }

            //Chuc nang 5:
            public static void timTheoTenKhachHang () {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ten khach hang can tim: ");
                String currentName = scanner.nextLine();
                //duyệt toàn bộ array list Customer
                for (Customer cus : bank.getCustomers()) {
                    //hiển thị tài khoản theo tên khách hàng
                    if (cus.getName().equals(currentName)) {
                        cus.displayInformation();
                    }
                }
                menu();
            }

            public static void exit () {
                System.exit(0);
            }

        }
