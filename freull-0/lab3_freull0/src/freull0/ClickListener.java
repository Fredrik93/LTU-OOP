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
        String randPNo = String.valueOf(random.nextInt(100));
        bank.createCustomer("Jim", "Jo", randPNo);

        JButton clickedButton = (JButton) e.getSource();
        label.setVisible(false);

        //Display lsit of customers
        String cName = bank.getCustomer(randPNo).getFirstName() + " " + bank.getCustomer(randPNo).getLastName();
        String text = cName;
        text += label.getText() + " \n";
        label.setText(text);

        if(clickedButton == button)
        {
            System.out.println("Customer created: " + bank.getCustomers());
            label.setVisible(true);
        }
    }

}
