package freull0.controller;

import freull0.model.Account;

import java.util.List;

public class AccountController
{
    private final CustomerLogic customerLogic;

    public AccountController(CustomerLogic customerLogic)
    {
        this.customerLogic = customerLogic;
    }

    public List<Account> getAccounts(String pNo)
    {
        return customerLogic.getCustomer(pNo).getAccounts();
    }
}
