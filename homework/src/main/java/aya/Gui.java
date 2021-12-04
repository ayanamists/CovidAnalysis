package aya;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Gui extends Frame {
    private final Functional f;
    private JFrame anwser1Frame;
    private void mkAnwser1() {
        String[]columnNames={};

        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("Country");
        m.addColumn("Confirm");
        f.task1().forEach((k, v) -> {
            Object[] arr = { k, v };
            m.addRow(arr);
        });
        JTable table = new JTable(m);
        table.setPreferredScrollableViewportSize(new Dimension(70, 70)); // size of table
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        anwser1Frame = new JFrame("Anwser1");
        anwser1Frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        anwser1Frame.setSize(300, 380); // size of window
        // scrollPane.setOpaque(true);
        anwser1Frame.setContentPane(scrollPane);
    }
    private JFrame answer2Frame;
    private JFrame answer3Frame;
    private void mkAnswer2(){
        String[]columnNames={};
        DefaultTableModel m=new DefaultTableModel();
        // only create a jrame for search in this function
        Label label=new Label("Search: ");
        final JTextField jTextField=new JTextField(20);
        jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        m.addColumn("Country");
        m.addColumn("Year");
        m.addColumn("Month");

        answer2Frame=new JFrame("Answer2");
        answer2Frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        answer2Frame.setSize(300, 380); // size of window
        // scrollPane.setOpaque(true);

        answer3Frame=new JFrame("Answer3");
        answer3Frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        answer3Frame.setSize(300, 380); // size of window
        // scrollPane.setOpaque(true);
    }

    private void mkNewAnswer2WeekData(String country) {
    }

    private void mkNewAnswersMonthData(String country) {
    }


    public Gui(Functional f) {
        this.f = f;
        mkAnwser1();
        JFrame homePage = new JFrame("Advanced Programming");

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxlayout);

        JLabel label1 = new JLabel("Total confirmed Covid-19 cases.");
        label1.setFont(new Font("Serif", Font.PLAIN, 20));
        JButton button1 = new JButton("Answer 1");

        JLabel label2 = new JLabel("Weekly and Monthly Cases");
        label2.setFont(new Font("Serif", Font.PLAIN, 20));
        JButton button2 = new JButton("Search");
        JTextField textField2 = new JTextField(16);

        JLabel label3 = new JLabel("Total highest/lowest death and recovered Covid-19 cases");
        label3.setFont(new Font("Serif", Font.PLAIN, 20));
        JButton button3 = new JButton("Answer 3");

        JLabel label4 = new JLabel("Search by country");
        label4.setFont(new Font("Serif", Font.PLAIN, 20));
        JButton button4 = new JButton("Search");
        JTextField textField = new JTextField(16);

        JLabel label5 = new JLabel("Ascending order of cofirmed Covid-19 cases");
        label3.setFont(new Font("Serif", Font.PLAIN, 20));
        JButton button5 = new JButton("Section 2");

        JLabel label6 = new JLabel("Descending order of cofirmed Covid-19 cases");
        label3.setFont(new Font("Serif", Font.PLAIN, 20));
        JButton button6 = new JButton("Section 2(1)");

        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                anwser1Frame.setVisible(true);
            }
        });
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            }
        });
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Button 3 Clicked");
            }
        });
        button4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Button 4 Clicked");
            }
        });
        button5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Button 5 Clicked");
            }
        });
        button6.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Button 6 Clicked");
            }
        });

        panel1.setBorder(BorderFactory.createTitledBorder("Question 1"));
        panel1.add(label1);
        panel1.add(button1);
        panel2.setBorder(BorderFactory.createTitledBorder("Question 2"));
        panel2.add(label2);
        panel4.add(textField);
        panel2.add(button2);
        panel3.setBorder(BorderFactory.createTitledBorder("Question 3"));
        panel3.add(label3);
        panel3.add(button3);
        panel4.setBorder(BorderFactory.createTitledBorder("Question 4"));
        panel4.add(label4);
        panel4.add(textField);
        panel4.add(button4);
        panel5.setBorder(BorderFactory.createTitledBorder("Question 5"));
        panel5.add(label5);
        panel5.add(button5);
        panel6.setBorder(BorderFactory.createTitledBorder("Question 6"));
        panel6.add(label6);
        panel6.add(button6);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(panel6);

        homePage.add(panel);
        homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homePage.setSize(600,600);
        homePage.setLocationRelativeTo(null);
        homePage.setVisible(true);

    }

    public static void main(String[] args)  {
        try {
            var f = new Functional();
            Gui homePage = new Gui(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}