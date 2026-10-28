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
    private List<Currency> insertedCurrencies;
    private CashRegister cashRegister;

    public VendingMachine(List<Slot> slots, CashRegister cashRegister){
        this.slots = slots;
        this.state = IdleState.getInstance();
        this.cashRegister = cashRegister;
        this.insertedCurrencies = new ArrayList<>();
    }


    public void insertMoney(Currency currency){
        state.insertMoney(this, currency);
    }

    public void selectProduct(String slotCode){
        state.selectProduct(this, slotCode);
    }

    public void cancel(){
        state.cancel(this);
    }

    public void setState(State state){
        this.state = state;
    }

    public void changeState(State state){
        this.state = state;
    }

    public void addMoney(Currency currency){
        insertedCurrencies.add(currency);
        cashRegister.addTransaction(new Transaction(currency, TransDir.INCOME));
        System.out.println("Current Balance : " + getTotalMoneyInserted());
    }

    public void refund(){
        for(Currency currency : insertedCurrencies){
            cashRegister.addTransaction(new Transaction(currency, TransDir.EXPENSE));
        }
        insertedCurrencies.clear();
    }

    public void returnChange(int changeRequired){
        cashRegister.returnChange(changeRequired);
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
        insertedCurrencies.clear();
    }

    public int getTotalMoneyInserted(){
        int total = 0;
        for(Currency currency : insertedCurrencies){
            total += currency.getValue();
        }
        return total;
    }


}