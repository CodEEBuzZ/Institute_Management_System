package institute.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

public class AddAchievement extends JFrame implements ActionListener {

    JTextField tfTitle, tfAchieverName, tfImagePath;
    JTextArea taDescription;
    JComboBox<String> cbAchieverType;
    JDateChooser dateChooser;
    JButton btnUpload, btnSubmit, btnCancel;
    File selectedFile;

    AddAchievement() {
        setTitle("Add New Achievement");
        getContentPane().setBackground(new Color(186, 248, 125));
        setLayout(null);

        JLabel heading = new JLabel("Add New Achievement");
        heading.setBounds(150, 20, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(50, 100, 150, 25);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblTitle);

        tfTitle = new JTextField();
        tfTitle.setBounds(200, 100, 300, 25);
        add(tfTitle);

        JLabel lblType = new JLabel("Achiever Type:");
        lblType.setBounds(50, 140, 150, 25);
        lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblType);

        String[] types = {"Student", "Faculty", "College"};
        cbAchieverType = new JComboBox<>(types);
        cbAchieverType.setBounds(200, 140, 300, 25);
        cbAchieverType.setBackground(Color.WHITE);
        add(cbAchieverType);

        JLabel lblName = new JLabel("Achiever's Name:");
        lblName.setBounds(50, 180, 150, 25);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblName);

        tfAchieverName = new JTextField();
        tfAchieverName.setBounds(200, 180, 300, 25);
        add(tfAchieverName);

        JLabel lblDate = new JLabel("Date:");
        lblDate.setBounds(50, 220, 150, 25);
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblDate);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(200, 220, 300, 25);
        add(dateChooser);

        JLabel lblDesc = new JLabel("Description:");
        lblDesc.setBounds(50, 260, 150, 25);
        lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblDesc);

        taDescription = new JTextArea();
        JScrollPane spDesc = new JScrollPane(taDescription);
        spDesc.setBounds(200, 260, 300, 100);
        add(spDesc);

        JLabel lblImage = new JLabel("Image:");
        lblImage.setBounds(50, 380, 150, 25);
        lblImage.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblImage);

        tfImagePath = new JTextField();
        tfImagePath.setBounds(200, 380, 200, 25);
        tfImagePath.setEditable(false);
        add(tfImagePath);

        btnUpload = new JButton("Upload");
        btnUpload.setBounds(410, 380, 90, 25);
        btnUpload.addActionListener(this);
        add(btnUpload);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(150, 450, 120, 30);
        btnSubmit.setBackground(Color.BLACK);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(300, 450, 120, 30);
        btnCancel.setBackground(Color.BLACK);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(this);
        add(btnCancel);

        setSize(600, 500);
        setLocation(250, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnUpload) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select an Image");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                tfImagePath.setText(selectedFile.getName());
            }
        } else if (e.getSource() == btnSubmit) {
            try {
                // --- Validation ---
                if (tfTitle.getText().isEmpty() || tfAchieverName.getText().isEmpty() || selectedFile == null || dateChooser.getDate() == null) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields and upload an image.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // --- Copy the file ---
                String fileName = selectedFile.getName();

                // --- THIS IS THE CORRECTED PATH ---
                File destFile = new File("resources/achievements/" + fileName);
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                String imagePath = "resources/achievements/" + fileName;
                // --- END OF CORRECTION ---

                // --- Get other details ---
                String title = tfTitle.getText();
                String type = (String) cbAchieverType.getSelectedItem();
                String name = tfAchieverName.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(dateChooser.getDate());
                String description = taDescription.getText();

                // --- Save to database ---
                String query = "INSERT INTO achievements (title, achiever_type, achiever_name, description, image_path, date_achieved) VALUES " +
                        "('" + title + "', '" + type + "', '" + name + "', '" + description + "', '" + imagePath + "', '" + date + "')";

                Conn c = new Conn();
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(this, "Achievement Added Successfully!");
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding achievement: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnCancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddAchievement();
    }
}
