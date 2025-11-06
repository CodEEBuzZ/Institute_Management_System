package institute.management.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File; // Needed for deleting the image file
import java.sql.ResultSet;

public class AchievementGallery extends JFrame implements ActionListener {

    JPanel galleryPanel;
    JScrollPane scrollPane;
    JButton btnAll, btnStudent, btnFaculty, btnCollege, btnDelete; // Added btnDelete

    AchievementGallery() {
        setTitle("Achievement Gallery");
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Achievement Gallery");
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(heading, BorderLayout.NORTH);

        // --- Filter and Action Buttons ---
        JPanel buttonPanel = new JPanel(); // Changed name from filterPanel for clarity
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        buttonPanel.add(new JLabel("Filter: ")); // Label for clarity
        btnAll = new JButton("Show All");
        btnAll.addActionListener(this);
        buttonPanel.add(btnAll);

        btnStudent = new JButton("Students");
        btnStudent.addActionListener(this);
        buttonPanel.add(btnStudent);

        btnFaculty = new JButton("Faculty");
        btnFaculty.addActionListener(this);
        buttonPanel.add(btnFaculty);

        btnCollege = new JButton("College");
        btnCollege.addActionListener(this);
        buttonPanel.add(btnCollege);

        // --- NEW: Delete Button ---
        buttonPanel.add(Box.createHorizontalStrut(30)); // Add some space
        btnDelete = new JButton("Delete Achievement");
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(this);
        buttonPanel.add(btnDelete);
        // --- END: Delete Button ---

        add(buttonPanel, BorderLayout.SOUTH);

        // --- Gallery Panel ---
        galleryPanel = new JPanel();
        galleryPanel.setBackground(Color.WHITE);
        galleryPanel.setLayout(new GridLayout(0, 3, 10, 10)); // 3 columns

        scrollPane = new JScrollPane(galleryPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        loadAchievements("All"); // Load all initially

        setSize(1000, 700);
        setLocation(250, 50);
        setVisible(true);
    }

    private void loadAchievements(String filter) {
        galleryPanel.removeAll();
        String query = "SELECT * FROM achievements";
        if (!filter.equals("All")) {
            query += " WHERE achiever_type = '" + filter + "'";
        }
        query += " ORDER BY date_achieved DESC";

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                String title = rs.getString("title");
                String name = rs.getString("achiever_name");
                String description = rs.getString("description");
                String imagePath = rs.getString("image_path");

                JPanel card = createAchievementCard(title, name, description, imagePath);
                galleryPanel.add(card);
                count++;
            }
            // Adjust layout if few items, looks better than stretching
            if (count < 3) {
                galleryPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            } else {
                galleryPanel.setLayout(new GridLayout(0, 3, 10, 10));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        galleryPanel.revalidate();
        galleryPanel.repaint();
    }

    private JPanel createAchievementCard(String title, String name, String description, String imagePath) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBorder(new LineBorder(Color.GRAY, 1, true));
        card.setBackground(new Color(245, 245, 245));
        card.setPreferredSize(new Dimension(300, 280)); // Give cards a fixed size

        // --- Image ---
        JLabel imageLabel;
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image image = icon.getImage().getScaledInstance(280, 150, Image.SCALE_SMOOTH); // Adjust size slightly
            imageLabel = new JLabel(new ImageIcon(image));
        } else {
            imageLabel = new JLabel("Image not found"); // Placeholder if image missing
        }
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(imageLabel, BorderLayout.NORTH);

        // --- Details Panel ---
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS)); // Vertical layout
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10)); // More padding

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(lblTitle);

        JLabel lblName = new JLabel("By: " + name);
        lblName.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lblName.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(lblName);

        // Use JTextArea for potentially long descriptions, make it non-editable and wrap text
        JTextArea txtDesc = new JTextArea("Details: " + description);
        txtDesc.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtDesc.setWrapStyleWord(true);
        txtDesc.setLineWrap(true);
        txtDesc.setEditable(false);
        txtDesc.setBackground(Color.WHITE);
        txtDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(Box.createVerticalStrut(5)); // Add space
        detailsPanel.add(txtDesc);

        card.add(detailsPanel, BorderLayout.CENTER);

        return card;
    }

    // --- NEW: Delete Logic ---
    private void deleteAchievement() {
        String titleToDelete = JOptionPane.showInputDialog(this, "Enter the EXACT title of the achievement to delete:", "Delete Achievement", JOptionPane.QUESTION_MESSAGE);

        if (titleToDelete == null || titleToDelete.trim().isEmpty()) {
            return; // User cancelled or entered nothing
        }
        titleToDelete = titleToDelete.trim(); // Remove leading/trailing spaces

        // --- Confirmation ---
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the achievement titled:\n'" + titleToDelete + "'?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            String imagePathToDelete = null;
            try {
                Conn c = new Conn();
                // First, get the image path BEFORE deleting the record
                String queryGetPath = "SELECT image_path FROM achievements WHERE title = '" + titleToDelete + "'";
                ResultSet rs = c.statement.executeQuery(queryGetPath);
                if (rs.next()) {
                    imagePathToDelete = rs.getString("image_path");
                } else {
                    JOptionPane.showMessageDialog(this, "Achievement with title '" + titleToDelete + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop if not found
                }

                // Now, delete the database record
                String queryDelete = "DELETE FROM achievements WHERE title = '" + titleToDelete + "'";
                int rowsAffected = c.statement.executeUpdate(queryDelete);

                if (rowsAffected > 0) {
                    // Try to delete the image file from the folder
                    if (imagePathToDelete != null) {
                        try {
                            File imgFile = new File(imagePathToDelete);
                            if (imgFile.exists()) {
                                if (imgFile.delete()) {
                                    System.out.println("Deleted image file: " + imagePathToDelete);
                                } else {
                                    System.err.println("Failed to delete image file: " + imagePathToDelete);
                                    // Optionally inform user file couldn't be deleted
                                }
                            }
                        } catch (Exception fileEx) {
                            System.err.println("Error deleting image file: " + fileEx.getMessage());
                        }
                    }

                    JOptionPane.showMessageDialog(this, "Achievement '" + titleToDelete + "' deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadAchievements("All"); // Refresh the gallery
                } else {
                    // Should have been caught by the SELECT earlier, but just in case
                    JOptionPane.showMessageDialog(this, "Could not delete achievement. Record might have already been removed.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error during deletion: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAll) {
            loadAchievements("All");
        } else if (e.getSource() == btnStudent) {
            loadAchievements("Student");
        } else if (e.getSource() == btnFaculty) {
            loadAchievements("Faculty");
        } else if (e.getSource() == btnCollege) {
            loadAchievements("College");
        }
        // --- NEW: Handle Delete Button Click ---
        else if (e.getSource() == btnDelete) {
            deleteAchievement();
        }
        // --- END: Delete Button Logic ---
    }

    public static void main(String[] args) {
        new AchievementGallery();
    }
}
