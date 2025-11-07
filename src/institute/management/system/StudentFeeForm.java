package institute.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class StudentFeeForm extends JFrame implements ActionListener{

    Choice cRollNumber;
    JComboBox courseBox, departmentBox, semesterBox;
    JLabel totalAmount;
    JButton update, pay, cancel;

    StudentFeeForm(){

        getContentPane().setBackground( Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/fees_image.png"));
        Image i2 = i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(400,50,500,300);
        add(img);

        JLabel rollNumber = new JLabel("Select Roll Number");
        rollNumber.setBounds(40,60,150,20);
        rollNumber.setFont(new Font("Tahoma",Font.BOLD,12));
        add(rollNumber);

        cRollNumber = new Choice();
        cRollNumber.setBounds(200,60,150,20);
        add(cRollNumber);

        try{
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("select * from student");
            while (rs.next()){
                cRollNumber.add(rs.getString("rollNo"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        JLabel name = new JLabel("Name");
        name.setBounds(40,100,150,20);
        add(name);

        JLabel textName = new JLabel();
        textName.setBounds(200,100,150,20);
        add(textName);

        JLabel fatherName = new JLabel("Father's Name");
        fatherName.setBounds(40,140,150,20);
        add(fatherName);

        JLabel textFatherName = new JLabel();
        textFatherName.setBounds(200,140,150,20);
        add(textFatherName);

        try{
            Conn c = new Conn();
            String q = "select * from student where rollNo = '"+cRollNumber.getSelectedItem()+"'";
            ResultSet resultSet = c.statement.executeQuery(q);
            while(resultSet.next()){
                textName.setText(resultSet.getString("name"));
                textFatherName.setText(resultSet.getString("fname"));

            }
        }catch (Exception E){
            E.printStackTrace();
        }

        cRollNumber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    Conn c = new Conn();
                    String q = "select * from student where rollNo = '"+cRollNumber.getSelectedItem()+"'";
                    ResultSet resultSet = c.statement.executeQuery(q);
                    while(resultSet.next()){
                        textName.setText(resultSet.getString("name"));
                        textFatherName.setText(resultSet.getString("fname"));

                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });


        JLabel Qualification = new JLabel("Course");
        Qualification.setBounds(40,180,150,20);
//        Qualification.setFont(new Font("Tahoma",Font.BOLD,16));
        add(Qualification);

        String course[] = {"BTech","MTech","BA","MA","BSC","MSC"};
        courseBox = new JComboBox(course);
        courseBox.setBounds(200,180,150,20);
        courseBox.setBackground(Color.WHITE);
        add(courseBox);

        JLabel Department = new JLabel("Branch");
        Department.setBounds(40,220,150,20);
//        Department.setFont(new Font("serif",Font.BOLD,16));
        add(Department);

        String department[] = {"Computer Science", "Electrical", "Mechanical", "Civil", "Information Technology"};
        departmentBox = new JComboBox(department);
        departmentBox.setBounds(200,220,150,20);
        departmentBox.setBackground(Color.WHITE);
        add(departmentBox);

        JLabel textSemester = new JLabel("Semester");
        textSemester.setBounds(40,260,150,20);
        add(textSemester);

        String semester[] = {"Semester 1","Semester 2","Semester 3","Semester 4","Semester 5","Semester 6","Semester 7","Semester 8"};
        semesterBox = new JComboBox(semester);
        semesterBox.setBounds(200,260,150,20);
        add(semesterBox);

        JLabel total = new JLabel("Total Payable");
        total.setBounds(40,300,150,20);
        add(total);

        totalAmount = new JLabel();
        totalAmount.setBounds(200,300,150,20);
        add(totalAmount);

        update = new JButton("Update");
        update.setBounds(30,380,100,25);
        update.addActionListener(this);
        add(update);

        pay = new JButton("Pay");
        pay.setBounds(150,380,100,25);
        pay.addActionListener(this);
        add(pay);

        cancel = new JButton("Cancel");
        cancel.setBounds(270,380,100,25);
        cancel.addActionListener(this);
        add(cancel);


        setSize(910,500);
        setLayout(null);
        setLocation(250,50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==update){
            String course = (String) courseBox.getSelectedItem();
            String semester = (String) semesterBox.getSelectedItem();
            try{
                Conn c =new Conn();
                String q = "select `" + semester + "` from fee where course = '" + course + "'";
                ResultSet resultSet = c.statement.executeQuery(q);
                while (resultSet.next()){
                    totalAmount.setText(resultSet.getString(semester));
                }
            }catch (Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource()==pay){
            String rollNo = cRollNumber.getSelectedItem();
            String course = (String) courseBox.getSelectedItem();
            String semester = (String) semesterBox.getSelectedItem();
            String branch = (String) departmentBox.getSelectedItem();
            String total = totalAmount.getText();

            try{
                Conn c = new Conn();
                String q = "insert into feeCollege values('"+rollNo+"', '"+course+"', '"+branch+"', '"+semester+"', '"+total+"')";
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Fee Submitted Successfully");
            }catch (Exception E){
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentFeeForm();
    }
}
