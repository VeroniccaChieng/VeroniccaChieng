package salesmanagement;


import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class searchSales {

    public static Scanner x;
    
    public static void main(String[] args) {
        String filepath = "C:\\NetBeans Projects\\search&filter\\src\\sales.csv";//change your file path

        String searchTerm = JOptionPane.showInputDialog("Enter the search term:"
                + "\n-Sales Id"
                + "\n-Date & Time"
                + "\n-Car Plate"
                + "\n-Customer Id"
                + "\n-Employee Id"
                + "\n(Any one above)");

        readRecords(searchTerm, filepath);
    }

    public static void readRecords(String searchTerm, String filepath) {
        boolean found = false;

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Sales ID");
        tableModel.addColumn("Date & Time");
        tableModel.addColumn("Car Plate");
        tableModel.addColumn("Customer ID");
        tableModel.addColumn("Employee ID");

        try {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while (x.hasNext()) {
                String salesId = x.next();
                String dateTime = x.next();
                String carPlate = x.next();
                String custId = x.next();
                String employeeId = x.next();

                if (salesId.equalsIgnoreCase(searchTerm)
                        || dateTime.equalsIgnoreCase(searchTerm)
                        || carPlate.equalsIgnoreCase(searchTerm)
                        || custId.equalsIgnoreCase(searchTerm)
                        || employeeId.equalsIgnoreCase(searchTerm)) {

                    tableModel.addRow(new Object[]{salesId, dateTime, carPlate, custId, employeeId});
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
