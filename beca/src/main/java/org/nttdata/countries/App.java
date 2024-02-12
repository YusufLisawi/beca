package org.nttdata.countries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Objects;

public class App extends Frame {

    private Label lblName, lblCapital, lblPopulation, lblContinent;
    private TextField txtName, txtCapital, txtPopulation, txtContinent;
    private Button btnDelete, btnEdit, btnSave, btnAdd;
    private Button btnFirst, btnNext, btnPrevious, btnLast;
    private ArrayList<Country> lstCountries;

    private Panel pAction, pLabel, pText, pNav, pInputs;
    private int index = 0;
    private String mode = "";

    public App() {
        super("Atlas");
        FileManager fileManager = new FileManager("src/main/java/org/nttdata/countries/countries.txt");
        this.lstCountries = fileManager.getCountries();

        this.setSize(500, 200);
        this.setLayout(new BorderLayout());

        lblName = new Label("Name");
        lblCapital = new Label("Capital");
        lblPopulation = new Label("Population");
        lblContinent = new Label("Continent");

        txtName = new TextField("", 10);
        txtCapital = new TextField("", 10);
        txtPopulation = new TextField("", 10);
        txtContinent = new TextField("", 10);

        btnDelete = new Button("Delete");
        btnEdit = new Button("Edit");
        btnSave = new Button("Save");
        btnAdd = new Button("Add");

        btnFirst = new Button("First");
        btnNext = new Button("Next");
        btnPrevious = new Button("Previous");
        btnLast = new Button("Last");

        pAction = new Panel();
        pLabel = new Panel();
        pText = new Panel();
        pNav = new Panel();

        pAction.setLayout(new GridLayout(1, 4));
        pAction.add(btnAdd);
        pAction.add(btnEdit);
        pAction.add(btnSave);
        pAction.add(btnDelete);

        pLabel.setLayout(new GridLayout(4, 1));
        pText.setLayout(new GridLayout(4, 3));
        pNav.setLayout(new GridLayout(1, 4));


        pLabel.add(lblName);
        pText.add(txtName);
        pLabel.add(lblCapital);
        pText.add(txtCapital);
        pLabel.add(lblPopulation);
        pText.add(txtPopulation);
        pLabel.add(lblContinent);
        pText.add(txtContinent);

        pNav.add(btnFirst);
        pNav.add(btnPrevious);
        pNav.add(btnNext);
        pNav.add(btnLast);

        this.add(pAction, BorderLayout.NORTH);
        this.add(pLabel, BorderLayout.WEST);
        this.add(pText, BorderLayout.CENTER);
        this.add(pNav, BorderLayout.SOUTH);

        this.updateTexts();

        btnSave.disable();
        this.disableTexts();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = "add";
                clear();
                enableTexts();
                btnSave.enable();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(mode, "add")) {
                    Country tmp = new Country(txtName.getText(), txtCapital.getText(), Integer.parseInt(txtPopulation.getText()), txtContinent.getText());
                    lstCountries.add(tmp);
                } else if (Objects.equals(mode, "edit")) {
                    Country current = lstCountries.get(index);
                    current.setName(txtName.getText());
                    current.setCapital(txtCapital.getText());
                    current.setPopulation(Integer.parseInt(txtPopulation.getText()));
                    current.setContinent(txtContinent.getText());
                }
                btnSave.disable();
                disableTexts();
                fileManager.saveCountries();
                mode = "";
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < 0) return;
                int res = JOptionPane.showConfirmDialog(App.this, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    lstCountries.remove(index);
                    index = 0;
                    updateTexts();
                }
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = "edit";
                enableTexts();
                btnSave.enable();
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index == lstCountries.size() - 1) return;
                index++;
                updateTexts();
            }
        });

        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = 0;
                updateTexts();
            }
        });

        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = lstCountries.size() - 1;
                updateTexts();
            }
        });

        btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index == 0) return;
                index--;
                updateTexts();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int res = JOptionPane.showConfirmDialog(App.this, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    fileManager.saveCountries();
                    System.exit(0);
                }
            }
        });
        this.setVisible(true);
    }

    private void updateTexts() {
        try {
            Country country = lstCountries.get(index);
            txtName.setText(country.getName());
            txtCapital.setText(country.getCapital());
            txtPopulation.setText(Integer.toString(country.getPopulation()));
            txtContinent.setText(country.getContinent());
        } catch (IndexOutOfBoundsException e) {
           clear();
        }
    }

    private void clear() {
        txtName.setText("");
        txtCapital.setText("");
        txtPopulation.setText("");
        txtContinent.setText("");
    }

    private void disableTexts() {
        txtName.setEditable(false);
        txtCapital.setEditable(false);
        txtPopulation.setEditable(false);
        txtContinent.setEditable(false);
        toggleBtns(false);
    }

    private void enableTexts() {
        txtName.setEditable(true);
        txtCapital.setEditable(true);
        txtPopulation.setEditable(true);
        txtContinent.setEditable(true);
        toggleBtns(true);
    }

    private void toggleBtns(boolean toggle) {
        if (toggle) {
            btnAdd.disable();
            btnDelete.disable();
            btnEdit.disable();
        } else {
            btnAdd.enable();
            btnDelete.enable();
            btnEdit.enable();
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
