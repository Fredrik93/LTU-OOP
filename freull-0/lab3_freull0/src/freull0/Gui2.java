package freull0;

import freull0.controller.BankController;
import freull0.model.Account;
import freull0.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Gui2 extends JFrame {
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField pNoField;
    private JTextField accountNumberField;
    private JTextField amountField;
    private final JLabel messageLabel;
    private final BankController bankController;
    private JComboBox<String> accountNumbersField;
    private JComboBox<String> personalNumbersField;

    private String selectedPNo;
    private List<Integer> accounts = new ArrayList<>();

    public Gui2() {
        bankController = new BankController();

        // Set up the frame
        setTitle("Customer App");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //create test customers. remove later.
        bankController.createCustomer("Jim", "johanssen", "1");
        bankController.createCustomer("Adam", "Niklasson", "2");
        //create some testaccounts
        bankController.createSavingsAccount("1");
        bankController.createCreditAccount("1");
        bankController.deposit("1", 1001, 1000);
        bankController.deposit("1", 1002, 1000);
        System.out.println("Customers created .. " + bankController.getCustomers());

        // Create the menu bar and the "Create Customer" menu item
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createCustomerItem = new JMenuItem("Create Customer");
        JMenuItem createNewAccountItem = new JMenuItem("Create Account");
        JMenuItem closeAccountItem = new JMenuItem("Close an Account");
        JMenuItem getCustomerItem = new JMenuItem("Get Customer");
        JMenuItem getAllCustomersItem = new JMenuItem("Get all customers");
        JMenuItem withdrawItem = new JMenuItem("Withdraw an amount");
        JMenuItem depositItem = new JMenuItem("Deposit an amount");
        JMenuItem deleteCustomerItem = new JMenuItem("Delete a customer");

        // Add action listener to show the input panel when clicked
        createCustomerItem.addActionListener(e -> showCustomerInputPanel());
        getCustomerItem.addActionListener(e -> getCustomerPanel());
        getAllCustomersItem.addActionListener(e -> getAllCustomersPanel());
        deleteCustomerItem.addActionListener(e -> deleteCustomerPanel());

        createNewAccountItem.addActionListener(e -> createAccountPanel());
        closeAccountItem.addActionListener(e -> closeAccountPanel());

        withdrawItem.addActionListener(e -> withdrawPanel());
        depositItem.addActionListener(e -> depositPanel());

        //add items to the menu
        menu.add(createCustomerItem);
        menu.add(createNewAccountItem);
        menu.add(closeAccountItem);
        menu.add(getAllCustomersItem);
        menu.add(getCustomerItem);
        menu.add(withdrawItem);
        menu.add(depositItem);
        menu.add(deleteCustomerItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);
        // Message label to display result
        messageLabel = new JLabel("Welcome", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);

    }

    private void showCustomerInputPanel() {
        // Create a panel for the customer input
        JPanel panel = new JPanel(new FlowLayout());

        JLabel nameLabel = new JLabel("Enter Name: ");
        nameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("Enter lastname: ");
        lastNameField = new JTextField(15);

        JLabel pNoLabel = new JLabel("Enter personal number: ");
        pNoField = new JTextField(15);

        JButton submitButton = new JButton("Submit");
        // Action listener for the submit button
        submitButton.addActionListener(new SubmitButtonListener());

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(lastNameLabel);
        panel.add(lastNameField);

        panel.add(pNoLabel);
        panel.add(pNoField);

        panel.add(submitButton);

        setMainPanel(panel);
    }

    private void getAllCustomersPanel() {
        // Create a panel for the customer input
        JPanel panel = new JPanel(new FlowLayout());

        JButton getAllCustomersButton = new JButton("Get all customers");
        // Action listener for the submit button
        getAllCustomersButton.addActionListener(new GetAllCustomersButtonListener());

        panel.add(getAllCustomersButton);

        setMainPanel(panel);
    }
    private void withdrawPanel() {
        JPanel panel = new JPanel();

        JButton withdrawBtn = new JButton("Withdraw from account");

        List<String> customersPnoList = bankController.getCustomers()
                .stream()
                .map(Customer::getpNo)
                .toList();
        String[] pnoArray = customersPnoList.toArray(new String[0]);

        JLabel personalNumbersLabel = new JLabel("Select personal number ");
        //Antar att JComboBox inte tar en List <String>, så får konvertera till en "gammaldags" array.
        personalNumbersField = new JComboBox<>(pnoArray);
        JButton selectCustomerBtn = new JButton("Search for customer accounts");

        // Add ActionListener to print the selected value
        selectCustomerBtn.addActionListener(new SelectCustomerDropDown());

        JLabel accountNumberLabel = new JLabel("Select account number: ");
        String[] accountNumbers = {};
        accountNumbersField = new JComboBox<>(accountNumbers);

        JLabel amountLabel = new JLabel("Enter amount to withdraw");
        amountField = new JTextField(10);

        // Add components to the panel
        // panel.add(pNoLabel);
        // panel.add(pNoField);

        panel.add(personalNumbersLabel);
        panel.add(personalNumbersField);
        panel.add(selectCustomerBtn);

        panel.add(accountNumberLabel);
        panel.add(accountNumbersField); // Use JComboBox instead of JTextField for account numbers

        panel.add(amountLabel);
        panel.add(amountField);

        withdrawBtn.addActionListener(new CreateWithdrawListener());

        panel.add(withdrawBtn);

        // Set the panel as the main content panel
        setMainPanel(panel);
    }
    private void depositPanel() {
        JPanel panel = new JPanel();

        JButton depositBtn = new JButton("Deposit from account");
        JLabel pNoLabel = new JLabel("Enter personal number: ");
        pNoField = new JTextField(15);

        JLabel accountNumberLabel = new JLabel("Enter accountnumber: ");
        accountNumberField = new JTextField(10);

        JLabel amountLabel = new JLabel("Enter amont to deposit");
        amountField = new JTextField(3);

        panel.add(pNoLabel);
        panel.add(pNoField);

        panel.add(accountNumberLabel);
        panel.add(accountNumberField);

        panel.add(amountLabel);
        panel.add(amountField);

        depositBtn.addActionListener(new CreateDepositListener());

        panel.add(depositBtn);

        setMainPanel(panel);

    }

    private void getCustomerPanel() {
        // Create a panel for the customer input
        JPanel panel = new JPanel(new FlowLayout());

        JLabel pNoLabel = new JLabel("Enter personal number: ");
        pNoField = new JTextField(15);

        JButton submitgetCustomerButton = new JButton("Get customer");
        // Action listener for the submit button
        submitgetCustomerButton.addActionListener(new GetCustomerButtonListener());

        panel.add(pNoLabel);
        panel.add(pNoField);

        panel.add(submitgetCustomerButton);

        setMainPanel(panel);
    }
    private void createAccountPanel() {
        JPanel panel = new JPanel();
        JLabel pNoLabel = new JLabel("Enter the personal number");
        pNoField = new JTextField(10);

        JButton createSavingsAccountButton = new JButton("Create new savings account");
        JButton createCreditAccountButton = new JButton("Create new credit account");

        createSavingsAccountButton.addActionListener(new CreateSavingsAccountButtonListener());
        createCreditAccountButton.addActionListener(new CreateCreditAccountButtonListener());

        panel.add(pNoLabel);
        panel.add(pNoField);
        panel.add(createSavingsAccountButton);
        panel.add(createCreditAccountButton);

        setMainPanel(panel);
    }

    private void closeAccountPanel() {
        JPanel panel = new JPanel();
        JLabel pNoLabel = new JLabel("Enter the personal number");
        pNoField = new JTextField(10);

        JLabel accountNumberLabel = new JLabel("Enter account number: ");
        accountNumberField = new JTextField(10);

        JButton closeAccountButton = new JButton("Close account");

        closeAccountButton.addActionListener(new CloseAccountButtonListener());

        panel.add(pNoLabel);
        panel.add(pNoField);

        panel.add(accountNumberLabel);
        panel.add(accountNumberField);

        panel.add(closeAccountButton);

        setMainPanel(panel);
    }
    private void deleteCustomerPanel() {
        JPanel panel = new JPanel();
        JLabel pNoLabel = new JLabel("Enter the personal number");
        pNoField = new JTextField(10);

        JButton deletCustomerButton = new JButton("Delete customer");

        deletCustomerButton.addActionListener(new DeleteCustomerButtonListener());

        panel.add(pNoLabel);
        panel.add(pNoField);

        panel.add(deletCustomerButton);
        setMainPanel(panel);
    }

    // ActionListener class for creating accounts
    private class CreateSavingsAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pNo = pNoField.getText()
                    .trim();

            if (!pNo.isEmpty()) {
                bankController.createSavingsAccount(pNo);
                System.out.println("account created: " + bankController.getCustomer(pNo)
                        .getAccounts());
            } else {
                messageLabel.setText("Incorrect pno.");
            }
        }
    }
    // ActionListener class for closing an account
    private class CloseAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pNo = pNoField.getText()
                    .trim();
            String accountNumber = accountNumberField.getText()
                    .trim();
            if (!pNo.isEmpty()) {
                bankController.closeAccount(pNo, Integer.parseInt(accountNumber));
                System.out.println("account closed: " + bankController.getCustomer(pNo)
                        .getAccounts());
            } else {
                messageLabel.setText("Incorrect pno.");
            }
        }
    }

    // ActionListener class for deleting a customer
    private class DeleteCustomerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pNo = pNoField.getText()
                    .trim();
            if (!pNo.isEmpty()) {
                System.out.println("Deleting customer: " + bankController.getCustomer(pNo));
                bankController.deleteCustomer(pNo);
            } else {
                messageLabel.setText("Incorrect pno.");
            }
        }
    }

    //Actionslistener for dropdown to select customer
    private class SelectCustomerDropDown implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Get the selected item from the JComboBox
            selectedPNo = (String) personalNumbersField.getSelectedItem();
            if (selectedPNo != null) {
                // Print the selected value
                System.out.println("Selected Personal Number: " + selectedPNo);
                accounts = bankController.getCustomer(selectedPNo)
                        .getAccounts()
                        .stream()
                        .map(Account::getAccountNumber)
                        .collect(Collectors.toList());
                System.out.println("acc array: " + accounts);

                String[] accountStrings = accounts.stream()
                        .map(String::valueOf)
                        .toArray(String[]::new);

                accountNumbersField.setModel(new DefaultComboBoxModel<>(accountStrings));

            }

        }
    }
    //Actionlistener for withdrawing an amount
    private class CreateWithdrawListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int NO_WITHDRAW_INPUT_FROM_USER = 0;
            if (amountField.getText()
                    .isEmpty()) {
                amountField.setText("0");
                //throw new IllegalArgumentException("Should be more than 0");
            }
            int amount = amountField.getText()
                                 .isEmpty() ? Integer.parseInt(amountField.getText()
                                                                       .trim()) : NO_WITHDRAW_INPUT_FROM_USER;

            selectedPNo = (String) personalNumbersField.getSelectedItem();
            System.out.println("Selected pno: " + selectedPNo);

            String selectedAccountNumber = (String) accountNumbersField.getSelectedItem();

            assert selectedAccountNumber != null;
            bankController.withdraw(selectedPNo, Integer.parseInt(selectedAccountNumber), amount);
            boolean successfulWithdrawal =
                    bankController.getAccount(selectedPNo, Integer.parseInt(selectedAccountNumber)) != null;

            //Skriv ut hur det gick
            String result = successfulWithdrawal
                            ? "Withdrawal successful: " + bankController.getAccount(selectedPNo, Integer.parseInt(
                    selectedAccountNumber))
                            : " Withdrawal not successful";
            System.out.println(result);
        }
    }
    //Actionlistener for withdrawing an amount
    private class CreateDepositListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pNo = pNoField.getText()
                    .trim();
            int accountNumber = Integer.parseInt(accountNumberField.getText()
                                                         .trim());
            int amount = Integer.parseInt(amountField.getText()
                                                  .trim());

            bankController.deposit(pNo, accountNumber, amount);
            System.out.println("Deposit successful: " + bankController.getAccount(pNo, accountNumber));
        }
    }

    // ActionListener class for creating accounts
    private class CreateCreditAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pNo = pNoField.getText()
                    .trim();

            if (!pNo.isEmpty()) {
                bankController.createCreditAccount(pNo);
                System.out.println("acc created: " + bankController.getCustomer(pNo)
                        .getAccounts());
                messageLabel.setText("creditaccount created: " + bankController.getCustomer(pNo)
                        .getAccounts());
            } else {
                messageLabel.setText("Incorrect pno.");
            }
        }
    }
    // ActionListener class for getting a customer
    private class GetCustomerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pNo = pNoField.getText()
                    .trim();
            Customer customer;
            if (!pNo.isEmpty()) {

                customer = bankController.getCustomer(pNo);
                System.out.println(customer.toString());
                messageLabel.setText("Customer found: " + " " + customer.getFirstName() + " " + customer.getLastName());
            } else {
                messageLabel.setText("Incorrect pno.");
            }
        }
    }

    // ActionListener class for getting all customers
    private class GetAllCustomersButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            bankController.getCustomers();
            bankController.getCustomers()
                    .stream()
                    .forEach(System.out::println);
        }
    }

    // ActionListener class for the submit button
    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText()
                    .trim();
            if (!name.isEmpty()) {
                bankController.createCustomer(name, lastNameField.getText()
                        .trim(), pNoField.getText()
                                                      .trim());
                System.out.println("created " + name + " lastname " + lastNameField.getText()
                        .trim() + " " + pNoField.getText()
                        .trim());
                System.out.println(bankController.getCustomer(pNoField.getText()
                                                                      .trim())
                                           .toString() + " customer ");
                messageLabel.setText("Customer Created: " + name + " " + lastNameField.getText()
                        .trim() + " " + pNoField.getText()
                        .trim());
            } else {
                messageLabel.setText("No name entered.");
            }
        }
    }
    private void setMainPanel(JPanel panel) {
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                                       {
                                           Gui2 app = new Gui2();
                                           app.setVisible(true);
                                       });
    }
}
