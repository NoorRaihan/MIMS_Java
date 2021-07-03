//main coder: raihan
import java.io.*;
import java.util.StringTokenizer;

public class Category {

    private String categoryID;
    private String categoryName;
    // private String updateDate; // format: dd/mm/yyyy eg: 02/1/2020

    //constructors
    public Category() {
        this(null,null);
    }

    public Category(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        // this.updateDate = updateDate;
    }

    //mutator
    public void setAll(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        // this.updateDate = updateDate;
    }

    //accessor 
    public String getCategoryID() {return categoryID;}
    public String getCategoryName() {return categoryName;}
    // public String getUpdateDate() {return updateDate;}

    //process
    public int calcQuantity(int month) {

        int quantity = 0;

        try {
            BufferedReader in = new BufferedReader(new FileReader("product.txt"));

            String data = in.readLine();
            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                String productID = inputs.nextToken();
                String productName = inputs.nextToken();
                int productQuantity = Integer.parseInt(inputs.nextToken());
                String category = inputs.nextToken();
                String date = inputs.nextToken();
                
                String [] parts = date.split("/",3);
                int mm = Integer.parseInt(parts[1]); 
                
                if(category.equalsIgnoreCase(categoryName) && mm == month) {
                    quantity += productQuantity;
                }
                in.close();
            }

        } catch (IOException ioe) {
            System.err.println("Something went wrong!");
        }
        return quantity;
    }
}