package institute.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class UpdateStudent extends JFrame implements ActionListener {

    // Editable fields
    JTextField textAddress, textPhone, textEmail, textAadharNo, textCourse, textBranch;
    // Non-editable fields
    JLabel textName, textFatherName, textDob, textM10, textM12;
    JLabel empText; // Roll Number (Non-editable)

    JButton submit, cancel;
    Choice c_empID; // Will hold Roll Numbers

    UpdateStudent() {
        setTitle("Update Student Details");
        getContentPane().setBackground(new Color(210, 230, 252)); // Light blue
        setLayout(null);

        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 35));
        add(heading);

        JLabel labelSelectID = new JLabel("Select Roll Number"); // Changed label
        labelSelectID.setBounds(50, 100, 200, 20);
        labelSelectID.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelSelectID);

        c_empID = new Choice(); // Renamed for clarity (was c_rollNo conceptually)
        c_empID.setBounds(250, 100, 200, 20);
        add(c_empID);

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("select * from student");
            while (rs.next()) {
                c_empID.add(rs.getString("rollNo")); // Populate with roll numbers
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        // --- Fields (using HTML for stars) ---

        // Row 1: Name & Father Name (Non-Editable)
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 150, 100, 30);
        labelName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelName);

        textName = new JLabel();
        textName.setBounds(200, 150, 150, 30);
        textName.setFont(new Font("serif", Font.PLAIN, 18));
        add(textName);

        JLabel fatherName = new JLabel("Father Name");
        fatherName.setBounds(400, 150, 200, 30);
        fatherName.setFont(new Font("serif", Font.PLAIN, 20));
        add(fatherName);

        textFatherName = new JLabel();
        textFatherName.setBounds(600, 150, 150, 30);
        textFatherName.setFont(new Font("serif", Font.PLAIN, 18));
        add(textFatherName);

        // Row 2: Roll No & DOB (Non-Editable)
        JLabel empIDLabel = new JLabel("Roll Number"); // Changed label
        empIDLabel.setBounds(50, 200, 200, 30);
        empIDLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(empIDLabel);

        empText = new JLabel(); // Will display selected Roll No
        empText.setBounds(200, 200, 150, 30);
        empText.setFont(new Font("serif", Font.BOLD, 20));
        add(empText);

        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(400, 200, 200, 30);
        dobLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(dobLabel);

        textDob = new JLabel();
        textDob.setBounds(600, 200, 150, 30);
        textDob.setFont(new Font("serif", Font.PLAIN, 18));
        add(textDob);

        // Row 3: Address & Phone (Editable)
        JLabel address = new JLabel("<html>Address <font color='red'>*</font></html>");
        address.setBounds(50, 250, 200, 30);
        address.setFont(new Font("serif", Font.PLAIN, 20));
        add(address);

        textAddress = new JTextField();
        textAddress.setBounds(200, 250, 150, 30);
        add(textAddress);

        JLabel phone = new JLabel("<html>Phone <font color='red'>*</font></html>");
        phone.setBounds(400, 250, 200, 30);
        phone.setFont(new Font("serif", Font.PLAIN, 20));
        add(phone);

        textPhone = new JTextField();
        textPhone.setBounds(600, 250, 150, 30);
        add(textPhone);

        // Row 4: Email (Editable) & Class 10 (Non-Editable)
        JLabel email = new JLabel("<html>Email <font color='red'>*</font></html>");
        email.setBounds(50, 300, 200, 30);
        email.setFont(new Font("serif", Font.PLAIN, 20));
        add(email);

        textEmail = new JTextField();
        textEmail.setBounds(200, 300, 150, 30);
        add(textEmail);

        JLabel M10 = new JLabel("Class 10 (%)");
        M10.setBounds(400, 300, 200, 30);
        M10.setFont(new Font("serif", Font.PLAIN, 20));
        add(M10);

        textM10 = new JLabel();
        textM10.setBounds(600, 300, 150, 30);
        textM10.setFont(new Font("serif", Font.PLAIN, 18));
        add(textM10);

        // Row 5: Class 12 (Non-Editable) & Aadhar (Editable)
        JLabel M12 = new JLabel("Class 12 (%)");
        M12.setBounds(50, 350, 200, 30);
        M12.setFont(new Font("serif", Font.PLAIN, 20));
        add(M12);

        textM12 = new JLabel();
        textM12.setBounds(200, 350, 150, 30);
        textM12.setFont(new Font("serif", Font.PLAIN, 18));
        add(textM12);

        JLabel AadharNo = new JLabel("<html>Aadhar No <font color='red'>*</font></html>");
        AadharNo.setBounds(400, 350, 200, 30);
        AadharNo.setFont(new Font("serif", Font.PLAIN, 20));
        add(AadharNo);

        textAadharNo = new JTextField();
        textAadharNo.setBounds(600, 350, 150, 30);
        add(textAadharNo);

        // Row 6: Course & Branch (Editable)
        JLabel Qualification = new JLabel("<html>Course <font color='red'>*</font></html>");
        Qualification.setBounds(50, 400, 200, 30);
        Qualification.setFont(new Font("serif", Font.PLAIN, 20));
        add(Qualification);

        textCourse = new JTextField();
        textCourse.setBounds(200, 400, 150, 30);
        add(textCourse);

        JLabel Department = new JLabel("<html>Branch <font color='red'>*</font></html>");
        Department.setBounds(400, 400, 200, 30);
        Department.setFont(new Font("serif", Font.PLAIN, 20));
        add(Department);

        textBranch = new JTextField();
        textBranch.setBounds(600, 400, 150, 30);
        add(textBranch);

        // --- Load initial data ---
        loadStudentData(c_empID.getSelectedItem());

        // --- Listener to reload data when Roll No changes ---
        c_empID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                loadStudentData(c_empID.getSelectedItem());
            }
        });

        // --- Buttons ---
        submit = new JButton("Update");
        submit.setBounds(250, 500, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 500, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900, 600);
        setLocation(350, 50);
        setVisible(true);
    }

    // --- Helper method to load data ---
    private void loadStudentData(String rollNo) {
        try {
            Conn c = new Conn();
            String query = "select * from student where rollNo = '" + rollNo + "'";
            ResultSet rs = c.statement.executeQuery(query);
            if (rs.next()) {
                textName.setText(rs.getString("name"));
                textFatherName.setText(rs.getString("fname"));
                empText.setText(rs.getString("rollNo")); // Display roll number
                textDob.setText(rs.getString("dob"));
                textAddress.setText(rs.getString("address"));
                textPhone.setText(rs.getString("phone"));
                textEmail.setText(rs.getString("email"));
                textM10.setText(rs.getString("class_x")); // Assuming column names
                textM12.setText(rs.getString("class_xii")); // Assuming column names
                textAadharNo.setText(rs.getString("aadhar"));
                textCourse.setText(rs.getString("course"));
                textBranch.setText(rs.getString("branch"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String rollNo = empText.getText(); // Get Roll No from the non-editable label
            // Get text from editable fields
            String address = textAddress.getText();
            String phone = textPhone.getText();
            String email = textEmail.getText();
            String aadhar = textAadharNo.getText();
            String course = textCourse.getText();
            String branch = textBranch.getText();

            // --- START: VALIDATION LOGIC ---
            if (address.isEmpty() || phone.isEmpty() || email.isEmpty() || aadhar.isEmpty() || course.isEmpty() || branch.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields marked with *", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Phone Number
            if (!Validation.isDigitsOnly(phone) || phone.length() != 10) {
                JOptionPane.showMessageDialog(null, "Invalid Phone Number. Must be exactly 10 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Aadhar Number
            if (!Validation.isDigitsOnly(aadhar) || aadhar.length() != 12) {
                JOptionPane.showMessageDialog(null, "Invalid Aadhar Number. Must be exactly 12 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Email
            if (!Validation.isValidEmail(email)) {
                JOptionPane.showMessageDialog(null, "Invalid Email Address format.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Address
            if (!Validation.isValidAddress(address)) {
                JOptionPane.showMessageDialog(null, "Invalid Address. Use only letters, numbers, spaces, and ( , / - ).", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Course (Optional: add specific checks if needed)
            if (!Validation.isAlpha(course.replace(".", ""))) { // Basic check
                JOptionPane.showMessageDialog(null, "Invalid Course. Use letters and spaces.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validate Branch (Optional: add specific checks if needed)
            if (!Validation.isAlpha(branch)) { // Basic check
                JOptionPane.showMessageDialog(null, "Invalid Branch. Use letters and spaces.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // --- END: VALIDATION LOGIC ---

            // If validation passes, update the database
            try {
                String query = "UPDATE student SET " +
                        "address = '" + address + "', " +
                        "phone = '" + phone + "', " +
                        "email = '" + email + "', " +
                        "aadhar = '" + aadhar + "', " +
                        "course = '" + course + "', " +
                        "branch = '" + branch + "' " +
                        "WHERE rollNo = '" + rollNo + "'";

                Conn c = new Conn();
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Student Details Updated Successfully");
                setVisible(false);

            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else { // Cancel button
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateStudent();
    }
}