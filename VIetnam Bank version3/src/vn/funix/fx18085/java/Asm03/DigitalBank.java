package vn.funix.fx18085.java.Asm03;

import vn.funix.fx18085.java.asm02.Bank;
import vn.funix.fx18085.java.asm02.Customer;

public class DigitalBank extends Bank {

    public Customer getCustomerId(String customerId) {

        for(Customer cus: getCustomers()) {
            if(cus.getCustomerId().equals(customerId)) {
                return cus;
            }
    }

        return null;

    }

    public void addCustomer(String customerId, String name) {
        Customer customer = new DigitalCustomer(customerId, name);
        setCustomers(customer);

    }


    public void withdraw(String customerId, String accountNumber, double amount) {
        for (Customer cus : getCustomers()) {
            if (cus.getCustomerId().equals(customerId)) {
                if (cus instanceof DigitalCustomer) {

                    DigitalCustomer newCus = (DigitalCustomer) cus;
                    if (newCus.withdraw(accountNumber, amount)) {
                        System.out.println("Rut tien trong tai khoan thanh cong");
                    } else {

                        System.out.println("Van chua rut duoc tien");
                    }
                }
            }
        }
        }



}
