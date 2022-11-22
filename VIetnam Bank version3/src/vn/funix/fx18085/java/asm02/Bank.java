package vn.funix.fx18085.java.asm02;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    private String Id;

    private ArrayList<Customer> customers;
    public Bank() {
        this.Id = String.valueOf(UUID.randomUUID());
        this.customers = new ArrayList<>();
    }


    public String getId() {
        return Id;
    }

    public void setCustomers(Customer customer) {
        customers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }

    //kiểm tra ID có tồn tại trong array list customers chưa?
    public boolean isCustomerExisted(String customerId) {
        for (int i = 0; i < customers.size(); i++) {
            //ID này đã có trọng hệ thống customer
            if (customers.get(i).getCustomerId().equals(customerId)) {
                System.out.println("Customer ID nay trung khop");
                return false;
            }
        }
        return true;
    }

    //add 1 account mới vào trong hệ thống bank, Chỉ thêm vào nếu khách hàng tồn tại,
    // tận dụng hàm addAccount của Customer
    public void addAccount(String customerId, Account account) {
        for(Customer cus: customers) {
            //check customer co trong Bank khong, TH co customer nay trong bank:
            if(cus.getCustomerId().equals(customerId)) {
                //add account vao trong Customer
                cus.addAccount(account);
            }
        }

    }


    public boolean checkAccount(Account account) {
        for(Customer cus: customers) {
            if(cus.addAccount(account)) {
                System.out.println("Da co account nay trong he thong");
                return true;
            }
        }
        System.out.println("Chua co account nay trong he thong");
        return false;
    }


}