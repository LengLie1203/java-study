package com.lqw.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LQW
 * @date 2021-03-29 16:29
 **/
class Customer {
    private static long counter = 1;
    private final long id = counter++;
    @Override
    public String toString() {
        return "Customer " + id;
    }
}

class Teller {
    private static long counter = 1;
    private final long id = counter++;
    @Override
    public String toString() {
        return "Teller " + id;
    }
}

class Bank {
    private List<BankTeller> tellers =
            new ArrayList<>();
    public void put(BankTeller bt) {
        tellers.add(bt);
    }
}

public class BankTeller {
    public static void serve(Teller t, Customer c) {
        System.out.println(t + " serves " + c);
    }
    public static void main(String[] args) {
        // Demonstrate fill():
        List<Customer> customers = Suppliers.fill(
                new ArrayList<>(), Customer::new, 12);

        // Demonstrate assisted latent typing:
        Bank bank = Suppliers.fill(
                new Bank(), Bank::put, BankTeller::new, 3);

        Bank bank1 = new Bank();
        Suppliers.fill(bank1,bank1::put,BankTeller::new, 3);


        // Can also use second version of fill():
        List<Customer> customers2 = Suppliers.fill(
                new ArrayList<>(),
                List::add, Customer::new, 12);
    }
}
