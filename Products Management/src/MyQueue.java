
//chứa thông tin và các hành vi cơ bản của queue
public class MyQueue<T> {
    private Node<T> headNode;
    private Node<T> tailNode;

    public MyQueue() {
        headNode = null;
        tailNode = null;
    }

    //kiểm tra queue có rỗng không
    public boolean isEmpty(){
        return headNode == null && tailNode == null;
    }

    //thêm 1 product vào tail của queue
    public void push(T item){
        Node<T> newNode = new Node<>(item); //tạo node mới
        //trường hợp queue rỗng, thì head = tail = newnode
        if(isEmpty()){
            headNode = newNode;
            tailNode = newNode;

        } else { //Ngược lại, thêm newNode vào đuôi
            tailNode.setNext(newNode);
            tailNode = newNode;
        }

    }

    //Xoá 1 item ra khỏi queue
    public T pop(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return null;
        }
        T itemDelete = headNode.getInfo();
        //khi queue chỉ có 1 item duy nhất
        if(headNode == tailNode){
            headNode = tailNode = null;
        } else { //trường hợp >= 2 items, lấy item ở head ra
            headNode = headNode.getNext();
        }
        return itemDelete;
    }




}
