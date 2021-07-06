//IO stuffs
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

//normal stuff
import java.util.Scanner;

public class Main {
    
    static void bannerCompany(Company data) {

        System.out.println("███╗░░░███╗██╗███╗░░░███╗░██████╗");
        System.out.println("████╗░████║██║████╗░████║██╔════╝");
        System.out.println("██╔████╔██║██║██╔████╔██║╚█████╗░");
        System.out.println("██║╚██╔╝██║██║██║╚██╔╝██║░╚═══██╗");
        System.out.println("██║░╚═╝░██║██║██║░╚═╝░██║██████╔╝");
        System.out.println("╚═╝░░░░░╚═╝╚═╝╚═╝░░░░░╚═╝╚═════╝░");
        System.out.println("Manufacturing Inventory Management System");
        System.out.println("Company Name  : " + data.getCompanyName());
        System.out.println("Company Phone : " + data.getCompanyPhone());
        
    }

    static void productMenu(Company data) {
        System.out.print("\u000C");
        bannerCompany(data);
        Scanner in = new Scanner(System.in);
        System.out.println("\n[1]  Add Product");
        System.out.println("[2]  Delete Product");
        System.out.println("[3]  Update Product");
        System.out.println("[4]  Search Product");
        System.out.println("[99] Back to Main Menu");

        int choice;
        boolean flag = true;
        while(flag) {

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    flag = false;
                    //add product method here
                    addProducts();
                    break;
                case 2:
                    flag = false;
                    //delete product method here
                    deleteProducts();
                    break;
                case 3:
                    flag = false;
                    //update product method here
                    updateProducts();
                    break;
                case 4:
                    flag = false;
                    //search product method here
                    searchingProducts();
                    break;
                case 99:
                    flag = false;
                    mainMenu(data);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        
    }
    
    static void categoryMenu(Company data) {
        System.out.print("\u000C");
        bannerCompany(data);
        Scanner in = new Scanner(System.in);
        System.out.println("\n[1]  Add Category");
        System.out.println("[2]  Delete Category");
        System.out.println("[3]  Update Category");
        System.out.println("[4]  Search Category");
        System.out.println("[99] Back to Main Menu");

        int choice;
        boolean flag = true;
        while(flag) {

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    flag = false;
                    //add category method here
                    addCategories();
                    break;
                case 2:
                    flag = false;
                    //delete category method here
                    deleteCategories();
                    break;
                case 3:
                    flag = false;
                    //update category method here
                    updateCategories();
                    break;
                case 4:
                    flag = false;
                    //search category method here
                    searchingCategories();
                    break;
                case 99:
                    flag = false;
                    mainMenu(data);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        
    }

    static void mainMenu(Company data) {
         //display main menu
        System.out.print("\u000C");
        bannerCompany(data);
        Scanner in = new Scanner(System.in);
        System.out.println("\n[1]  Category Section");
        System.out.println("[2]  Product Section");
        System.out.println("[3]  Generate Report");
        System.out.println("[4]  DEBUG MENU <- CODE TESTING");
        System.out.println("[99] Exit Program");

        int choice;
        boolean flag = true;
        while(flag) {

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    flag = false;
                    //category menu here
                    categoryMenu(data);
                    break;
                case 2:
                    flag = false;
                    //product menu here
                    productMenu(data);
                    break;
                case 3:
                    flag = false;
                    //report menu here
                    break;
                case 4:
                    flag = false;
                    //report menu here
                    displayDEBUG(data);;//testing purpose
                    break;
                case 99:
                    flag = false;
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
        
    }

    static boolean choicePicker(String choice) { //return true or false depends on the choice yes or no
        boolean flag = false;

        if(choice.equalsIgnoreCase("yes")) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    static int checkLength(Object [] array) { //this one for checking actual length of array
        int count = 0;
        for(Object myobj : array) { //using for-loop for code optimizinggggg and simple wohoo
            if(myobj != null) {
                count++;
            }
        }
        return count;
    }

    static void addCategories() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id,name;
        boolean choice;

        System.out.println("--------------ADD CATEGORY---------------");
        System.out.print("\nCategory ID: ");
        id = in.nextLine();

        System.out.print("Category Name: ");
        name = in.nextLine();

        Category data = new Category(id,name);
        System.out.print("Are you confirm [yes/no]: ");
        choice = choicePicker(in.nextLine());

       if(choice) {
           data.add(); //do the add product job
       }else {
           addCategories(); //call this function itself 
       }
    }

    static void deleteCategories() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id;
        boolean choice;

        System.out.println("--------------DELETE CATEGORY---------------");
        System.out.print("\nCategory ID: ");
        id = in.nextLine();

        System.out.print("Are you confirm [yes/no]: ");
        choice = choicePicker(in.nextLine());

       if(choice) {
            Category.delete(id); //do the delete process; //do the add product job
       }else {
           deleteCategories(); //call this function itself 
       }
    }

    static void updateCategories() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id,name;
        boolean choice;

        System.out.println("--------------EDIT CATEGORY---------------");
        System.out.print("\nCategory ID: ");
        id = in.nextLine();

        boolean exists = Category.checkExist(id);

        if(exists) {
            System.out.print("New Category Name: ");
            name = in.nextLine();
            
            System.out.print("Are you confirm [yes/no]: ");
            choice = choicePicker(in.nextLine());

            if(choice) {
                Category newData = new Category(id,name);
                newData.update();//do the update category job
            }else {
                updateCategories();//call this function itself 
            }

        } else {
            System.out.println("Category ID not found!");
        }
    }

    static void addProducts() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id,name,updateDate,categoryID = null;
        boolean choice;
        int quantity,bulkValue;
        Category catData = null;

        System.out.println("--------------ADD NEW PRODUCT---------------");
        System.out.print("\nProduct ID: ");
        id = in.nextLine();

        System.out.print("Product Name: ");
        name = in.nextLine();

        while(catData == null) {
            System.out.print("Product Category ID [eg:AX119]: ");
            categoryID = in.nextLine();
            catData = Category.search(categoryID,null);//find category name by id and get whole object
            if(catData == null) {
                System.out.println("Category ID not exist!");
            }
        }
    

        System.out.print("Product Initial Quantity: ");
        quantity = Integer.parseInt(in.nextLine());

        System.out.print("Product bulk value: ");
        bulkValue = Integer.parseInt(in.nextLine());

        System.out.print("Insertion Date [eg: 21/1/2020]: ");
        updateDate = in.nextLine();

        Product data = new Product(categoryID,catData.getCategoryName(),id,name,quantity,quantity,bulkValue,updateDate);

        System.out.print("Are you confirm [yes/no]: ");
        choice = choicePicker(in.nextLine());

       if(choice) {
           data.add();//do the add product job
       }else {
           addProducts();//call this function itself 
       }
    }

