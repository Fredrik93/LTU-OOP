package freull0;

import freull0.controller.BankController;
import freull0.model.Customer;

import javax.swing.*;
import java.util.List;

public class GUI extends JFrame
{

    private final BankController controller;

    public GUI()
    {
        // Initialize the BankController
        controller = new BankController();

        // Set up test users
        controller.createCustomer("Jim", "Johnson", "1");
        controller.createCustomer("Patrik", "Nilsson", "2");
        controller.createSavingsAccount("2");
        controller.createCreditAccount("2");

        // Setup the JFrame
        setTitle("bank mgmt system");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Add Menues
        JMenu customerMenu = new JMenu("Customer");
        JMenu accountMenu = new JMenu("Account");
        JMenu transactionMenu = new JMenu("Transaction");

        // Add Menu Items and Listeners
        addCustomerMenuItems(customerMenu);
        addAccountMenuItems(accountMenu);
        addTransactionMenuItems(transactionMenu);

        // Add menus to the menu bar
        menuBar.add(customerMenu);
        menuBar.add(accountMenu);
        menuBar.add(transactionMenu);

        // Set the menu bar
        setJMenuBar(menuBar);
    }

    private void addCustomerMenuItems(JMenu menu)
    {
        JMenuItem createCustomer = new JMenuItem("Create Customer");
        createCustomer.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter first name:");
            String surname = JOptionPane.showInputDialog("Enter last name:");
            String pNo = JOptionPane.showInputDialog("Enter personal number:");
            boolean success = controller.createCustomer(name, surname, pNo);
            showMessage(success ? "Customer created successfully!" : "Failed to create customer.");
        });

        JMenuItem changeCustomerName = new JMenuItem("Change Customer Name");
        changeCustomerName.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter new first name:");
            String surname = JOptionPane.showInputDialog("Enter new last name:");
            String pNo = JOptionPane.showInputDialog("Enter personal number:");
            boolean success = controller.changeCustomerName(name, surname, pNo);
            showMessage(success ? "Customer name updated successfully!" : "Failed to update customer name.");
        });

        JMenuItem getAllCustomers = new JMenuItem("Get All Customers");
        getAllCustomers.addActionListener(e -> {
            List<String> customers = controller.getAllCustomersAsStrings();
            JOptionPane.showMessageDialog(this, String.join("\n", customers), "Customers",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JMenuItem getCustomer = new JMenuItem("Get a customer");
        getCustomer.addActionListener(e -> {
            String pNo = JOptionPane.showInputDialog("Enter personal number:");
            Customer customer = controller.getCustomer(pNo);
            showMessage("Customer found:" + customer.toString());
        });

        menu.add(createCustomer);
        menu.add(changeCustomerName);
        menu.add(getAllCustomers);
        menu.add(getCustomer);
    }

    private void addAccountMenuItems(JMenu menu)
    {
        JMenuItem createSavingsAccount = new JMenuItem("Create Savings Account");
        createSavingsAccount.addActionListener(e -> {
            String pNo = JOptionPane.showInputDialog("Enter personal number:");
            int accountId = controller.createSavingsAccount(pNo);
            showMessage("Savings account created with ID: " + accountId);
        });

        JMenuItem closeAccount = new JMenuItem("Close Account");
        closeAccount.addActionListener(e -> {
            String pNo = JOptionPane.showInputDialog("Enter personal number:");
            int accountId = Integer.parseInt(JOptionPane.showInputDialog("Enter account ID:"));
            String info = controller.closeAccount(pNo, accountId);
            showMessage("Account closed: " + info);
        });

        menu.add(createSavingsAccount);
        menu.add(closeAccount);
    }

    private void addTransactionMenuItems(JMenu menu)
    {
        JMenuItem deposit = new JMenuItem("Deposit");
        deposit.addActionListener(e -> {
            String pNo = JOptionPane.showInputDialog("Enter personal number:");
            int accountNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter account number:"));
            int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter deposit amount:"));
            boolean success = controller.deposit(pNo, accountNumber, amount);
            showMessage(success ? "Deposit successful!" : "Failed to deposit.");
        });

        JMenuItem withdraw = new JMenuItem("Withdraw");
        withdraw.addActionListener(e -> {
            String pNo = JOptionPane.showInputDialog("Enter personal number:");
            int accountNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter account number:"));
            int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter withdrawal amount:"));
            boolean success = controller.withdraw(pNo, accountNumber, amount);
            showMessage(success ? "Withdrawal successful!" : "Failed to withdraw.");
        });

        menu.add(deposit);
        menu.add(withdraw);
    }

    private void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args)
    {

        GUI gui = new GUI();
        gui.setVisible(true);

    }
}
