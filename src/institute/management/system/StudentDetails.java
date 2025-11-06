package institute.management.system;

import com.mysql.cj.protocol.Resultset;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class StudentDetails extends JFrame implements ActionListener {

    Choice choice;
    JTable table;
    JButton search, print, update, add, cancel, delete;

    StudentDetails() {
        getContentPane().setBackground(new Color(186, 248, 125));

        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        choice = new Choice();
        choice.setBounds(180, 20, 150, 20);
        add(choice);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student");
            while (resultSet.next()) {
                choice.add(resultSet.getString("rollNo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception E) {
            E.printStackTrace();
        }

        JScrollPane js = new JScrollPane(table);
        js.setBounds(0, 100, 900, 600);
        add(js);

        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        add = new JButton("Add");
        add.setBounds(220, 70, 80, 20);
        add.addActionListener(this);
        add(add);

        update = new JButton("Update");
        update.setBounds(320, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        delete = new JButton("Delete");
        delete.setBounds(420, 70, 80, 20);
        delete.addActionListener(this);
        add(delete);

        cancel = new JButton("Cancel");
        cancel.setBounds(520, 70, 80, 20);
        cancel.addActionListener(this);
        add(cancel);


        setLayout(null);
        setSize(900, 500);
        setLocation(250, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String q = "select * from student where rollNo = '" + choice.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == add) {
            setVisible(false);
            new AddStudent();
        } else if (e.getSource() == update) {
            // yet to be done
            new UpdateStudent();
        } else if (e.getSource() == delete) {
            String selectedRollNo = choice.getSelectedItem();

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete student with Roll No: " + selectedRollNo + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String q = "delete from student where rollNo = '" + selectedRollNo + "'";
                try {
                    Conn c = new Conn();
                    c.statement.executeUpdate(q);

                    // Refresh the table after deletion
                    ResultSet resultSet = c.statement.executeQuery("select * from student");
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));

                    // Refresh the choice dropdown
                    choice.removeAll();
                    ResultSet rs = c.statement.executeQuery("select rollNo from student");
                    while (rs.next()) {
                        choice.add(rs.getString("rollNo"));
                    }

                    JOptionPane.showMessageDialog(null, "Student with Roll No " + selectedRollNo + " deleted successfully");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else { // cancel
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentDetails();
    }
}
