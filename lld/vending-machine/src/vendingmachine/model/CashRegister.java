package vendingmachine.model;

import vendingmachine.enums.Currency;
import vendingmachine.model.Transaction;
import vendingmachine.enums.TransDir;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


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
        notes.put(transaction.getCurrency(), notes.getOrDefault(transaction.getValue(), 0) + 1);
        if(transaction.getDirection() == TransDir.INCOME){
            balance += transaction.getValue();
        }else{
            balance -= transaction.getValue();
        }
    }

    public boolean isChangeAvailable(int changeRequired){

        Map<Currency, Integer> temp = notes;
        int amt = changeRequired;

        if(changeRequired < 0) throw new IllegalArgumentException("Invalid change");
        
        if(changeRequired == 0) return true;

        if(balance < changeRequired) return false;
        
        while(amt > 0){

            if(temp.isEmpty()) return false;
            
            if(Currency.HUNDRED.getValue() <= amt && temp.getOrDefault(Currency.HUNDRED, 0) > 0){
                
                temp.put(Currency.HUNDRED, temp.get(Currency.HUNDRED) - 1);
                amt -= Currency.HUNDRED.getValue();
                
            }
            
            if(Currency.FIFTY.getValue() <= amt && temp.getOrDefault(Currency.FIFTY, 0) > 0){
                
                temp.put(Currency.FIFTY, temp.get(Currency.FIFTY) - 1);
                amt -= Currency.FIFTY.getValue();

            }
            
            if(Currency.TWENTY.getValue() <= amt && temp.getOrDefault(Currency.TWENTY, 0) > 0){
                
                temp.put(Currency.TWENTY, temp.get(Currency.TWENTY) - 1);
                amt -= Currency.TWENTY.getValue();

            }
            
            if(Currency.TEN.getValue() <= amt && temp.getOrDefault(Currency.TEN, 0) > 0){

                temp.put(Currency.TEN, temp.get(Currency.TEN) - 1);
                amt -= Currency.TEN.getValue();
                
            }
            
            if(Currency.FIVE.getValue() <= amt && temp.getOrDefault(Currency.FIVE, 0) > 0){
                
                temp.put(Currency.FIVE, temp.get(Currency.FIVE) - 1);
                amt -= Currency.FIVE.getValue();

            }
            
            if(Currency.TWO.getValue() <= amt && temp.getOrDefault(Currency.TWO, 0) > 0){

                temp.put(Currency.TWO, temp.get(Currency.TWO) - 1);
                amt -= Currency.TWO.getValue();
                
            }
            
        
            if(Currency.ONE.getValue() <= amt && temp.getOrDefault(Currency.ONE, 0) > 0){
                
                temp.put(Currency.ONE, temp.get(Currency.ONE) - 1);
                amt -= Currency.ONE.getValue();
                
            }
        }

        return true;
        
    }
    
}

