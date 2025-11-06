package institute.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TeacherLeave extends JFrame implements ActionListener {

    Choice choiceEmpID, choiceTime; // Renamed choiceRollNo to choiceEmpID for clarity
    JDateChooser dateStart, dateEnd; // <-- Changed from one to two
    JButton submit, cancel;

    TeacherLeave() { // Constructor name corrected from Teacher Leave()
        setTitle("Apply Leave (Faculty)");
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(null);

        JLabel heading = new JLabel("Apply Leave (Faculty)"); // Title updated
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel EmpIDSE = new JLabel("Search by Employee ID"); // Label updated
        EmpIDSE.setBounds(60, 100, 200, 20);
        EmpIDSE.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(EmpIDSE);

        choiceEmpID = new Choice(); // Renamed variable
        choiceEmpID.setBounds(60, 130, 200, 20);
        add(choiceEmpID);

        try {
            Conn c = new Conn();
            // Query the teacher table for empID
            ResultSet resultSet = c.statement.executeQuery("select * from teacher");
            while (resultSet.next()) {
                choiceEmpID.add(resultSet.getString("empID")); // Use empID column
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // --- NEW: Start Date ---
        JLabel lbldateStart = new JLabel("Start Date");
        lbldateStart.setBounds(60, 180, 200, 20);
        lbldateStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbldateStart);

        dateStart = new JDateChooser();
        dateStart.setBounds(60, 210, 200, 25);
        add(dateStart);

        // --- NEW: End Date ---
        JLabel lbldateEnd = new JLabel("End Date");
        lbldateEnd.setBounds(60, 260, 200, 20);
        lbldateEnd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbldateEnd);

        dateEnd = new JDateChooser();
        dateEnd.setBounds(60, 290, 200, 25);
        add(dateEnd);

        // --- NEW: Time (for single day) ---
        JLabel time = new JLabel("Time (for single day leave)");
        time.setBounds(60, 340, 200, 20);
        time.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(time);

        choiceTime = new Choice();
        choiceTime.setBounds(60, 370, 200, 20);
        choiceTime.add("Full Day");
        choiceTime.add("Half Day");
        add(choiceTime);

        submit = new JButton("Submit");
        submit.setBounds(60, 430, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(200, 430, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setSize(510, 500);
        setLocation(250, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String empID = choiceEmpID.getSelectedItem(); // Use correct variable name
            String startDate = ((JTextField) dateStart.getDateEditor().getUiComponent()).getText();
            String endDate = ((JTextField) dateEnd.getDateEditor().getUiComponent()).getText();
            String durationChoice = choiceTime.getSelectedItem(); // Renamed variable

            // --- NEW: Validation and Logic ---
            if (startDate.isEmpty() || endDate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select both Start and End dates.");
                return;
            }

            // Default status is "Pending"
            String status = "Pending";

            // Date formatting
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            LocalDate start = LocalDate.parse(startDate, dtf);
            LocalDate end = LocalDate.parse(endDate, dtf);

            if (end.isBefore(start)) {
                JOptionPane.showMessageDialog(null, "End Date cannot be before Start Date.");
                return;
            }

            // Calculate duration in days
            long days = ChronoUnit.DAYS.between(start, end) + 1;

            // Determine final duration string
            String finalDuration = (days == 1) ? durationChoice : (days + " Days");

            // --- NEW: Updated SQL Query for teacherLeave table ---
            try {
                // Insert into teacherLeave table with new columns
                String q = "INSERT INTO teacherLeave(empID, start_date, end_date, duration, status) VALUES ('"
                        + empID + "','" + startDate + "', '" + endDate + "', '" + finalDuration + "', '" + status + "')";

                Conn c = new Conn();
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Leave Request Submitted. Status is Pending.");

                // --- NEW: Conditional Email Logic for OIC ---
                if (days > 1) {
                    // Placeholder call to email function
                    sendLeaveEmail("OIC_email@example.com", "Faculty Leave Request", "Faculty member " + empID + " has requested " + days + " days of leave.");
                }

                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else { // Cancel button
            setVisible(false);
        }
    }

    // --- NEW: Part 3 - Email Sending Method Placeholder ---
    public void sendLeaveEmail(String to, String subject, String body) {
        System.out.println("--- EMAIL SIMULATION ---");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        System.out.println("-------------------------");
        JOptionPane.showMessageDialog(this, "Simulating email to OIC (for leave > 1 day)");
    }

    public static void main(String[] args) {
        new TeacherLeave();
    }
}
