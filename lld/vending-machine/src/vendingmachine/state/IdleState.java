package vendingmachine.state;

import vendingmachine.enums.Currency;
import vendingmachine.VendingMachine;

public class IdleState implements State {

    public static IdleState getInstance(){
        return new IdleState();
    }

    @Override
    public void insertMoney(VendingMachine machine, Currency currency){
        machine.addMoney(currency);
        machine.setState(HasMoneyState.getInstance());
    }

    @Override
    public void selectProduct(VendingMachine machine, String slotCode){
        System.out.println("Please insert money first");
    }

    @Override
    public void cancel(VendingMachine machine){
        System.out.println("No money to refund");
    }
}