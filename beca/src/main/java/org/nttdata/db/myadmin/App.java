package org.nttdata.db.myadmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class App extends Frame implements ActionListener {
    Choice sqlServerChoice, databaseChoice;
    TextArea queryTextArea, resultTextArea;
    List tablesList;
    Button executeButton;

    private Connection conn;
    private Statement st;

    public App() throws SQLException {
        super("MyAdmin");
        conn = DataBaseManager.connect();
        st = conn.createStatement();

        setSize(650, 400);
        setLayout(new BorderLayout());

        Panel serverPanel = new Panel(new FlowLayout());

        sqlServerChoice = new Choice();
        sqlServerChoice.add("MySQL");

        serverPanel.add(new Label("SQL Server:"));
        serverPanel.add(sqlServerChoice);

        databaseChoice = new Choice();
        databaseChoice.add("None");

        serverPanel.add(new Label("Database:"));
        serverPanel.add(databaseChoice);

        tablesList = new List(3);
        tablesList.setFocusable(false);

        serverPanel.add(new Label("Tables:"));
        serverPanel.add(tablesList);

        queryTextArea = new TextArea(5, 40);

        executeButton = new Button("Execute");
        executeButton.addActionListener(this);

        resultTextArea = new TextArea(10, 40);
        resultTextArea.setEditable(false);

        add(serverPanel, BorderLayout.NORTH);
        add(queryTextArea, BorderLayout.CENTER);

        Panel bottomPanel = new Panel(new BorderLayout());
        bottomPanel.add(executeButton, BorderLayout.NORTH);
        bottomPanel.add(resultTextArea, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        initData();

        sqlServerChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                System.out.println("sql server changed");
            }
        });

        databaseChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                tablesList.removeAll();
                if (databaseChoice.getSelectedItem().equals("None")) return;
                try {
                    st.execute("USE " + databaseChoice.getSelectedItem() + ";");
                    ResultSet rs = st.executeQuery("SHOW TABLES;");
                    while (rs.next()) {
                        tablesList.add(rs.getString(1));
                    }
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != executeButton) return;

        String query = queryTextArea.getText().toLowerCase().trim();
        try {
            if (query.startsWith("show")) {
                ResultSet rs = st.executeQuery(query);
                StringBuilder stringBuilder = new StringBuilder();
                while (rs.next()) {
                    stringBuilder.append(rs.getString(1)).append("\n");
                }
                resultTextArea.setText(stringBuilder.toString());
                rs.close();
            }
            else if (st.execute(query)) {
                ResultSet rs = st.getResultSet();
                ResultSetMetaData rsmd = rs.getMetaData();
                int nColumns = rsmd.getColumnCount();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i < nColumns; i++) {
                    stringBuilder.append(rsmd.getColumnLabel(i)).append("\t|\t");
                }
                stringBuilder.append("\n");
                while (rs.next()) {
                    for (int i = 1; i < nColumns; i++) {
                        stringBuilder.append(rs.getString(i)).append("\t|\t");
                    }
                    stringBuilder.append("\n");
                }
                resultTextArea.setText(stringBuilder.toString());
                rs.close();
            } else {
                int linesAffected = st.getUpdateCount();
                resultTextArea.setText(linesAffected + " Lines have been " + (query.startsWith("delete") ? "deleted" : "updated"));
            }
        } catch (SQLException ex) {
            resultTextArea.setText(ex.getMessage());
        }

        queryTextArea.setText("");
    }

    private void initData() {
        try {
            ResultSet rs = st.executeQuery("SHOW DATABASES;");
            while (rs.next()) {
                databaseChoice.add(rs.getString(1));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        new App();
    }
}
