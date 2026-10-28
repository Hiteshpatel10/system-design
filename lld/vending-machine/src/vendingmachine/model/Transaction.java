package vendingmachine.model;

import vendingmachine.enums.Currency;
import vendingmachine.enums.TransDir;

public class Transaction {

    private Currency currency;
    private TransDir direction;


    public Transaction(Currency currency, TransDir direction){
        this.currency = currency;
        this.direction = direction;
    }

    public Currency getCurrency() {
        return currency;
    }

    public TransDir getDirection() {
        return direction;
    }

    public int getValue(){
        return currency.getValue();
    }
    
}