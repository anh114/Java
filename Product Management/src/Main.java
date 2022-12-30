import java.io.*;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static MyList<Product> list = new MyList<>();
    private static OperationToProduct product = new OperationToProduct();


    public static void main(String[] args) throws FileNotFoundException{
        //đường dẫn đến file text đọc dữ liệu
        FileOutputStream file = new FileOutputStream("console_output.txt");
        //ghi dữ liệu byte sang văn bản vào file
        PrintStream printStream = new PrintStream(file);
        DoublePrintStream doublePrintStream = new DoublePrintStream(System.out, printStream);
        System.setOut(doublePrintStream);
//        System.setOut(new DoublePrintStream(System.out, new PrintStream(new FileOutputStream("console_output.txt"))));
        chucNang();

    }


    //Menu chính các chức năng
    public static void chucNang(){
        System.out.println("+----------------------+-----------------------+");
        System.out.println("| 1. Load data from file and display           |");
        System.out.println("| 2. Input & add to the end                    |");
        System.out.println("| 3. Display data                              |");
        System.out.println("| 4. Save product list to file                 |");
        System.out.println("| 5. Search by ID                              |");
        System.out.println("| 6. Delete by ID                              |");
        System.out.println("| 7. Sort by ID                                |");
        System.out.println("| 8. Convert to binary                         |");
        System.out.println("| 9. Load to stack and display                 |");
        System.out.println("| 10. Load to queue and display                |");
        System.out.println("| 0. Exit                                      |");
        System.out.println("+----------------------+-----------------------+");
        try{
            System.out.println("Chuc nang: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    loadDataDisplay();
                    break;
                case 2:
                    addEndLinkedList();
                    break;
                case 3:
                    displayLinkedList();
                    break;
                case 4:
                    saveInFile();
                    break;
                case 5:
                    searchID();
                    break;
                case 6:
                    deleteID();
                    break;
                case 7:
                    sortList();
                    break;
                case 8:
                    quantityConvertToBinary();
                    break;
                case 9:
                    saveStack();
                    break;
                case 10:
                    saveQueue();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Xin hay chon tu 0 den 10");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

//1.Đọc dữ liệu có sẵn từ file đã tự tạo và lưu vào danh sách móc nối và hiển thị danh sách ra màn hình
    public static void loadDataDisplay() throws IOException{
        //doc du lieu va luu du lieu co san vao linked List
        product.getAllItemsFromFile("data.txt", list);
        chucNang();

    }

    //2.Nhập và thêm một sản phẩm vào cuối của danh sách móc nối
    public static void addEndLinkedList() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Product ID:");
        String bcode = sc.nextLine();
        System.out.println("Product title: ");
        String title = sc.nextLine();
        System.out.println("Product quantity: ");
        int quantity = sc.nextInt();
        System.out.println("Product price: ");
        double price = sc.nextDouble();

        //them newProduct vao linked List
        Product newProduct = product.createProduct(bcode, title, quantity, price, list);
        if(newProduct != null){
            list.insertAtTail(newProduct);
        }

        chucNang();

    }

    //3.Hiển thị thông tin của các sản phẩm trong danh sách móc nối
    public static void displayLinkedList()throws IOException{
        //hiển thị các product trong linked list
        System.out.println(list.toString());
        chucNang();

    }

    //4.Lưu danh sách móc nối các sản phẩm vào file
    public static void saveInFile() throws IOException{
      //Dữ liệu  trong danh sách sẽ được lưu vào tệp data.txt
        //update lai linked list vao trong file data
        System.out.println("Da luu linked list vao data.txt");
        product.writeAllItemsToFile("data.txt", list);
        chucNang();
    }

    //5. Tìm kiếm thông tin của sản phẩm theo ID
    public static void searchID(){
        Scanner sc = new Scanner(System.in);
        System.out.println("ID Product Search:");
        String id = sc.nextLine();
        //Search product theo ID mà user input
        product.searchByCode(list, id);
        chucNang();
    }
    //6. Xóa sản phẩm trong danh sách theo ID
    public static void deleteID() throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("ID Product Delete:");
        String id = sc.nextLine();
        //Tìm vị trí của product trong linked list
        int pos = product.ProductDeletePos(list, id);
        System.out.println("Delete position: " + pos);
        //Xoá product đó đi
        product.deleteAtPosition(list, pos);
        chucNang();
    }

    //7. Sắp xếp các sản phẩm  trong danh sách theo ID
    public static void sortList() throws IOException{
        System.out.println("Items duoc sap xep theo ID: ");

        /* Cach1: convert to Array and sort
        //Convert linked list thanh array ID Products
        Product[] products = product.convertLinkedList(list);
        // sap xep lai array
        Product[] productsSorted = product.mergeSort(products);
        //convert lai array tro ve linkedlist
        //xoa toan bo list cu
        list.clear();
        //insert product sorted vao lai list
        for(Product p: productsSorted){
            list.insertAtTail(p);
        }
        System.out.println(list.toString());
         */

        //Cach 2: sort by Linked list
        product.linkedListSort(list);
        chucNang();
    }

    //8. Biểu diễn số lượng sản phẩm (đang ở hệ đếm cơ số 10) của phần tử đầu tiên trong Linked List
    // về hệ đếm nhị phân bằng phương pháp đệ quy.
    public static void quantityConvertToBinary(){
        int quantity = product.quantity(list);
        System.out.print("Binary: ");
        product.convertToBinary(quantity);
        System.out.println();
        chucNang();
    }

    //9. Đọc dữ liệu từ file chứa các sản phẩm rồi lưu vào stack. Hiển thị ra màn hình các sản phẩm có trong stack.
    public static void saveStack() throws IOException{
        MyStack<Product> stack = new MyStack<>();
        product.getAllItemsFromFile("data.txt", stack);
        //In product trong stack ra mà ko xoá (pop) nó
        stack.printStack();
        chucNang();
    }


    //10. Đọc dữ liệu từ file chứa các sản phẩm lưu vào queue. Hiển thị ra màn hình các sản phẩm có trong queue
    public static void saveQueue() throws IOException{
        MyQueue<Product> queue = new MyQueue<>();
        product.getAllItemsFromFile("data.txt", queue);
        //In product trong queue ra thông qua hàm pop
        while (!queue.isEmpty()) {
            System.out.println(queue.pop());
        }
        chucNang();
    }

    //0. Thoát khỏi chương trình
    public static void exit(){
        System.out.println("Thanks!!!");
        System.exit(0);
    }



}