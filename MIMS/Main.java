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
                    break;
                case 3:
                    flag = false;
                    //update product method here
                    break;
                case 4:
                    flag = false;
                    //search product method here
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
                    break;
                case 3:
                    flag = false;
                    //update category method here
                    break;
                case 4:
                    flag = false;
                    //search category method here
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
        bannerCompany(data);
        Scanner in = new Scanner(System.in);
        System.out.println("\n[1]  Category Section");
        System.out.println("[2]  Product Section");
        System.out.println("[3]  Generate Report");
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

    static Category searchCategory(String id) { //to search and return category name
        
        Category dat = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader("category.txt"));
            String data = in.readLine();

            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                String catID =  inputs.nextToken();
                String catName = inputs.nextToken();

                if(catID.equalsIgnoreCase(id)) {
                    dat = new Category(id,catName);
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
           data.addCategory(); //do the add product job
       }else {
           addCategories(); //call this function itself 
       }
    }

    static void addProducts() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id,name,updateDate,categoryID = null;
        boolean choice;
        int quantity,bulkValue;
        Category catData = null;

        System.out.println("--------------ADD PRODUCT---------------");
        System.out.print("\nProduct ID: ");
        id = in.nextLine();

        System.out.print("Product Name: ");
        name = in.nextLine();

        while(catData == null) {
            System.out.print("Product Category ID [eg:AX119]: ");
            categoryID = in.nextLine();
            catData = searchCategory(categoryID);//find category name by id and get whole object
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

        Product data = new Product(categoryID,catData.getCategoryName(),id,name,quantity,bulkValue,updateDate);

        System.out.print("Are you confirm [yes/no]: ");
        choice = choicePicker(in.nextLine());

       if(choice) {
           data.addProduct();//do the add product job
       }else {
           addProducts();//call this function itself 
       }
    }

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