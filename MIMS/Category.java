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
    static boolean checkExist(String ID) {
        boolean flag = false;

        File chkfile = new File("category.txt");
        boolean exists = chkfile.exists();

        if(exists) {

            try {
                BufferedReader in = new BufferedReader(new FileReader("category.txt"));
                String data = in.readLine();
    
                while(data != null) {
                    StringTokenizer inputs = new StringTokenizer(data, ";");
                    String  tempID = inputs.nextToken();
    
                    if(tempID.equalsIgnoreCase(ID)) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                    data = in.readLine();
                }
                in.close();
            } catch (IOException ioe) {
                System.err.println("Something went wrong!" +ioe);
            }
        } else {
            System.out.println("executeeee"); //debugggg
            flag = false;
        }
        
        return flag;
    }

    public void add() {
        boolean exist = checkExist(categoryID);

        if(exist) {
            System.out.println("Category already existed!");
        } else {
            try {
                PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter("category.txt",true)));

                add.println(categoryID + ";" + categoryName);
                add.close();

            }catch (IOException ioe) {
                System.err.println("Something went wrong\n" + ioe);
            }
        }
    }

    static void delete(String id) {
        boolean exist = checkExist(id);
        String catID,catName;
        String temp = "temp.txt";
        String ori = "category.txt";
        File tempFile = new File(temp);
        File oriFile = new File(ori);

        if(exist) {
            
            try {
                PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter(temp))); //create a temp file

                BufferedReader in = new BufferedReader(new FileReader(ori)); //read the category file
                String data = in.readLine();

                while(data != null) {
                    StringTokenizer inputs = new StringTokenizer(data, ";");

                    catID = inputs.nextToken();
                    catName = inputs.nextToken();

                    if(!catID.equalsIgnoreCase(id)) {
                        add.println(catID + ";" + catName);
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

    public void update() {
        boolean exist = checkExist(categoryID);
        String catID,catName;
        String temp = "temp.txt";
        String ori = "category.txt";
        File tempFile = new File(temp);
        File oriFile = new File(ori);
        if(!exist) {
            System.out.println("Category ID not found!");
        } else {

            try {
            PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter(temp))); //create a temp file

                BufferedReader in = new BufferedReader(new FileReader(ori)); //read the category file
                String data = in.readLine();

                while(data != null) {
                    StringTokenizer inputs = new StringTokenizer(data, ";");

                    catID = inputs.nextToken();
                    catName = inputs.nextToken();

                    if(catID.equalsIgnoreCase(categoryID)) {
                        add.println(catID + ";" + categoryName);
                    } else {
                        add.println(catID + ";" + catName);
                    }

                    data = in.readLine();
                }
                in.close();
                add.flush();
                add.close();

                oriFile.delete(); //delete the original file
                File newFile = new File(ori);
                tempFile.renameTo(newFile); //rename the file
                System.out.println("Category successfully updated!");

            }catch (IOException ioe) {
                System.err.println("Something went wrong\n" + ioe);
            }
        }
    }

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

    static Category search(String id, String name) { //to search and return category name
        
        Category dat = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader("category.txt"));
            String data = in.readLine();

            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                String catID =  inputs.nextToken();
                String catName = inputs.nextToken();

                if(catID.equalsIgnoreCase(id) || catName.equalsIgnoreCase(name)) {
                    dat = new Category(catID,catName);
                    break;
                }
                data = in.readLine();
            }
            in.close();
        }catch (IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe);
        }
        return dat;
    }

    public String toString() {
        return "\nCategory ID: " + categoryID + "\nCategory Name: " + categoryName;
    }
}