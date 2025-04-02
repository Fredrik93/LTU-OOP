package freull0.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Inlämningsuppgift4 syfte: Definierar fält för en kund
 *
 * @author Fredrik Ullman, freull-0
 */

public class Customer implements Serializable
{
    private final String pNo;
    private String firstName;
    private String lastName;
    private final List<Account> accounts;

    /**
     * Konstruktorn. Används för att skapa ny instans av objektet
     *
     * @param firstName
     *         förnamnet
     * @param lastName
     *         efternamet
     * @param pNo
     *         personnummret
     */
    public Customer(String firstName, String lastName, String pNo)
    {
        this.pNo = pNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = new ArrayList<>();
    }

    /**
     * @param newName
     *         det nya förnamnet
     */
    public void changeFirstName(String newName)
    {
        this.firstName = newName;
    }

    /**
     * @param newLastName
     *         det nya förnamnet
     */
    public void changeLastName(String newLastName)
    {
        this.lastName = newLastName;
    }

    /**
     * @param account
     *         nytt konton
     */
    public void addAccountToCustomer(Account account)
    {
        accounts.add(account);
    }

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

    /**
     * @return alla konton för en kund
     */
    public List<Account> getAccounts()
    {
        return accounts;
    }

    @Override
    public String toString()
    {
        return "Customer{" + "pNo='" + pNo + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", accounts=" + accounts + '}';
    }
}
