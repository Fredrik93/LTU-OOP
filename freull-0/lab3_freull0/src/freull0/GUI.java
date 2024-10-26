package freull0;

import freull0.view.CustomerView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame
{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private final JLabel textLabel = new JLabel();
    private final JTextField textField = new JTextField(20);
    private final JLabel textLabel2 = new JLabel();

    public GUI()
    {
        setSize(WIDTH, HEIGHT);
        createTextInputFieldComponent();
    }

    private void createTextInputFieldComponent()
    {
        ActionListener listener = new TextListener(textField, textLabel);
        textField.addActionListener(listener);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter firstname ");
        panel.add(label);
        panel.add(textField);
        panel.add(textLabel);

        add(panel);

    }

    private void createButtonComponents()
    {
        JButton redButton = new JButton("Röd");
        JButton blueButton = new JButton("blå");
        JButton greenButton = new JButton("grön");
        JTextField createCustomerField = new JTextField();
        //I exemplet så skapade vi en inre klass för att kunna använda privata metoder,
        // jag lade koden i en egen klass ClickListener istället
        ActionListener listener = new ClickListener(textLabel);
        redButton.addActionListener(listener);
        blueButton.addActionListener(listener);
        greenButton.addActionListener(listener);

        //Arbetsyta
        JPanel panel = new JPanel();

        panel.add(redButton);
        panel.add(blueButton);
        panel.add(greenButton);
        panel.add(textLabel);
        add(panel);
    }

    public static void main(String[] args)
    {
        CustomerView cv = new CustomerView();

        //gör ett fönster
        JFrame frame = new GUI();
        frame.setTitle("Skapa ny kund");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        //cv.createCustomer();
    }

}
