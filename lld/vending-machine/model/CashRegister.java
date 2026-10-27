package vendingmachine;

public class CashRegister {

    private int one;
    private int two;
    private int five;
    private int ten;
    private int twenty;
    private int fifty;
    private int hundred;

    public CashRegister(){
        this.one = 5;
        this.two = 5;
        this.five = 5;
        this.ten = 5;
        this.twenty = 5;
        this.fifty = 5;
        this.hundred = 5;
    }

    public int currentValue(){
        return one + two*2 + five*5 + ten*10 + twenty*20 + fifty*50 + hundred*100;
    }

    public void setHundred(int hundred) {
        this.hundred = hundred;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public void setTwenty(int twenty) {
        this.twenty = twenty;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public void setTen(int ten) {
        this.ten = ten;
    }

    public void setFifty(int fifty) {
        this.fifty = fifty;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public int getHundred() {
        return hundred;
    }

    public int getOne() {
        return one;
    }

    public int getTwenty() {
        return twenty;
    }

    public int getTwo() {
        return two;
    }

    public int getTen() {
        return ten;
    }

    public int getFifty() {
        return fifty;
    }

    public int getFive() {
        return five;
    }
}

