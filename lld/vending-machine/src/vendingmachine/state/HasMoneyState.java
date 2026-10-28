package vendingmachine.state;

import vendingmachine.VendingMachine;
import vendingmachine.enums.Currency;
import vendingmachine.model.Slot;

public class HasMoneyState implements State{


    public static HasMoneyState getInstance(){
        return new HasMoneyState();
    }

    @Override
    public void insertMoney(VendingMachine machine, Currency currency){
        machine.addMoney(currency);
    }

    @Override
    public void selectProduct(VendingMachine machine, String slotCode){

        Slot slot = machine.searchSlot(slotCode);
        if(slot == null){
            System.out.println("Invalid Slot");
            return;
        }
        if(slot.getQuantity() == 0){
            System.out.println("Sold out. Choose another product or cancel");
            return;
        }

        int price = slot.getProduct().getPrice();
        int moneyInserted = machine.getTotalMoneyInserted();

        if(moneyInserted < price){
            System.out.println("Insufficient money : " + (price - moneyInserted) + " more");
            return;
        }

        if(!machine.isChangeAvailable(moneyInserted - price)){
            System.out.println("Cannot make change. Please select another product or cancel");
            machine.refund();
            machine.setState(IdleState.getInstance());
            return;
        }

        machine.setState(DispenseState.getInstance());
        machine.completeSale(slot);
        machine.setState(IdleState.getInstance());
    }

    @Override
    public void cancel(VendingMachine machine){
        machine.refund();
        machine.setState(IdleState.getInstance());
    }
}