package institute.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CgpaCalc extends JFrame implements ActionListener {

    private JTextField[] sgpaFields;
    private JTextField tfCreditsPerSemester;
    private JButton calculateButton, clearButton, backButton;
    private JLabel resultLabel;
    private final int MAX_SEMESTERS = 10; // Max semesters (e.g., for 5-year program)

    CgpaCalc() {
        setTitle("CGPA Calculator (Cumulative)");
        getContentPane().setBackground(new Color(240, 255, 230));
        setLayout(null);

        JLabel heading = new JLabel("Cumulative Grade Point Average (CGPA)");
        heading.setBounds(50, 20, 500, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        add(heading);

        JLabel subHeading = new JLabel("Enter SGPA for each completed semester (max 10):");
        subHeading.setBounds(50, 60, 400, 20);
        subHeading.setFont(new Font("Tahoma", Font.ITALIC, 14));
        add(subHeading);

        sgpaFields = new JTextField[MAX_SEMESTERS];
        for (int i = 0; i < MAX_SEMESTERS; i++) {
            JLabel semLabel = new JLabel("Semester " + (i + 1) + " SGPA:");
            semLabel.setBounds(80, 100 + (i * 30), 120, 25);
            add(semLabel);
            sgpaFields[i] = new JTextField();
            sgpaFields[i].setBounds(210, 100 + (i * 30), 80, 25);
            add(sgpaFields[i]);
        }

        // Input for Credits per Semester
        JLabel creditsLabel = new JLabel("Avg. Credits per Semester (e.g., 20):");
        creditsLabel.setBounds(50, 100 + (MAX_SEMESTERS * 30) + 10, 250, 25);
        creditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(creditsLabel);

        tfCreditsPerSemester = new JTextField("20"); // Default to 20
        tfCreditsPerSemester.setBounds(300, 100 + (MAX_SEMESTERS * 30) + 10, 80, 25);
        add(tfCreditsPerSemester);


        calculateButton = new JButton("Calculate CGPA");
        calculateButton.setBounds(50, 100 + (MAX_SEMESTERS * 30) + 70, 150, 30);
        calculateButton.setBackground(Color.BLACK);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.addActionListener(this);
        add(calculateButton);

        clearButton = new JButton("Clear All");
        clearButton.setBounds(210, 100 + (MAX_SEMESTERS * 30) + 70, 100, 30);
        clearButton.setBackground(Color.DARK_GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(this);
        add(clearButton);

        backButton = new JButton("Back");
        backButton.setBounds(320, 100 + (MAX_SEMESTERS * 30) + 70, 100, 30);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        resultLabel = new JLabel("Your CGPA will appear here.");
        resultLabel.setBounds(50, 100 + (MAX_SEMESTERS * 30) + 120, 400, 30);
        resultLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(resultLabel);

        setSize(500, 100 + (MAX_SEMESTERS * 30) + 200); // Adjust size dynamically
        setLocation(250, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            double totalCreditIndexSum = 0.0;
            double totalCreditsSum = 0.0;
            int semestersEntered = 0;

            try {
                double creditsPerSem = Double.parseDouble(tfCreditsPerSemester.getText());
                if (creditsPerSem <= 0) {
                    JOptionPane.showMessageDialog(this, "Credits per Semester must be greater than 0.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (int i = 0; i < MAX_SEMESTERS; i++) {
                    String text = sgpaFields[i].getText();
                    if (!text.isEmpty()) {
                        double sgpa = Double.parseDouble(text);
                        if (sgpa < 0 || sgpa > 10) {
                            JOptionPane.showMessageDialog(this, "SGPA value for Semester " + (i+1) + " must be between 0 and 10.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        // Credit Index for a semester = SGPA * Total Credits for that Semester
                        totalCreditIndexSum += (sgpa * creditsPerSem);
                        totalCreditsSum += creditsPerSem;
                        semestersEntered++;
                    }
                }
                if (semestersEntered > 0) {
                    double cgpa = totalCreditIndexSum / totalCreditsSum;
                    resultLabel.setText(String.format("Calculated CGPA: %.2f", cgpa));
                } else {
                    resultLabel.setText("Please enter at least one SGPA.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter only numeric values for SGPA and Credits.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == clearButton) {
            for(JTextField field : sgpaFields) {
                field.setText("");
            }
            tfCreditsPerSemester.setText("20"); // Reset to default
            resultLabel.setText("Your CGPA will appear here.");
        } else if (e.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CgpaCalc();
    }
}