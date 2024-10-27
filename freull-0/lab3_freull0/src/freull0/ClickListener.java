package freull0;

import freull0.controller.BankController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ClickListener implements ActionListener
{
    private final JLabel label;
    private final JButton button;
    private final BankController bank;

    public ClickListener(JLabel textLabel, JButton button, BankController bank)
    {
        this.label = textLabel;
        this.button = button;
        this.bank = bank;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Random random = new Random();
        JButton clickedButton = (JButton) e.getSource();
        label.setVisible(false);
        if(clickedButton == button)
        {
            bank.createCustomer("Jim", "Jo", String.valueOf(random.nextInt(100)));
            System.out.println("Customer created: " + bank.getCustomers());
            label.setVisible(true);
        }
    }

}
