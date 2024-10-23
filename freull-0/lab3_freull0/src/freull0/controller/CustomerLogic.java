package freull0.controller;

import freull0.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerLogic
{
    private Map<String, Customer> customerMap = new HashMap<>();

    public Customer getCustomer(String pNo)
    {
        System.out.println("Found customer with pNO" + pNo + ": " + customerMap.get(pNo));
        return customerMap.get(pNo);
    }
}
