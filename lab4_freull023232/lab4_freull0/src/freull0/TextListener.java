package freull0;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Inlämningsuppgift4 Syfte: Lyssnare. används som mall och i exempel.
 *
 * @author Fredrik Ullman, freull-0
 */
public class TextListener implements ActionListener
{
    private final JTextField textField;
    private final JLabel textLabel;

    public TextListener(JTextField textField, JLabel textLabel)
    {
        this.textField = textField;
        this.textLabel = textLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String text = "Du skrev '";
        text += textField.getText() + "'";
        textLabel.setText(text);
    }
}
