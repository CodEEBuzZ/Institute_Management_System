package institute.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {
     private void addNameLabel(String personName, int yPosition) {
        JLabel nameLabel = new JLabel(personName);
        nameLabel.setBounds(70, yPosition, 580, 40);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        getContentPane().add(nameLabel);
    }

    About(){

        setTitle("About");
        setLayout(null);
        setSize(800, 650);
        setLocation(250, 50);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/CollegeGate.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(300,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(400,20,350,220);
        add(img);

        JLabel heading = new JLabel("GCETTS");
        heading.setBounds(70,40,300,50);
        heading.setFont(new Font("Tahoma",Font.BOLD,32));
        add(heading);

        addNameLabel("Atirath Pal", 220);
        addNameLabel("Saikat Munshib", 280);
        addNameLabel("Choiti Chatterjee", 340);
        addNameLabel("Namrata Ghosh", 400);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new About();
    }
}
