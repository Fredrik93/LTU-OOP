package freull0;

import freull0.view.CustomerView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame
{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private final JLabel textLabel = new JLabel();

    public GUI()
    {
        setSize(WIDTH, HEIGHT);
        createComponents();
    }

    private void createComponents()
    {
        JButton redButton = new JButton("Röd");
        JButton blueButton = new JButton("blå");
        JButton greenButton = new JButton("grön");
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
