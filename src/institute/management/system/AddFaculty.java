package institute.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddFaculty extends JFrame implements ActionListener {

    JTextField textFirstName, textMiddleName, textLastName;
    JTextField textFFirstName, textFMiddleName, textFLastName;
    JTextField textAddress, textPhone, textEmail, textM10, textM12, textAadharNo;

    JLabel empText;
    JDateChooser cdob;
    JComboBox<String> courseBox, departmentBox;
    JButton submit, cancel;
    Random ran = new Random();
    long f4 = Math.abs(ran.nextLong() % 9000L + 1000L);

    AddFaculty() {
        setTitle("New Teacher Details");
        getContentPane().setBackground(new Color(220, 220, 255)); // Light purple color
        setLayout(null);

        JLabel heading = new JLabel("New Teacher Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        // --- NEW: Name Row (First, Middle, Last) with Red Stars ---
        JLabel labelFirstName = new JLabel("<html>First Name <font color='red'>*</font></html>");
        labelFirstName.setBounds(50, 150, 150, 30);
        labelFirstName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelFirstName);

        textFirstName = new JTextField();
        textFirstName.setBounds(200, 150, 150, 30);
        add(textFirstName);

        JLabel labelFatherFirstName = new JLabel("<html>Father's First Name <font color='red'>*</font></html>");
        labelFatherFirstName.setBounds(400, 150, 200, 30);
        labelFatherFirstName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelFatherFirstName);

        textFFirstName = new JTextField();
        textFFirstName.setBounds(600, 150, 150, 30);
        add(textFFirstName);

        // --- NEW: Middle Name Row (Optional) ---
        JLabel labelMiddleName = new JLabel("Middle Name");
        labelMiddleName.setBounds(50, 190, 150, 30);
        labelMiddleName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelMiddleName);

        textMiddleName = new JTextField();
        textMiddleName.setBounds(200, 190, 150, 30);
        add(textMiddleName);

        JLabel labelFatherMiddleName = new JLabel("Father's Middle Name");
        labelFatherMiddleName.setBounds(400, 190, 200, 30);
        labelFatherMiddleName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelFatherMiddleName);

        textFMiddleName = new JTextField();
        textFMiddleName.setBounds(600, 190, 150, 30);
        add(textFMiddleName);

        // --- NEW: Last Name Row ---
        JLabel labelLastName = new JLabel("<html>Last Name <font color='red'>*</font></html>");
        labelLastName.setBounds(50, 230, 150, 30);
        labelLastName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelLastName);

        textLastName = new JTextField();
        textLastName.setBounds(200, 230, 150, 30);
        add(textLastName);

        JLabel labelFatherLastName = new JLabel("<html>Father's Last Name <font color='red'>*</font></html>");
        labelFatherLastName.setBounds(400, 230, 200, 30);
        labelFatherLastName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelFatherLastName);

        textFLastName = new JTextField();
        textFLastName.setBounds(600, 230, 150, 30);
        add(textFLastName);

        // --- Row 4: EmpID and DOB ---
        JLabel empID = new JLabel("Employee ID");
        empID.setBounds(50, 270, 200, 30);
        empID.setFont(new Font("serif", Font.PLAIN, 20));
        add(empID);

        empText = new JLabel(String.valueOf(f4));
        empText.setBounds(200, 270, 150, 30);
        empText.setFont(new Font("serif", Font.BOLD, 20));
        add(empText);

        JLabel dob = new JLabel("<html>Date of Birth <font color='red'>*</font></html>");
        dob.setBounds(400, 270, 200, 30);
        dob.setFont(new Font("serif", Font.PLAIN, 20));
        add(dob);

        cdob = new JDateChooser();
        cdob.setBounds(600, 270, 150, 30);
        add(cdob);

        // --- Row 5: Address and Phone ---
        JLabel address = new JLabel("<html>Address <font color='red'>*</font></html>");
        address.setBounds(50, 310, 200, 30);
        address.setFont(new Font("serif", Font.PLAIN, 20));
        add(address);

        textAddress = new JTextField();
        textAddress.setBounds(200, 310, 150, 30);
        add(textAddress);

        JLabel phone = new JLabel("<html>Phone Number <font color='red'>*</font></html>");
        phone.setBounds(400, 310, 200, 30);
        phone.setFont(new Font("serif", Font.PLAIN, 20));
        add(phone);

        textPhone = new JTextField();
        textPhone.setBounds(600, 310, 150, 30);
        add(textPhone);

        // --- Row 6: Email and Class 10 ---
        JLabel email = new JLabel("<html>Email <font color='red'>*</font></html>");
        email.setBounds(50, 350, 200, 30);
        email.setFont(new Font("serif", Font.PLAIN, 20));
        add(email);

        textEmail = new JTextField();
        textEmail.setBounds(200, 350, 150, 30);
        add(textEmail);

        JLabel M10 = new JLabel("<html>Class 10 (%) <font color='red'>*</font></html>");
        M10.setBounds(400, 350, 200, 30);
        M10.setFont(new Font("serif", Font.PLAIN, 20));
        add(M10);

        textM10 = new JTextField();
        textM10.setBounds(600, 350, 150, 30);
        add(textM10);

        // --- Row 7: Class 12 and Aadhar ---
        JLabel M12 = new JLabel("<html>Class 12 (%) <font color='red'>*</font></html>");
        M12.setBounds(50, 390, 200, 30);
        M12.setFont(new Font("serif", Font.PLAIN, 20));
        add(M12);

        textM12 = new JTextField();
        textM12.setBounds(200, 390, 150, 30);
        add(textM12);

        JLabel AadharNo = new JLabel("<html>Aadhar Number <font color='red'>*</font></html>");
        AadharNo.setBounds(400, 390, 200, 30);
        AadharNo.setFont(new Font("serif", Font.PLAIN, 20));
        add(AadharNo);

        textAadharNo = new JTextField();
        textAadharNo.setBounds(600, 390, 150, 30);
        add(textAadharNo);

        // --- Row 8: Qualification and Dept ---
        JLabel Qualification = new JLabel("<html>Qualification <font color='red'>*</font></html>");
        Qualification.setBounds(50, 430, 200, 30);
        Qualification.setFont(new Font("serif", Font.PLAIN, 20));
        add(Qualification);

        String[] course = {"B.Tech", "M.Tech", "BA", "MA", "BSC", "MSC", "PhD"};
        courseBox = new JComboBox<>(course);
        courseBox.setBounds(200, 430, 150, 30);
        courseBox.setBackground(Color.WHITE);
        add(courseBox);

        JLabel Department = new JLabel("<html>Department <font color='red'>*</font></html>");
        Department.setBounds(400, 430, 200, 30);
        Department.setFont(new Font("serif", Font.PLAIN, 20));
        add(Department);

        String[] department = {"Computer Science", "Electrical", "Mechanical", "Civil", "Information Technology"};
        departmentBox = new JComboBox<>(department);
        departmentBox.setBounds(600, 430, 150, 30);
        departmentBox.setBackground(Color.WHITE);
        add(departmentBox);

        // --- Row 9: Buttons ---
        submit = new JButton("Submit");
        submit.setBounds(250, 520, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 520, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900, 650); // Increased height for new fields
        setLocation(350, 50);
        setLayout(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            // 1. Get text from all fields
            String firstName = textFirstName.getText();
            String middleName = textMiddleName.getText();
            String lastName = textLastName.getText();
            String fFirstName = textFFirstName.getText();
            String fMiddleName = textFMiddleName.getText();
            String fLastName = textFLastName.getText();

            String empID = empText.getText();
            String dob = ((JTextField) cdob.getDateEditor().getUiComponent()).getText();
            String address = textAddress.getText();
            String phone = textPhone.getText();
            String email = textEmail.getText();
            String x = textM10.getText(); // Class 10 Marks
            String xii = textM12.getText(); // Class 12 Marks
            String aadhar = textAadharNo.getText();
            String course = (String) courseBox.getSelectedItem();
            String department = (String) departmentBox.getSelectedItem();

            // --- START: NEW VALIDATION LOGIC ---

            // Check for empty mandatory fields (note: middle names are optional)
            if (firstName.isEmpty() || lastName.isEmpty() || fFirstName.isEmpty() || fLastName.isEmpty() ||
                    dob.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty() ||
                    x.isEmpty() || xii.isEmpty() || aadhar.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Please fill in all fields marked with *", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Name fields (letters and spaces only)
            if (!Validation.isAlpha(firstName) || !Validation.isAlpha(lastName)) {
                JOptionPane.showMessageDialog(null, "Invalid Name. First and Last Name must contain letters and spaces only.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!middleName.isEmpty() && !Validation.isAlpha(middleName)) {
                JOptionPane.showMessageDialog(null, "Invalid Middle Name. Must contain letters and spaces only.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Father's Name fields
            if (!Validation.isAlpha(fFirstName) || !Validation.isAlpha(fLastName)) {
                JOptionPane.showMessageDialog(null, "Invalid Father's Name. First and Last Name must contain letters and spaces only.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!fMiddleName.isEmpty() && !Validation.isAlpha(fMiddleName)) {
                JOptionPane.showMessageDialog(null, "Invalid Father's Middle Name. Must contain letters and spaces only.", "Input Error", JOptionPane.ERROR_MESSAGE);
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

            // Validate Percentages (is a number AND between 0-100)
            if (!Validation.isPercentage(x)) {
                JOptionPane.showMessageDialog(null, "Invalid Class 10 (%). Must be a number between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Validation.isPercentage(xii)) {
                JOptionPane.showMessageDialog(null, "Invalid Class 12 (%). Must be a number between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Email
            if (!Validation.isValidEmail(email)) {
                JOptionPane.showMessageDialog(null, "Invalid Email Address format.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Address (alphanumeric and common chars)
            if (!Validation.isValidAddress(address)) {
                JOptionPane.showMessageDialog(null, "Invalid Address. Use only letters, numbers, spaces, and ( , / - ).", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // --- END: NEW VALIDATION LOGIC ---

            // If all validations pass, concatenate names and save
            String name = firstName + " " + middleName + " " + lastName;
            String fname = fFirstName + " " + fMiddleName + " " + fLastName;

            try {
                String q = "insert into teacher values('" + name + "','" + fname + "','" + empID + "','" + dob + "','" + address + "','" + phone + "','" + email + "','" + x + "','" + xii + "','" + aadhar + "','" + course + "','" + department + "')";

                Conn c = new Conn();
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Teacher Details Inserted Successfully");
                setVisible(false);

            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving to database: " + E.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

        } else { // This is for the 'cancel' button
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddFaculty();
    }
}