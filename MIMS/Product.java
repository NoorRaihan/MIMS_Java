//main coder: raihan
import java.io.*;
import java.util.StringTokenizer;

public class Product extends Category {

    private String productID;
    private String productName;
    private int productQuantity;
    private int stocks;
    private int bulkValue;
    private String updateDate; // format: dd/mm/yyyy eg: 02/1/2020


    //constructor
    public Product() {
        this(null,null,null,null,0,0,0,null);
    }

    public Product(String categoryID, String categoryName,String productID, String productName, int productQuantity,int stocks, int bulkValue, String updateDate) {
        super(categoryID,categoryName);
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.stocks = stocks;
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
    public int getProductStocks() {return stocks;}
    public int getBulkValue() {return bulkValue;}
    public String getUpdateDate() {return updateDate;}

    //process

    //check the product ID already exist or not in the textfile
    static boolean checkExist(String ID) { //check if product exist or not
        boolean flag = false;

        File chkfile = new File("product.txt");
        boolean exists = chkfile.exists();

        if(exists) {
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
        } else {
            flag = false;
        }
        
        return flag;
    
    }

    //add new Product to the product.txt
    public void add() { 

        //check if product exist or not
        boolean exist = checkExist(productID);

        if(exist) {
            System.out.println("Product ID is exits\n Please update the product stocks instead!");
        } else {
            try {
                PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter("product.txt",true)));

                add.println(productID + ";" + productName + ";" + productQuantity + ";" + stocks +";"+ bulkValue + ";" +super.getCategoryID() + ";" + updateDate);
                add.close();
                add.flush();
            } catch (IOException ioe) {
                System.err.println("Something went wrong!\n" + ioe);
            }
            
        }
    }

    //update a existed product stocks
    public void update() {
         //check if product exist or not
         boolean exist = checkExist(productID);

         if(!exist) {
             System.out.println("Product does not exist!");
         } else {
             try {
                 PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter("product.txt",true)));
 
                 add.println(productID + ";" + productName + ";" + productQuantity + ";" + stocks + ";" + bulkValue + ";" +super.getCategoryID() + ";" + updateDate);
                 add.close();
                 add.flush();
                 System.out.println("Product stock update successfully!");
 
             } catch (IOException ioe) {
                 System.err.println("Something went wrong!\n" + ioe);
             }
             
         }
    }

    //delete all the product records by its ID
    static void delete(String id) {
        boolean exist = checkExist(id);
        String pid,pname,pcategory,pupdate;
        int pquantity,pbulk,pstocks;
        String temp = "temp.txt";
        String ori = "product.txt";
        File tempFile = new File(temp);
        File oriFile = new File(ori);

        if(exist) {
            
            try {
                PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter(temp))); //create a temp file

                BufferedReader in = new BufferedReader(new FileReader(ori)); //read the category file
                String data = in.readLine();

                while(data != null) {
                    StringTokenizer inputs = new StringTokenizer(data, ";");

                    pid = inputs.nextToken();
                    pname = inputs.nextToken();
                    pquantity = Integer.parseInt(inputs.nextToken());
                    pstocks = Integer.parseInt(inputs.nextToken());
                    pbulk = Integer.parseInt(inputs.nextToken());
                    pcategory = inputs.nextToken();
                    pupdate = inputs.nextToken();

                    if(!pid.equalsIgnoreCase(id)) {
                        add.println(pid + ";" + pname + ";" + pquantity + ";" + pstocks +";" + pbulk + ";" + pcategory + ";" + pupdate);
                    }

                    data = in.readLine();
                }
                in.close();
                add.flush();
                add.close();

                oriFile.delete(); //delete the original file
                File newFile = new File(ori);
                tempFile.renameTo(newFile); //rename the file

            }catch (IOException ioe) {
                System.err.println("Something went wrong!\n" + ioe);
            }
        } else {
            System.out.println("Category does not exist!");
        }

    }

    //search and return all the specific product records by its own ID
    static Product [] searchPro(String id,String pname) {

        Product [] dat = new Product[9999];//array size let it bigger...
        String pid,name,category,date;
        int quantity,bulkvalue,pstocks,i = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader("product.txt"));
            String data = in.readLine();

            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                pid = inputs.nextToken();
                name = inputs.nextToken();
                quantity = Integer.parseInt(inputs.nextToken());
                pstocks = Integer.parseInt(inputs.nextToken());
                bulkvalue = Integer.parseInt(inputs.nextToken());
                category = inputs.nextToken();
                date = inputs.nextToken();
                //find category id
                Category catName = Category.search(category, null);

                if(pid.equalsIgnoreCase(id) || name.equalsIgnoreCase(pname)) {
                    dat[i] = new Product(category,catName.getCategoryName(),pid,name,quantity,pstocks,bulkvalue,date);
                    i++;
                }
                data = in.readLine();
            }
            in.close();
        }catch (IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe);
        }
        return dat;
    }

    //get all products info in textfile and return 2d array of product ID and Name
    static String[][] getAllProducts() {

        String [][]  seen = new String[9999][2]; //use multidimensional array
        String pid,pname;
        int j=0;
        boolean flag = false;
        try {
            BufferedReader in = new BufferedReader(new FileReader("product.txt"));

            String data = in.readLine();
            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                pid = inputs.nextToken();
                pname = inputs.nextToken();

                for(int i=0;i<seen.length;i++) {
  
                    if(seen[i][0] == null) {
                        flag = false;
                        break;
                    } else if(seen[i][0].equalsIgnoreCase(pid)){
                        flag = true;
                        break;
                    }
                }
                if(flag == false) {
                    seen[j][0] = pid;
                    seen[j][1] = pname;
                    j++;
                }
                data = in.readLine();
            }
            in.close();
        }catch (IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe.getMessage());
        }
        return seen;
    }

    //get all products details and return 2d array of produt id and name with specific category
    static String[][] getAllProducts(String id) { //overload method receive category id

        String [][]  seen = new String[9999][2]; //use multidimensional array
        String pid,pname,pcategory;
        int j=0;
        int pquantity,pstocks,pbulk;
        boolean flag = false;
        try {
            BufferedReader in = new BufferedReader(new FileReader("product.txt"));

            String data = in.readLine();
            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                pid = inputs.nextToken();
                pname = inputs.nextToken();
                pquantity = Integer.parseInt(inputs.nextToken());
                pstocks = Integer.parseInt(inputs.nextToken());
                pbulk = Integer.parseInt(inputs.nextToken());
                pcategory = inputs.nextToken();
                    
                if(pcategory.equalsIgnoreCase(id)) {
                    for(int i=0;i<seen.length;i++) {
                    
                        if(seen[i][0] == null) {
                            flag = false;
                            break;
                        } else if(seen[i][0].equalsIgnoreCase(pid)){
                            flag = true;
                            break;
                        }
                        
                    }
                    if(flag == false) {
                        seen[j][0] = pid;
                        seen[j][1] = pname;
                        j++;
                    }
                }
                data = in.readLine();
            }
            in.close();
        }catch (IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe.getMessage());
        }
        return seen;
    }

    public String toString() {
        return super.toString() + "\nProduct ID: " + productID + "\nProduct Name: " + productName + "\nMonth Production: " + productQuantity +"\nStocks: "+ stocks + "\nUpdate Date: " + updateDate + "\n";
    }
}