package salesmanagement;


import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class searchEmployee {

    private static Scanner x;

    public static void main(String[] args) {
        String filepath = "C:\\NetBeans Projects\\search&filter\\src\\employee.csv";//change your file path
        String searchTerm = JOptionPane.showInputDialog("Enter the search term:"
                + "\n-Employee Id"
                + "\n-Employee Name"
                + "\n-Status (1 for under management)"
                + "\n-Password"
                + "\n(Any one above)");

        readRecords(searchTerm, filepath);
    }

    public static void readRecords(String searchTerm, String filepath) {
        boolean found = false;

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Employee ID");
        tableModel.addColumn("Employee Name");
        tableModel.addColumn("Status");
        tableModel.addColumn("Password");

        try {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while (x.hasNext()) {
                String employeeId = x.next();
                String employeeName = x.next();
                String employeeStatus = x.next();
                String password = x.next();

                if (employeeId.equalsIgnoreCase(searchTerm)
                        || employeeName.equalsIgnoreCase(searchTerm)
                        || employeeStatus.equalsIgnoreCase(searchTerm)
                        || password.equalsIgnoreCase(searchTerm)) {

                    
                    tableModel.addRow(new Object[]{employeeId, employeeName, employeeStatus, password});
                    found = true;
                }
            }

            if (found) {
                JTable resultTable = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(resultTable);
                JOptionPane.showMessageDialog(null, scrollPane, "Search Results", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Record not found");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        } finally {
            if (x != null) {
                x.close();
            }
        }
    }
}
