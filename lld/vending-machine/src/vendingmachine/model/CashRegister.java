package vendingmachine.model;

import vendingmachine.enums.Currency;
import vendingmachine.model.Transaction;
import vendingmachine.enums.TransDir;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class CashRegister {

    private List<Transaction> transactionLog;
    private Map<Currency, Integer> notes;
    private int balance;

    public CashRegister(){
        this.transactionLog = new ArrayList<>();
        this.notes = new HashMap<>();
        this.balance = 0;
    }



    public void addTransaction(Transaction transaction){
        transactionLog.add(transaction);
        int delta = transaction.getDirection().getSign();
        notes.put(transaction.getCurrency(), notes.getOrDefault(transaction.getCurrency(), 0) + delta);
        balance += transaction.getValue() * delta;
    }

    public void returnChange(int changeRequired){
        Map<Currency, Integer> changeGiven = getChange(changeRequired);

        if(changeGiven == null){
            throw new IllegalStateException("Cannot make change");
        }

        // One EXPENSE transaction per coin, so a ₹20 change of 2×10 removes both coins
        for(Map.Entry<Currency, Integer> entry : changeGiven.entrySet()){
            for(int i = 0; i < entry.getValue(); i++){
                addTransaction(new Transaction(entry.getKey(), TransDir.EXPENSE));
            }
        }
    }

    public Map<Currency, Integer> getCoins(){
        return new HashMap<>(notes);
    }


    public boolean isChangeAvailable(int changeRequired) {
        if (changeRequired < 0) throw new IllegalArgumentException("Invalid change");
        if (changeRequired == 0) return true;
        if (balance < changeRequired) return false;

        return getChange(changeRequired) != null;
    }

    // Largest first, built once instead of on every call
    private static final List<Currency> DENOMINATIONS_DESC = Arrays.stream(Currency.values())
            .sorted(Comparator.comparingInt(Currency::getValue).reversed())
            .toList();

    // Returns coin -> count for the change, or null if no combination of available coins works
    private Map<Currency, Integer> getChange(int changeRequired) {
        return findChange(0, changeRequired);
    }

    // Backtracking: for the denomination at `index`, decide HOW MANY to use (most first),
    // then let the smaller denominations cover the rest. If they can't, use one fewer and retry.
    // Unlike greedy, this finds 3×20 for ₹60 when the register holds {50×1, 20×3}.
    private Map<Currency, Integer> findChange(int index, int amount) {
        if (amount == 0) return new HashMap<>();                 // paid in full
        if (index == DENOMINATIONS_DESC.size()) return null;     // out of coin types, still owe money

        Currency c = DENOMINATIONS_DESC.get(index);
        int maxUse = Math.min(notes.getOrDefault(c, 0), amount / c.getValue());

        for (int use = maxUse; use >= 0; use--) {
            Map<Currency, Integer> rest = findChange(index + 1, amount - use * c.getValue());
            if (rest != null) {
                if (use > 0) rest.put(c, use);
                return rest;
            }
        }
        return null;
    }

}

