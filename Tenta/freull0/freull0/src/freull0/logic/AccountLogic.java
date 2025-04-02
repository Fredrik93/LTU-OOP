package freull0.logic;

import freull0.model.Account;
import freull0.model.CreditAccount;
import freull0.model.Customer;
import freull0.model.SavingsAccount;
import freull0.model.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
/**
 * Inlämningsuppgift4 Syfte: Logik för konton
 *
 * @author Fredrik Ullman, freull-0
 */
public class AccountLogic {
    List<Integer> listOfAccountNumbers = new ArrayList<>();

    //Add numbers to accountnumbers list
    public boolean addAccountNumberToList(int accountNumber) {
        //add to list of account numbers
        listOfAccountNumbers.add(accountNumber);
        return true;
    }

    //Get list of accountnumbers
    public List<Integer> getAccountNumberList() {
        return listOfAccountNumbers;
    }

    public int createCreditAccount(Customer customer) {
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal interestRate = new BigDecimal("1.1");
        if (customer != null) {
            CreditAccount creditAccount = new CreditAccount(amount, interestRate);
            customer.addAccountToCustomer(creditAccount);
            addAccountNumberToList(creditAccount.getAccountNumber());
            return creditAccount.getAccountNumber();
        }
        return -1;
    }

    /**
     * Skapar ett konto för en kund
     *
     * @param customer
     *         kunden personnummer return det nya kontonummret
     */
    public int createSavingsAccount(Customer customer) {

        if (customer == null) {
            return -1;
        }

        Account newAccount = new SavingsAccount(new BigDecimal(0), new BigDecimal("2.4"));
        customer.addAccountToCustomer(newAccount);
        addAccountNumberToList(newAccount.getAccountNumber());
        return newAccount.getAccountNumber();
    }

    public List<String> getTransactions(Customer customer, int accountId) {
        String pNo = customer.getpNo();
        Account account = findAccount(customer, accountId);
        //Kolla om konto finns
        if (account == null) {
            return null;
        }
        if (!customer.getpNo()
                .equals(pNo)) {
            System.out.println("Incorrect pNo: " + pNo + " for customer " + customer.getpNo());
            return null;
        }

        List<String> transactionsPerAccount = new ArrayList<>();
        for (Transaction transaction : account.getTransactions()) {
            if (transaction.accountId() == accountId) {
                transactionsPerAccount.add(transaction.toString());
            }
        }
        return transactionsPerAccount;
        //Customer doesnt exist
    }

    /**
     * Printa kunds kontoutdrag
     * @param transactions utdrag
     * @param customer kund
     * @param accountNumber kontonummer
     * @param directoryPath vägen till filen
     * @return lista med utdrag
     */
    public List<String> printStatementOfAccountTransactions(List<String> transactions,
                                                            Customer customer,
                                                            int accountNumber,
                                                            String directoryPath) throws FileSystemException {

        //skapa upp vägen som utdragen sparas i
        String fileName = "utdrag_" + customer.getLastName() + "_konto_" + accountNumber + ".txt";
        String filePath = directoryPath + "/" + fileName;

        //Bufferedwriter fungerade bättre för att skriva till textfiler
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Kontoinformation för " + customer.getFirstName() + " " + customer.getLastName() + " "
                                 + customer.getpNo());
            writer.newLine();
            //hämta kontonummer
            writer.write("Konto: " + accountNumber);
            writer.newLine();
            writer.write("Saldo: " + findAccount(customer,accountNumber).getAmount());
            writer.newLine();
            for (String transaction : transactions) {
                writer.write(transaction);
                writer.newLine();
            }
            writer.write("=============================================");

        } catch (IOException e) {
            System.out.println("Kunde inte skriva till textfil.");
            e.printStackTrace();

        }

        return transactions;
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
    public Account findAccount(Customer customer, int accountNumber) {
        if (customer != null) {
            for (int i = 0; i < customer.getAccounts()
                    .size(); i++) {
                if (customer.getAccounts()
                        .get(i)
                        .getAccountNumber() == accountNumber) {
                    return customer.getAccounts()
                            .get(i);
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
    public String closeAccount(Customer customer, int accountId) {
        Account account = findAccount(customer, accountId);

        if (account == null) {
            return null;
        }
        String deletedAccount = account.closedAccountMessage();
        customer.getAccounts()
                .remove(account);
        return deletedAccount;
    }

}
