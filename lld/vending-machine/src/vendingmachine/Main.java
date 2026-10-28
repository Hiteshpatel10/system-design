package vendingmachine;


import vendingmachine.model.Slot;
import vendingmachine.model.Product;
import vendingmachine.model.CashRegister;
import vendingmachine.model.Transaction;
import java.util.List;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        

        Slot slot = new Slot("A1", new Product("A1", 10), 10);

        List<Slot> slots = new ArrayList<>();
        slots.add(slot);
        
        CashRegister cashRegister = new CashRegister();
        VendingMachine vendingMachine = new VendingMachine(slots, cashRegister);
    
        
    }
}