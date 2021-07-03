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

    static void mainMenu() {
         //display main menu
        Scanner in = new Scanner(System.in);
        System.out.println("\n[1] Category Section");
        System.out.println("[2] Product Section");
        System.out.println("[3] Generate Report");

        int choice;
        boolean flag = true;
        while(flag) {

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    flag = false;
                    //category menu here
                    break;
                case 2:
                    flag = false;
                    //product menu here
                    break;
                case 3:
                    flag = false;
                    //report menu here
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
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
        System.out.print("\u000C"); 
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
        bannerCompany(companyDetails);
        mainMenu();
    }
}