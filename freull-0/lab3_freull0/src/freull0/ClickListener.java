package freull0;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListener implements ActionListener
{
    private final JLabel textLabel;

    public ClickListener(JLabel textLabel)
    {
        this.textLabel = textLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton clickedButton = (JButton) e.getSource();
        String text = clickedButton.getText();
        textLabel.setText(text);
        System.out.println("Clicked ");
    }
}
