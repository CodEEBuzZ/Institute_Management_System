package institute.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class TeacherDetails extends JFrame implements ActionListener {
    Choice choice;
    JTable table;
    JButton search, print, update, add, cancel, delete;

    TeacherDetails() {
        getContentPane().setBackground(new Color(186,248, 125));

        JLabel heading = new JLabel("Search by Employee ID");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        choice = new Choice();
        choice.setBounds(180, 20, 150, 20);
        add(choice);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from teacher");
            while (resultSet.next()) {
                choice.add(resultSet.getString("empID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from teacher");
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
        setSize(910,500);
        setLocation(250, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String q = "select * from teacher where empID = '" + choice.getSelectedItem() + "'";
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
            new AddFaculty();
        } else if (e.getSource() == update) {
            // yet to be done
            new UpdateTeacher();
        } else if (e.getSource() == delete) {
            String selectedEmpID = choice.getSelectedItem();

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete teacher with Employee ID: " + selectedEmpID + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String q = "delete from teacher where empID = '" + selectedEmpID + "'";
                try {
                    Conn c = new Conn();
                    c.statement.executeUpdate(q);

                    // Refresh the table after deletion
                    ResultSet resultSet = c.statement.executeQuery("select * from teacher");
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));

                    // Refresh the choice dropdown
                    choice.removeAll();
                    ResultSet rs = c.statement.executeQuery("select empID from teacher");
                    while (rs.next()) {
                        choice.add(rs.getString("empID"));
                    }

                    JOptionPane.showMessageDialog(null, "Teacher with Employee ID " + selectedEmpID + " deleted successfully");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else { // cancel
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }
}
