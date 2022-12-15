import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static Algorithm al = new Algorithm();
    static float[] arr;

    public static void main(String[] args) throws IOException{
        System.out.println("+----------------Menu---------------------+");
        System.out.println("|   1. Input                              |");
        System.out.println("|   2. Output                             |");
        System.out.println("|   3. Bubble sort                        |");
        System.out.println("|   4. Selection sort                     |");
        System.out.println("|   5. Insertion sort                     | ");
        System.out.println("|   6. Search > value                     |");
        System.out.println("|   7. Search = value                     |");
        System.out.println("|   8. Runtime calculate");
        System.out.println("|   0. Exit                               |");
        System.out.println("+-----------------------------------------+");
        menu();

    }

    public static void menu() throws IOException{
            System.out.println("Chuc nang:");
            try{
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        nhapDuLieu();
                        break;
                    case 2:
                        docDuLieu();
                        break;
                    case 3:
                        bubbleSortDisplay();
                        break;
                    case 4:
                        selectionSortDisplay();
                        break;
                    case 5:
                        insertionSortDisplay();
                        break;
                    case 6:
                        searchDisplay();
                        break;
                    case 7:
                        binarySearchDisplay();
                        break;
                    case 8:
                        runtimeDisplay();
                        break;
                    case 0:
                        exit();
                        break;
                    default:
                        System.out.println("Nhap so tu 0 den 8");
                }
            }catch (Exception e){
                System.out.println("Please input valid number!");
            }

    }


    //Chuc nang 1:
    public static void nhapDuLieu() throws IOException {
        System.out.println("Day so co chieu dai n: ");
        int n = scanner.nextInt();
        float[] arr = new float[n];
        System.out.println("Nhap cac gia tri trong day so: ");
        for(int i = 0; i < n; i++){
            arr[i] = scanner.nextFloat();
        }
        al.writeFile("INPUT.TXT", arr);
        menu();

    }

    public static void docDuLieu() throws IOException{
        //luu value vao arr
        arr = al.readFile("INPUT.TXT");
        System.out.println("Read from file");
        for(float num: arr) {
            System.out.print(num + "  ");
        }
        System.out.println();
        menu();
    }

    public static void bubbleSortDisplay() throws IOException{
        //Sap xep lai array theo bubble sort
        arr = al.bubbleSort(al.readFile("INPUT.TXT"));
        //luu lai array da sap xep vao output1.txt
        al.writeFile("OUTPUT1.TXT", arr);
        //Hien thi bubble sort arr tren console
//        for(float num: arr){
//            System.out.print(num + "  ");
//        }
        menu();

    }

    public static void selectionSortDisplay() throws IOException{
        //Sap xep lai array theo selection sort
        arr = al.selectionSort(al.readFile("INPUT.TXT"));
        //luu lai array da sap xep vao output2.txt
        al.writeFile("OUTPUT2.TXT", arr);

        menu();
    }

    public static void insertionSortDisplay() throws IOException{
        //Sap xep lai array theo insertion sort
        arr = al.insertionSort(al.readFile("INPUT.TXT"));
        //luu lai array da sap xep vao output3.txt
        al.writeFile("OUTPUT3.TXT", arr);
        menu();
    }

    public static void searchDisplay() throws IOException{
        //arr ban dau
        arr = al.readFile("INPUT.TXT");
        System.out.println("Input value: ");
        float value = scanner.nextFloat();

        //luu newArr vao output4.txt
        al.writeFile("OUTPUT4.TXT", al.search(arr, value));
        menu();
    }

    public static void binarySearchDisplay() throws IOException{
        System.out.println("Binary Search");
        System.out.println("Input value: ");
        float value = scanner.nextFloat();
        //array da duoc sap xep
        arr = al.readFile("OUTPUT2.TXT");
        int left = 0;
        int right = arr.length -1;
        //Binary search
        int indexSearch = al.binarySearch(arr, left, right, value);
        System.out.println("Index of the first element: " + indexSearch);
        //ep kieu int sang float[]
        float[] result = {(float) indexSearch};
        //luu vao output5.txt
        al.writeFile("OUTPUT5.TXT", result);
        menu();

    }

    public static void runtimeDisplay() throws IOException{
        System.out.println("1.  Dữ liệu đã được sắp xếp");
        System.out.println("2.  Dữ liệu sắp xếp theo thứ tự ngược lại");
        System.out.println("3.  Dữ liệu có xáo trộn ngẫu nhiên");
        int data = scanner.nextInt();
        switch (data){
            case 1:
                //Dữ liệu đã được sắp xếp
                arr = al.readFile("OUTPUT2.TXT");
                //thời gian chạy của 3 thuật toán
                al.runtime(arr);
                break;
            case 2:
                //Dữ liệu sắp xếp theo thứ tự ngược lại
                arr = al.readFile("OUTPUT2.TXT");
                int length = arr.length;
                float[] newArr = new float[length];
                int j = length;
                //Dao nguoc lai vi tri tu arr sang newArr
                for(int i = 0; i < length; i++){
                    newArr[j-1] = arr[i];
                    j--;
                }
                //thời gian chạy của 3 thuật toán
                al.runtime(newArr);
                break;
            case 3:
                //Dữ liệu có xáo trộn ngẫu nhiên
                arr = al.readFile("INPUT.TXT");
                //thời gian chạy của 3 thuật toán
                al.runtime(arr);
                break;
            default:
                System.out.println("Xin hay chon data dung yeu cau");
            }
        menu();
    }

    public static void exit(){
        System.out.println("Thanks!!!");
        System.exit(0);
    }



}