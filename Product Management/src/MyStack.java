
//chứa thông tin và các hành vi cơ bản của stack
public class MyStack<T> {
    private Node<T> topNode;

    public MyStack() {
        topNode = null;
    }

    //kiểm tra stack rỗng không
    public boolean isEmpty(){
        return topNode == null;
    }

    //thêm item vào head stack
    public boolean push(T item){
        Node<T> newNode = new Node<>(item);
        newNode.setNext(topNode);
        topNode = newNode;
        return true;
    }


    //xoá item ra khỏi stack
    public T pop(){
        if(isEmpty()){
           return null;
        }
        T item = topNode.getInfo();
        topNode = topNode.getNext();
        return item;
    }

    //in các item trong stack mà không xoá item đó
    public void printStack(){
        if(isEmpty()) {
            System.out.println("stack is empty");
            return;
        }
        Node<T> temp = topNode;
        while (temp != null){
            System.out.println(temp.toString());
            temp = temp.getNext();
        }
    }
}
