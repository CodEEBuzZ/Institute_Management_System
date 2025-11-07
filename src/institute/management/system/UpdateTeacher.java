package institute.management.system;

// No JDateChooser needed here as DOB is not editable in this version
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class UpdateTeacher extends JFrame implements ActionListener {

    // Editable fields based on screenshot
    JTextField textAddress, textPhone, textEmail, textAadharNo, textCourse, textBranch;
    // Non-editable fields shown in screenshot
    JLabel textName, textFatherName, textDob, textM10, textM12;
    JLabel empText; // Employee ID (Non-editable)

    JButton submit, cancel;
    Choice c_empID;

    UpdateTeacher() { // Corrected constructor name
        setTitle("Update Teacher Details");
        getContentPane().setBackground(new Color(255, 255, 255)); // Light purple
        setLayout(null);

        JLabel heading = new JLabel("Update Teacher Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 35));
        add(heading);

        JLabel labelSelectID = new JLabel("Select Employee ID");
        labelSelectID.setBounds(50, 100, 200, 20);
        labelSelectID.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelSelectID);

        c_empID = new Choice();
        c_empID.setBounds(250, 100, 200, 20);
        add(c_empID);

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("select * from teacher");
            while (rs.next()) {
                c_empID.add(rs.getString("empID")); // Correct column name is likely empID
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        // --- Fields (using HTML for stars) ---

        // Row 1: Name (Non-Editable) & Father Name (Non-Editable)
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 150, 100, 30);
        labelName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelName);

        textName = new JLabel(); // Non-editable
        textName.setBounds(200, 150, 150, 30);
        textName.setFont(new Font("serif", Font.PLAIN, 18));
        add(textName);

        JLabel fatherName = new JLabel("Father Name");
        fatherName.setBounds(400, 150, 200, 30);
        fatherName.setFont(new Font("serif", Font.PLAIN, 20));
        add(fatherName);

        textFatherName = new JLabel(); // Non-editable
        textFatherName.setBounds(600, 150, 150, 30);
        textFatherName.setFont(new Font("serif", Font.PLAIN, 18));
        add(textFatherName);

        // Row 2: Employee ID (Non-Editable) & DOB (Non-Editable)
        JLabel empIDLabel = new JLabel("Employee ID");
        empIDLabel.setBounds(50, 200, 200, 30);
        empIDLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(empIDLabel);

        empText = new JLabel(); // Non-editable ID
        empText.setBounds(200, 200, 150, 30);
        empText.setFont(new Font("serif", Font.BOLD, 20));
        add(empText);

        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(400, 200, 200, 30);
        dobLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(dobLabel);

        textDob = new JLabel(); // Non-editable DOB
        textDob.setBounds(600, 200, 150, 30);
        textDob.setFont(new Font("serif", Font.PLAIN, 18));
        add(textDob);

        // Row 3: Address (Editable) & Phone (Editable)
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

        textM10 = new JLabel(); // Non-editable
        textM10.setBounds(600, 300, 150, 30);
        textM10.setFont(new Font("serif", Font.PLAIN, 18));
        add(textM10);

        // Row 5: Class 12 (Non-Editable) & Aadhar (Editable)
        JLabel M12 = new JLabel("Class 12 (%)");
        M12.setBounds(50, 350, 200, 30);
        M12.setFont(new Font("serif", Font.PLAIN, 20));
        add(M12);

        textM12 = new JLabel(); // Non-editable
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

        // Row 6: Qualification (Editable) & Department (Editable)
        JLabel Qualification = new JLabel("<html>Qualification <font color='red'>*</font></html>");
        Qualification.setBounds(50, 400, 200, 30);
        Qualification.setFont(new Font("serif", Font.PLAIN, 20));
        add(Qualification);

        textCourse = new JTextField(); // Changed from JComboBox based on screenshot
        textCourse.setBounds(200, 400, 150, 30);
        add(textCourse);

        JLabel Department = new JLabel("<html>Department <font color='red'>*</font></html>");
        Department.setBounds(400, 400, 200, 30);
        Department.setFont(new Font("serif", Font.PLAIN, 20));
        add(Department);

        textBranch = new JTextField(); // Changed from JComboBox based on screenshot
        textBranch.setBounds(600, 400, 150, 30);
        add(textBranch);

        // --- Load initial data ---
        try {
            Conn c = new Conn();
            String query = "select * from teacher where empID = '" + c_empID.getSelectedItem() + "'";
            ResultSet rs = c.statement.executeQuery(query);
            if (rs.next()) { // Use if instead of while for single record
                textName.setText(rs.getString("name"));
                textFatherName.setText(rs.getString("fname"));
                empText.setText(rs.getString("empID"));
                textDob.setText(rs.getString("dob"));
                textAddress.setText(rs.getString("address"));
                textPhone.setText(rs.getString("phone"));
                textEmail.setText(rs.getString("email"));
                textM10.setText(rs.getString("class_x")); // Assuming column names
                textM12.setText(rs.getString("class_xii")); // Assuming column names
                textAadharNo.setText(rs.getString("aadhar"));
                textCourse.setText(rs.getString("education")); // Assuming column name
                textBranch.setText(rs.getString("department")); // Assuming column name
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        // --- Listener to reload data when ID changes ---
        c_empID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from teacher where empID = '" + c_empID.getSelectedItem() + "'";
                    ResultSet rs = c.statement.executeQuery(query);
                    if (rs.next()) {
                        textName.setText(rs.getString("name"));
                        textFatherName.setText(rs.getString("fname"));
                        empText.setText(rs.getString("empID"));
                        textDob.setText(rs.getString("dob"));
                        textAddress.setText(rs.getString("address"));
                        textPhone.setText(rs.getString("phone"));
                        textEmail.setText(rs.getString("email"));
                        textM10.setText(rs.getString("class_x"));
                        textM12.setText(rs.getString("class_xii"));
                        textAadharNo.setText(rs.getString("aadhar"));
                        textCourse.setText(rs.getString("education"));
                        textBranch.setText(rs.getString("department"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        // --- Buttons ---
        submit = new JButton("Update");
        submit.setBounds(250, 500, 120, 30); // Adjusted Y position
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 500, 120, 30); // Adjusted Y position
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900, 500); // Adjusted size
        setLocation(250, 50);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String empID = empText.getText(); // Get ID from the non-editable label
            // Get text from editable fields
            String address = textAddress.getText();
            String phone = textPhone.getText();
            String email = textEmail.getText();
            String aadhar = textAadharNo.getText();
            String course = textCourse.getText(); // Qualification
            String branch = textBranch.getText(); // Department

            // --- START: VALIDATION LOGIC ---
            if (address.isEmpty() || phone.isEmpty() || email.isEmpty() || aadhar.isEmpty() || course.isEmpty() || branch.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields marked with *", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Phone Number (digits only AND 10 digits long)
            if (!Validation.isDigitsOnly(phone) || phone.length() != 10) {
                JOptionPane.showMessageDialog(null, "Invalid Phone Number. Must be exactly 10 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Aadhar Number (digits only AND 12 digits long)
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

            // Validate Course/Qualification (Optional: add specific checks if needed, e.g., isAlpha)
            if (!Validation.isAlpha(course.replace(".", ""))) { // Basic check allowing letters, spaces, dots
                JOptionPane.showMessageDialog(null, "Invalid Qualification. Use letters and spaces.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Department (Optional: add specific checks if needed, e.g., isAlpha)
            if (!Validation.isAlpha(branch)) {
                JOptionPane.showMessageDialog(null, "Invalid Department. Use letters and spaces.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // --- END: VALIDATION LOGIC ---

            // If validation passes, update the database
            try {
                // Corrected UPDATE query syntax
                String query = "UPDATE teacher SET " +
                        "address = '" + address + "', " +
                        "phone = '" + phone + "', " +
                        "email = '" + email + "', " +
                        "aadhar = '" + aadhar + "', " +
                        "education = '" + course + "', " + // Assuming column name is education
                        "department = '" + branch + "' " +
                        "WHERE empID = '" + empID + "'";

                Conn c = new Conn();
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Teacher Details Updated Successfully");
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
        new UpdateTeacher();
    }
}