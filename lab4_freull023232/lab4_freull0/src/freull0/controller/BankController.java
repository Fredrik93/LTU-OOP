package freull0.controller;

import freull0.logic.AccountLogic;
import freull0.logic.CustomerLogic;
import freull0.logic.TransactionLogic;
import freull0.model.Account;
import freull0.model.Customer;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;

/**
 * Inlämningsuppgift4 Syfte: Controller för olika tjänster s.k logic-klasser
 *
 * @author Fredrik Ullman, freull-0
 */

public class BankController {
    AccountLogic accountLogic = new AccountLogic();
    CustomerLogic customerLogic = new CustomerLogic();
    TransactionLogic transactionLogic = new TransactionLogic();
    List<Integer> listOfAccountNumbers = new ArrayList<>();
    // Konstanter för att spara filer
    private String DIRECTORY_PATH = "lab4_freull0/freull-0_files";

    private String FILE_NAME = "bank.dat";

    private String FILE_PATH = DIRECTORY_PATH + "/" + FILE_NAME;

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
    public boolean deposit(String pNo, int accountNumber, int amount) {
        Customer customer = customerLogic.findCustomer(pNo);
        Account account = accountLogic.findAccount(customer, accountNumber);
        return transactionLogic.deposit(account, amount);
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
    public boolean withdraw(String pNo, int accountNumber, int amount) {
        Customer customer = customerLogic.findCustomer(pNo);
        Account account = accountLogic.findAccount(customer, accountNumber);
        return transactionLogic.withdraw(account, amount);
    }

    /**
     * Hämtar alla kunder
     */
    public List<String> getAllCustomersAsStrings() {
        return customerLogic.getAllCustomersAsStrings();
    }

    /**
     * Hämtar alla kunder
     */
    public List<Customer> getCustomers() {

        return customerLogic.getCustomers();
    }

    /**
     * hämtar en kund
     *
     * @param pNo
     *         personnummer return lista med information om den specifika kunden
     */
    public List<String> getCustomerString(String pNo) {
        return customerLogic.getCustomer(pNo);
    }

    /**
     * hämtar en kund
     *
     * @param pNo
     *         personnummer return lista med information om den specifika kunden
     */
    public Customer getCustomer(String pNo) {
        return customerLogic.findCustomer(pNo);
    }

    /**
     * @param pNo
     *         personnummer
     * @param accountId
     *         kontoNummer
     * @return information om ett specifikt konto
     */
    public String getAccount(String pNo, int accountId) {
        return customerLogic.getAccount(pNo, accountId);
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
    public boolean createCustomer(String name, String surname, String pNo) {
        return customerLogic.createCustomer(name, surname, pNo);

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
    public boolean changeCustomerName(String name, String surname, String pNo) {
        return customerLogic.changeCustomerName(name, surname, pNo);
    }

    /**
     * Skapar ett konto för en kund
     *
     * @param pNo
     *         personnummer return det nya kontonummret
     */
    public int createSavingsAccount(String pNo) {
        Customer customer = customerLogic.findCustomer(pNo);
        return accountLogic.createSavingsAccount(customer);
    }

    /**
     * Stänger ett konto för en kund
     *
     * @param pNo
     *         personnummer
     * @param accountId
     *         kontonummret return information om det stängda kontot
     */
    public String closeAccount(String pNo, int accountId) {
        Customer customer = customerLogic.findCustomer(pNo);
        return accountLogic.closeAccount(customer, accountId);
    }

    /**
     * Tar bort en kund
     *
     * @param pNo
     *         personnummer return information om den stänga kunden
     */
    public List<String> deleteCustomer(String pNo) {
        return customerLogic.deleteCustomer(pNo);
    }

    public List<String> getTransactions(String pNo, int accountId) {
        Customer customer = customerLogic.findCustomer(pNo);
        return accountLogic.getTransactions(customer, accountId);
    }
    /**
     * Writes a customers account statement and balance to a text file.
     * @param pNO personnummer
     * @param accountNumber kontonummer e.g., 1001
     * @return
     */
    public List<String> printStatementOfAccountTransactions(String pNO, int accountNumber) throws FileSystemException {
        //Hämta transaktioner och saldo
        List<String> transactions = getTransactions(pNO, accountNumber);
        Customer customer = getCustomer(pNO);
        checkIfDirectoryExist(DIRECTORY_PATH);
        accountLogic.printStatementOfAccountTransactions(transactions, customer, accountNumber, DIRECTORY_PATH);
        return transactions;
    }
    public int createCreditAccount(String pNO) {
        Customer customer = customerLogic.findCustomer(pNO);
        return accountLogic.createCreditAccount(customer);
    }

    //********** Input output methods *********

    /**
     * Save all customers to a file
     */
    public boolean saveAllCustomersToFile() throws IOException {
        if (getCustomers().size() <= 0) {
            throw new IOException(
                    "There are no customers to be saved. If youd like to erase file data, remove the file instead.");
        }

        checkIfDirectoryExist(DIRECTORY_PATH);
        customerLogic.saveAllCustomers(getCustomers(), FILE_PATH);
        return true;
    }

    public List<Customer> loadAllCustomersFromFile() {
        List<Customer> customers = customerLogic.loadCustomersFromFile(FILE_PATH);
        for (Customer c : customers) {
            listOfAccountNumbers.addAll(c.getAccounts()
                                                .stream()
                                                .map(a -> a.getAccountNumber())
                                                .toList());
            //Om det redan finns kontonummer, sätt det till det senaste kontonummret.
            if (!listOfAccountNumbers.isEmpty()) {
                Account.setLastAccountNumber(listOfAccountNumbers.getLast());
            }
        }
        return customers;
    }
    //Kolla så mappen existerar som man sparar filer till
    private void checkIfDirectoryExist(String directoryPath) throws FileSystemException {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            //Skapa mapp om den inte finns
            directory.mkdir();
            System.out.println("Mappen skapad");
        }
    }

}
