package vendingmachine.state;


import vendingmachine.VendingMachine;
import vendingmachine.enums.Currency;

public interface State {

    void insertMoney(VendingMachine machine, Currency currency);
    void selectProduct(VendingMachine machine, String slotCode);
    void cancel(VendingMachine machine);
}