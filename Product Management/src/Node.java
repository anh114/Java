public class Node <T> {
    private Node<T> next;
    private T info;

    public Node(T product){
        info = product;
    }

    public Node() {
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return this.info + " ";
    }
}
