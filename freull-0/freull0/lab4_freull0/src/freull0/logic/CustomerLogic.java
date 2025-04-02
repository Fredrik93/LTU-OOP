package freull0.logic;

import freull0.model.Account;
import freull0.model.Customer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
/**
 * Inlämningsuppgift4 Syfte: Logik för kunder
 *
 * @author Fredrik Ullman, freull-0
 */
public class CustomerLogic {
    private List<Customer> customers = new ArrayList<>();

    /**
     * Hitta en specifik kund
     *
     * @param pNo
     *         personnummer
     * @return kund-objektet
     */
    public Customer findCustomer(String pNo) {
        for (Customer customer : customers) {
            if (customer.getpNo()
                    .equals(pNo)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Hämtar alla kunder
     */
    public List<String> getAllCustomersAsStrings() {
        List<String> customerList = new ArrayList<>();
        for (Customer c : customers) {
            customerList.add(c.getpNo() + " " + c.getFirstName() + " " + c.getLastName());
        }
        return customerList;
    }

    /**
     * Hämtar alla kunder
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * @param pNo
     *         personnummer
     * @param accountId
     *         kontoNummer
     * @return information om ett specifikt konto
     */
    public String getAccount(String pNo, int accountId) {
        for (Customer c : customers) {
            if (c.getpNo()
                    .equals(pNo)) {
                for (int i = 0; i < c.getAccounts()
                        .size(); i++) {
                    if (c.getAccounts()
                            .get(i)
                            .getAccountNumber() == accountId) {

                        return c.getAccounts()
                                .get(i)
                                .toString();
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
    public boolean createCustomer(String name, String surname, String pNo) {
        // Kolla om kund finns
        boolean customerExist = findCustomer(pNo) != null;

        if (customerExist) {
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
    public boolean changeCustomerName(String name, String surname, String pNo) {
        for (Customer c : customers) {
            if (c.getpNo()
                    .equals(pNo)) {
                // när både för och efternamn är tomma strängar returnera false
                if (name.isEmpty() && surname.isEmpty()) {
                    return false;
                }

                if (!name.isEmpty()) {
                    c.changeFirstName(name);
                }
                if (!surname.isEmpty()) {
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
    public List<String> getCustomer(String pNo) {
        List<String> customer = new ArrayList<>();
        //iterera över lista med kunder
        Customer c = findCustomer(pNo);
        // Om kund inte finns så behövs denna check, annars får vi nullpointer exception.
        if (c == null) {
            return null;
        }

        //Skapa upp en lista med info om kontoinformationen
        ArrayList<String> accountItemStrings = new ArrayList<>();
        if (c.getAccounts() != null) {
            for (Account item : c.getAccounts()) {
                accountItemStrings.add(item.toString());
            }
        }

        // Sätt ihop listan med kontoinformation
        String accountItemListString = String.join(", ", accountItemStrings);

        //Om kontoinfo-listan är tom så borde vi inte visa "[]"
        if (!c.getAccounts()
                .isEmpty()) {
            customer.add(c.getpNo() + " " + c.getFirstName() + " " + c.getLastName() + ", " + accountItemListString);

        }
        // Visa kontoinformation
        else {
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
    public List<String> deleteCustomer(String pNo) {
        Customer customer = findCustomer(pNo);
        List<String> customerInformation = new ArrayList<>();

        // kolla null
        if (customer != null) {
            StringBuilder removedAccountsString = new StringBuilder();
            for (Account account : customer.getAccounts()) {
                removedAccountsString.append(", ");
                removedAccountsString.append(account.closedAccountMessage());
            }

            // om kund inte har några konton alls
            if (customer.getAccounts()
                    .isEmpty()) {
                customerInformation.add(customer.getpNo() + " " + customer.getFirstName() + " " + customer.getLastName());
                getCustomers().remove(customer);
                return customerInformation;

            } else {
                // finns konton så visa även dessa
                customerInformation.add(customer.getpNo() + " " + customer.getFirstName() + " " + customer.getLastName()
                                                + removedAccountsString);
                getCustomers().remove(customer);
                return customerInformation;

            }
        }
        return null;
    }

    public boolean saveAllCustomers(List<Customer> allCustomers, String filePath) {
        try {
            //Kolla så man inte råkar spara en tom lista.
            if (customers.isEmpty()) {
                throw new IOException("There are no customers to be saved to the file.");
            } else {
                //sätter man append till false så rensas filen innan man skriver till den på nytt. Man ser alltså till att filen skrivs över istället för att man lägger till (appendar)
                ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream(filePath, false));
                utfil.writeObject(allCustomers);

                System.out.println(customers.size() + " customers saved successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Ladda kunddata från fil
     * @return lista med kunder
     */
    public List<Customer> loadCustomersFromFile(String filePath) {
        ObjectInputStream infil;
        List<Customer> allCustomers;

        try {
            infil = new ObjectInputStream(new FileInputStream(filePath));
            allCustomers = (List<Customer>) infil.readObject();
            //Print each customer in the file
            allCustomers.stream()
                    .map(c -> "Kund: " + c)
                    .forEach(System.out::println);
            allCustomers.stream()
                    .map(c -> createCustomer(c.getFirstName(), c.getLastName(), c.getpNo()));
            //Ta bort alla nuvarande kunder och ladda in filen på nytt
            customers.clear();
            customers.addAll(allCustomers);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allCustomers;
    }

    //Kolla så mappen existerar som man sparar filer till
    private void checkIfDirectoryExist(String directoryPath) throws FileSystemException {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Mappen skapad");
        }
    }
}
