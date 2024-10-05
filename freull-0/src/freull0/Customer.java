package freull0;

import java.util.ArrayList;
import java.util.List;

/**
 * Inlämningsuppgift 1 syfte: Definierar fält för en kund
 *
 * @author Fredrik Ullman, freull-0
 */

public class Customer
{
    private final String pNo;
    private String firstName;
    private String lastName;
    private final List<freull0.Account> accounts;

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
     * @return uppdaterat förnamn
     */
    public String changeFirstName(String newName)
    {
        return this.firstName = newName;
    }

    /**
     * @param newLastName
     *         det nya förnamnet
     * @return uppdaterat förnamn
     */
    public String changeLastName(String newLastName)
    {
        return this.lastName = newLastName;
    }

    /**
     * @param account
     *         nytt konton
     * @return info om det nya kontot
     */
    public int addAccountToCustomer(freull0.Account account)
    {
        accounts.add(account);
        return account.getAccountNumber();
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
