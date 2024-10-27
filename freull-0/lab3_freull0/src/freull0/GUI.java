package freull0;

import freull0.controller.BankController;
import freull0.view.CustomerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame
{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private final JLabel label = new JLabel();
    private final JTextField textField = new JTextField(20);
    private final JButton imageButton = new JButton("Bild 1");
    private final JButton customerButton = new JButton("Skapa kund");
    private final JLabel imageLabel = new JLabel();
    private final JLabel customerLabel = new JLabel();
    BankController bank = new BankController();

    public GUI()
    {
        setLocation(400, 400);
        setSize(WIDTH, HEIGHT);
        createCustomerComponent();

    }

    private void createImageComponent()
    {
        ImageIcon image = new ImageIcon("photo.png");
        imageLabel.setIcon(image);
        imageLabel.setVisible(true);

    }

    private void createTextInputFieldComponent()
    {
        ActionListener listener = new TextListener(textField, label);
        textField.addActionListener(listener);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter firstname ");
        panel.add(label);
        panel.add(textField);
        panel.add(this.label);

        add(panel);

    }

    private void createCustomerComponent()
    {
        ActionListener listener = new ClickListener(customerLabel, customerButton, bank);
        customerButton.addActionListener(listener);

        JPanel panel = new JPanel();

        panel.add(customerButton);
        add(panel);
    }

    private void createButtonComponents()
    {
        ActionListener listener = new ClickListener(imageLabel, imageButton, bank);
        imageButton.addActionListener(listener);

        //skapa och sätt storlek på bild
        ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("java-icon.png"));
        Image scaledImage = image.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        imageLabel.setIcon(resizedIcon);
        imageLabel.setVisible(false);

        //Arbetsyta
        JPanel panel = new JPanel();

        panel.add(imageButton);
        panel.add(imageLabel);
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
