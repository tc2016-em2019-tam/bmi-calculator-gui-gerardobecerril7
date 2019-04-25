package com.gerardobecerril;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Bmi extends JFrame {

    private double calculateBMI(double height, double weight, boolean isM) {
        if (isM) {
            return weight / (height * height) * 10000;
        } else {
            return weight / (height * height) * 703;
        }
    }

    private boolean isMetric = true;

    private String category(double i) {
        String cat = "obese";
        if (i < 18.5) {
            cat = "underweight";
        } else if (i >= 18.5 && i < 25) {
            cat = "normal weight";
        } else if (i >= 25 && i <= 29.9) {
            cat = "overweight";
        }
        return cat;
    }

    public Bmi() {
        this.setTitle("BMI Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());

        JRadioButton metricButton = new JRadioButton("Metric");
        JRadioButton imperialButton = new JRadioButton("Imperial");
        metricButton.setSelected(true);

        ButtonGroup buttonGroup = new ButtonGroup();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel label1 = new JLabel("Height (cm):");
        JTextField textfield1 = new JTextField("");
        JLabel label2 = new JLabel("Weight (kg):");
        JTextField textfield2 = new JTextField("");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1));

        JLabel label3 = new JLabel("                         ");
        JLabel label4 = new JLabel("                         ");

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 2));

        JButton button = new JButton("Calculate!");
        JButton button2 = new JButton("Clean.");

        buttonGroup.add(metricButton);
        buttonGroup.add(imperialButton);

        panel.add(metricButton);
        panel.add(imperialButton);
        panel.add(label1);
        panel.add(textfield1);
        panel.add(label2);
        panel.add(textfield2);

        panel2.add(label3);
        panel2.add(label4);

        panel3.add(button);
        panel3.add(button2);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double h = Double.parseDouble(textfield1.getText());
                double w = Double.parseDouble(textfield2.getText());
                double bmi = calculateBMI(h, w, isMetric);
                DecimalFormat df2 = new DecimalFormat("#.##");
                label3.setText("Your BMI is " + df2.format(bmi) + ".");
                label4.setText("Your category is: " + category(bmi) + ".");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textfield1.setText("");
                textfield2.setText("");
                label3.setText("                         ");
                label4.setText("                         ");
            }
        });

        metricButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMetric = true;
                label1.setText("Height (cm): ");
                label2.setText("Weight (kg): ");
            }
        });

        imperialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMetric = false;
                label1.setText("Height (in): ");
                label2.setText("Weight (lb): ");
            }
        });

        this.add(panel, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);

        this.setSize(500, 175);
        this.setVisible(true);
    }
}