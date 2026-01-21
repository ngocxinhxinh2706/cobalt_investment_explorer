import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Cobalt {

    private DataSource dataSource = null;
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    JTable table;

    public Cobalt() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL("jdbc:mysql://localhost/cobalt");
        ds.setUser("root");
        ds.setPassword("Epilogipromnight30062019");
        this.dataSource = ds;
    }

    public void loadData(String query) throws Exception {
        try {
            connect = dataSource.getConnection();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            createTableFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

    private void createTableFromResultSet(ResultSet resultSet) throws SQLException {
        int numOfColumns = resultSet.getMetaData().getColumnCount();
        String[] columnNames = new String[numOfColumns];
        for (int i = 0; i < numOfColumns; i++) {
            columnNames[i] = resultSet.getMetaData().getColumnName(i + 1);
        }

        List<Object[]> rowData = new ArrayList<>();
        while (resultSet.next()) {
            Object[] row = new Object[numOfColumns];
            for (int col = 0; col < numOfColumns; col++) {
                row[col] = resultSet.getObject(col + 1);
            }
            rowData.add(row);
        }

        Object[][] data = rowData.toArray(new Object[0][numOfColumns]);
        table = new JTable(new DefaultTableModel(data, columnNames));
        table.setPreferredScrollableViewportSize(new Dimension(800, 400));
        table.setFillsViewportHeight(true);
    }

    private void close() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connect != null) connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void componentManager(Container pane) {
        pane.setLayout(new BorderLayout());

        
        JComboBox<String> dropdown = new JComboBox<>(new String[]{
            "Loc_Pt", "GeolMinOcc", "Loc_Poly", "Production", "Resources",
            //"Procedure: CSP4a", "Procedure: CSP4b"
        });
        
        // data displaying functionality
        JButton fetchButton = new JButton("Load Data");
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) dropdown.getSelectedItem();
                try {
                        loadData("SELECT * FROM " + selectedItem);
                    JScrollPane scrollPane = new JScrollPane(table);
                    pane.add(scrollPane, BorderLayout.CENTER);
                    pane.revalidate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error fetching data: " + ex.getMessage());
                }
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select table you want to see: "));
        topPanel.add(dropdown);
        topPanel.add(fetchButton);

        pane.add(topPanel, BorderLayout.NORTH);
        
        //create buttons for procedures csp4a, csp4b
        JLabel csp4aLabel = new JLabel("Explore Commodity Opportunities?");
        JButton csp4aButton = new JButton("Execute CSP4a");
        csp4aButton.addActionListener(new ActionListener() {
            
        @Override
        public void actionPerformed(ActionEvent e) {
            String commodity = JOptionPane.showInputDialog("Enter Commodity:");
            String ftrType = JOptionPane.showInputDialog("Enter Feature Type:");
            try {
                loadData("CALL cobalt.FilterGeolMinOcc('" + commodity + "', '" + ftrType + "')");
                JScrollPane scrollPane = new JScrollPane(table);
                pane.add(scrollPane, BorderLayout.CENTER);
                pane.revalidate();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error executing CSP4a query: " + ex.getMessage());
            }
        }
    });
        
        
        JLabel csp4bLabel = new JLabel("Clean USGS Indicators?");
        JButton csp4bButton = new JButton("Execute CSP4b");
        csp4bButton.addActionListener(new ActionListener() {
            
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                loadData("CALL cobalt.CleanAndDisplayContained()");
                JScrollPane scrollPane = new JScrollPane(table);
                pane.add(scrollPane, BorderLayout.CENTER);
                pane.revalidate();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error executing CSP4b procedure: " + ex.getMessage());
            }
        }
    });

        topPanel.add(csp4aLabel);
        topPanel.add(csp4aButton);
        topPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing
        topPanel.add(csp4bLabel);
        topPanel.add(csp4bButton);
    }

    private void windowManager() {
        JFrame frame = new JFrame("Cobalt Investment Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        componentManager(frame.getContentPane());
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            System.out.println("Starting application");
            Cobalt app = new Cobalt();
            app.windowManager();
            System.out.println("Application terminated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
