package vendingmachine.state;

import vendingmachine.VendingMachine;
import vendingmachine.enums.Currency;

public class DispenseState implements State {

    public static DispenseState getInstance(){
        return new DispenseState();
    }

    @Override
    public void insertMoney(VendingMachine machine, Currency currency){
        System.out.println("Already dispensed. Please collect refund or select another product");
    }

    @Override
    public void selectProduct(VendingMachine machine, String slotCode){
        System.out.println("Already dispensed. Please collect refund or select another product");
    }

    @Override
    public void cancel(VendingMachine machine){
        System.out.println("Already dispensed. Please collect refund or select another product");
    }
}