package vendingmachine.enums;


public enum TransDir {
    INCOME(1),
    EXPENSE(-1);

    private final int sign;

    TransDir(int sign) {
        this.sign = sign;
    }

    public int getSign() {
        return sign;
    }
}