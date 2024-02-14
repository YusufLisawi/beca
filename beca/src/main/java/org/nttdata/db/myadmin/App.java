package org.nttdata.db.myadmin;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Objects;

public class App extends JFrame implements ActionListener {

    JTextField txtHost, txtUsername, txtPassword;
    JButton btnConnect;
    JComboBox<String> databaseChoice;
    JTextArea queryTextArea, resultTextArea;
    JList<String> tablesList;
    JButton executeButton;

    private Connection conn;
    private Statement st;

    public App() throws SQLException {
        super("MyAdmin");
        conn = null;
        st = null;
        setSize(700, 500);
        setLayout(new BorderLayout());

        txtHost = new JTextField(10);
        txtUsername = new JTextField(10);
        txtPassword = new JPasswordField(10);
        btnConnect = new JButton("Connect");

        txtHost.setText("localhost");
        txtUsername.setText("yusuf");
        txtPassword.setText("DB_sql_0000");

        JPanel connectionPanel = new JPanel();
        connectionPanel.add(new JLabel("Host:"));
        connectionPanel.add(txtHost);
        connectionPanel.add(new JLabel("Username:"));
        connectionPanel.add(txtUsername);
        connectionPanel.add(new JLabel("Password:"));
        connectionPanel.add(txtPassword);
        connectionPanel.add(btnConnect);

        btnConnect.addActionListener(this);

        JPanel serverPanel = new JPanel(new FlowLayout());

        databaseChoice = new JComboBox<>();
        databaseChoice.addItem("None");

        serverPanel.add(new JLabel("Database:"));
        serverPanel.add(databaseChoice);

        tablesList = new JList<>();
        tablesList.setFocusable(false);

        serverPanel.add(new JLabel("Tables:"));
        serverPanel.add(new JScrollPane(tablesList));

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));
        northPanel.add(connectionPanel);
        northPanel.add(serverPanel);

        add(northPanel, BorderLayout.NORTH);

        queryTextArea = new JTextArea(5, 40);

        executeButton = new JButton("Execute");
        executeButton.addActionListener(this);

        resultTextArea = new JTextArea(10, 40);
        resultTextArea.setEditable(false);

        add(new JScrollPane(queryTextArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(executeButton, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        databaseChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                tablesList.removeAll();
                if (Objects.equals(databaseChoice.getSelectedItem(), "None")) return;
                try {
                    st.execute("USE " + databaseChoice.getSelectedItem() + ";");
                    ResultSet rs = st.executeQuery("SHOW TABLES;");
                    DefaultListModel<String> model = new DefaultListModel<>();
                    while (rs.next()) {
                        model.addElement(rs.getString(1));
                    }
                    tablesList.setModel(model);
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println("from db choice : " + ex.getMessage());
                }
            }
        });

        tablesList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg) {
                if (!arg.getValueIsAdjusting()) {
                    String result;
                    try {
                        st.execute("DESCRIBE " + tablesList.getSelectedValue() + ";");
                        result = getQueryResult();
                    } catch (SQLException e) {
                        result = "error describe : " + e.getMessage();
                    }
                    resultTextArea.setText(result);
                }
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConnect) {
            String host = txtHost.getText();
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            try {
                connectDB(host, username,password);
                st = conn.createStatement();
                initData();
            } catch (SQLException ex) {
                resultTextArea.setText(ex.getMessage());
            }
        };
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
                String result = getQueryResult();
                resultTextArea.setText(result);
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
                databaseChoice.addItem(rs.getString(1));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void connectDB(String host, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + host, username, password);
            resultTextArea.setText("Connected to the mysql server successfully.");
        } catch (SQLException e) {
            resultTextArea.setText(e.getMessage());
        } catch (ClassNotFoundException e) {
            resultTextArea.setText("Class not found " + e.getMessage());
        }
    }

    public String getQueryResult() throws SQLException {
        ResultSet rs = st.getResultSet();
        ResultSetMetaData rsmd = rs.getMetaData();
        int nColumns = rsmd.getColumnCount();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < nColumns; i++) {
            stringBuilder.append(rsmd.getColumnLabel(i)).append("\t");
        }
        stringBuilder.append("\n");
        while (rs.next()) {
            for (int i = 1; i < nColumns; i++) {
                stringBuilder.append(rs.getString(i)).append("\t");
            }
            stringBuilder.append("\n");
        }
        rs.close();
        return stringBuilder.toString();
    }

        public static void main(String[] args) throws SQLException {
        new App();
    }
}
