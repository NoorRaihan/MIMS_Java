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
    }
}