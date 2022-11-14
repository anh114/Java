package vn.funix.fx18085.java.asm02;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    Scanner scanner = new Scanner(System.in);
    private String name;
    private long customerId;


    public User(long customerId, String name) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        //trường hợp ID đã đạt đủ yêu cầu theo form cccd
                if(checkIdAvailable(customerId)) {
                    //chuyển đổi từ String sang Long
                    this.customerId = Long.parseLong(customerId);
                }
    }


    //xét điều kiện CCCD đúng form hay không?
    public static boolean checkIdAvailable(String customerId) {
            //Check validation of ID number
            String regex = "^0\\d{11}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(customerId);
            //ID hop le
            if (matcher.find()) {
                System.out.println("Ma CCCD hop le");
                return true;
            }
            return false;
    }
}