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

public class StudentLeave extends JFrame implements ActionListener {

    Choice choiceRollNo, choiceTime;
    JDateChooser dateStart, dateEnd; // <-- Changed from one to two
    JButton submit, cancel;

    StudentLeave() {
        setTitle("Apply Leave (Student)");
        getContentPane().setBackground(new Color(210, 232, 252));
        setLayout(null);

        JLabel heading = new JLabel("Apply Leave (Student)");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel RollNoSE = new JLabel("Search by Roll Number");
        RollNoSE.setBounds(60, 100, 200, 20);
        RollNoSE.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(RollNoSE);

        choiceRollNo = new Choice();
        choiceRollNo.setBounds(60, 130, 200, 20);
        add(choiceRollNo);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student");
            while (resultSet.next()) {
                choiceRollNo.add(resultSet.getString("rollNo"));
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

        setSize(500, 550);
        setLocation(550, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String rollNo = choiceRollNo.getSelectedItem();
            String startDate = ((JTextField) dateStart.getDateEditor().getUiComponent()).getText();
            String endDate = ((JTextField) dateEnd.getDateEditor().getUiComponent()).getText();
            String duration = choiceTime.getSelectedItem();

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

            // If it's a single day leave, use the "Half/Full Day" choice.
            // Otherwise, it's a multi-day leave.
            String finalDuration = (days == 1) ? duration : (days + " Days");

            // --- NEW: Updated SQL Query ---
            try {
                String q = "insert into studentLeave(rollNo, start_date, end_date, duration, status) values('" + rollNo + "','" + startDate + "', '" + endDate + "', '" + finalDuration + "', '" + status + "')";
                Conn c = new Conn();
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Leave Request Submitted. Status is Pending.");

                // --- NEW: Conditional Email Logic ---
                if (days > 1) {
                    // This is where you call the email function
                    // We will build this in Part 3
                    sendLeaveEmail("HOD_email@example.com", "Student Leave Request", "Student " + rollNo + " has requested " + days + " days of leave.");
                }

                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }

    // --- NEW: Part 3 - Email Sending Method (Conceptual) ---
    // This method is a placeholder. See Part 3 for explanation.
    public void sendLeaveEmail(String to, String subject, String body) {
        System.out.println("--- EMAIL SIMULATION ---");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        System.out.println("-------------------------");
        // To make this send a REAL email, you need the JavaMail API.
        JOptionPane.showMessageDialog(this, "Simulating email to HOD (since " + body + ")");
    }

    public static void main(String[] args) {
        new StudentLeave();
    }
}