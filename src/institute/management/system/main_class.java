package institute.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener {

    main_class() {
        // --- Setup Background Image ---
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1540, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        add(img);

        // --- Create Menu Bar ---
        JMenuBar mb = new JMenuBar();

        // --- 1. New Information ---
        JMenu newInfo = new JMenu("New Information");
        newInfo.setForeground(Color.BLACK);
        mb.add(newInfo);

        JMenuItem facultyInfo = new JMenuItem("New Faculty Information");
        facultyInfo.setBackground(Color.WHITE);
        facultyInfo.addActionListener(this);
        newInfo.add(facultyInfo);

        JMenuItem studentInfo = new JMenuItem("New Student Information");
        studentInfo.setBackground(Color.WHITE);
        studentInfo.addActionListener(this);
        newInfo.add(studentInfo);


        // --- 2. View Details ---
        JMenu details = new JMenu("View Details");
        details.setForeground(Color.BLACK);
        mb.add(details);

        JMenuItem facultyDetails = new JMenuItem("View Faculty Details");
        facultyDetails.setBackground(Color.WHITE);
        facultyDetails.addActionListener(this);
        details.add(facultyDetails);

        JMenuItem studentDetails = new JMenuItem("View Student Details");
        studentDetails.setBackground(Color.WHITE);
        studentDetails.addActionListener(this);
        details.add(studentDetails);


        // --- 3. Apply Leave ---
        JMenu leave = new JMenu("Apply Leave");
        leave.setForeground(Color.BLACK);
        mb.add(leave);

        JMenuItem facultyLeave = new JMenuItem("Faculty Leave");
        facultyLeave.setBackground(Color.WHITE);
        facultyLeave.addActionListener(this);
        leave.add(facultyLeave);

        JMenuItem studentLeave = new JMenuItem("Student Leave");
        studentLeave.setBackground(Color.WHITE);
        studentLeave.addActionListener(this);
        leave.add(studentLeave);


        // --- 4. Leave Details ---
        JMenu leaveDetails = new JMenu("Leave Details");
        leaveDetails.setForeground(Color.BLACK);
        mb.add(leaveDetails);

        JMenuItem facultyLeaveDetails = new JMenuItem("Faculty Leave Details");
        facultyLeaveDetails.setBackground(Color.WHITE);
        facultyLeaveDetails.addActionListener(this);
        leaveDetails.add(facultyLeaveDetails);

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

        JMenuItem updateFacultyInfo = new JMenuItem("Update Faculty Details");
        updateFacultyInfo.setBackground(Color.WHITE);
        updateFacultyInfo.addActionListener(this);
        updateInfo.add(updateFacultyInfo);

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