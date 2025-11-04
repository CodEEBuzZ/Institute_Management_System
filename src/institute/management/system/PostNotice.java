package institute.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class PostNotice extends JFrame implements ActionListener {

    JTextField tfTitle;
    JTextArea taContent;
    JComboBox<String> cbAudience;
    JDateChooser expiryDateChooser;
    JButton btnSubmit, btnCancel;

    PostNotice() {
        setTitle("Post New Notice");
        getContentPane().setBackground(new Color(240, 248, 255)); // Light Alice Blue
        setLayout(null);

        JLabel heading = new JLabel("Post New Notice");
        heading.setBounds(150, 20, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(50, 80, 150, 25);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblTitle);

        tfTitle = new JTextField();
        tfTitle.setBounds(200, 80, 350, 25);
        add(tfTitle);

        JLabel lblAudience = new JLabel("Target Audience:");
        lblAudience.setBounds(50, 120, 150, 25);
        lblAudience.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblAudience);

        String[] audiences = {"All", "Students", "Faculty"}; // Add more specific groups if needed
        cbAudience = new JComboBox<>(audiences);
        cbAudience.setBounds(200, 120, 350, 25);
        cbAudience.setBackground(Color.WHITE);
        add(cbAudience);

        JLabel lblContent = new JLabel("Content:");
        lblContent.setBounds(50, 160, 150, 25);
        lblContent.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblContent);

        taContent = new JTextArea();
        taContent.setLineWrap(true);
        taContent.setWrapStyleWord(true);
        JScrollPane spContent = new JScrollPane(taContent);
        spContent.setBounds(200, 160, 350, 150);
        add(spContent);

        JLabel lblExpiry = new JLabel("Expiry Date (Optional):");
        lblExpiry.setBounds(50, 330, 200, 25);
        lblExpiry.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblExpiry);

        expiryDateChooser = new JDateChooser();
        expiryDateChooser.setBounds(250, 330, 300, 25);
        add(expiryDateChooser);

        btnSubmit = new JButton("Post Notice");
        btnSubmit.setBounds(150, 400, 140, 30);
        btnSubmit.setBackground(Color.BLACK);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(310, 400, 120, 30);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(this);
        add(btnCancel);

        setSize(600, 500);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String title = tfTitle.getText();
            String content = taContent.getText();
            String audience = (String) cbAudience.getSelectedItem();
            String expiryDate = null;

            // --- Validation ---
            if (title.isEmpty() || content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Title and Content.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Format expiry date if selected
            if (expiryDateChooser.getDate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                expiryDate = sdf.format(expiryDateChooser.getDate());
            }

            try {
                Conn c = new Conn();
                String query;
                // Use prepared statements ideally, but for simplicity:
                if (expiryDate != null) {
                    query = "INSERT INTO notices (title, content, target_audience, expiry_date) VALUES ('" +
                            title + "', '" + content + "', '" + audience + "', '" + expiryDate + "')";
                } else {
                    query = "INSERT INTO notices (title, content, target_audience) VALUES ('" +
                            title + "', '" + content + "', '" + audience + "')";
                }

                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Notice Posted Successfully!");
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error posting notice: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnCancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PostNotice(); // For testing this form directly
    }
}