package notepad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class About extends JFrame implements ActionListener {
    JButton b1;

    public About() {
        setBounds(650, 250, 750, 600);
        setLayout(null);

        // Load and scale the first icon
        ImageIcon i = new ImageIcon(getClass().getResource("/notepad/icons/icon2.png"));
        Image i2 = i.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel headerIcon = new JLabel(i3);
        headerIcon.setBounds(200, 35, 120, 120); // Adjusted to account for padding

        // Set border with padding around the first icon
        headerIcon.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        add(headerIcon);

        // Load and scale the second icon
        ImageIcon ig1 = new ImageIcon(getClass().getResource("/notepad/icons/notepadicon.png"));
        Image i4 = ig1.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT);
        ImageIcon i5 = new ImageIcon(i4);
        JLabel hicon = new JLabel(i5);
        hicon.setBounds(55, 185, 95, 95); // Adjusted to account for padding

        // Set border with padding around the second icon
        hicon.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        add(hicon);

        JLabel t = new JLabel("<html>Code for Interview<br>Youtube Channel Version 2021<br>2021 Code for Interview. All rights reserved<br><br>Notepad is a word processing program, <br>which allows changing of text in a computer file.<br>Notepad is a simple text editor for basic text-editing program<br> which enables computer users to create documents. </html>");
        t.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        t.setBounds(155, 135, 505, 305);
        add(t);

        b1 = new JButton("OK");
        b1.setBounds(580, 500, 80, 25);
        b1.addActionListener(this);
        add(b1);
    }

    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
    }

    public static void main(String args[]) {
        new About().setVisible(true);
    }
}
