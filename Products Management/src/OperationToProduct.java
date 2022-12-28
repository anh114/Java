import java.io.*;


//chứa các phương thức thức biểu diễn các yêu cầu chức năng của bài toán
public class OperationToProduct {
    //Searching and returning the quantity of the first product in the list
    public int quantity(MyList<Product> list){
        //Truong hop linked list khong rong
        if(!list.isEmpty()){
            //lay quantity cua head
            Node<Product> head = list.getHead();
            Product p = (Product) head.getInfo();
            int quantity = p.getQuantity();
            System.out.println("Quantity = " + quantity);
            return quantity;
        }
       return -1;
    }

    //tao 1 new Product
    public Product createProduct(String bcode, String title, int quantity, double price, MyList<Product> list) throws IOException{
        if(checkID(list, bcode)){
            Product newProduct = new Product(bcode, title, quantity, price);
            System.out.println(newProduct.toString());
            //tra ve 1 new product
            return newProduct;
        }
        return null;
    }


    //Kiem tra id moi co trung voi id hien tai trong list
    public boolean checkID(MyList<Product> list, String bcode){
        //Truong hop list da co product
        if(!list.isEmpty()) {
            Node<Product> current = list.getHead();
            while (current != null){
                Product p = (Product) current.getInfo();
                //Kiem tra bcode moi co trung voi id trong list khong (khong phan biet viet hoa)
                if(p.getBcode().equalsIgnoreCase(bcode)){
                    System.out.println("Trung id");
                    return false;
                }
                //di chuyen con tro den product ke tiep
                current = current.getNext();
            }

        }

        return true;
    }

