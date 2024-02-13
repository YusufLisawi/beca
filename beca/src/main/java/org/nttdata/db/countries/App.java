package org.nttdata.db.countries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class App extends Frame {

    private Label lblName, lblCapital, lblPopulation, lblContinent;
    private TextField txtName, txtCapital, txtPopulation, txtContinent;
    private Button btnDelete, btnEdit, btnSave, btnAdd;
    private Button btnFirst, btnNext, btnPrevious, btnLast;
    private Panel pAction, pLabel, pText, pNav;

    private ResultSet resultSet;

    private final Statement st;

    private String mode = "";

    public App() throws SQLException {
        super("Atlas");
        Connection conn = DataBaseManager.connect();
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        this.setSize(500, 200);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.initUI();
        this.toggleBtns(false);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = "add";
                txtName.requestFocus();
                clear();
                toggleBtns(true);
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = "edit";
                txtName.requestFocus();
                toggleBtns(true);
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    try {
                        if (mode.equals("add")) {
                            String query = "INSERT INTO country (name, capital, population, continent) VALUES (?,?,?,?)";
                            PreparedStatement pst = conn.prepareStatement(query);
                            pst.setString(1, txtName.getText());
                            pst.setString(2, txtCapital.getText());
                            pst.setInt(3, Integer.parseInt(txtPopulation.getText()));
                            pst.setString(4, txtContinent.getText());
                            pst.executeUpdate();
                            resultSet = st.executeQuery("SELECT * FROM country");
                            resultSet.next();
                        } else if (mode.equals("edit")) {
                            String query = "UPDATE country SET name=?, capital=?, population=?, continent=? WHERE id=?";
                            PreparedStatement pst = conn.prepareStatement(query);
                            pst.setString(1, txtName.getText());
                            pst.setString(2, txtCapital.getText());
                            pst.setInt(3, Integer.parseInt(txtPopulation.getText()));
                            pst.setString(4, txtContinent.getText());
                            pst.setInt(5, resultSet.getInt("id"));
                            pst.executeUpdate();
                            resultSet = st.executeQuery("SELECT * FROM country");
                            resultSet.next();
                        }
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }

                toggleBtns(false);
                mode = "";
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(App.this, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    try {
                        st.executeUpdate("DELETE FROM country WHERE id='" + resultSet.getInt("id") + "'");
                        resultSet = st.executeQuery("SELECT * FROM country");
                        resultSet.next();
                        updateTexts();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.first();
                    updateTexts();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.last();
                    updateTexts();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!resultSet.isLast() && resultSet.next())
                        updateTexts();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!resultSet.isFirst() && resultSet.previous())
                        updateTexts();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                try {
                    resultSet = st.executeQuery("SELECT * FROM country");
                    resultSet.next();
                    updateTexts();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    private void updateTexts() {
        try {
            txtName.setText(resultSet.getString("name"));
            txtCapital.setText(resultSet.getString("capital"));
            txtPopulation.setText(Integer.toString(resultSet.getInt("population")));
            txtContinent.setText(resultSet.getString("continent"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void clear() {
        txtName.setText("");
        txtCapital.setText("");
        txtPopulation.setText("");
        txtContinent.setText("");
    }

    private void toggleBtns(boolean toggle) {
        btnAdd.setEnabled(!toggle);
        btnDelete.setEnabled(!toggle);
        btnEdit.setEnabled(!toggle);
        btnSave.setEnabled(toggle);

        btnLast.setEnabled(!toggle);
        btnNext.setEnabled(!toggle);
        btnPrevious.setEnabled(!toggle);
        btnFirst.setEnabled(!toggle);

        txtName.setEditable(toggle);
        txtCapital.setEditable(toggle);
        txtPopulation.setEditable(toggle);
        txtContinent.setEditable(toggle);
    }

    private void initUI() {

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
        pLabel.setLayout(new GridLayout(4, 1));
        pText.setLayout(new GridLayout(4, 1));
        pNav.setLayout(new GridLayout(1, 4));

        pAction.add(btnAdd);
        pAction.add(btnEdit);
        pAction.add(btnSave);
        pAction.add(btnDelete);

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
    }

    public static void main(String[] args) throws SQLException {
        new App();
    }
}
