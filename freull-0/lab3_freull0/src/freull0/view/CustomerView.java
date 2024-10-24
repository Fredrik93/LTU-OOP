package freull0.view;

import freull0.controller.BankLogic;
import freull0.model.Customer;

import javax.swing.*;

public class CustomerView
{

    public void createCustomer()
    {
        String firstName = JOptionPane.showInputDialog("Enter first name");
        String lastName = JOptionPane.showInputDialog("Enter last name");
        String pNo = JOptionPane.showInputDialog("Enter personalnumber");

        BankLogic logic = new BankLogic();
        logic.createCustomer(firstName, lastName, pNo);
        Customer createdCustomer = logic.findCustomer(pNo);
        System.out.println("cust" + createdCustomer);
        JOptionPane.showMessageDialog(null, "Customer created: " + createdCustomer, "New customer",
                JOptionPane.WARNING_MESSAGE);

    }
}
