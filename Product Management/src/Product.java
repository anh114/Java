public class Product{
    //Lớp Product chứa thông tin và hành vi cần thiết cho sản phẩm
    private String bcode;
    private String title;
    private int quantity;
    private double price;


    public Product(){

    }

    public Product(String bcode, String title, int quantity, double price) {
        this.bcode = bcode;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        String s = this.bcode + "    |  " + this.title + "    |  " + this.quantity + "    |  " + this.price;
    return s;
    }

    public String printList(){
        String s = this.bcode + "      " + this.title + "      " + this.quantity + "     " + this.price + "\n";
        return s;
    }
}
