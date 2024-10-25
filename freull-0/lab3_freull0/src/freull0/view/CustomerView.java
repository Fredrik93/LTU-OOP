package freull0.view;

import freull0.controller.BankController;
import freull0.logic.CustomerLogic;
import freull0.model.Customer;

import javax.swing.*;

public class CustomerView
{

    public void createCustomer()
    {
        String firstName = JOptionPane.showInputDialog("Enter first name");
        String lastName = JOptionPane.showInputDialog("Enter last name");
        String pNo = JOptionPane.showInputDialog("Enter personalnumber");

        BankController logic = new BankController();
        CustomerLogic cLogic = new CustomerLogic();
        logic.createCustomer(firstName, lastName, pNo);
        Customer createdCustomer = cLogic.findCustomer(pNo);
        System.out.println("cust" + createdCustomer);
        JOptionPane.showMessageDialog(null, "Customer created: " + createdCustomer, "New customer",
                JOptionPane.WARNING_MESSAGE);

    }
}
