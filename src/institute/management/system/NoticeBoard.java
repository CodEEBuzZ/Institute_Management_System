package institute.management.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.time.LocalDate; // For checking expiry date

public class NoticeBoard extends JFrame {

    JPanel noticesPanel;
    JScrollPane scrollPane;

    NoticeBoard() {
        setTitle("Notice Board");
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Notice Board");
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(heading, BorderLayout.NORTH);

        noticesPanel = new JPanel();
        noticesPanel.setBackground(new Color(245, 245, 245)); // Light gray background
        // Use BoxLayout for a vertical list
        noticesPanel.setLayout(new BoxLayout(noticesPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(noticesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        loadNotices(); // Load notices when the board opens

        setSize(900, 500);
        setLocation(250,50); // Center the window
        setVisible(true);
    }

    private void loadNotices() {
        noticesPanel.removeAll(); // Clear previous notices

        // SQL to get current notices (not expired)
        String query = "SELECT title, content, DATE_FORMAT(posted_date, '%d %b %Y %h:%i %p') as posted, target_audience " +
                "FROM notices " +
                "WHERE expiry_date IS NULL OR expiry_date >= CURDATE() " + // Only show non-expired or no-expiry notices
                "ORDER BY posted_date DESC"; // Newest first

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery(query);
            boolean foundNotices = false;

            while (rs.next()) {
                foundNotices = true;
                String title = rs.getString("title");
                String content = rs.getString("content");
                String postedDate = rs.getString("posted");
                String audience = rs.getString("target_audience");

                // Create a panel for each notice
                JPanel noticeCard = createNoticeCard(title, content, postedDate, audience);
                noticesPanel.add(noticeCard);
                noticesPanel.add(Box.createVerticalStrut(10)); // Add space between notices
            }

            if (!foundNotices) {
                JLabel noNoticesLabel = new JLabel("No current notices found.");
                noNoticesLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
                noNoticesLabel.setHorizontalAlignment(SwingConstants.CENTER);
                noticesPanel.add(noNoticesLabel);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Error loading notices: " + e.getMessage());
            errorLabel.setForeground(Color.RED);
            noticesPanel.add(errorLabel);
        }

        noticesPanel.revalidate();
        noticesPanel.repaint();
    }

    private JPanel createNoticeCard(String title, String content, String postedDate, String audience) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(5, 5));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                new EmptyBorder(10, 10, 10, 10)) // Add padding inside border
        );
        card.setBackground(Color.WHITE);
        // Prevent card from stretching vertically
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, card.getPreferredSize().height));

        // Header Panel (Title and Date)
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        headerPanel.add(lblTitle, BorderLayout.CENTER);

        JLabel lblDate = new JLabel("Posted: " + postedDate + " | For: " + audience);
        lblDate.setFont(new Font("Tahoma", Font.ITALIC, 12));
        lblDate.setForeground(Color.DARK_GRAY);
        headerPanel.add(lblDate, BorderLayout.SOUTH);

        card.add(headerPanel, BorderLayout.NORTH);

        // Content Area
        JTextArea txtContent = new JTextArea(content);
        txtContent.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtContent.setWrapStyleWord(true);
        txtContent.setLineWrap(true);
        txtContent.setEditable(false);
        txtContent.setBackground(Color.WHITE);
        // Add scroll pane if content is very long, but limit height
        JScrollPane contentScrollPane = new JScrollPane(txtContent);
        contentScrollPane.setBorder(null); // Remove border from scroll pane
        contentScrollPane.setPreferredSize(new Dimension(600, 80)); // Limit height

        card.add(contentScrollPane, BorderLayout.CENTER);

        return card;
    }

    public static void main(String[] args) {
        new NoticeBoard(); // For testing this view directly
    }
}