package freull0;

import freull0.controller.BankController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Inlämningsuppgift4 Syfte: Lyssnare för kunder. används delvis.
 *
 * @author Fredrik Ullman, freull-0
 */
public class CustomerListener extends Component implements ActionListener
{

    private final BankController controller;

    public CustomerListener(BankController controller)
    {
        this.controller = controller;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand().toLowerCase();

        switch(actionCommand)
        {
            case "create new customer" -> createNewCustomer();
            case "change customer name" -> changeCustomerName();
            case "get all customers" -> getAllCustomers();
        }

    }

    private void getAllCustomers()
    {
        List<String> customers = controller.getAllCustomersAsStrings();
        JOptionPane.showMessageDialog(this, String.join("\n", customers), "Customers", JOptionPane.INFORMATION_MESSAGE);

    }

    private void createNewCustomer()
    {
        String name = JOptionPane.showInputDialog("Enter first name:");
        String surname = JOptionPane.showInputDialog("Enter last name:");
        String pNo = JOptionPane.showInputDialog("Enter personal number:");
        boolean success = controller.createCustomer(name, surname, pNo);
        String msg = success ? "Customer created" : "Failed to create customer";
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Customer created: " + controller.getCustomer(pNo));

    }

    private void changeCustomerName()
    {
        String name = JOptionPane.showInputDialog("Enter new first name:");
        String surname = JOptionPane.showInputDialog("Enter new last name:");
        String pNo = JOptionPane.showInputDialog("Enter personal number:");
        boolean success = controller.changeCustomerName(name, surname, pNo);
        String msg = success ? "Customer name updated successfully!" : "Failed to update customer name.";
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

    }
}
