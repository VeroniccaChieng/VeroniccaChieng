package salesmanagement;


import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class searchCust {

    private static Scanner x;

    public static void main(String[] args) {
        // TODO code application logic here

        String filepath = "C:\\NetBeans Projects\\search&filter\\src\\cust.csv";//change your file path

        String searchTerm = JOptionPane.showInputDialog("Enter the search term:"
                + "\n-Customer Id"
                + "\n-Customer Name"
                + "\n-Phone Number"
                + "\n-Postcode"
                + "\n(Any one above)");

        readRecord(searchTerm, filepath);

    }

    public static void readRecord(String searchTerm, String filepath) {
        boolean found = false;
        String custId = "";
        String custName = "";
        String phoneNum = "";
        String postcode = "";

        try {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while (x.hasNext() && !found) {
                custId = x.next();
                custName = x.next();
                phoneNum = x.next();
                postcode = x.next();

                if (custId.equalsIgnoreCase(searchTerm)
                        || custName.equalsIgnoreCase(searchTerm)
                        || phoneNum.equalsIgnoreCase(searchTerm)
                        || postcode.equalsIgnoreCase(searchTerm)) {

                    found = true;

                }
            }
            if (found) {
                JOptionPane.showMessageDialog(null, "Customer Id: " + custId + "\nCustomer Name: " + custName + "\nPhone Number: " + phoneNum + "\nPostcode: " + postcode);

            } else {
                JOptionPane.showMessageDialog(null, "Record not found");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");

        }
    }

}
