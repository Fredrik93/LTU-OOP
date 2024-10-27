package freull0;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListener implements ActionListener
{
    private final JLabel label;
    private final JButton button;

    public ClickListener(JLabel textLabel, JButton button)
    {
        this.label = textLabel;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton clickedButton = (JButton) e.getSource();
        label.setVisible(false);
        System.out.println(clickedButton.getText() + " button : " + button.getText());
        if(clickedButton == button)
        {
            System.out.println("Found");
            label.setVisible(true);
        }
    }
}
