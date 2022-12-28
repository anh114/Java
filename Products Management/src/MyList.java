public class MyList<T> extends Product{
    //chứa thông tin và hành vi cơ bản của một danh sách móc nối
    private Node<T> head;
    private Node<T> tail;


    public MyList() {
    }

    public MyList(Node<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    //Kiểm tra linked list có empty không
    public boolean isEmpty(){
        return head == null;
    }

    //kiểm tra độ dài của list
    public int length(){
        int length = 0;
        Node<T> current = this.head;
        while(current != null){
            length++;
            current = current.getNext(); //cap nhap vi tri moi
        }
        return length;
    }

    //Thêm product vào đầu linked list
    public void insertToHead(T item){
        Node<T> newNode = new Node<>(item); //tao newNode
        newNode.setNext(this.head); //tro node vao head cu
        this.head = newNode; // cap nhap vi tri head moi

    }

    //thêm product vào đuôi linked list
    public void insertAtTail(T item){
        Node<T> newNode = new Node<>(item);
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
            return;
        }
        this.tail.setNext(newNode);
        this.tail = newNode;

    }


    //Xoá product theo vị trí
    public void deleteElement(int pos){
        if(isEmpty()) return;
        Node<T> temp = head;
        //Truong hop element can xoa la head
        if(pos == 0){
            head = head.getNext();
            return;
        }
        //Element nam giua va cuoi linked list
        //Find prevNode
        for(int i = 0; temp != null && i < pos-1; i++){
            temp = temp.getNext();
        }
        if(temp == null || temp.getNext() == null) return;
        Node<T> next = temp.getNext().getNext();
        temp.setNext(next);

    }


    //Method: merge sort
    //sắp xếp 2 phần tử theo đúng vị trí alpha
    public Node<T> sortedMerge(Node<T> left, Node<T> right){
        Node<T> result = null;
        if(left == null) return right;
        if (right == null) return left;

    if(left.toString().compareTo(right.toString()) <= 0 ){
            result = left;
            result.setNext(sortedMerge(left.getNext(), right));
        } else {
            result = right;
            result.setNext(sortedMerge(left, right.getNext()));
        }
        return result;

    }


    //phân tách các phân tử ra cho đến khi chỉ các phần tử đứng riêng lẻ
    public Node<T> mergeSort(Node<T> head){
        //Truong hop head null || con tro null
        if(head == null || head.getNext() == null){
            return head;
        }
        //split the list into 2 halves
        Node<T> middle = getMiddle(head);
        Node<T> nextMiddle = middle.getNext();
        middle.setNext(null);
        Node<T> left = mergeSort(head);
        Node<T> right = mergeSort(nextMiddle);
        return sortedMerge(left, right);
    }
    //Search middle Node of Linked List
    public Node<T> getMiddle(Node<T> head){
        if(head == null) return null;
        Node<T> slow = head, fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }




    @Override
    public String toString() {
        String result = "";
        Node<T> current = this.head;
        while(current != null){
            result += current.getInfo() + "\n";
            current = current.getNext();
        }
        return result;
    }


    //Xoá toàn bộ linked list
    public void clear(){
        this.head = null;
    }
}
