package aya;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class Gui extends Frame {
    private final Functional f;
    private JFrame answer1Frame;
    private JFrame answer2Frame;
    private JFrame answer3Frame;

    private void mkAnwser1() {
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
        answer1Frame = new JFrame("Answer1");
        answer1Frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        answer1Frame.setSize(300, 380); // size of window
        // scrollPane.setOpaque(true);
        answer1Frame.setContentPane(scrollPane);
    }

    private void mkAnswer2(){
        DefaultTableModel m=new DefaultTableModel();
        Label label=new Label("Answer2");
        final JTextField jTextField=new JTextField(20);
        JButton button = new JButton("View");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mkNewAnswer2WeekData(jTextField.getText());
                mkNewAnswersMonthData(jTextField.getText());
            }
        });
        var p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(label);
        p.add(jTextField);
        p.add(button);
        answer2Frame = new JFrame("Answer2");
        answer2Frame.setSize(300, 300);
        answer2Frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        answer2Frame.setContentPane(p);
    }

    private void mkNewAnswer2WeekData(String country) {
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("Year/week");
        m.addColumn("Confirmed");

        var data = f.task2Week(country);
        for (var i : data) {
            m.addRow(i);
        }
        var table = new JTable(m);
        table.setPreferredScrollableViewportSize(new Dimension(70, 70)); // size of table
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setFillsViewportHeight(true);
        var f = new JFrame("Answer2 Week");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(300, 300);
        f.setContentPane(scrollPane);
        f.setVisible(true);
    }

    private void mkNewAnswersMonthData(String country) {
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("Year/month");
        m.addColumn("Confirmed");

        var data = f.task2Month(country);
        for (var i : data) {
            m.addRow(i);
        }
        var table = new JTable(m);
        table.setPreferredScrollableViewportSize(new Dimension(70, 70)); // size of table
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setFillsViewportHeight(true);
        var f = new JFrame("Answer2 Month");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(300, 300);
        f.setContentPane(scrollPane);
        f.setVisible(true);
    }

    private void mkAnswer3() {
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("Country");
        m.addColumn("Lowest death");
        m.addColumn("Highest death");
        m.addColumn("Lowest recovered");
        m.addColumn("Highest recovered");

        var res = f.task3();
        var m1 = res.get(0);
        var m2 = res.get(1);
        m1.forEach((k, v) -> {
            Object[] o = { k, v[0], v[1], m2.get(k)[0], m2.get(k)[1] };
            m.addRow(o);
        });
        JTable table = new JTable(m);
        table.setPreferredScrollableViewportSize(new Dimension(70, 70)); // size of table
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        answer3Frame = new JFrame("Answer3");
        answer3Frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        answer3Frame.setSize(600, 380); // size of window
        answer3Frame.setContentPane(scrollPane);
    }

    private void mkAnswer4(String country) {
        Integer[] res = Arrays.stream(f.task4(country)).boxed().toArray(Integer[]::new);
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("Confirmed");
        m.addColumn("Death");
        m.addColumn("Recovered");
        m.addRow(res);

        JTable table = new JTable(m);
        table.setPreferredScrollableViewportSize(new Dimension(70, 70)); // size of table
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        var f = new JFrame();

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setContentPane(scrollPane);
        f.setSize(300, 380);
        f.setVisible(true);
    }

    private void mkAnswer5Or6(int n) {
        var l = f.task5();
        if (n == 5) {
            l = f.task5();
        } else {
            l = f.task6();
        }
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("Country");
        m.addColumn("Confirm");
        l.forEach(m::addRow);
        JTable table = new JTable(m);
        table.setPreferredScrollableViewportSize(new Dimension(70, 70)); // size of table
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        var f = new JFrame("Answer" + (n == 5 ? "5" : "6"));
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(300, 380); // size of window
        f.setContentPane(scrollPane);
        f.setVisible(true);
    }

    public Gui(Functional f) {
        this.f = f;
        mkAnwser1();
        mkAnswer2();
        mkAnswer3();
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
        JButton button2 = new JButton("Answer 2");
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
                answer1Frame.setVisible(true);
            }
        });
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                answer2Frame.setVisible(true);
            }
        });
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                answer3Frame.setVisible(true);
            }
        });
        button4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                var country = textField.getText();
                mkAnswer4(country);
            }
        });
        button5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mkAnswer5Or6(5);
            }
        });
        button6.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mkAnswer5Or6(6);
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