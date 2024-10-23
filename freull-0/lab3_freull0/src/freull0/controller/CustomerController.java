package freull0.controller;

import freull0.model.Customer;

import java.util.List;

public class CustomerController
{
    private final BankLogic logic;

    public CustomerController(BankLogic logic)
    {
        this.logic = logic;
    }

    public List<Customer> getCustomers()
    {
        //make new method that fetches Customer list instead of String
        return logic.getCustomers();
    }
}
