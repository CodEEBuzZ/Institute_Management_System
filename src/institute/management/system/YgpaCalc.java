package institute.management.system;


import javax.swing.*;
import javax.swing.border.TitledBorder; // Correct path
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class YgpaCalc extends JFrame implements ActionListener {

    private JPanel oddSemesterPanel, evenSemesterPanel;
    private List<JTextField> oddCiFields, oddCFields;
    private List<JTextField> evenCiFields, evenCFields;
    private JButton addOddSubjectButton, addEvenSubjectButton, calculateButton, backButton, clearButton;
    private JLabel resultLabel;
    private int oddSubjectCount = 1;
    private int evenSubjectCount = 1;

    YgpaCalc() {
        setTitle("YGPA Calculator (Credit Index)");
        getContentPane().setBackground(new Color(255, 240, 230));
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Yearly Grade Point Average (YGPA)");
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.5); // Equal size for both panels
        splitPane.setBackground(new Color(255, 240, 230));

        // Odd Semester Panel
        oddSemesterPanel = new JPanel();
        oddSemesterPanel.setLayout(new GridBagLayout());
        oddSemesterPanel.setBackground(new Color(255, 248, 240));
        TitledBorder oddBorder = BorderFactory.createTitledBorder("Odd Semester");
        oddBorder.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
        oddSemesterPanel.setBorder(oddBorder);
        JScrollPane oddScrollPane = new JScrollPane(oddSemesterPanel);
        oddScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        oddCiFields = new ArrayList<>();
        oddCFields = new ArrayList<>();
        addSubjectRow(oddSemesterPanel, oddCiFields, oddCFields, oddSubjectCount, true);

        // Even Semester Panel
        evenSemesterPanel = new JPanel();
        evenSemesterPanel.setLayout(new GridBagLayout());
        evenSemesterPanel.setBackground(new Color(240, 255, 248));
        TitledBorder evenBorder = BorderFactory.createTitledBorder("Even Semester");
        evenBorder.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
        evenSemesterPanel.setBorder(evenBorder);
        JScrollPane evenScrollPane = new JScrollPane(evenSemesterPanel);
        evenScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        evenCiFields = new ArrayList<>();
        evenCFields = new ArrayList<>();
        addSubjectRow(evenSemesterPanel, evenCiFields, evenCFields, evenSubjectCount, false);

        splitPane.setLeftComponent(oddScrollPane);
        splitPane.setRightComponent(evenScrollPane);
        add(splitPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBackground(new Color(255, 240, 230));

        addOddSubjectButton = new JButton("Add Odd Subject");
        addOddSubjectButton.setBackground(Color.BLUE);
        addOddSubjectButton.setForeground(Color.WHITE);
        addOddSubjectButton.addActionListener(this);
        buttonPanel.add(addOddSubjectButton);

        addEvenSubjectButton = new JButton("Add Even Subject");
        addEvenSubjectButton.setBackground(Color.BLUE);
        addEvenSubjectButton.setForeground(Color.WHITE);
        addEvenSubjectButton.addActionListener(this);
        buttonPanel.add(addEvenSubjectButton);

        calculateButton = new JButton("Calculate YGPA");
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

        resultLabel = new JLabel("YGPA: --");
        resultLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(new Color(255, 240, 230));
        southPanel.add(resultLabel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocation(250, 50);
        setVisible(true);
    }

    private void addSubjectRow(JPanel panel, List<JTextField> ciFields, List<JTextField> cFields, int subjectNum, boolean isOdd) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = subjectNum - 1;
        panel.add(new JLabel("Sub " + subjectNum + ":"), gbc);

        gbc.gridx = 1;
        panel.add(new JLabel("CI:"), gbc);
        JTextField tfCreditIndex = new JTextField(6);
        ciFields.add(tfCreditIndex);
        gbc.gridx = 2;
        panel.add(tfCreditIndex, gbc);

        gbc.gridx = 3;
        panel.add(new JLabel("C:"), gbc);
        JTextField tfCredit = new JTextField(6);
        cFields.add(tfCredit);
        gbc.gridx = 4;
        panel.add(tfCredit, gbc);

        panel.revalidate();
        panel.repaint();
    }

    private double[] calculateSemesterTotals(List<JTextField> ciFields, List<JTextField> cFields, String semesterName) throws NumberFormatException {
        double totalCreditIndex = 0;
        double totalCredits = 0;

        for (int i = 0; i < ciFields.size(); i++) {
            String ciText = ciFields.get(i).getText();
            String cText = cFields.get(i).getText();

            if (ciText.isEmpty() && cText.isEmpty()) {
                continue;
            }

            double creditIndex = Double.parseDouble(ciText);
            double credits = Double.parseDouble(cText);

            if (credits <= 0) {
                JOptionPane.showMessageDialog(this, "Credits for " + semesterName + " Subject " + (i + 1) + " must be greater than 0.", "Input Error", JOptionPane.ERROR_MESSAGE);
                throw new NumberFormatException("Invalid credits");
            }
            if (creditIndex < 0) {
                JOptionPane.showMessageDialog(this, "Credit Index for " + semesterName + " Subject " + (i + 1) + " cannot be negative.", "Input Error", JOptionPane.ERROR_MESSAGE);
                throw new NumberFormatException("Invalid credit index");
            }

            totalCreditIndex += creditIndex;
            totalCredits += credits;
        }
        return new double[]{totalCreditIndex, totalCredits};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addOddSubjectButton) {
            oddSubjectCount++;
            addSubjectRow(oddSemesterPanel, oddCiFields, oddCFields, oddSubjectCount, true);
            SwingUtilities.invokeLater(() -> {
                JScrollBar vertical = ((JScrollPane) oddSemesterPanel.getParent()).getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
            });
        } else if (e.getSource() == addEvenSubjectButton) {
            evenSubjectCount++;
            addSubjectRow(evenSemesterPanel, evenCiFields, evenCFields, evenSubjectCount, false);
            SwingUtilities.invokeLater(() -> {
                JScrollBar vertical = ((JScrollPane) evenSemesterPanel.getParent()).getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
            });
        } else if (e.getSource() == calculateButton) {
            try {
                double[] oddTotals = calculateSemesterTotals(oddCiFields, oddCFields, "Odd Semester");
                double[] evenTotals = calculateSemesterTotals(evenCiFields, evenCFields, "Even Semester");

                double totalCreditIndex = oddTotals[0] + evenTotals[0];
                double totalCredits = oddTotals[1] + evenTotals[1];

                if (totalCredits > 0) {
                    double ygpa = totalCreditIndex / totalCredits;
                    resultLabel.setText(String.format("Calculated YGPA: %.2f", ygpa));
                } else {
                    resultLabel.setText("YGPA: Cannot calculate, Total Credits must be greater than 0.");
                }

            } catch (NumberFormatException ex) {
                // Error message already shown by calculateSemesterTotals
                resultLabel.setText("YGPA: Error in input.");
            }
        } else if (e.getSource() == clearButton) {
            oddCiFields.clear();
            oddCFields.clear();
            oddSemesterPanel.removeAll();
            oddSubjectCount = 1;
            addSubjectRow(oddSemesterPanel, oddCiFields, oddCFields, oddSubjectCount, true);

            evenCiFields.clear();
            evenCFields.clear();
            evenSemesterPanel.removeAll();
            evenSubjectCount = 1;
            addSubjectRow(evenSemesterPanel, evenCiFields, evenCFields, evenSubjectCount, false);

            resultLabel.setText("YGPA: --");
            oddSemesterPanel.revalidate();
            oddSemesterPanel.repaint();
            evenSemesterPanel.revalidate();
            evenSemesterPanel.repaint();

        } else if (e.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new YgpaCalc();
    }
}