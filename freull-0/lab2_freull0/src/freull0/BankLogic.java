package freull0;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Inlämningsuppgift1 Syfte: Innehåller logik för att hantera kunder och dess konton.
 *
 * @author Fredrik Ullman, freull-0
 */

public class BankLogic
{
    private final List<Customer> customers = new ArrayList<>();

    /**
     * Används för att sätt in pengar på ett konto
     *
     * @param pNo
     *         personnummret
     * @param accountNumber
     *         kontonummer
     * @param amount
     *         saldo
     * @return flagga som indikerar om uttaget är godkänt
     */
    public boolean deposit(String pNo, int accountNumber, int amount)
    {
        Customer customer = findCustomer(pNo);

        for(int i = 0; i < customer.getAccounts().size(); i++)
        {
            if(customer.getAccounts().get(i).getAccountNumber() == accountNumber)
            {
                if(amount > 0)
                {
                    customer.getAccounts().get(i).depositAmount(BigDecimal.valueOf(amount));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Hitta en specifik kund
     *
     * @param pNo
     *         personnummer
     * @return kund-objektet
     */
    private Customer findCustomer(String pNo)
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
     * Används för att ta pengar på ett konto
     *
     * @param pNo
     *         personnummret
     * @param accountNumber
     *         kontonummer
     * @param amount
     *         saldo
     * @param accountType
     *         kontotyp
     * @return flagga som indikerar om insättningen är godkänd
     */
    public boolean withdraw(String pNo, int accountNumber, int amount)
    {
        Customer customer = findCustomer(pNo);
        Account account = findAccount(customer, accountNumber);
        if(account != null)
        {
            if(account.getAccountType().equals(AccountType.SPARKONTO))
            {
                if(account.getAmount().intValue() >= amount && amount > 0)
                {
                    account.withdrawAmount(BigDecimal.valueOf(amount));
                    return true;
                }
            }
            //Else-if för läsbarhet. hade räckt med if och sedan else.
            else if(account.getAccountType().equals(AccountType.KREDITKONTO))
            {
                {
                   return account.withdrawAmount(BigDecimal.valueOf(amount));
                   
                }
            }
        }
        return false;
    }

    /**
     * Används för att ta pengar på ett konto
     *
     * @param pNo
     *         personnummret
     * @param accountNumber
     *         kontonummer
     * @param amount
     *         saldo
     * @return flagga som indikerar om insättningen är godkänd
     */
    public boolean withdrawFromCreditAccount(String pNo, int accountNumber, int amount)
    {
        Customer customer = findCustomer(pNo);
        Account account = findAccount(customer, accountNumber);
        if(account != null)
        {
            if(account.getAmount().intValue() >= amount && amount > 0)
            {
                account.withdrawAmount(BigDecimal.valueOf(amount));
                return true;
            }
        }
        return false;
    }

    /**
     * Hittar en kunds specifika konto
     *
     * @param customer
     *         kunden
     * @param accountNumber
     *         kontonummret
     * @return ett specifikt konto
     */
    private Account findAccount(Customer customer, int accountNumber)
    {
        if(customer != null)
        {
            for(int i = 0; i < customer.getAccounts().size(); i++)
            {
                if(customer.getAccounts().get(i).getAccountNumber() == accountNumber)
                {
                    return customer.getAccounts().get(i);
                }
            }
        }
        return null;
    }

    /** Hämtar alla kunder */
    public List<String> getAllCustomers()
    {
        List<String> customerList = new ArrayList<>();
        for(Customer c : customers)
        {
            customerList.add(c.getpNo() + " " + c.getFirstName() + " " + c.getLastName());
        }
        return customerList;
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
        boolean customerExist;
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
     * Skapar ett konto för en kund
     *
     * @param pNo
     *         personnummer return det nya kontonummret
     */
    public int createSavingsAccount(String pNo)
    {
        for(Customer c : customers)
        {
            if(c.getpNo().equals(pNo))
            {
                Account newAccount = new SavingsAccount(new BigDecimal(0), new BigDecimal("2.4"),
                        AccountType.SPARKONTO);
                c.addAccountToCustomer(newAccount);
                return newAccount.getAccountNumber();
            }
        }
        return -1;
    }

    /**
     * Stänger ett konto för en kund
     *
     * @param pNo
     *         personnummer
     * @param accountId
     *         kontonummret return information om det stängda kontot
     */
    public String closeAccount(String pNo, int accountId)
    {
        for(Customer c : customers)
        {
            if(c.getpNo().equals(pNo))
            {
                for(int i = 0; i < c.getAccounts().size(); i++)
                {
                    if(c.getAccounts().get(i).getAccountNumber() == accountId)
                    {
                        String deletedAccount = c.getAccounts().get(i).closedAccountMessage();
                        c.getAccounts().remove(i);
                        return deletedAccount;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Tar bort en kund
     *
     * @param pNo
     *         personnummer return information om den stänga kunden
     */
    public List<String> deleteCustomer(String pNo)
    {
        List<String> customerInformation = new ArrayList<>();
        Customer customer = findCustomer(pNo);

        // kolla null
        if(customer != null)
        {
            String removedAccountsString = showClosedAccountsString(customer);

            // om kund inte har några konton alls
            if(customer.getAccounts().isEmpty())
            {
                customerInformation.add(
                        customer.getpNo() + " " + customer.getFirstName() + " " + customer.getLastName());
                customers.remove(customer);
                return customerInformation;

            }
            else
            {
                // finns konton så visa även dessa
                customerInformation.add(
                        customer.getpNo() + " " + customer.getFirstName() + " " + customer.getLastName() + removedAccountsString);
                customers.remove(customer);
                return customerInformation;

            }
        }
        return null;
    }

    /**
     * formatterar saldo och ränta för information om avslutad kund
     *
     * @param c
     *         kunden
     * @return Textinformation om avslutad kund
     */
    private String showClosedAccountsString(Customer c)
    {
        StringBuilder removedAccountsString = new StringBuilder();
        for(Account account : c.getAccounts())
        {
            removedAccountsString.append(", ");
            removedAccountsString.append(account.closedAccountMessage());
        }

        return removedAccountsString.toString();
    }

    public List<String> getTransactions(String pNo, int accountId)
    {
        return null;
    }

    public int createCreditAccount(String pNO)
    {
        Customer customer = findCustomer(pNO);

        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal interestRate = new BigDecimal("1.1");
        if(customer != null)
        {
            CreditAccount creditAccount = new CreditAccount(amount, interestRate, AccountType.KREDITKONTO);
            customer.addAccountToCustomer(creditAccount);
            return creditAccount.getAccountNumber();
        }
        return -1;
    }
}