package freull0;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Fredrik Ullman, freull-0
 */

public class BankLogic
{
    List<Customer> customers = new ArrayList<>();

    public boolean deposit(String pNo, int accountNumber, int amount)
    {
        return false;
    }

    public boolean withdraw(String pNo, int accountNumber, int amount)
    {
        return false;
    }

    public List<String> getAllCustomers()
    {
        List<String> customerList = new ArrayList<>();
        if(customers != null)
        {
            for(Customer c : customers)
            {
                customerList.add(c.getpNo() + " " + c.getFirstName() + " " + c.getLastName());
            }
        }
        return customerList;
    }

    public List<String> getCustomer(String pNo)
    {
        List<String> customer = new ArrayList<>();
        boolean customerExist = false;
        for(Customer c : customers)
        {
            if(pNo.equals(c.getpNo()))
            {
                ArrayList<String> accountItemStrings = new ArrayList<>();
                for(Account item : c.getAccounts())
                {
                    accountItemStrings.add(item.toString());
                }

                // Join the list of strings into one single string
                String accountItemListString = String.join(", ", accountItemStrings);

                if(!c.getAccounts().isEmpty())
                {
                    customer.add(
                            c.getpNo() + " " + c.getFirstName() + " " + c.getLastName() + ", " + accountItemListString);

                }
                else
                {
                    customer.add(c.getpNo() + " " + c.getFirstName() + " " + c.getLastName());
                }
                customerExist = true;
            }
        }
        if(customerExist)
        {
            return customer;
        }
        return null;
    }

    public String getAccount(String pNo, int accountId)
    {
        return null;
    }

    public boolean createCustomer(String name, String surname, String pNo)
    {
        for(Customer c : customers)
        {
            if(Objects.equals(c.getpNo(), pNo))
            {
                //customer with that pNo already exist
                return false;
            }
        }
        Customer customer = new Customer(name, surname, pNo);
        customers.add(customer);
        return true;

    }

    public boolean changeCustomerName(String name, String surname, String pNo)
    {
        for(Customer c : customers)
        {
            if(c.getpNo().equals(pNo))
            {
                if(!name.isBlank())
                {
                    c.changeFirstName(name);
                }
                if(!surname.isBlank())
                {
                    c.changeLastName(surname);
                }
                return true;
            }
        }
        return false;
    }

    public int createSavingsAccount(String pNo)
    {
        for(Customer c : customers)
        {
            if(c.getpNo().equals(pNo))
            {
                Account newAccount = new Account(new BigDecimal(0), new BigDecimal("2.4"), AccountType.SPARKONTO);
                c.addAccountToCustomer(newAccount);
                return newAccount.getAccountNumber();
            }
        }
        return -1;
    }

    public String closeAccount(String pNo, int accountId)
    {
        return null;
    }

    public List<String> deleteCustomer(String pNo)
    {
        List<String> customer = new ArrayList<>();
        boolean customerExist = false;
        // Iterate backward to avoid index shift issues
        for(int i = customers.size() - 1; i >= 0; i--)
        {
            if(customers.get(i).getpNo().equals(pNo))
            {
                customerExist = true;
                customer.add(customers.get(i).getpNo() + " " + customers.get(i).getFirstName() + " " + customers.get(i)
                        .getLastName());
                customers.remove(i);
            }
        }
        if(customerExist)
        {
            return customer;
        }
        return null;
    }
}