package freull0;

import freull0.controller.BankController;
import freull0.model.Account;
import freull0.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Gui2 extends JFrame {
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField pNoField;
    private JTextField amountField;
    private JLabel messageLabel;
    private final transient BankController bankController;
    private JComboBox<String> accountNumbersField;
    private JComboBox<String> personalNumbersField;

    private String selectedPNo;
    private List<Integer> accounts = new ArrayList<>();
    private transient Logger logger = Logger.getLogger(getClass().getName());
    private static final String INCORRECT_PNO = "Incorrect personal number";
    private static final String SEARCH_CUSTOMER_ACCOUNTS = "Search for customer accounts";
    private static final String TEST_CUSTOMER_1 = "850404-2791";
    private static final String TEST_CUSTOMER_2 = "640112-4354";
    private static final String SELECT_ACCOUNT_NUMBER = "Select account number: ";


    public Gui2() {
        bankController = new BankController();

        // Set up the frame
        setTitle("Customer App");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //create a few customers. saves a bit of time when testing.
        bankController.createCustomer("Jim", "Johanssen", TEST_CUSTOMER_1);
        bankController.createCustomer("Adam", "Niklasson", TEST_CUSTOMER_2);
        bankController.createCustomer("Miranda", "Amro", "071212-4365");
        //create some test accounts
        bankController.createSavingsAccount(TEST_CUSTOMER_1);
        bankController.createSavingsAccount(TEST_CUSTOMER_2);
        bankController.createCreditAccount(TEST_CUSTOMER_1);
        bankController.createCreditAccount(TEST_CUSTOMER_2);





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
        JButton selectCustomerBtn = new JButton(SEARCH_CUSTOMER_ACCOUNTS);

        // Add ActionListener to print the selected value
        selectCustomerBtn.addActionListener(new SelectCustomerDropDown());

        JLabel accountNumberLabel = new JLabel(SELECT_ACCOUNT_NUMBER);
        String[] accountNumbers = {};
        accountNumbersField = new JComboBox<>(accountNumbers);

        JLabel amountLabel = new JLabel("Enter amount to withdraw");
        amountField = new JTextField(10);

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

        JButton depositBtn = new JButton("Deposit into account");

        List<String> customersPnoList = bankController.getCustomers()
                .stream()
                .map(Customer::getpNo)
                .toList();
        String[] pnoArray = customersPnoList.toArray(new String[0]);

        JLabel personalNumbersLabel = new JLabel("Select personal number ");
        //Antar att JComboBox inte tar en List <String>, så får konvertera till en "gammaldags" array.
        personalNumbersField = new JComboBox<>(pnoArray);
        JButton selectCustomerBtn = new JButton(SEARCH_CUSTOMER_ACCOUNTS);

        // Add ActionListener to print the selected value
        selectCustomerBtn.addActionListener(new SelectCustomerDropDown());

        JLabel accountNumberLabel = new JLabel(SELECT_ACCOUNT_NUMBER);
        String[] accountNumbers = {};
        accountNumbersField = new JComboBox<>(accountNumbers);

        JLabel amountLabel = new JLabel("Enter amount to deposit");
        amountField = new JTextField(3);

        panel.add(personalNumbersLabel);
        panel.add(personalNumbersField);
        panel.add(selectCustomerBtn);

        panel.add(accountNumberLabel);
        panel.add(accountNumbersField); // Use JComboBox instead of JTextField for account numbers

        panel.add(amountLabel);
        panel.add(amountField);

        depositBtn.addActionListener(new CreateDepositListener());

        panel.add(depositBtn);

        setMainPanel(panel);

    }

    private void getCustomerPanel() {
        // Create a panel for the customer input
        JPanel panel = new JPanel(new FlowLayout());

        //Set personal number input
        List<String> customersPnoList = bankController.getCustomers()
                .stream()
                .map(Customer::getpNo)
                .toList();
        String[] pnoArray = customersPnoList.toArray(new String[0]);

        JLabel personalNumbersLabel = new JLabel("Select personal number ");
        //Antar att JComboBox inte tar en List <String>, så får konvertera till en "gammaldags" array.
        personalNumbersField = new JComboBox<>(pnoArray);
        JButton selectCustomerBtn = new JButton(SEARCH_CUSTOMER_ACCOUNTS);

        // Add ActionListener to print the selected value
        selectCustomerBtn.addActionListener(new SelectCustomerDropDown());


        JButton submitgetCustomerButton = new JButton("Get customer");
        // Action listener for the submit button
        submitgetCustomerButton.addActionListener(new GetCustomerButtonListener());

        panel.add(personalNumbersLabel);
        panel.add(personalNumbersField);

        panel.add(submitgetCustomerButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(messageLabel);

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
        //Set personal number input
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

        JLabel accountNumberLabel = new JLabel(SELECT_ACCOUNT_NUMBER);
        String[] accountNumbers = {};
        accountNumbersField = new JComboBox<>(accountNumbers);

        JButton closeAccountButton = new JButton("Close account");

        closeAccountButton.addActionListener(new CloseAccountButtonListener());

        panel.add(personalNumbersLabel);
        panel.add(personalNumbersField);

        panel.add(selectCustomerBtn);

        panel.add(accountNumberLabel);
        panel.add(accountNumbersField);

        panel.add(closeAccountButton);

        setMainPanel(panel);
    }
    private void deleteCustomerPanel() {
        JPanel panel = new JPanel();

        //Set personal number input
        List<String> customersPnoList = bankController.getCustomers()
                .stream()
                .map(Customer::getpNo)
                .toList();
        String[] pnoArray = customersPnoList.toArray(new String[0]);

        JLabel personalNumbersLabel = new JLabel("Select personal number ");
        //Antar att JComboBox inte tar en List <String>, så får konvertera till en "gammaldags" array.
        personalNumbersField = new JComboBox<>(pnoArray);

        JButton deletCustomerButton = new JButton("Delete customer");

        deletCustomerButton.addActionListener(new DeleteCustomerButtonListener());

        panel.add(personalNumbersLabel);
        panel.add(personalNumbersField);

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
                logger.info("account created: " + bankController.getCustomer(pNo)
                        .getAccounts());
            } else {
                messageLabel.setText(INCORRECT_PNO);
            }
        }
    }
    // ActionListener class for closing an account
    private class CloseAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            selectedPNo = (String) personalNumbersField.getSelectedItem();

            String accountNumber = (String) accountNumbersField.getSelectedItem();

            if (!selectedPNo.isEmpty()) {
                assert accountNumber != null;
                String resultMessage =
                        bankController.closeAccount(selectedPNo, Integer.parseInt(accountNumber));
                JOptionPane.showMessageDialog(null, "account closed: " + resultMessage
                        , "Close account", JOptionPane.INFORMATION_MESSAGE);

            } else {
                messageLabel.setText(INCORRECT_PNO);
            }

        }
    }

    // ActionListener class for deleting a customer
    private class DeleteCustomerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedPNo = (String) personalNumbersField.getSelectedItem();

            assert selectedPNo != null;
            if (!selectedPNo.isEmpty()) {
                String resultMessage = bankController.deleteCustomer(selectedPNo).toString();

                JOptionPane.showMessageDialog(null, "Customer deleted: " + resultMessage
                        , "Delete customer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                messageLabel.setText(INCORRECT_PNO);
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
                accounts = bankController.getCustomer(selectedPNo)
                        .getAccounts()
                        .stream()
                        .map(Account::getAccountNumber)
                        .toList();

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

            if (amountField.getText()
                    .isEmpty()) {
                amountField.setText("0");
            }
            int amount = Integer.parseInt(amountField.getText());

            selectedPNo = (String) personalNumbersField.getSelectedItem();

            String selectedAccountNumber = (String) accountNumbersField.getSelectedItem();

            assert selectedAccountNumber != null;
            boolean successfulWithdrawal = bankController.withdraw(selectedPNo, Integer.parseInt(selectedAccountNumber), amount);

            //Skriv ut hur det gick
            String result = successfulWithdrawal
                            ? "Withdrawal successful: " + bankController.getAccount(selectedPNo, Integer.parseInt(
                    selectedAccountNumber))
                            : " Withdrawal not successful";
            logger.info(result);
            // Show the result in a dialog
            JOptionPane.showMessageDialog(null, result, "Withdrawal Result", JOptionPane.INFORMATION_MESSAGE);

        }
    }
    //Actionlistener for deopsiting an amount
    private class CreateDepositListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (amountField.getText()
                    .isEmpty()) {
                amountField.setText("0");
            }
            int amount = Integer.parseInt(amountField.getText());

            selectedPNo = (String) personalNumbersField.getSelectedItem();

            String selectedAccountNumber = (String) accountNumbersField.getSelectedItem();

            assert selectedAccountNumber != null;
            bankController.deposit(selectedPNo, Integer.parseInt(selectedAccountNumber), amount);
            boolean successfulDeposit =
                    bankController.getAccount(selectedPNo, Integer.parseInt(selectedAccountNumber)) != null;

            //Skriv ut hur det gick
            String result = successfulDeposit
                            ? "Deposit successful: " + bankController.getAccount(selectedPNo, Integer.parseInt(
                    selectedAccountNumber))
                            : " Deposit not successful";
            logger.info(result);
            // Show the result in a dialog
            JOptionPane.showMessageDialog(null, result, "Withdrawal Result", JOptionPane.INFORMATION_MESSAGE);

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
                logger.info("account created: " + bankController.getCustomer(pNo)
                        .getAccounts());
                messageLabel.setText("credit account created: " + bankController.getCustomer(pNo)
                        .getAccounts());
            } else {
                messageLabel.setText(INCORRECT_PNO);
            }
        }
    }
    // ActionListener class for getting a customer
    private class GetCustomerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            selectedPNo = (String) personalNumbersField.getSelectedItem();

            Customer customer;
            if (!selectedPNo.isEmpty()) {

                customer = bankController.getCustomer(selectedPNo);
                messageLabel.setText(customer.toString());
            } else {
                messageLabel.setText(INCORRECT_PNO);
            }
        }
    }

    // ActionListener class for getting all customers
    private class GetAllCustomersButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Fetch the list of customers
            List<Customer> customers = bankController.getCustomers();

            if (customers.isEmpty()) {
                // Show a message if there are no customers
                JOptionPane.showMessageDialog(
                        null,
                        "No customers found.",
                        "Customer List",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                // Format the list of customers into a readable string
                String customerList = customers.stream()
                        .map(customer -> "Name: " + customer.getFirstName() + ", Personal Number: " + customer.getpNo())
                        .collect(Collectors.joining("\n"));
                // Show the formatted customer list in a dialog
                JOptionPane.showMessageDialog(
                        null,
                        customerList,
                        "Customer List",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
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
                JOptionPane.showMessageDialog(null, "Customer created! "
                        , "Create customer", JOptionPane.INFORMATION_MESSAGE);
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
