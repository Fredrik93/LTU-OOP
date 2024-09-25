package freull0;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
    private final String pNo;
    private String firstName;
    private String lastName;
    private final List<Account> accounts;

    public Customer(String firstName, String lastName, String pNo)
    {
        this.pNo = pNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = new ArrayList<>();
    }

    public String changeFirstName(String newName)
    {
        return this.firstName = newName;
    }

    public String changeLastName(String newLastName)
    {
        return this.lastName = newLastName;
    }

    public int addAccountToCustomer(Account account)
    {
        accounts.add(account);
        return account.getAccountNumber();
    }

    ;

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getpNo()
    {
        return pNo;
    }

    public List<Account> getAccounts()
    {
        return accounts;
    }

}
