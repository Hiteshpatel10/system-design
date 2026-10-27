package vendingmachine;

public class Product {
    String name;
    int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getProductName(){
        return name;
    }

    public int getProductPrice(){
        return price;
    }
}