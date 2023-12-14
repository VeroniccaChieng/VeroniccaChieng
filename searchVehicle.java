package latest;

import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class searchVehicle {

    private static Scanner x;

    public static void main(String[] args) {
        String filepath = "src\\vehicle.csv";//change your file path

        String searchTerm = JOptionPane.showInputDialog("Enter the search term:"
                + "\n-Car Plate"
                + "\n-Car Model"
                + "\n-Acquire Price"
                + "\n-Car Status (1 for in stock)"
                + "\n(Any one above )"
                + "\n(If you need to search for a range of sales prices, press OK.)");

        if ("".equalsIgnoreCase(searchTerm)) {
            String min = JOptionPane.showInputDialog("Enter the minimum sales price: ");
            String max = JOptionPane.showInputDialog("Enter the maximum sales price: ");
            readRecords2(min, max, filepath);
        } else {
            readRecords(searchTerm, filepath);
        }

    }

    public static void readRecords(String searchTerm, String filepath) {
        boolean found = false;

        try {
            x = new Scanner(new File(filepath));
            
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Car Plate");
            tableModel.addColumn("Car Model");
            tableModel.addColumn("Acquire Price");
            tableModel.addColumn("Car Status");
            tableModel.addColumn("Sales Price");

            while (x.hasNextLine()) {
                String line = x.nextLine();
                String[] data = line.split(",");

                if (data.length >= 5) {
                    String carPlate = data[0].trim();
                    String carModel = data[1].trim();
                    String acquirePrice = data[2].trim();
                    String carStatus = data[3].trim();
                    String salesPrice = data[4].trim();

                    if (carPlate.equalsIgnoreCase(searchTerm)
                            || carModel.equalsIgnoreCase(searchTerm)
                            || (carStatus.equalsIgnoreCase(searchTerm))
                            || acquirePrice.equalsIgnoreCase(searchTerm)) {

                        tableModel.addRow(new Object[]{carPlate, carModel, acquirePrice, carStatus, salesPrice});
                        found = true;
                    }
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

    public static void readRecords2(String min, String max, String filepath) {
        boolean found = false;

        try {
            x = new Scanner(new File(filepath));

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Car Plate");
            tableModel.addColumn("Car Model");
            tableModel.addColumn("Acquire Price");
            tableModel.addColumn("Car Status");
            tableModel.addColumn("Sales Price");

            while (x.hasNextLine()) {
                String line = x.nextLine();
                String[] data = line.split(",");

                if (data.length >= 5) {
                    String carPlate = data[0].trim();
                    String carModel = data[1].trim();
                    String acquirePrice = data[2].trim();
                    String carStatus = data[3].trim();
                    String salesPrice = data[4].trim();

                    if ((isNumeric(salesPrice) && Integer.parseInt(salesPrice) >= Integer.parseInt(min))
                            && (isNumeric(salesPrice) && Integer.parseInt(salesPrice) <= Integer.parseInt(max))) {

                        tableModel.addRow(new Object[]{carPlate, carModel, acquirePrice, carStatus, salesPrice});
                        found = true;
                    }
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

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

}
