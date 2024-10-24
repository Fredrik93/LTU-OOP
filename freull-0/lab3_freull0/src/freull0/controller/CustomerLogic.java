package freull0.controller;

import freull0.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerLogic
{
    private Map<String, Customer> customerMap = new HashMap<>();

    public Customer createCustomer(String pNo, String firstName, String lastName)
    {
        Customer newCustomer = new Customer(pNo, firstName, lastName);
        customerMap.put(pNo, newCustomer);
        return newCustomer;
    }

    public Customer getCustomer(String pNo)
    {
        System.out.println("Found customer with pNO" + pNo + ": " + customerMap.get(pNo));
        return customerMap.get(pNo);
    }

    public Map<String, Customer> getCustomers()
    {
        return customerMap;
    }
}