    public void getAllItemsFromFile(String filename, MyList<Product> list) throws IOException {
        list.clear();
        BufferedReader reader = null;
        System.out.println("ID     |  Title     | Quantity    |  price");
        System.out.println("------------------------------------------");
        String str = "";
        try{
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null){
                str = line;
                //Tách từng mục nhỏ trên 1 line
                String[] splits = str.split("\\s+");
                if(splits.length == 4){
                    //thêm từng mục nhỏ dc tách ra thành 1 product với đủ bcode, title, quantity và price
                    if(checkID(list,splits[0])) {
                        Product newProduct = createProduct(splits[0],splits[1], Integer.parseInt(splits[2]), Double.parseDouble(splits[3]), list);
                        //add product vao LinkedList
                        list.insertAtTail(newProduct);
                    }
                }
            }
        }finally {
            if(reader != null){
                reader.close();
            }
        }

    }

    //Reading all products from the file and insert them to the stack.
    public void getAllItemsFromFile(String filename, MyStack<Product> stack) throws IOException{
        BufferedReader reader = null;
        System.out.println("---------------STACK----------------------");
        System.out.println("ID     |  Title     | Quantity    |  price");
        System.out.println("------------------------------------------");
        String str = "";
        try{
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null){
                str = line;
                //Tách từng mục nhỏ trên 1 line
                String[] splits = str.split("\\s+");
                if(splits.length == 4){
                    //thêm từng mục nhỏ dc tách ra thành 1 product với đủ bcode, title, quantity và price
                    Product newProduct = new Product(splits[0],splits[1], Integer.parseInt(splits[2]), Double.parseDouble(splits[3]));
                    //add product vao stack
                    stack.push(newProduct);
                }
            }

        }finally {
            if(reader != null){
                reader.close();
            }
        }

    }


    //Reading all products from the file and insert them to the queue.
    public void getAllItemsFromFile(String filename, MyQueue<Product> queue) throws IOException{
        BufferedReader reader = null;
        System.out.println("---------------QUEUE----------------------");
        System.out.println("ID     |  Title     | Quantity    |  price");
        System.out.println("------------------------------------------");
        String str = "";
        try{
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null){
                str = line;
                //Tách từng mục nhỏ trên 1 line
                String[] splits = str.split("\\s+");
                if(splits.length == 4){
                    //thêm từng mục nhỏ dc tách ra thành 1 product với đủ bcode, title, quantity và price
                    Product newProduct = new Product(splits[0],splits[1], Integer.parseInt(splits[2]), Double.parseDouble(splits[3]));
                    //add product vao queue
                    queue.push(newProduct);
                }
            }

        }finally {
            if(reader != null){
                reader.close();
            }
        }
    }



    //update Toan bo linked list moi vao file
    public void writeAllItemsToFile(String filename, MyList<Product> list) throws IOException{
        BufferedWriter writer = null;
        Node current = list.getHead();
        try {
//            writer = new BufferedWriter(new FileWriter(filename));
            FileWriter fw = new FileWriter(filename, false);
            writer = new BufferedWriter(fw);
           while (current != null){
               Product p = (Product) current.getInfo();
               String item = p.printList();
               writer.write(item);
               current = current.getNext();
           }
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }


    //Searching product by ID input from keyboard
    public void searchByCode(MyList<Product> list, String id){
        id = id.toLowerCase();
        Node current = list.getHead();
       while(current != null){
           Product p = (Product) current.getInfo();
           //tìm product có ID giồng id cần tìm
           if(p.getBcode().toLowerCase().equals(id)){
               //in product đó ra
               System.out.println(p.toString());
               return;
           }
           //nếu ko giống id cần tìm, di chuyển con trỏ đến product tiếp theo
           current = current.getNext();
       }
        System.out.println("-1");

    }


    //delete first product that has ID input in linked list
    public int ProductDeletePos(MyList<Product> list, String id){
        id = id.toLowerCase();
        Node<Product> current = list.getHead();
        //set vi tri product dau tien = 0
        int index = 0;
        while(current != null){
            Product p = (Product) current.getInfo();
            //tìm product có id giống id cần tìm
            if(p.getBcode().toLowerCase().equals(id)){
                //trả lại vị trí của product đó
                return index;
            }
            //nếu ko phải product cần tìm, tiếp tục di chuyển đến product kế tiếp đồng thời tăng vị trí index lên ++
            index++;
            current = current.getNext();
        }
        System.out.println("ID is not exist");
        return index;
    }

    public void linkedListSort(MyList<Product> list){
        if(!list.isEmpty()) {
            Node<Product> head = list.getHead();
            list.setHead(list.mergeSort(head));
            System.out.println(list.toString());
        }


    }


    //Convert linked list to array
    public Product[] convertLinkedList(MyList<Product> list){
        //Array products có độ dài bằng list
        Product[] products = new Product[list.length()];
        int index = 0;
        Node<Product> current = list.getHead();

        while (current.getNext() != null){
            //thêm lần lượt từng product trong list vào products array
            products[index] = current.getInfo();
            //cho product trong list chạy lần lượt
            current = current.getNext();
            //vị trí trong array cũng tăng lần lượt
            index++;
        }
        products[index] = current.getInfo();
        return products;

    }
    //Phan ra array ban dau thanh cac id rieng le
    public Product[] mergeSort(Product[] products){
        int length = products.length;
        //trường hợp array chỉ có 1 product duy nhất
        if(length < 2){
            return products;
        }
        int midIndex = length/2; // split array 2 side
        Product[] leftHalf = new Product[midIndex]; //left: 0-midIndex
        Product[] rightHalf = new Product[length - midIndex]; //right: midIndex - lastIndex
        //truyen gia tri tu idProducts[] sang leftHalf[]
        //chay i tu 0 - midIndex
        for(int i = 0; i< midIndex; i++){
            leftHalf[i] = products[i];
        }
        //truyen gia tri tu idProducts[] sang rightHalf[]
        //chay i tu midIndex - lastIndex, nhung phai dua index cua rightHalf ve 0
        for(int i = midIndex; i < length; i++){
            rightHalf[i-midIndex] = products[i];
        }
        //phan tach array cho den khi chi con 1 element bang de quy
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        //Merge
        merge(products, leftHalf, rightHalf);
        return products;
    }

    //tron tat ca element trong left va right vao trong idProduct va tra lai array da sap xep
    private Product[] merge(Product[] productsSorted, Product[] left, Product[] right){
        int leftSize = left.length;
        int rightSize = right.length;

        //i = left; j = right; k = idProducts
        int i = 0, j = 0, k = 0;
        //Condition: i va j chay het cac phan tu trong left[] va right[]
        while (i < leftSize && j < rightSize){
            //neu gia tri cua left dung truoc gia tri cua right theo alpha
            if(left[i].getBcode().compareToIgnoreCase(right[j].getBcode()) <= 0){
                //dua id dung truoc vao trong idProducts[]
                productsSorted[k] = left[i];
                i++;
            } else { //nguoc lai
                productsSorted[k] = right[j];
                j++;
            }
            k++;
        }
        //truong hop 1 trong 2 array da chay het phan tu nhung array con lai van con element
        while(i < leftSize){
            productsSorted[k] = left[i];
            i++; k++;
        }
        while (j < rightSize){
            productsSorted[k] = right[j];
            j++; k++;
        }
        return productsSorted;
    }




     //Convert a decimal to a integer number. Example: input i = 18 -> Output = 10010
    public void convertToBinary(int quantity){
        int binary[] = new int[40];
        int index = 0;
        while(quantity > 0){
            binary[index++] = quantity%2;
            quantity = quantity/2;
        }
        for(int i = index -1; i >= 0; i--){
            System.out.print(binary[i]);
        }
    }


    //delete product at position
    public void deleteAtPosition(MyList<Product> list, int pos) throws IOException{
        //delete item in linked list
        list.deleteElement(pos);

    }

}
