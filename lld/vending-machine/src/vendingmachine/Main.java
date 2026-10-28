package vendingmachine;


import vendingmachine.enums.Currency;
import vendingmachine.model.Slot;
import vendingmachine.model.Product;
import vendingmachine.model.CashRegister;
import java.util.List;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {

        run("1. Select before inserting money", Main::selectBeforeInsert);
        run("2. Insufficient money, then top up", Main::insufficientThenTopUp);
        run("3. Sold out keeps money, cancel refunds", Main::soldOutThenCancel);
        run("4. Cancel with no money", Main::cancelWhenIdle);
        run("5. Change given from earlier customers' coins", Main::changeGiven);
        run("6. Change not possible -> refund", Main::changeNotPossible);
        run("7. Sample flow from PROBLEM.md", Main::sampleFlow);
        run("8. Several coins of one kind as change", Main::multipleCoinsOfOneKind);
        run("9. Backtracking beats greedy (₹60 = 3×20, not 50+?)", Main::backtrackingBeatsGreedy);    }

    // Fresh machine per test: A1 = Coke ₹25 (qty 2), A2 = Chips ₹20 (qty 0)
    private static VendingMachine newMachine(){
        List<Slot> slots = new ArrayList<>();
        slots.add(new Slot("A1", new Product("Coke", 25), 2));
        slots.add(new Slot("A2", new Product("Chips", 20), 0));
        return new VendingMachine(slots, new CashRegister());
    }

    // Runs one test; an exception fails only that test instead of stopping the rest.
    private static void run(String name, Runnable test){
        System.out.println("\n=== " + name);
        try {
            test.run();
        } catch (Exception e) {
            System.out.println("!!! FAILED with exception: " + e);
        }
    }

    private static void selectBeforeInsert(){
        VendingMachine vm = newMachine();
        vm.selectProduct("A1");            // expect: Please insert money first
    }

    private static void insufficientThenTopUp(){
        VendingMachine vm = newMachine();
        vm.insertMoney(Currency.TWENTY);   // expect: Current Balance : 20
        vm.selectProduct("A1");            // expect: Insufficient money : 5 more
        vm.insertMoney(Currency.FIVE);     // expect: Current Balance : 25
        vm.selectProduct("A1");            // expect: sale, no change
        vm.selectProduct("A1");            // expect: Please insert money first (balance reset)
    }

    private static void soldOutThenCancel(){
        VendingMachine vm = newMachine();
        vm.insertMoney(Currency.TWENTY);   // expect: Current Balance : 20
        vm.selectProduct("A2");            // expect: Sold out. Choose another product or cancel
        vm.selectProduct("A9");            // expect: Invalid Slot
        vm.cancel();                       // expect: refund ₹20
        vm.selectProduct("A1");            // expect: Please insert money first
    }

    private static void cancelWhenIdle(){
        VendingMachine vm = newMachine();
        vm.cancel();                       // expect: No money to refund
    }

    private static void changeGiven(){
        VendingMachine vm = newMachine();
        // Customer 1 pays exact, which leaves a ₹5 coin in the register
        vm.insertMoney(Currency.TWENTY);
        vm.insertMoney(Currency.FIVE);
        vm.selectProduct("A1");            // expect: sale, no change
        // Customer 2 pays ₹30 for ₹25 -> needs ₹5 change, which the register has
        vm.insertMoney(Currency.TEN);
        vm.insertMoney(Currency.TEN);
        vm.insertMoney(Currency.TEN);
        vm.selectProduct("A1");            // expect: Returning change : 5
    }

    private static void changeNotPossible(){
        VendingMachine vm = newMachine();
        // Empty register: ₹30 for ₹25 needs ₹5 change, but only ₹10 coins exist
        vm.insertMoney(Currency.TEN);
        vm.insertMoney(Currency.TEN);
        vm.insertMoney(Currency.TEN);
        vm.selectProduct("A1");            // expect: Cannot make change ... (refund, back to Idle)
        vm.selectProduct("A1");            // expect: Please insert money first
    }

    private static void sampleFlow(){
        VendingMachine vm = newMachine();
        vm.insertMoney(Currency.TEN);
        vm.insertMoney(Currency.TEN);      // expect: Current Balance : 20
        vm.selectProduct("A1");            // expect: Insufficient money : 5 more
        vm.insertMoney(Currency.TEN);      // expect: Current Balance : 30
        vm.selectProduct("A1");            // expect: Cannot make change (no ₹5 in an empty register)
        vm.selectProduct("A2");            // expect: Please insert money first
        vm.insertMoney(Currency.TWENTY);
        vm.selectProduct("A2");            // expect: Sold out
        vm.cancel();                       // expect: refund ₹20
    }

    private static void multipleCoinsOfOneKind(){
        CashRegister register = new CashRegister();
        List<Slot> slots = new ArrayList<>();
        slots.add(new Slot("A1", new Product("Coke", 25), 2));
        VendingMachine vm = new VendingMachine(slots, register);

        // Customer 1 pays exact with 10+10+5 -> register {TEN=2, FIVE=1}
        vm.insertMoney(Currency.TEN);
        vm.insertMoney(Currency.TEN);
        vm.insertMoney(Currency.FIVE);
        vm.selectProduct("A1");
        // Customer 2 pays ₹50 for ₹25 -> change ₹25 = 2×10 + 1×5
        vm.insertMoney(Currency.FIFTY);
        vm.selectProduct("A1");            // expect: Returning change : 25
        System.out.println("Register: " + register.getCoins()); // expect: FIFTY=1, TEN=0, FIVE=0
    }

    private static void backtrackingBeatsGreedy(){
        CashRegister register = new CashRegister();
        List<Slot> slots = new ArrayList<>();
        slots.add(new Slot("B1", new Product("Juice", 50), 5));
        slots.add(new Slot("B2", new Product("Sandwich", 60), 5));
        slots.add(new Slot("B3", new Product("Water", 40), 5));
        VendingMachine vm = new VendingMachine(slots, register);

        vm.insertMoney(Currency.FIFTY);    // exact for Juice -> register {FIFTY=1}
        vm.selectProduct("B1");
        vm.insertMoney(Currency.TWENTY);   // exact for Sandwich -> register {FIFTY=1, TWENTY=3}
        vm.insertMoney(Currency.TWENTY);
        vm.insertMoney(Currency.TWENTY);
        vm.selectProduct("B2");

        // ₹100 for ₹40 Water -> ₹60 change. Greedy takes the 50, then can't make 10, and refuses.
        // Backtracking drops the 50 and finds 3×20.
        vm.insertMoney(Currency.HUNDRED);
        vm.selectProduct("B3");            // expect: Returning change : 60
        System.out.println("Register: " + register.getCoins()); // expect: HUNDRED=1, FIFTY=1, TWENTY=0
    }
}
