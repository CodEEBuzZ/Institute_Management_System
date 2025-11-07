package institute.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DgpaCalc extends JFrame implements ActionListener {

    private JComboBox<String> courseTypeDropdown;
    private JCheckBox lateralEntryCheckbox;
    private JTextField[] ygpaFields; // For YGPA1, YGPA2, YGPA3, YGPA4
    private JButton calculateButton, clearButton, backButton;
    private JLabel resultLabel;
    private final int MAX_YGPAS = 4; // Corresponds to YGPA1 to YGPA4

    DgpaCalc() {
        setTitle("DGPA Calculator (Degree GPA)");
        getContentPane().setBackground(new Color(230, 255, 240));
        setLayout(null);

        JLabel heading = new JLabel("Degree Grade Point Average (DGPA)");
        heading.setBounds(50, 20, 500, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        add(heading);

        // Course Type Selection
        JLabel courseLabel = new JLabel("Select Course Duration:");
        courseLabel.setBounds(50, 70, 180, 25);
        courseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(courseLabel);

        String[] courses = {"4 Year Degree", "3 Year Degree", "2 Year Degree", "1 Year Degree"};
        courseTypeDropdown = new JComboBox<>(courses);
        courseTypeDropdown.setBounds(240, 70, 150, 25);
        courseTypeDropdown.setBackground(Color.WHITE);
        courseTypeDropdown.addActionListener(this); // To dynamically show/hide YGPA fields
        add(courseTypeDropdown);

        // Lateral Entry Checkbox
        lateralEntryCheckbox = new JCheckBox("Lateral Entry Student");
        lateralEntryCheckbox.setBounds(50, 110, 200, 25);
        lateralEntryCheckbox.setBackground(new Color(230, 255, 240));
        lateralEntryCheckbox.addActionListener(this); // To update YGPA field visibility
        add(lateralEntryCheckbox);

        // YGPA Input Fields
        ygpaFields = new JTextField[MAX_YGPAS];
        for (int i = 0; i < MAX_YGPAS; i++) {
            JLabel ygpaLabel = new JLabel("YGPA " + (i + 1) + ":");
            ygpaLabel.setBounds(80, 150 + (i * 35), 100, 25);
            ygpaLabel.setName("YGPALabel" + (i + 1)); // For easy identification
            ygpaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            add(ygpaLabel);

            ygpaFields[i] = new JTextField();
            ygpaFields[i].setBounds(180, 150 + (i * 35), 100, 25);
            ygpaFields[i].setName("YGPAField" + (i + 1)); // For easy identification
            add(ygpaFields[i]);
        }

        updateYgpaFieldVisibility(); // Initial call to set visibility

        calculateButton = new JButton("Calculate DGPA");
        calculateButton.setBounds(50, 150 + (MAX_YGPAS * 35) + 30, 150, 30);
        calculateButton.setBackground(Color.BLACK);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.addActionListener(this);
        add(calculateButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(210, 150 + (MAX_YGPAS * 35) + 30, 100, 30);
        clearButton.setBackground(Color.DARK_GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(this);
        add(clearButton);

        backButton = new JButton("Back");
        backButton.setBounds(320, 150 + (MAX_YGPAS * 35) + 30, 100, 30);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        resultLabel = new JLabel("Your DGPA will appear here.");
        resultLabel.setBounds(50, 150 + (MAX_YGPAS * 35) + 80, 400, 30);
        resultLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(resultLabel);

        setSize(480, 150 + (MAX_YGPAS * 35) + 150); // Dynamic size
        setLocation(250, 50);
        setVisible(true);
    }

    private void updateYgpaFieldVisibility() {
        String selectedCourse = (String) courseTypeDropdown.getSelectedItem();
        boolean isLateralEntry = lateralEntryCheckbox.isSelected();
        int fieldsToShow = 0;

        if (selectedCourse.equals("4 Year Degree")) {
            fieldsToShow = isLateralEntry ? 3 : 4; // YGPA2,3,4 for lateral; YGPA1,2,3,4 for regular
        } else if (selectedCourse.equals("3 Year Degree")) {
            fieldsToShow = 3; // YGPA1,2,3
        } else if (selectedCourse.equals("2 Year Degree")) {
            fieldsToShow = 2; // YGPA1,2
        } else if (selectedCourse.equals("1 Year Degree")) {
            fieldsToShow = 1; // YGPA1
        }

        for (int i = 0; i < MAX_YGPAS; i++) {
            JLabel label = (JLabel) getContentPane().getComponent(findComponentIndex("YGPALabel" + (i+1)));
            JTextField field = (JTextField) getContentPane().getComponent(findComponentIndex("YGPAField" + (i+1)));

            if (label != null && field != null) {
                // If 4 Year Lateral, YGPA1 is hidden, others are shifted
                if (selectedCourse.equals("4 Year Degree") && isLateralEntry) {
                    if (i == 0) { // Hide YGPA1 for lateral entry 4-year
                        label.setVisible(false);
                        field.setVisible(false);
                    } else { // Shift YGPA2, YGPA3, YGPA4 up
                        label.setVisible(true);
                        field.setVisible(true);
                        label.setText("YGPA " + (i + 1) + ":"); // Ensure label is correct
                        label.setBounds(80, 150 + ((i-1) * 35), 100, 25);
                        field.setBounds(180, 150 + ((i-1) * 35), 100, 25);
                    }
                } else { // Normal visibility based on fieldsToShow
                    label.setVisible(i < fieldsToShow);
                    field.setVisible(i < fieldsToShow);
                    label.setText("YGPA " + (i + 1) + ":"); // Reset label text and position
                    label.setBounds(80, 150 + (i * 35), 100, 25);
                    field.setBounds(180, 150 + (i * 35), 100, 25);
                }
            }
        }
        // Repaint to ensure changes are visible
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    // Helper to find components by name
    private int findComponentIndex(String name) {
        Component[] components = getContentPane().getComponents();
        for (int i = 0; i < components.length; i++) {
            if (name.equals(components[i].getName())) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == courseTypeDropdown || e.getSource() == lateralEntryCheckbox) {
            updateYgpaFieldVisibility();
            resultLabel.setText("Your DGPA will appear here."); // Clear result on selection change
        } else if (e.getSource() == calculateButton) {
            try {
                double[] ygpas = new double[MAX_YGPAS];
                int validYgpasCount = 0;

                for (int i = 0; i < MAX_YGPAS; i++) {
                    // Only process visible and non-empty fields
                    if (ygpaFields[i].isVisible() && !ygpaFields[i].getText().isEmpty()) {
                        double ygpa = Double.parseDouble(ygpaFields[i].getText());
                        if (ygpa < 0 || ygpa > 10) {
                            JOptionPane.showMessageDialog(this, "YGPA " + (i + 1) + " must be between 0 and 10.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        ygpas[i] = ygpa;
                        validYgpasCount++;
                    } else if (ygpaFields[i].isVisible() && ygpaFields[i].getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter all visible YGPA values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (validYgpasCount == 0) {
                    resultLabel.setText("Please enter YGPA values.");
                    return;
                }

                double dgpa = 0;
                String selectedCourse = (String) courseTypeDropdown.getSelectedItem();
                boolean isLateralEntry = lateralEntryCheckbox.isSelected();

                switch (selectedCourse) {
                    case "4 Year Degree":
                        if (isLateralEntry) {
                            // YGPA2 + 1.5 * YGPA3 + 1.5 * YGPA4 / 4
                            if (validYgpasCount < 3) { // Expects YGPA2, YGPA3, YGPA4 (3 values)
                                JOptionPane.showMessageDialog(this, "Please enter YGPA2, YGPA3, and YGPA4 for Lateral Entry (4 Year Course).", "Input Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            // Note: ygpas[0] is YGPA1, ygpas[1] is YGPA2, etc.
                            // So YGPA2 is ygpas[1], YGPA3 is ygpas[2], YGPA4 is ygpas[3]
                            dgpa = (ygpas[1] + (1.5 * ygpas[2]) + (1.5 * ygpas[3])) / 4.0;
                        } else {
                            // YGPA1 + YGPA2 + 1.5 * YGPA3 + 1.5 * YGPA4 / 5
                            if (validYgpasCount < 4) { // Expects YGPA1, YGPA2, YGPA3, YGPA4 (4 values)
                                JOptionPane.showMessageDialog(this, "Please enter YGPA1, YGPA2, YGPA3, and YGPA4 for 4 Year Degree Course.", "Input Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            dgpa = (ygpas[0] + ygpas[1] + (1.5 * ygpas[2]) + (1.5 * ygpas[3])) / 5.0;
                        }
                        break;
                    case "3 Year Degree":
                        // YGPA1 + YGPA2 + YGPA3 / 3
                        if (validYgpasCount < 3) { // Expects YGPA1, YGPA2, YGPA3 (3 values)
                            JOptionPane.showMessageDialog(this, "Please enter YGPA1, YGPA2, and YGPA3 for 3 Year Degree Course.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        dgpa = (ygpas[0] + ygpas[1] + ygpas[2]) / 3.0;
                        break;
                    case "2 Year Degree":
                        // YGPA1 + YGPA2 / 2
                        if (validYgpasCount < 2) { // Expects YGPA1, YGPA2 (2 values)
                            JOptionPane.showMessageDialog(this, "Please enter YGPA1 and YGPA2 for 2 Year Degree Course.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        dgpa = (ygpas[0] + ygpas[1]) / 2.0;
                        break;
                    case "1 Year Degree":
                        // YGPA1
                        if (validYgpasCount < 1) { // Expects YGPA1 (1 value)
                            JOptionPane.showMessageDialog(this, "Please enter YGPA1 for 1 Year Degree Course.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        dgpa = ygpas[0];
                        break;
                }
                resultLabel.setText(String.format("Calculated DGPA: %.2f", dgpa));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values for YGPAs.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == clearButton) {
            for (JTextField field : ygpaFields) {
                field.setText("");
            }
            courseTypeDropdown.setSelectedIndex(0); // Reset to 4 Year Degree
            lateralEntryCheckbox.setSelected(false);
            updateYgpaFieldVisibility(); // Reset visibility
            resultLabel.setText("Your DGPA will appear here.");
        } else if (e.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new DgpaCalc();
    }
}