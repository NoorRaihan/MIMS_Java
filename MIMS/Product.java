//main coder: raihan
import java.io.*;
import java.util.StringTokenizer;

public class Product extends Category {

    private String productID;
    private String productName;
    private int productQuantity;
    private int bulkValue;
    private String updateDate; // format: dd/mm/yyyy eg: 02/1/2020


    //constructor
    public Product() {
        this(null,null,null,null,0,0,null);
    }

    public Product(String categoryID, String categoryName,String productID, String productName, int productQuantity,int bulkValue, String updateDate) {
        super(categoryID,categoryName);
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.bulkValue = bulkValue;
        this.updateDate = updateDate;
    }

    //mutator 
    public void setAll(String productID, String productName, int productQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

    //accessor
    public String getProductID() {return productID;}
    public String getProductName() {return productName;}
    public int getProductQuantity() {return productQuantity;}
    public int getBulkValue() {return bulkValue;}

    //process
    public boolean checkExist(String ID) { //check if product exist or not
        boolean flag = false;
        try {
            BufferedReader inpt = new BufferedReader(new FileReader("product.txt"));
            String data = inpt.readLine();
            
            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data,";");
                String tempID = inputs.nextToken();

                if(tempID.equalsIgnoreCase(ID)) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
                
                data = inpt.readLine();
            }
            inpt.close();
        } catch (IOException ioe) {
            System.err.println("Something went wrong!");
        }
        return flag;
    
    }

    public void addProduct() { //add new Product to the product.txt

        //check if product exist or not
        boolean exist = checkExist(productID);

        if(exist) {
            System.out.println("Product already existed! \nPlease update the product instead!");
        } else {
            try {
                PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter("product.txt",true)));

                add.println(productID + ";" + productName + ";" + productQuantity + ";" + super.getCategoryName() + ";" + updateDate);
                add.close();

            } catch (IOException ioe) {
                System.err.println("Something went wrong!\n" + ioe);
            }
            
        }
    }
}