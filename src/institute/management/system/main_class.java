package institute.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener {
    private SlidingImagePanel imagePanel;
    private String[] imagePaths = {
            "icon/CollegeGate.jpeg",
            "icon/mainGate.jpeg",
            "icon/clglane.jpeg",
            "icon/CSEbuilding.jpeg",
            "icon/clglane1.jpeg"
    };
    private String[] imageTexts = {
            "Welcome to GCETTS College",
            "Beautiful College Lane",
            "CSE Building",
            "Peaceful Campus View",
            "Main Gate Entrance"
    };
    private int currentImageIndex = 0;

    main_class() {
         setLayout(null); 
        // --- Setup Background Image ---
         ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/GCETTS logo.jpg"));
        setIconImage(icon.getImage());


        ImageIcon firstIcon = new ImageIcon(ClassLoader.getSystemResource(imagePaths[0]));
        Image firstImage = firstIcon.getImage().getScaledInstance(1510, 750, Image.SCALE_SMOOTH);
        imagePanel = new SlidingImagePanel(firstImage);
        imagePanel.setBackground(Color.BLACK);   // avoid white/black flicker
        imagePanel.setBounds(0, 0, 1510, 750);
        add(imagePanel);

        // Start the slideshow
        startImageLoop();


        UIManager.put("Menu.font", new Font("Segoe UI", Font.BOLD, 18));
        UIManager.put("MenuItem.font", new Font("Segoe UI", Font.PLAIN, 16));
        UIManager.put("MenuBar.font", new Font("Segoe UI", Font.BOLD, 18));



        // --- Create Menu Bar ---
        JMenuBar mb = new JMenuBar();
         mb.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));

        // --- 1. New Information ---
        JMenu newInfo = new JMenu("New Information");
        newInfo.setForeground(Color.BLACK);
        mb.add(newInfo);

        // New Faculty Information
        
        JMenuItem facultyInfo = new JMenuItem("New Faculty Information");
        facultyInfo.setBackground(Color.WHITE);
        facultyInfo.addActionListener(this);
        newInfo.add(facultyInfo);

        // New Student Information
        
        JMenuItem studentInfo = new JMenuItem("New Student Information");
        studentInfo.setBackground(Color.WHITE);
        studentInfo.addActionListener(this);
        newInfo.add(studentInfo);


        // --- 2. View Details ---
        JMenu details = new JMenu("View Details");
        details.setForeground(Color.BLACK);
        mb.add(details);

        // Faculty details
        JMenuItem facultyDetails = new JMenuItem("View Faculty Details");
        facultyDetails.setBackground(Color.WHITE);
        facultyDetails.addActionListener(this);
        details.add(facultyDetails);

        // Students details
        JMenuItem studentDetails = new JMenuItem("View Student Details");
        studentDetails.setBackground(Color.WHITE);
        studentDetails.addActionListener(this);
        details.add(studentDetails);


        // --- 3. Apply Leave ---
        JMenu leave = new JMenu("Apply Leave");
        leave.setForeground(Color.BLACK);
        mb.add(leave);

        // Faculty leave
        JMenuItem facultyLeave = new JMenuItem("Faculty Leave");
        facultyLeave.setBackground(Color.WHITE);
        facultyLeave.addActionListener(this);
        leave.add(facultyLeave);

        // Student leave
        JMenuItem studentLeave = new JMenuItem("Student Leave");
        studentLeave.setBackground(Color.WHITE);
        studentLeave.addActionListener(this);
        leave.add(studentLeave);


        // --- 4. Leave Details ---
        JMenu leaveDetails = new JMenu("Leave Details");
        leaveDetails.setForeground(Color.BLACK);
        mb.add(leaveDetails);

        // Faculty leave details
        JMenuItem facultyLeaveDetails = new JMenuItem("Faculty Leave Details");
        facultyLeaveDetails.setBackground(Color.WHITE);
        facultyLeaveDetails.addActionListener(this);
        leaveDetails.add(facultyLeaveDetails);

        // Student leave details
        JMenuItem studentLeaveDetails = new JMenuItem("Student Leave Details");
        studentLeaveDetails.setBackground(Color.WHITE);
        studentLeaveDetails.addActionListener(this);
        leaveDetails.add(studentLeaveDetails);


        // --- 5. Examination ---
        JMenu exam = new JMenu("Examination");
        exam.setForeground(Color.BLACK);
        mb.add(exam);

        JMenuItem examinationDetails = new JMenuItem("Examination Details");
        examinationDetails.setBackground(Color.WHITE);
        examinationDetails.addActionListener(this);
        exam.add(examinationDetails);

        JMenuItem enterMarks = new JMenuItem("Enter Marks");
        enterMarks.setBackground(Color.WHITE);
        enterMarks.addActionListener(this);
        exam.add(enterMarks);


        // --- 6. Update Details ---
        JMenu updateInfo = new JMenu("Update Details");
        updateInfo.setForeground(Color.BLACK);
        mb.add(updateInfo);

        //Update Faculty Information
        JMenuItem updateFacultyInfo = new JMenuItem("Update Faculty Details");
        updateFacultyInfo.setBackground(Color.WHITE);
        updateFacultyInfo.addActionListener(this);
        updateInfo.add(updateFacultyInfo);

        //Update Student Information
        JMenuItem updateStudentInfo = new JMenuItem("Update Student Details");
        updateStudentInfo.setBackground(Color.WHITE);
        updateStudentInfo.addActionListener(this);
        updateInfo.add(updateStudentInfo);


        // --- 7. Fee Details ---
        JMenu fee = new JMenu("Fee Details");
        fee.setForeground(Color.BLACK);
        mb.add(fee);

        JMenuItem feeStructure = new JMenuItem("Fee Structure");
        feeStructure.setBackground(Color.WHITE);
        feeStructure.addActionListener(this);
        fee.add(feeStructure);

        JMenuItem feeForm = new JMenuItem("Student Fee Form");
        feeForm.setBackground(Color.WHITE);
        feeForm.addActionListener(this);
        fee.add(feeForm);


        // --- 8. Utility Section ---
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLACK);
        mb.add(utility);

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setBackground(Color.WHITE);
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setBackground(Color.WHITE);
        notepad.addActionListener(this);
        utility.add(notepad);

        // --- GPA Calculators ---
        JMenuItem sgpaCalcItem = new JMenuItem("SGPA Calculator");
        sgpaCalcItem.setBackground(Color.WHITE);
        sgpaCalcItem.addActionListener(this);
        utility.add(sgpaCalcItem);

        JMenuItem ygpaCalcItem = new JMenuItem("YGPA Calculator");
        ygpaCalcItem.setBackground(Color.WHITE);
        ygpaCalcItem.addActionListener(this);
        utility.add(ygpaCalcItem);

        JMenuItem cgpaCalcItem = new JMenuItem("CGPA Calculator");
        cgpaCalcItem.setBackground(Color.WHITE);
        cgpaCalcItem.addActionListener(this);
        utility.add(cgpaCalcItem);

        JMenuItem dgpaCalcItem = new JMenuItem("DGPA Calculator");
        dgpaCalcItem.setBackground(Color.WHITE);
        dgpaCalcItem.addActionListener(this);
        utility.add(dgpaCalcItem);

        JMenuItem sgpaToPer = new JMenuItem("SGPA/CGPA to Percentage Calculator");
        sgpaToPer.setBackground(Color.WHITE);
        sgpaToPer.addActionListener(this);
        utility.add(sgpaToPer);


        // --- 9. Achievement Section ---
        JMenu achievements = new JMenu("Achievements");
        achievements.setForeground(Color.BLACK);
        mb.add(achievements);

        JMenuItem viewAchievements = new JMenuItem("View Gallery");
        viewAchievements.setBackground(Color.WHITE);
        viewAchievements.addActionListener(this);
        achievements.add(viewAchievements);

        JMenuItem addAchievement = new JMenuItem("Add New Achievement");
        addAchievement.setBackground(Color.WHITE);
        addAchievement.addActionListener(this);
        achievements.add(addAchievement);


        // --- 10. Notice Section ---
        JMenu noticeMenu = new JMenu("Notices");
        noticeMenu.setForeground(Color.BLACK);
        mb.add(noticeMenu);

        JMenuItem viewNotices = new JMenuItem("View Notice Board");
        viewNotices.setBackground(Color.WHITE);
        viewNotices.addActionListener(this);
        noticeMenu.add(viewNotices);

        JMenuItem postNotice = new JMenuItem("Post New Notice");
        postNotice.setBackground(Color.WHITE);
        postNotice.addActionListener(this);
        noticeMenu.add(postNotice);


        // --- 11. About Section ---
        JMenu about = new JMenu("About");
        about.setForeground(Color.BLACK);
        mb.add(about);

        JMenuItem About = new JMenuItem("About Us");
        About.setBackground(Color.WHITE);
        About.addActionListener(this);
        about.add(About);


        // --- 12. Exit ---
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.BLACK);
        mb.add(exit);

        JMenuItem Exit = new JMenuItem("Exit");
        Exit.setBackground(Color.WHITE);
        Exit.addActionListener(this);
        exit.add(Exit);

        // --- Set Menu Bar & Frame Properties ---
        setJMenuBar(mb);
        setSize(1540, 850);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Good practice
    }
    static class SlidingImagePanel extends JPanel {
        private Image currentImage;
        private Image nextImage;
        private int offsetX = 0;
        private Timer slideTimer;
        private String currentText = ""; // Text to display after slide

        public SlidingImagePanel(Image firstImage) {
            setDoubleBuffered(true);
            setBackground(Color.BLACK);
            this.currentImage = firstImage;
        }

        public void setImages(Image current, Image next, String textAfterSlide) {
            this.currentImage = current;
            this.nextImage = next;
            this.offsetX = 0;
            this.currentText = ""; // hide text until slide finishes

            if (slideTimer != null && slideTimer.isRunning()) slideTimer.stop();

            slideTimer = new Timer(10, e -> {
                offsetX += 20;
                if (offsetX >= getWidth()) {
                    currentImage = nextImage;
                    nextImage = null;
                    offsetX = 0;

                    // Show text after slide completes
                    currentText = textAfterSlide;

                    slideTimer.stop();
                }
                repaint();
            });
            slideTimer.start();
        }

          @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (currentImage != null)
                g.drawImage(currentImage, -offsetX, 0, getWidth(), getHeight(), this);
            if (nextImage != null)
                g.drawImage(nextImage, getWidth() - offsetX, 0, getWidth(), getHeight(), this);

            // Draw text centered
            if (currentText != null && !currentText.isEmpty()) {
                g.setFont(new Font("Segoe UI", Font.BOLD, 48)); // larger font
                g.setColor(Color.YELLOW);

                // Measure the text
                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(currentText);
                int textHeight = fm.getHeight();

                // Calculate coordinates for centered text
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + fm.getAscent();

                g.drawString(currentText, x, y);
            }
        }

    }

    private void updateImage() {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(imagePaths[currentImageIndex]));
        int w = getWidth() > 0 ? getWidth() : 1000;
        int h = getHeight() > 0 ? getHeight() : 750;
        Image scaledImage = icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);

    }
    
    private void startImageLoop() {
        Timer timer = new Timer(5000, e -> {
            int nextIndex = (currentImageIndex + 1) % imagePaths.length;

            ImageIcon currentIcon = new ImageIcon(ClassLoader.getSystemResource(imagePaths[currentImageIndex]));
            Image current = currentIcon.getImage().getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon nextIcon = new ImageIcon(ClassLoader.getSystemResource(imagePaths[nextIndex]));
            Image next = nextIcon.getImage().getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);

            // pass the text to appear AFTER slide completes
            imagePanel.setImages(current, next, imageTexts[currentImageIndex]);

            currentImageIndex = nextIndex;
        });
        timer.start();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String sm = e.getActionCommand();

        // --- Main Section ---
        if (sm.equals("New Faculty Information")) {
            new AddFaculty();
        } else if (sm.equals("New Student Information")) {
            new AddStudent();
        } else if (sm.equals("View Faculty Details")) {
            new TeacherDetails();
        } else if (sm.equals("View Student Details")) {
            new StudentDetails();
        } else if (sm.equals("Faculty Leave")) {
            new TeacherLeave();
        } else if (sm.equals("Student Leave")) {
            new StudentLeave();
        } else if (sm.equals("Faculty Leave Details")) {
            new TeacherLeaveDetails();
        } else if (sm.equals("Student Leave Details")) {
            new StudentLeaveDetails();
        } else if (sm.equals("Update Faculty Details")) {
            new UpdateTeacher();
        } else if (sm.equals("Update Student Details")) {
            new UpdateStudent();
        } else if (sm.equals("Enter Marks")) {
            new EnterMarks();
        } else if (sm.equals("Examination Details")) {
            new ExaminationDetails();
        } else if (sm.equals("Fee Structure")) {
            new FeeStructure();
        } else if (sm.equals("Student Fee Form")) {
            new StudentFeeForm();
        }

        // --- Utility Section ---
        else if (sm.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (sm.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (sm.equals("SGPA Calculator")) {
            new SgpaCalc();
        } else if (sm.equals("YGPA Calculator")) {
            new YgpaCalc();
        } else if (sm.equals("CGPA Calculator")) {
            new CgpaCalc();
        } else if (sm.equals("DGPA Calculator")) {
            new DgpaCalc();
        } else if (sm.equals("SGPA/CGPA to Percentage Calculator")) {
            new SgpaToPercentageCalc();
        }

        // --- Achievement Section ---
        else if (sm.equals("View Gallery")) {
            new AchievementGallery();
        } else if (sm.equals("Add New Achievement")) {
            new AddAchievement();
        }

        // --- CORRECT PLACEMENT for Notice Logic ---
        else if (sm.equals("View Notice Board")) {
            new NoticeBoard();
        } else if (sm.equals("Post New Notice")) {
            new PostNotice();
        }
        // --- END CORRECTION ---

        // --- Other Sections ---
        else if (sm.equals("About Us")) {
            new About();
        } else if (sm.equals("Exit")) {
            // A cleaner way to exit
            // System.exit(0); // 0 usually indicates normal exit
            // Or dispose the window first if needed
            setVisible(false);
            dispose(); // Releases window resources
            System.exit(0);
        }
    } // <-- End of actionPerformed method

    public static void main(String[] args) {
        // Ensure Swing components are created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new main_class());
    }
} // <-- End of main_class
