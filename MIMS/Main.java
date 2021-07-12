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
    
    //head banner
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

    //display product menu
    static void productMenu() {
        System.out.print("\u000C");
        Company data = verifyCompany();
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
    
    //display category menu
    static void categoryMenu() {
        System.out.print("\u000C");
        Company data = verifyCompany();
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

    //display main menu
    static void mainMenu(Company data) {
         //display main menu
        System.out.print("\u000C");
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
                    categoryMenu();
                    break;
                case 2:
                    flag = false;
                    //product menu here
                    productMenu();
                    break;
                case 3:
                    flag = false;
                    //report menu here
                    testReport(data);
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

    //delayed the process
    static void tunggu(int ms) {
        try {
            Thread.sleep(3000);
        }catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
       }
    }

    //for press any key to continue.... hehe
    static void pressAnyKey() {
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e){
            System.err.println("went wrong!");
        }  
    }

    //add a new category to the system
    static void addCategories() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id,name;
        boolean choice;

        System.out.println("─█▀▀█ ░█▀▀▄ ░█▀▀▄ 　 ░█▀▀█ ─█▀▀█ ▀▀█▀▀ ░█▀▀▀ ░█▀▀█ ░█▀▀▀█ ░█▀▀█ ░█──░█ ");
        System.out.println("░█▄▄█ ░█─░█ ░█─░█ 　 ░█─── ░█▄▄█ ─░█── ░█▀▀▀ ░█─▄▄ ░█──░█ ░█▄▄▀ ░█▄▄▄█ ");
        System.out.println("░█─░█ ░█▄▄▀ ░█▄▄▀ 　 ░█▄▄█ ░█─░█ ─░█── ░█▄▄▄ ░█▄▄█ ░█▄▄▄█ ░█─░█ ──░█──");
        viewSortedAll();
        System.out.print("\nCategory ID: ");
        id = in.nextLine();

        System.out.print("Category Name: ");
        name = in.nextLine();

        Category data = new Category(id,name);
        System.out.print("Are you confirm [yes/no]: ");
        choice = choicePicker(in.nextLine());

       if(choice) {
           data.add(); //do the add product job
           System.out.println("Category added successfully!");
           tunggu(3000);
           categoryMenu();
       }else {
           addCategories(); //call this function itself 
       }
    }

    //delete a category record
    static void deleteCategories() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id;
        boolean choice;

        System.out.println("░█▀▀▄ ░█▀▀▀ ░█─── ░█▀▀▀ ▀▀█▀▀ ░█▀▀▀ 　 ░█▀▀█ ─█▀▀█ ▀▀█▀▀ ░█▀▀▀ ░█▀▀█ ░█▀▀▀█ ░█▀▀█ ░█──░█ ");
        System.out.println("░█─░█ ░█▀▀▀ ░█─── ░█▀▀▀ ─░█── ░█▀▀▀ 　 ░█─── ░█▄▄█ ─░█── ░█▀▀▀ ░█─▄▄ ░█──░█ ░█▄▄▀ ░█▄▄▄█");
        System.out.println("░█▄▄▀ ░█▄▄▄ ░█▄▄█ ░█▄▄▄ ─░█── ░█▄▄▄ 　 ░█▄▄█ ░█─░█ ─░█── ░█▄▄▄ ░█▄▄█ ░█▄▄▄█ ░█─░█ ──░█──");
        viewSortedAll();
        System.out.print("\nCategory ID: ");
        id = in.nextLine();

        System.out.print("Are you confirm [yes/no]: ");
        choice = choicePicker(in.nextLine());

       if(choice) {
            Category.delete(id); //do the delete process; //do the add product job
            System.out.println("Category deleted successfully!");
            tunggu(3000);
            categoryMenu();
       }else {
           deleteCategories(); //call this function itself 
       }
    }

    //edit and update a existed categories
    static void updateCategories() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id,name;
        boolean choice;

        System.out.println("░█▀▀▀ ░█▀▀▄ ▀█▀ ▀▀█▀▀ 　 ░█▀▀█ ─█▀▀█ ▀▀█▀▀ ░█▀▀▀ ░█▀▀█ ░█▀▀▀█ ░█▀▀█ ░█──░█ ");
        System.out.println("░█▀▀▀ ░█─░█ ░█─ ─░█── 　 ░█─── ░█▄▄█ ─░█── ░█▀▀▀ ░█─▄▄ ░█──░█ ░█▄▄▀ ░█▄▄▄█ ");
        System.out.println("░█▄▄▄ ░█▄▄▀ ▄█▄ ─░█── 　 ░█▄▄█ ░█─░█ ─░█── ░█▄▄▄ ░█▄▄█ ░█▄▄▄█ ░█─░█ ──░█──");
        viewSortedAll();
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
                tunggu(3000);
                categoryMenu();
            }else {
                updateCategories();//call this function itself 
            }

        } else {
            System.out.println("Category ID not found!");
            pressAnyKey();
            updateCategories();
        }
    }

    //add a new product to the system
    static void addProducts() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id,name,updateDate,categoryID = null;
        boolean choice;
        int quantity,bulkValue;
        Category catData = null;

        System.out.println("─█▀▀█ ░█▀▀▄ ░█▀▀▄ 　 ░█▀▀█ ░█▀▀█ ░█▀▀▀█ ░█▀▀▄ ░█─░█ ░█▀▀█ ▀▀█▀▀ ");
        System.out.println("░█▄▄█ ░█─░█ ░█─░█ 　 ░█▄▄█ ░█▄▄▀ ░█──░█ ░█─░█ ░█─░█ ░█─── ─░█── ");
        System.out.println("░█─░█ ░█▄▄▀ ░█▄▄▀ 　 ░█─── ░█─░█ ░█▄▄▄█ ░█▄▄▀ ─▀▄▄▀ ░█▄▄█ ─░█──");
        viewSortedAll();
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
           System.out.println("Product added successfully!");
           tunggu(3000);
           productMenu();
       }else {
           addProducts();//call this function itself 
       }
    }

    //delete a product and all the records by inputting id
    static void deleteProducts() {
        System.out.print("\u000C");//to clear the terminal :) 
        Scanner in = new Scanner(System.in);
        String id;
        boolean choice;
        
        System.out.println("░█▀▀▄ ░█▀▀▀ ░█─── ░█▀▀▀ ▀▀█▀▀ ░█▀▀▀ 　 ░█▀▀█ ░█▀▀█ ░█▀▀▀█ ░█▀▀▄ ░█─░█ ░█▀▀█ ▀▀█▀▀ ");
        System.out.println("░█─░█ ░█▀▀▀ ░█─── ░█▀▀▀ ─░█── ░█▀▀▀ 　 ░█▄▄█ ░█▄▄▀ ░█──░█ ░█─░█ ░█─░█ ░█─── ─░█── ");
        System.out.println("░█▄▄▀ ░█▄▄▄ ░█▄▄█ ░█▄▄▄ ─░█── ░█▄▄▄ 　 ░█─── ░█─░█ ░█▄▄▄█ ░█▄▄▀ ─▀▄▄▀ ░█▄▄█ ─░█──");
        viewSortedAll();
        System.out.print("\nProduct ID: ");
        id = in.nextLine();

        System.out.println("[WARNING] : It will delete all the product records!");
        System.out.print("Are you confirm [yes/no]: ");
        choice = choicePicker(in.nextLine());

       if(choice) {
            Product.delete(id); //do the delete process; //do the add product job
            System.out.println("Product deleted successfully!");
            tunggu(3000);
            productMenu();
       }else {
           deleteProducts(); //call this function itself 
       }
    }

    //updating product for every month
    static void updateProducts() {
        System.out.print("\u000C");//to clear the terminal :)
        Scanner in = new Scanner(System.in);
        String id,updatedate;
        int quantity;

        System.out.println("░█─░█ ░█▀▀█ ░█▀▀▄ ─█▀▀█ ▀▀█▀▀ ░█▀▀▀ 　 ░█▀▀█ ░█▀▀█ ░█▀▀▀█ ░█▀▀▄ ░█─░█ ░█▀▀█ ▀▀█▀▀ ");
        System.out.println("░█─░█ ░█▄▄█ ░█─░█ ░█▄▄█ ─░█── ░█▀▀▀ 　 ░█▄▄█ ░█▄▄▀ ░█──░█ ░█─░█ ░█─░█ ░█─── ─░█── ");
        System.out.println("─▀▄▄▀ ░█─── ░█▄▄▀ ░█─░█ ─░█── ░█▄▄▄ 　 ░█─── ░█─░█ ░█▄▄▄█ ░█▄▄▀ ─▀▄▄▀ ░█▄▄█ ─░█───");
        viewSortedAll();
        System.out.print("\nProduct ID: ");
        id = in.nextLine();

        Product [] data = Product.searchPro(id, null);
        int length = ItemsInfo.checkLength(data);
        int arrLatest = length-1;
        if(length > 0) {
            System.out.print("Quantity: ");
            quantity = Integer.parseInt(in.nextLine());

            System.out.print("Insertion Date [eg:12/7/2021]: ");
            updatedate = in.nextLine();

            int actQuantity = quantity + data[arrLatest].getProductStocks();

            Product newData = new Product(data[arrLatest].getCategoryID(),data[arrLatest].getCategoryName(),id,data[arrLatest].getProductName(),quantity,actQuantity,data[arrLatest].getBulkValue(),updatedate);

            System.out.print("Are you confirm [yes/no]: ");
            boolean choice = choicePicker(in.nextLine());
            if(choice) {
                newData.update();//add the update stockS
                tunggu(3000);
                productMenu();
           }else {
               updateProducts(); //call this function itself 
           }
            
        } else {
            System.out.println("Product ID not found!");
            pressAnyKey();
            productMenu();
        }
    }

    //searching products by inputiing id
    static void searchingProducts() {
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        String id;

        System.out.println("░█▀▀▀█ ░█▀▀▀ ─█▀▀█ ░█▀▀█ ░█▀▀█ ░█─░█ 　 ░█▀▀█ ░█▀▀█ ░█▀▀▀█ ░█▀▀▄ ░█─░█ ░█▀▀█ ▀▀█▀▀ ");
        System.out.println("─▀▀▀▄▄ ░█▀▀▀ ░█▄▄█ ░█▄▄▀ ░█─── ░█▀▀█ 　 ░█▄▄█ ░█▄▄▀ ░█──░█ ░█─░█ ░█─░█ ░█─── ─░█──  ");
        System.out.println("░█▄▄▄█ ░█▄▄▄ ░█─░█ ░█─░█ ░█▄▄█ ░█─░█ 　 ░█─── ░█─░█ ░█▄▄▄█ ░█▄▄▀ ─▀▄▄▀ ░█▄▄█ ─░█──");
        viewSortedAll();
        System.out.print("\nEnter Product ID [eg:AX119]: ");
        id = in.nextLine();

        Product [] data = Product.searchPro(id, null);
        // System.out.println(ItemsInfo.checkLength(data)); //for debugging purpose =>show how many result
        int length = ItemsInfo.checkLength(data);
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
        pressAnyKey();
        productMenu();
    }

    static void searchingCategories() { //search categories by inputting id
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        String id;

        System.out.println("░█▀▀▀█ ░█▀▀▀ ─█▀▀█ ░█▀▀█ ░█▀▀█ ░█─░█ 　 ░█▀▀█ ─█▀▀█ ▀▀█▀▀ ░█▀▀▀ ░█▀▀█ ░█▀▀▀█ ░█▀▀█ ░█──░█ ");
        System.out.println("─▀▀▀▄▄ ░█▀▀▀ ░█▄▄█ ░█▄▄▀ ░█─── ░█▀▀█ 　 ░█─── ░█▄▄█ ─░█── ░█▀▀▀ ░█─▄▄ ░█──░█ ░█▄▄▀ ░█▄▄▄█  ");
        System.out.println("░█▄▄▄█ ░█▄▄▄ ░█─░█ ░█─░█ ░█▄▄█ ░█─░█ 　 ░█▄▄█ ░█─░█ ─░█── ░█▄▄▄ ░█▄▄█ ░█▄▄▄█ ░█─░█ ──░█──");
        viewSortedAll();
        System.out.print("\nEnter Category ID [eg:AX119]: ");
        id = in.nextLine();

        Category data = Category.search(id,null);
        if(data != null) {
            System.out.println(data.toString());
        } else {
            System.out.println("Category not exist!");
        }
        pressAnyKey();
        categoryMenu();
    }

    //diplaying sorted list
    static void viewSortedAll() {
        System.out.println("=========================================");
        System.out.println("LISTING ALL CATEGORIES AND PRODUCTS");
        System.out.println("=========================================");
        Category [] catList = Category.getAllCategories();
        int length = ItemsInfo.checkLength(catList);
        String [] list = new String[length];
        String [] Catsorted = new String[length];
        String [][] plist = null;
         
        for (int i=0;i<length;i++){
            //insert id into the listing array
            list[i] = catList[i].getCategoryID();
        }

        Catsorted = ItemsInfo.sorting(list);
        //display the sorted
        for(int j=0;j<length;j++) {
            System.out.println("\nCategory:"+ "[" +Catsorted[j] + "]"+ Category.search(Catsorted[j], null).getCategoryName());
            plist = Product.getAllProducts(Catsorted[j]);
            if(plist[0][0] == null) {
                System.out.println("NO RECORD!");
            }
            for(int x=0;x<plist.length;x++) {
                if(plist[x][0] != null) {
                    System.out.println(plist[x][0]+":"+plist[x][1]);
                }
            }
        }
        System.out.println("=========================================");
    }


    
    //generate and write a report
    static void testReport(Company compInfo) {
        System.out.print("\u000C");
        Category [] catList = Category.getAllCategories();
        String [][] prodList = Product.getAllProducts();
        Scanner in = new Scanner(System.in);

        System.out.println("░█▀▀█ ░█▀▀▀ ░█▄─░█ ░█▀▀▀ ░█▀▀█ ─█▀▀█ ▀▀█▀▀ ░█▀▀▀ 　 ░█▀▀█ ░█▀▀▀ ░█▀▀█ ░█▀▀▀█ ░█▀▀█ ▀▀█▀▀ ");
        System.out.println("░█─▄▄ ░█▀▀▀ ░█░█░█ ░█▀▀▀ ░█▄▄▀ ░█▄▄█ ─░█── ░█▀▀▀ 　 ░█▄▄▀ ░█▀▀▀ ░█▄▄█ ░█──░█ ░█▄▄▀ ─░█── ");
        System.out.println("░█▄▄█ ░█▄▄▄ ░█──▀█ ░█▄▄▄ ░█─░█ ░█─░█ ─░█── ░█▄▄▄ 　 ░█─░█ ░█▄▄▄ ░█─── ░█▄▄▄█ ░█─░█ ─░█──");
        int year = 0,choice,month = 0;
        System.out.println("\n[1] Yearly Report");
        System.out.println("[2] Monthly Report");
        System.out.println("[99] Back to Main Menu");

        boolean flag = true;
        while(flag) {
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());

            if(choice == 1) {
                System.out.print("Enter year: ");
                year = Integer.parseInt(in.nextLine());
                flag = false;
            } else if(choice == 2){
                System.out.print("Enter year: ");
                year = Integer.parseInt(in.nextLine());

                System.out.println("Enter month: ");
                month = Integer.parseInt(in.nextLine());
                flag = false;
            } else if(choice == 99) {
                mainMenu(compInfo);
                flag = false;
            } else {
                System.out.println("Invalid choice");
            }
        }
        Report reportTest = new Report(compInfo,catList,prodList,year);
        viewSortedAll();
        reportTest.generateReportTest(month);
        reportTest.generate2pdf(month);
        pressAnyKey();
        mainMenu(compInfo);
    }

    //registering a company for fresh start/new user
    static Company registerCompany() {

        String companyName,companyPhone,companyAddress,businessNumber;
        Scanner in = new Scanner(System.in);

        System.out.println("░█▀▀█ ░█▀▀▀█ ░█▀▄▀█ ░█▀▀█ ─█▀▀█ ░█▄─░█ ░█──░█ 　 ░█▀▀█ ░█▀▀▀ ░█▀▀█ ▀█▀ ░█▀▀▀█ ▀▀█▀▀ ░█▀▀▀ ░█▀▀█ ");
        System.out.println("░█─── ░█──░█ ░█░█░█ ░█▄▄█ ░█▄▄█ ░█░█░█ ░█▄▄▄█ 　 ░█▄▄▀ ░█▀▀▀ ░█─▄▄ ░█─ ─▀▀▀▄▄ ─░█── ░█▀▀▀ ░█▄▄▀");
        System.out.println("░█▄▄█ ░█▄▄▄█ ░█──░█ ░█─── ░█─░█ ░█──▀█ ──░█── 　 ░█─░█ ░█▄▄▄ ░█▄▄█ ▄█▄ ░█▄▄▄█ ─░█── ░█▄▄▄ ░█─░█");
        
        System.out.print("\nCompany Name: ");
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
            wrt.flush();

        } catch (IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe.getMessage());
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
                    System.out.println("NO COMPANY INFO DETECTED!...");
                    tunggu(3000);
                    compData = registerCompany();
                }

            } catch (IOException ioe) {
                System.err.print("Something went Wrong!\n" + ioe.getMessage());
            }

        } else {
            compData = registerCompany();
        }

        return compData;
    }

    public static void main(String [] args) {

        //verify the user registered the company or not
        Company companyDetails = verifyCompany();
        //execute the main menu
        mainMenu(companyDetails);
    }
}