    static void deleteProducts() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id;
        boolean choice;

        System.out.println("--------------DELETE PRODUCT---------------");
        System.out.print("\nProduct ID: ");
        id = in.nextLine();

        System.out.println("[WARNING] : It will delete all the product records!");
        System.out.print("Are you confirm [yes/no]: ");
        choice = choicePicker(in.nextLine());

       if(choice) {
            Product.delete(id); //do the delete process; //do the add product job
       }else {
           deleteProducts(); //call this function itself 
       }
    }

    static void updateProducts() {
        System.out.print("\u000C");//to clear the terminal :)
        Scanner in = new Scanner(System.in);
        String id,updatedate;
        int quantity;

        System.out.println("--------------UPDATE PRODUCT STOCKS---------------");
        System.out.print("\nProduct ID: ");
        id = in.nextLine();

        Product [] data = Product.searchPro(id, null);
        int length = checkLength(data);
        int arrLatest = length-1;
        if(length > 0) {
            System.out.print("Quantity: ");
            quantity = Integer.parseInt(in.nextLine());

            System.out.print("Insertion Date [eg:12/7/2021]: ");
            updatedate = in.nextLine();

            int actQuantity = quantity + data[arrLatest].getProductStocks();

            Product newData = new Product(data[arrLatest].getCategoryID(),data[arrLatest].getCategoryName(),id,data[arrLatest].getProductName(),quantity,actQuantity,data[arrLatest].getBulkValue(),updatedate);
            newData.update();//add the update stocks
        } else {
            System.out.println("Product ID not found!");
        }
    }

    static void searchingProducts() {
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        String id;
        System.out.println("--------------------SEARCH PRODUCT----------------------");
        System.out.print("\nEnter Product ID [eg:AX119]: ");
        id = in.nextLine();

        Product [] data = Product.searchPro(id, null);
        // System.out.println(checkLength(data)); //for debugging purpose =>show how many result
        int length = checkLength(data);
        if(length > 0) {
            for(int i=0;i<length;i++){
                if(data[i] == null) {
                    break;
                } else {
                    System.out.println(data[i].toString());
                }
            }
        } else {
            System.out.println("Product does not exist!");
        }
    }

    static void searchingCategories() { //search categories by inputting id
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        String id;
        System.out.println("--------------------SEARCH CATEGORY----------------------");
        System.out.print("\nEnter Category ID [eg:AX119]: ");
        id = in.nextLine();

        Category data = Category.search(id,null);
        if(data != null) {
            System.out.println(data.toString());
        } else {
            System.out.println("Category not exist!");
        }
    }


    //--------------------testing purpose----------------------------------------------------

    static void displayDEBUG(Company data) {
        System.out.print("\u000C");
        bannerCompany(data);
        Scanner in = new Scanner(System.in);
        System.out.println("\n[1]  DEBUG CALCULATE CATEGORIES");
        System.out.println("[2]  DEBUG AVERAGE CALCULATION");
        System.out.println("[3]  DEBUG BULK AMOUNT");
        System.out.println("[4]  DEBUG LOWEST PRODUCTION");
        System.out.println("[5] DEBUG LOWEST PRODUCT STOCKS");
        System.out.println("[6] DEBUG LIST ALL CATEGORIES");
        System.out.println("[99] Back to Main Menu");

        int choice;
        boolean flag = true;
        while(flag) {

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    flag = false;
                    //add product method here
                    calculateCategories();
                    break;
                case 2:
                    flag = false;
                    //delete product method here
                    calculateAverage();
                    break;
                case 3:
                    flag = false;
                    //update product method here
                    
                    break;
                case 4:
                    flag = false;
                    //search product method here
                    break;
                case 5:
                    flag = false;
                    //debug method here
                    break;
                case 6:
                    flag = false;
                    //debug method here
                    listAllCategories();
                    break;
                case 99:
                    flag = false;
                    mainMenu(data);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static Category [] getAllCategories() {
        Category [] dat = new Category[9999];
        String id,name;
        try {
            BufferedReader in = new BufferedReader(new FileReader("category.txt"));
            String data = in.readLine();
            int i = 0;
            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                id = inputs.nextToken();
                name = inputs.nextToken();
                
                dat[i]= new Category(id,name);
                data = in.readLine();
                i++;
            }
            in.close();
        }catch (IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe);
        }
        return dat;
    }

    static void calculateCategories() {
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        String id;
        int month,year;//testing purpose
        System.out.println("--------------------DEBUG CALCULATE CATEGORIES BY MONTH----------------------");
        System.out.print("\nEnter Category ID [eg:AX119]: ");
        id = in.nextLine();

        Category data = Category.search(id,null);
        if(data != null) {
            System.out.print("Enter month: ");
            month = Integer.parseInt(in.nextLine());
            System.out.print("Enter year: ");
            year = Integer.parseInt(in.nextLine());
            int stocks = data.calcQuantity(month, year);
            System.out.println("\n" + data.toString());
            System.out.println("Month Production: " + stocks);
            
        } else {
            System.out.println("Category not exist!");
        }
    }

    static void listAllCategories() {
        System.out.print("\u000C");
        Category [] data = getAllCategories();
        int length = checkLength(data);
    
        for(int i=0; i<length; i++) {
            System.out.println(data[i].toString());
        }
    }

    static void calculateAverage() {
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        Category [] data = getAllCategories();
        int length = checkLength(data);
        int totalProduction = 0;
        int average,month,year;
        
        System.out.print("Enter month: ");
        month = Integer.parseInt(in.nextLine());
        System.out.print("Enter year: ");
        year = Integer.parseInt(in.nextLine());

        for(int i=0; i<length; i++) {
            totalProduction += data[i].calcQuantity(month, year);
        }

        average = totalProduction/length;
        System.out.println("Total Categories: " + length);
        System.out.println("Total Production: " + totalProduction);
        System.out.println("Average production: " + average);
    }

    //---------------------------------------------------------------------------------------


    static Company registerCompany() {

        String companyName,companyPhone,companyAddress,businessNumber;
        Scanner in = new Scanner(System.in);

        System.out.println("--------------------NEW USER COMPANY REGISTRATION----------------------");
        
        System.out.print("Company Name: ");
        companyName = in.nextLine();

        System.out.print("Company Phone: ");
        companyPhone = in.nextLine();

        System.out.print("Company Address: ");
        companyAddress = in.nextLine();

        System.out.print("Business Number: ");
        businessNumber = in.nextLine();

        Company compData = new Company(companyName,companyPhone,companyAddress,businessNumber);

        try {
            
            PrintWriter wrt = new PrintWriter(new BufferedWriter(new FileWriter("company.txt")));
            
            wrt.println(compData.getCompanyName()+";"+compData.getCompanyPhone()+";"+compData.getCompanyAddress()+";"+compData.getBusinessNumber());
            wrt.close();

        } catch (IOException ioe) {
            System.err.println("Something went wrong!");
        }
        System.out.print("\u000C");//to clear the terminal :) 
        return compData;
    }


    static Company verifyCompany() { //checking there is company details in the company.txt or not

        Company compData = null;
        String companyName,companyPhone,companyAddress,businessNumber;
        
        File chkfile = new File("company.txt");
        boolean exists = chkfile.exists();

        if(exists) {
            //read the company.txt
            try {
                BufferedReader in = new BufferedReader(new FileReader("company.txt"));
                
                String temp = in.readLine();

                if(temp != null) {

                    while (temp != null) {
                        StringTokenizer inputs = new StringTokenizer(temp, ";");

                        companyName = inputs.nextToken();
                        companyPhone = inputs.nextToken();
                        companyAddress = inputs.nextToken();
                        businessNumber = inputs.nextToken();

                        compData = new Company(companyName,companyPhone,companyAddress,businessNumber);

                        temp = in.readLine();
                    }
                    in.close();

                } else {
                    compData = registerCompany();
                }

            } catch (IOException ioe) {
                System.err.print("Something went Wrong!");
            }

        } else {
            compData = registerCompany();
        }

        return compData;
    }

    public static void main(String [] args) {

        //verify the user registered the company or not
        Company companyDetails = verifyCompany();
        mainMenu(companyDetails);
    }
}