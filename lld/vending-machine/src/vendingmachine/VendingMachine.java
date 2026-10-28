package vendingmachine;

import vendingmachine.enums.TransDir;
import vendingmachine.enums.Currency;
import vendingmachine.model.Slot;
import vendingmachine.model.CashRegister;
import vendingmachine.model.Transaction;
import vendingmachine.state.State;
import vendingmachine.state.IdleState;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine{

    private List<Slot> slots;
    private State state;
    private List<Transaction> transaction;
    private CashRegister cashRegister;

    public VendingMachine(List<Slot> slots, CashRegister cashRegister){
        this.slots = slots;
        this.state = IdleState.getInstance();
        this.cashRegister = cashRegister;
        this.transaction = new ArrayList<>();
    }


    public void setState(State state){
        this.state = state;
    }

    public void changeState(State state){
        this.state = state;
    }

    public void addMoney(Currency currency){
        transaction.add(new Transaction(currency, TransDir.INCOME));
        cashRegister.addTransaction(new Transaction(currency, TransDir.INCOME));
        System.out.println("Current Balance : " + getTotalMoneyInserted());
    }

    public void refund(){
        for(Transaction transaction : transaction){
            cashRegister.addTransaction(new Transaction(transaction.getCurrency(), TransDir.EXPENSE));
        }
        transaction.clear();
    }

    public boolean isChangeAvailable(int changeRequired){
        return cashRegister.isChangeAvailable(changeRequired);
    }
    
    public Slot searchSlot(String slotCode){
        for(Slot slot : slots){
            if(slot.getSlotCode().equals(slotCode)){
                return slot;
            }
        }
        return null;
    }

    public void completeSale(Slot slot){
        slot.setQuantity(slot.getQuantity() - 1);        
    }

    public int getTotalMoneyInserted(){
        int total = 0;
        for(Transaction transaction : transaction){
            if(transaction.getDirection() == TransDir.INCOME){
                total += transaction.getValue();
            }
        }
        return total;
    }


}