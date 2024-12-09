package freull0.logic;

import freull0.model.Account;
import freull0.model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerLogic
{
    private List<Customer> customers = new ArrayList<>();

    /**
     * Hitta en specifik kund
     *
     * @param pNo
     *         personnummer
     * @return kund-objektet
     */
    public Customer findCustomer(String pNo)
    {
        for(Customer customer : customers)
        {
            if(customer.getpNo().equals(pNo))
            {
                return customer;
            }
        }
        return null;
    }

    /**
     * Hämtar alla kunder
     */
    public List<String> getAllCustomersAsStrings()
    {
        List<String> customerList = new ArrayList<>();
        for(Customer c : customers)
        {
            customerList.add(c.getpNo() + " " + c.getFirstName() + " " + c.getLastName());
        }
        return customerList;
    }

    /**
     * Hämtar alla kunder
     */
    public List<Customer> getCustomers()
    {
        return customers;
    }

    /**
     * @param pNo
     *         personnummer
     * @param accountId
     *         kontoNummer
     * @return information om ett specifikt konto
     */
    public String getAccount(String pNo, int accountId)
    {
        for(Customer c : customers)
        {
            if(c.getpNo().equals(pNo))
            {
                for(int i = 0; i < c.getAccounts().size(); i++)
                {
                    if(c.getAccounts().get(i).getAccountNumber() == accountId)
                    {

                        return c.getAccounts().get(i).toString();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Skapa upp en ny kund
     *
     * @param name
     *         förnamn
     * @param surname
     *         efternamn
     * @param pNo
     *         personnummer return flagga för att indikera om kund skapades elle ej
     */
    public boolean createCustomer(String name, String surname, String pNo)
    {
        // Kolla om kund finns
        boolean customerExist = findCustomer(pNo) != null;

        if(customerExist)
        {
            System.out.println("Customer exists");
            return false;
        }
        Customer newCustomer = new Customer(name, surname, pNo);
        customers.add(newCustomer);
        return true;

    }

    /**
     * Ändra namn på en kund
     *
     * @param name
     *         förnamn
     * @param surname
     *         efternamn
     * @param pNo
     *         personnummer return flagga för att indikera om kundnamn ändrats eller ej
     */
    public boolean changeCustomerName(String name, String surname, String pNo)
    {
        for(Customer c : customers)
        {
            if(c.getpNo().equals(pNo))
            {
                // när både för och efternamn är tomma strängar returnera false
                if(name.isEmpty() && surname.isEmpty())
                {
                    return false;
                }

                if(!name.isEmpty())
                {
                    c.changeFirstName(name);
                }
                if(!surname.isEmpty())
                {
                    c.changeLastName(surname);

                }

                return true;
            }
        }
        return false;
    }

    /**
     * hämtar en kund
     *
     * @param pNo
     *         personnummer return lista med information om den specifika kunden
     */
    public List<String> getCustomer(String pNo)
    {
        List<String> customer = new ArrayList<>();
        //iterera över lista med kunder
        Customer c = findCustomer(pNo);
        // Om kund inte finns så behövs denna check, annars får vi nullpointer exception.
        if(c == null)
        {
            return null;
        }

        //Skapa upp en lista med info om kontoinformationen
        ArrayList<String> accountItemStrings = new ArrayList<>();
        if(c.getAccounts() != null)
        {
            for(Account item : c.getAccounts())
            {
                accountItemStrings.add(item.toString());
            }
        }

        // Sätt ihop listan med kontoinformation
        String accountItemListString = String.join(", ", accountItemStrings);

        //Om kontoinfo-listan är tom så borde vi inte visa "[]"
        if(!c.getAccounts().isEmpty())
        {
            customer.add(c.getpNo() + " " + c.getFirstName() + " " + c.getLastName() + ", " + accountItemListString);

        }
        // Visa kontoinformation
        else
        {
            customer.add(c.getpNo() + " " + c.getFirstName() + " " + c.getLastName());
        }

        return customer;
    }

    /**
     * Tar bort en kund
     *
     * @param pNo
     *         personnummer kunddata
     */
    public List<String> deleteCustomer(String pNo)
    {
        Customer customer = findCustomer(pNo);
        List<String> customerInformation = new ArrayList<>();

        // kolla null
        if(customer != null)
        {
            StringBuilder removedAccountsString = new StringBuilder();
            for(Account account : customer.getAccounts())
            {
                removedAccountsString.append(", ");
                removedAccountsString.append(account.closedAccountMessage());
            }

            // om kund inte har några konton alls
            if(customer.getAccounts().isEmpty())
            {
                customerInformation.add(
                        customer.getpNo() + " " + customer.getFirstName() + " " + customer.getLastName());
                getCustomers().remove(customer);
                return customerInformation;

            }
            else
            {
                // finns konton så visa även dessa
                customerInformation.add(
                        customer.getpNo() + " " + customer.getFirstName() + " " + customer.getLastName() + removedAccountsString);
                getCustomers().remove(customer);
                return customerInformation;

            }
        }
        return null;
    }

    public boolean saveAllCustomers(List<Customer> allCustomers)
    {
        try
        {
            ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("all-customers.dat"));
            utfil.writeObject(allCustomers);
            System.out.println("All customers saved successfully." + customers.size() + " customers");
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Customer> loadCustomersFromFile()
    {
        ObjectInputStream infil;
        List<Customer> allCustomers;
        try
        {
            infil = new ObjectInputStream(new FileInputStream("all-customers.dat"));
            allCustomers = (List<Customer>) infil.readObject();
            //Print each customer in the file
            allCustomers.stream().map(c -> "Kund: " + c).forEach(System.out::println);
            // customers.addAll(allCustomers);
            //du stannade vid att om du laddar filen och sen lägger till konton på miranda amro så sparas  dom inte till filen. du behöver göra något för att spara de till filen när du skapar kontona.

        }
        catch(IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        return allCustomers;
    }

}
