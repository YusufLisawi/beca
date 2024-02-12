package org.nttdata.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator extends Frame {
    Label labelNumber1, labelNumber2, labelResult;
    TextField textNumber1, textNumber2, textResult;
    Button buttonCalculate;

    public Calculator() {
        super("Calculator");
        this.setSize(500, 200);
        labelNumber1 = new Label("Number");
        labelNumber2 = new Label("Number");
        labelResult = new Label("Result");
        textNumber1 = new TextField("", 10);
        textNumber2 = new TextField("", 10);
        textResult = new TextField("", 10);
        buttonCalculate = new Button("Calculate");
//        this.setLayout(null);
        this.setLayout(new FlowLayout());
//        this.setLayout(new BorderLayout());
//        this.setLayout(new GridLayout());
//        this.setLayout(new CardLayout());
//        this.setLayout(new GridBagLayout());
        this.add(labelNumber1);
        this.add(textNumber1);
        this.add(labelNumber2);
        this.add(textNumber2);
        this.add(buttonCalculate);
        this.add(labelResult);
        this.add(textResult);
        buttonCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n1, n2;
                n1 = Integer.parseInt(textNumber1.getText());
                n2 = Integer.parseInt(textNumber2.getText());
                textResult.setText(Integer.toString(n1 + n2));
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
