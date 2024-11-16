package freull0;

import freull0.controller.BankController;
import freull0.model.Customer;

import javax.swing.*;

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

        // Add Menu

        JMenu menu = new JMenu("Options");

        // Add Menu Items and Listeners
        addMenuItems(menu);

        // Add menus to the menu bar
        menuBar.add(menu);

        // Set the menu bar
        setJMenuBar(menuBar);
    }

    private void addMenuItems(JMenu menu)
    {
        CustomerListener customerListener = new CustomerListener(controller);

        JMenuItem createCustomer = new JMenuItem("Create new customer");
        createCustomer.addActionListener(customerListener);

        JMenuItem changeCustomerName = new JMenuItem("Change Customer Name");
        changeCustomerName.addActionListener(customerListener);

        JMenuItem getAllCustomers = new JMenuItem("Get All Customers");
        getAllCustomers.addActionListener(customerListener);

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
