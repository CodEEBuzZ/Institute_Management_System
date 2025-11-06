package institute.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SgpaToPercentageCalc extends JFrame implements ActionListener {

    JTextField tfSgpa;
    JButton convertButton, backButton;
    JLabel resultLabel;

    SgpaToPercentageCalc() {
        setTitle("SGPA to Percentage Calculator");
        getContentPane().setBackground(new Color(210, 232, 252));
        setLayout(null);

        JLabel heading = new JLabel("SGPA to Percentage");
        heading.setBounds(50, 20, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        JLabel sgpaLabel = new JLabel("Enter SGPA:");
        sgpaLabel.setBounds(60, 90, 150, 25);
        sgpaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sgpaLabel);

        tfSgpa = new JTextField();
        tfSgpa.setBounds(200, 90, 150, 25);
        add(tfSgpa);

        convertButton = new JButton("Convert");
        convertButton.setBounds(80, 150, 100, 30);
        convertButton.setBackground(Color.BLACK);
        convertButton.setForeground(Color.WHITE);
        convertButton.addActionListener(this);
        add(convertButton);

        backButton = new JButton("Back");
        backButton.setBounds(220, 150, 100, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        resultLabel = new JLabel("Result will be shown here");
        resultLabel.setBounds(60, 210, 300, 30);
        resultLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(resultLabel);

        setSize(450, 300);
        setLocation(250, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            try {
                double sgpa = Double.parseDouble(tfSgpa.getText());
                if (sgpa < 0 || sgpa > 10) {
                    JOptionPane.showMessageDialog(null, "Please enter an SGPA value between 0 and 10.");
                    return;
                }
                double percentage = (sgpa - 0.75) * 10;
                resultLabel.setText(String.format("Percentage: %.2f %%", percentage));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value for SGPA.");
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new SgpaToPercentageCalc();
    }
}