package vn.funix.fx18085.java.asm01;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Asm01 {
    private static Scanner sc = new Scanner(System.in);
    //Current ID card which user want to check
    private static String currentCCCD;
    private static ArrayList<String> placeList = new ArrayList<>();


    public static void main(String[] args) {
        menu();
        chucNang();
        sc.nextLine();
    }

    public static void menu() {
        final String AUTHOR = "FX18085";
        final String VERSION = "@v1.0.0";
        System.out.println("+----------+----------------------+----------+");
        System.out.println("| NHAP HANG SO | "+ AUTHOR.concat(VERSION) +"              |");
        System.out.println("+----------+----------------------+----------+");
        System.out.println("| 1. Nhap CCCD                               |");
        System.out.println("| 0. Thoat                                   |");
        System.out.println("+----------+----------------------+----------+");
        System.out.println("Chuc nang: ");
        chucNang();
    }


    public static void chucNang() {
        do {
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    //User want to quit
                    case 0:
                        exit();
                        break;
                    //User want to enter the ID card
                    case 1:
                        do {
                            System.out.println("1.EASY CODE");
                            System.out.println("2.HARD CODE");
                            try {
                                int mode = sc.nextInt();
                                if(mode == 1) {
                                    easyCode();
                                } else if (mode == 2) {
                                    hardCode();
                                } else {
                                    System.out.println("Vui long nhap 1 hoac 2");
                                    continue;
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Please input valid number!");
                                sc.nextLine();
                                continue;
                            }
                        } while(true);
                        break;
                    default:
                        System.out.println("Please enter 1 or 0!");
                        continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Please input valid number!");
                sc.nextLine();
            }
        } while(true);

    }

    //Easy code
    public static void easyCode() {
        //create random number 100-999
        int randomNumber = (int) Math.floor((Math.random() * 899) + 100);
        System.out.println("Nhap ma xac thuc: " + randomNumber);
        int inputNumber = sc.nextInt();
            //If User's input wrong
            if (inputNumber != randomNumber) {
                System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
                menu();
                return;
            } else {
                //If User input correct
                System.out.println("Ma xac thuc chinh xac");
                //Allow to enter number of ID Card
                nhapCCCD();
            }
    }

    //Hard code
    public static void hardCode() {
        //Create a random String for security
        String randomString = random(6);
        System.out.println("Nhap ma xac thuc: " + randomString);
        String input = sc.next();
        //If User's input wrong
        if (!input.equals(randomString)) {
            System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
            menu();
            return;
        } else {
            //If User input correct
            System.out.println("Ma xac thuc chinh xac");
            //Allow to enter number of ID Card
            nhapCCCD();
        }
    }

    //Create random String which contain both letters and numbers
    public static String random (int length) {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // generate a random number between 0 to length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }


    public static void nhapCCCD() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vui long nhap so CCCD: ");
        String input = scanner.nextLine();
        //Check validation of ID number
        String regex = "^0(01|02|04|06|08|10|11|12|14|15|17|19|20|22|24|25|26|27|30|31|33|34|35|36|37|38|40|42|44|45|46|48" +
                "|49|51|52|54|56|58|60|62|64|66|67|68|70|72|74|75|77|79|80|82|83|84|86|87|89|91|92|93|94|95|96)\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        //ID hop le
        if(matcher.find()) {
            //Storage value of current ID number
            currentCCCD = input;
            System.out.println("Ma CCCD hop le");
            displayInfo();
        } else {
            invalidID();
        }

    }

    public static void invalidID() {
        //ID khong hop le
        System.out.println("So CCCD khong hop le. Vui long nhap lai hoac ???No??? de thoat: ");
        System.out.println("1. Nhap lai: ");
        System.out.println("0. No");
        int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    nhapCCCD();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Khong hop le");
                    menu();
                    return;
            }

    }

    //Check place of ID card
    public static void provinces() {
        //Add 63 provinces into placeList
        placeList.add(0, "null");
        placeList.add(1, "Ha Noi");
        placeList.add(2, "Ha Giang");
        placeList.add(3, "null");
        placeList.add(4, "Cao Bang");
        placeList.add(5, "null");
        placeList.add(6, "Bac Kan");
        placeList.add(7, "null");
        placeList.add(8, "Tuyen Quang");
        placeList.add(9, "null");
        placeList.add(10, "L??o Cai");
        placeList.add(11, "??i???n Bi??n");
        placeList.add(12, "Lai Ch??u");
        placeList.add(13, "null");
        placeList.add(14, "S??n La");
        placeList.add(15, "Y??n B??i");
        placeList.add(16, "null");
        placeList.add(17, "H??a B??nh");
        placeList.add(18, "null");
        placeList.add(19, "Th??i Nguy??n");
        placeList.add(20, "L???ng S??n");
        placeList.add(21, "null");
        placeList.add(22, "Qu???ng Ninh");
        placeList.add(23, "null");
        placeList.add(24, "B???c Giang");
        placeList.add(25, "Ph?? Th???");
        placeList.add(26, "V??nh Ph??c");
        placeList.add(27, "B???c Ninh");
        placeList.add(28, "null");
        placeList.add(29, "null");
        placeList.add(30, "H???i D????ng");
        placeList.add(31, "H???i Ph??ng");
        placeList.add(32, "null");
        placeList.add(33, "H??ng Y??n");
        placeList.add(34, "Th??i B??nh");
        placeList.add(35, "H?? Nam");
        placeList.add(36, "Nam ?????nh");
        placeList.add(37, "Ninh Binh");
        placeList.add(38, "Thanh Hoa");
        placeList.add(39, "null");
        placeList.add(40, "Nghe An");
        placeList.add(41, "null");
        placeList.add(42, "Ha Tinh");
        placeList.add(43, "null");
        placeList.add(44, "Quang Binh");
        placeList.add(45, "Quang Tri");
        placeList.add(46, "Thua Thien Hue");
        placeList.add(47, "null");
        placeList.add(48, "Da Nang");
        placeList.add(49, "Qu???ng Nam");
        placeList.add(50, "null");
        placeList.add(51, "Qu???ng Ng??i");
        placeList.add(52, "B??nh ?????nh");
        placeList.add(53, "null");
        placeList.add(54, "Ph?? Y??n");
        placeList.add(55, "null");
        placeList.add(56, "Kh??nh H??a");
        placeList.add(57, "null");
        placeList.add(58, "Ninh Thu???n");
        placeList.add(59, "null");
        placeList.add(60, "B??nh Thu???n");
        placeList.add(61, "null");
        placeList.add(62, "Kon Tum");
        placeList.add(63, "null");
        placeList.add(64, "Gia Lai");
        placeList.add(65, "null");
        placeList.add(66, "?????k L???k");
        placeList.add(67, "?????k N??ng");
        placeList.add(68, "L??m ?????ng");
        placeList.add(69, "null");
        placeList.add(70, "B??nh Ph?????c");
        placeList.add(71, "null");
        placeList.add(72, "T??y Ninh");
        placeList.add(73, "null");
        placeList.add(74, "B??nh D????ng");
        placeList.add(75, "?????ng Nai");
        placeList.add(76, "null");
        placeList.add(77, "B?? R???a - V??ng T??u");
        placeList.add(78, "null");
        placeList.add(79, "H??? Ch?? Minh");
        placeList.add(80, "Long An");
        placeList.add(81, "null");
        placeList.add(82, "Ti???n Giang");
        placeList.add(83, "B???n Tre");
        placeList.add(84, "Tr?? Vinh");
        placeList.add(85, "null");
        placeList.add(86, "V??nh Long");
        placeList.add(87, "?????ng Th??p");
        placeList.add(88, "null");
        placeList.add(89, "An Giang");
        placeList.add(90, "null");
        placeList.add(91, "Ki??n Giang");
        placeList.add(92, "C???n Th??");
        placeList.add(93, "H???u Giang");
        placeList.add(94, "S??c Tr??ng");
        placeList.add(95, "B???c Li??u");
        placeList.add(96, "C?? Mau");

        String check = currentCCCD.substring(1, 3);
        String birthPlace = placeList.get(Integer.parseInt(check));
        System.out.println("Noi sinh: " + birthPlace);
        displayInfo();
    }

    //check age and gender of ID card
    public static void ageGender() {
        int letter = Character.getNumericValue(currentCCCD.charAt(3));
//                System.out.println("Current ID: " + currentCCCD);
//                System.out.println("Check char: " + letter);
        String birthYear = currentCCCD.substring(4, 6);
        switch (letter) {
            case 0:
                System.out.println("Gioi tinh: Nam | 19" + birthYear);
                break;
            case 1:
                System.out.println("Gioi tinh: Nu | 19" + birthYear);
                break;
            case 2:
                System.out.println("Gioi tinh: Nam | 20" + birthYear);
                break;
            case 3:
                System.out.println("Gioi tinh: Nu | 20" + birthYear);
                break;
            case 4:
                System.out.println("Gioi tinh: Nam | 21" + birthYear);
                break;
            case 5:
                System.out.println("Gioi tinh: Nu | 21" + birthYear);
                break;
            case 6:
                System.out.println("Gioi tinh: Nam | 22" + birthYear);
                break;
            case 7:
                System.out.println("Gioi tinh: Nu | 22" + birthYear);
                break;
            case 8:
                System.out.println("Gioi tinh: Nam | 23" + birthYear);
                break;
            case 9:
                System.out.println("Gioi tinh: Nu | 23" + birthYear);
                break;
        }
                    displayInfo();
    }

    //Check last 6 digits of ID card
    public static void randomID() {
        System.out.println("So ngau nhien: " + currentCCCD.substring(6));
        displayInfo();
    }


    public static void displayInfo() {
        System.out.println("| 1. Kiem tra noi sinh");
        System.out.println("| 2. Kiem tra tuoi, gioi tinh");
        System.out.println("| 3. Kiem tra so ngau nhien");
        System.out.println("| 0. Thoat");
        System.out.println("Chuc nang: ");
        int choice = sc.nextInt();
        try{
            switch (choice) {
                case 1:
                    provinces();
                    break;
                case 2:
                    ageGender();
                    break;
                case 3:
                    randomID();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Please enter valid number!");
                    menu();
                    return;
            }
        } catch (Exception e) {
            System.out.println("Xin vui long chon nhap dung du lieu");
            menu();
        }
    }

    public static void exit() {
        System.exit(0);
    }

}
