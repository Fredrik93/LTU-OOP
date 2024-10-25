package freull0.logic;

import freull0.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountLogic
{

    public int createCreditAccount(Customer customer)
    {
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal interestRate = new BigDecimal("1.1");
        if(customer != null)
        {
            CreditAccount creditAccount = new CreditAccount(amount, interestRate);
            customer.addAccountToCustomer(creditAccount);
            return creditAccount.getAccountNumber();
        }
        return -1;
    }

    /**
     * Skapar ett konto för en kund
     *
     * @param pNo
     *         personnummer return det nya kontonummret
     */
    public int createSavingsAccount(Customer customer)
    {
        if(customer == null)
        {
            return -1;
        }

        Account newAccount = new SavingsAccount(new BigDecimal(0), new BigDecimal("2.4"));
        customer.addAccountToCustomer(newAccount);
        return newAccount.getAccountNumber();
    }

    public List<String> getTransactions(Customer customer, int accountId)
    {
        String pNo = customer.getpNo();
        Account account = findAccount(customer, accountId);
        //Kolla om konto finns
        if(account == null)
        {
            return null;
        }
        if(!customer.getpNo().equals(pNo))
        {
            System.out.println("Incorrect pNo: " + pNo + " for customer " + customer.getpNo());
            return null;
        }

        List<String> transactionsPerAccount = new ArrayList<>();
        for(Transaction transaction : account.getTransactions())
        {
            if(transaction.accountId() == accountId)
            {
                transactionsPerAccount.add(transaction.toString());
            }
        }
        return transactionsPerAccount;
        //Customer doesnt exist
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
    public Account findAccount(Customer customer, int accountNumber)
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

    /**
     * Stänger ett konto för en kund
     *
     * @param customer
     *         customerdata
     * @param accountId
     *         kontoid
     */
    public String closeAccount(Customer customer, int accountId)
    {
        Account account = findAccount(customer, accountId);

        if(account == null)
        {
            return null;
        }
        String deletedAccount = account.closedAccountMessage();
        customer.getAccounts().remove(account);
        return deletedAccount;
    }

}
