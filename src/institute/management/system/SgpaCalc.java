package institute.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SgpaCalc extends JFrame implements ActionListener {

    private JPanel inputPanel;
    private List<JTextField> creditIndexFields;
    private List<JTextField> creditFields;
    private JButton addSubjectButton, calculateButton, backButton, clearButton;
    private JLabel resultLabel;
    private int subjectCount = 1; // Start with one subject

    SgpaCalc() {
        setTitle("SGPA Calculator (Credit Index)");
        getContentPane().setBackground(new Color(230, 240, 255));
        setLayout(new BorderLayout()); // Use BorderLayout for better organization

        JLabel heading = new JLabel("Semester Grade Point Average (SGPA)");
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading, BorderLayout.NORTH);

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(new Color(230, 240, 255));
        JScrollPane scrollPane = new JScrollPane(inputPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(550, 300)); // Adjust size as needed
        add(scrollPane, BorderLayout.CENTER);

        creditIndexFields = new ArrayList<>();
        creditFields = new ArrayList<>();

        addSubjectRow(1); // Add initial subject row

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(230, 240, 255));

        addSubjectButton = new JButton("Add Subject");
        addSubjectButton.setBackground(Color.BLUE);
        addSubjectButton.setForeground(Color.WHITE);
        addSubjectButton.addActionListener(this);
        buttonPanel.add(addSubjectButton);

        calculateButton = new JButton("Calculate SGPA");
        calculateButton.setBackground(Color.BLACK);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.addActionListener(this);
        buttonPanel.add(calculateButton);

        clearButton = new JButton("Clear All");
        clearButton.setBackground(Color.DARK_GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        resultLabel = new JLabel("SGPA: --");
        resultLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(resultLabel, BorderLayout.SOUTH); // Re-add result label to make sure it's visible

        // Re-adjust result label and button panel placement
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(new Color(230, 240, 255));
        southPanel.add(resultLabel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);


        setSize(600, 500);
        setLocation(400, 150);
        setVisible(true);
    }

    private void addSubjectRow(int subjectNum) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Subject Label
        gbc.gridx = 0;
        gbc.gridy = subjectNum - 1;
        inputPanel.add(new JLabel("Subject " + subjectNum + ":"), gbc);

        // Credit Index Label and Field
        gbc.gridx = 1;
        inputPanel.add(new JLabel("Credit Index:"), gbc);
        JTextField tfCreditIndex = new JTextField(8);
        creditIndexFields.add(tfCreditIndex);
        gbc.gridx = 2;
        inputPanel.add(tfCreditIndex, gbc);

        // Credits Label and Field
        gbc.gridx = 3;
        inputPanel.add(new JLabel("Credits:"), gbc);
        JTextField tfCredit = new JTextField(8);
        creditFields.add(tfCredit);
        gbc.gridx = 4;
        inputPanel.add(tfCredit, gbc);

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addSubjectButton) {
            subjectCount++;
            addSubjectRow(subjectCount);
            // Scroll to bottom
            SwingUtilities.invokeLater(() -> {
                JScrollBar vertical = ((JScrollPane) inputPanel.getParent().getParent()).getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
            });
        } else if (e.getSource() == calculateButton) {
            double totalCreditIndex = 0;
            double totalCredits = 0;
            boolean validInput = true;

            for (int i = 0; i < creditIndexFields.size(); i++) {
                String ciText = creditIndexFields.get(i).getText();
                String cText = creditFields.get(i).getText();

                if (ciText.isEmpty() && cText.isEmpty()) {
                    continue; // Skip empty rows
                }

                try {
                    double creditIndex = Double.parseDouble(ciText);
                    double credits = Double.parseDouble(cText);

                    if (credits <= 0) {
                        JOptionPane.showMessageDialog(this, "Credits for Subject " + (i + 1) + " must be greater than 0.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        validInput = false;
                        break;
                    }
                    if (creditIndex < 0) { // Credit Index can be 0 or more
                        JOptionPane.showMessageDialog(this, "Credit Index for Subject " + (i + 1) + " cannot be negative.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        validInput = false;
                        break;
                    }

                    totalCreditIndex += creditIndex;
                    totalCredits += credits;

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input for Subject " + (i + 1) + ". Please enter numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    validInput = false;
                    break;
                }
            }

            if (validInput) {
                if (totalCredits > 0) {
                    double sgpa = totalCreditIndex / totalCredits;
                    resultLabel.setText(String.format("Calculated SGPA: %.2f", sgpa));
                } else {
                    resultLabel.setText("SGPA: Cannot calculate, Total Credits must be greater than 0.");
                }
            }
        } else if (e.getSource() == clearButton) {
            creditIndexFields.clear();
            creditFields.clear();
            inputPanel.removeAll();
            subjectCount = 1;
            addSubjectRow(subjectCount);
            resultLabel.setText("SGPA: --");
            inputPanel.revalidate();
            inputPanel.repaint();
        }
        else if (e.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new SgpaCalc();
    }
}