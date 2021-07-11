import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Report extends ItemsInfo {

    private int reportYear;

    //constructor
    public Report() {
        this(null,null,null,0);
    }

    public Report(Company companyInfo, Category [] catList, String[][] prodList,int reportYear) {
        
        super(companyInfo,catList,prodList);
        this.reportYear = reportYear;
    }

    //acessor
    public int getReportYear() {return reportYear;}

    //mutator
    public void setAll(int reportYear) {this.reportYear = reportYear;}

    //process

    //calculate total production each categories
    public int calculateCategories(String id,int month) {
        int production = 0;
        
        Category data = Category.search(id,null);
        if(data != null) {
            production = data.calcQuantity(month, reportYear);
        } else {
            System.out.println("Category not exist!");
        }
        return production;
    }

    //view total production each categories
    public void viewTotalProd(int month) {
        System.out.println("=========================================");
        System.out.println("TOTAL PRODUCTION EACH CATEGORY");
        System.out.println("=========================================");
        Category [] catList = super.getCategoryList();
        int length = ItemsInfo.checkLength(catList);
        String [] list = new String[length];
        for (int x=0;x<length;x++){
            //insert id into the listing array
            list[x] = catList[x].getCategoryID();
        }
        
        list = ItemsInfo.sorting(list);
        for(int i=0;i<length;i++) {
            String cid = list[i];
            String cname = Category.search(list[i],null).getCategoryName();
            int production = calculateCategories(cid, month);
            System.out.println(cname + ":"  + production);
            try {
                PrintWriter wrt = new PrintWriter(new BufferedWriter(new FileWriter("tempreport.txt",true)));

                wrt.println(cname + ":"  + production);
                wrt.close();
            }catch(IOException ioe) {
                System.err.println("Something went wrong!\n" + ioe.getMessage());
            }
        }
    }

    //calculate total whole production average
    public double calculateAverage(int month) {
        Category [] data = super.getCategoryList();
        int length = ItemsInfo.checkLength(data);
        double totalProduction = 0;
        double average;

        for(int i=0; i<length; i++) {
            totalProduction += data[i].calcQuantity(month, reportYear);
        }

        average = totalProduction/length;
        return average;
    }

    //view and write the average
    public void viewAverage(int month) {

        System.out.println("=========================================");
        System.out.println("TOTAL AVERAGE WHOLE PRODUCTION");
        System.out.println("=========================================");
        double average = calculateAverage(month);
        System.out.println("TOTAL AVERAGE PRODUCTION: " + average);
        try {
            PrintWriter wrt = new PrintWriter(new BufferedWriter(new FileWriter("tempreport.txt",true)));

            wrt.println("AVERAGE PRODUCTION : " + average);
            wrt.close();
        }catch(IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe.getMessage());
        }
    }

    //calculate lowest stocks
    public String [] findLowestStocks(String id,int month) { //receive category id
        String [][] prodList = super.getProductList();
        Product [] prodData = null;
        int stocks = -1;
        int lowest = -1;
        int yearCount = 0;
        String lowName = null;
        String tempName= null;
        String [] lowData = new String [3];

        for(int i=0;i<prodList.length;i++) {
            
            if(prodList[i][0] != null) {
                String pid = prodList[i][0];
                prodData = Product.searchPro(pid, null);
                int length = ItemsInfo.checkLength(prodData);

                for(int j=0;j<length;j++) {
                    String category = prodData[j].getCategoryID();
                    String date = prodData[j].getUpdateDate();
                    String [] parts = date.split("/",3);
                    int mm = Integer.parseInt(parts[1]);
                    int yy = Integer.parseInt(parts[2]);

                    if(category.equalsIgnoreCase(id) && reportYear == yy ) {
                        
                        if (month == 0) {
                            yearCount++;
                        } else if(month > 0){
                            if(month == mm) {
                                stocks = prodData[j].getProductStocks();
                                tempName = prodData[j].getProductName();
                                break;
                            }
                        }
                    }
                }
                // System.out.println(stocks);
                // System.out.println(lowest);
                if(month == 0) {
                    if(yearCount == 0) {
                        // System.out.println("execute1");
                        continue;
                    } else {
                        // System.out.println("execute2");
                        stocks = prodData[yearCount-1].getProductStocks();
                        tempName = prodData[yearCount-1].getProductName();
                    } 
                }
                
                //comparing the lowest
                if(lowest<0) {
                    // System.out.println("execute3");
                    lowest = stocks;
                    lowName = tempName;
                } else if (stocks <= lowest) {
                    // System.out.println("execute4");
                    lowest = stocks;
                    lowName = tempName;
                } 
             } else {
                 continue;
             }
             yearCount = 0;
        }
        lowData[0] = lowName;
        lowData[1] = Integer.toString(lowest);
        return lowData;
    }

    //calculate highest production
    public String [] findHighestProduction(String id,int month) {
        String [][] prodList = super.getProductList();
        Product [] prodData = null;
        int tempProduction = 0;
        int highest = -1;
        String highName = null;
        String tempName = null;
        String [] highData = new String [2];

        for(int i=0;i<prodList.length;i++){
            
            if(prodList[i][0] != null) {
                String pid = prodList[i][0];
                prodData = Product.searchPro(pid, null);
                int length = ItemsInfo.checkLength(prodData);

                for(int j=0;j<length;j++) {
                    String category = prodData[j].getCategoryID();
                    String date = prodData[j].getUpdateDate();
                    String [] parts = date.split("/",3);
                    int mm = Integer.parseInt(parts[1]);
                    int yy = Integer.parseInt(parts[2]);

                    if(category.equalsIgnoreCase(id) && reportYear == yy) {
                        if(month == 0) {
                            tempProduction += prodData[j].getProductQuantity();
                            tempName = prodData[j].getProductName();
                        }else if(month > 0){
                            if(month == mm) {
                                tempProduction = prodData[j].getProductQuantity();
                                tempName = prodData[j].getProductName();
                                break;
                            }
                        }
                    }
                }
                if(highest < 0) {
                    highest = tempProduction;
                    highName = tempName;
                } else if(tempProduction > highest) {
                    highest = tempProduction;
                    highName = tempName;
                }
            }else {
                continue;
            }
            if(month == 0) {
                tempProduction = 0;
            }
        }
        highData[0] = highName;
        highData[1] = Integer.toString(highest);
        return highData;
    }

    //count bulk for each category
    public int calcBulk(String id,int month) {
        int bulk=0,bulkVal=0,tempStocks=0,mm,yy;
        String tempDate;
        Product [] prodResult = Product.searchPro(id, null);
        int length = ItemsInfo.checkLength(prodResult);
        
        if(month > 0) {
            for(int i=0;i<length;i++){ //searching for specific month and year
                tempDate = prodResult[i].getUpdateDate();

                String [] parts = tempDate.split("/",3);
                mm = Integer.parseInt(parts[1]);
                yy = Integer.parseInt(parts[2]);

                if(mm == month && yy == reportYear){
                    tempStocks = prodResult[i].getProductStocks();
                    bulkVal = prodResult[i].getBulkValue();
                    break;
                }
            }
        } else if(month == 0) {
            int yearCount = 0;
            for(int i=0;i<length;i++){ //searching for specific year
                tempDate = prodResult[i].getUpdateDate();

                String [] parts = tempDate.split("/",3);
                yy = Integer.parseInt(parts[2]);

                if(yy == reportYear){
                    yearCount++;
                }
            }
            if (yearCount == 0) {
                return 0;
            } else {
                tempStocks = prodResult[yearCount-1].getProductStocks();
                bulkVal = prodResult[yearCount-1].getBulkValue();
            }
            
        }
        //counting the bulk
        if(bulkVal == 0) {
            bulk = 0;
        }else {
            while(tempStocks >= bulkVal ) {
                bulk++;
                tempStocks -= bulkVal;
            }
        }
        return bulk;
    }


    //write and view bulk list each products
    public void bulkList(int month) {
        System.out.println("=========================================");
        System.out.println("BULK VALUE FOR ALL PRODUCTS");
        System.out.println("=========================================");
        String [][] prodList = super.getProductList(); //get the product id and name list
        String id,name;
        int bulk;

        for(int i=0;i<prodList.length;i++) {

            if(prodList[i][0] != null) {
                id = prodList[i][0];
                name = prodList[i][1];
                bulk = calcBulk(id, month);
                System.out.println(name + " : " + bulk);
                try {
                    PrintWriter wrt = new PrintWriter(new BufferedWriter(new FileWriter("tempreport.txt",true)));

                    wrt.println(name + " : " + bulk);
                    wrt.close();
                }catch(IOException ioe) {
                    System.err.println("Something went wrong!\n" + ioe.getMessage());
                }
            }
       }
    }

    //write and view HIGHEST PRODUCTION EACH CATEGORY
    public void viewHighest(int month) {
        System.out.println("=========================================");
        System.out.println("HIGHEST PRODUCTION EACH CATEGORY");
        System.out.println("=========================================");
        Category [] catList = super.getCategoryList();
        int length = ItemsInfo.checkLength(catList);
        String [] list = new String[length];

        for(int x=0;x<length;x++) {
            list[x] = catList[x].getCategoryID();
        }
        list = ItemsInfo.sorting(list);
        for(int i=0;i<length;i++) {
            String [] data = findHighestProduction(list[i],month);
            String pname = data[0];
            int stocks = Integer.parseInt(data[1]);

            if(pname == null) {
                pname = "NO RECORD";
            }
            System.out.println("CATEGORY : " + Category.search(list[i], null).getCategoryName());
            System.out.println("[PRODUCT] "+pname + " : " + stocks);
            try {
                PrintWriter wrt = new PrintWriter(new BufferedWriter(new FileWriter("tempreport.txt",true)));

                wrt.println("CATEGORY : " + Category.search(list[i], null).getCategoryName());
                wrt.println("[PRODUCT] "+pname + " : " + stocks);
                wrt.close();
            }catch(IOException ioe) {
                System.err.println("Something went wrong!\n" + ioe.getMessage());
            }
        }
    }

    //write and view LOWEST STOCKS EACH CATEGORY
    public void viewLowest(int month) {
        System.out.println("=========================================");
        System.out.println("LOWEST STOCKS EACH CATEGORY");
        System.out.println("=========================================");
        Category [] catList = super.getCategoryList();
        int length = ItemsInfo.checkLength(catList);
        String [] list = new String[length];

        for(int x=0;x<length;x++) {
            list[x] = catList[x].getCategoryID();
        }

        list = ItemsInfo.sorting(list);
        for(int i=0;i<length;i++) {
            String [] data = findLowestStocks(list[i],month);
            String pname = data[0];
            int stocks = Integer.parseInt(data[1]);

            if(pname == null && stocks == -1) {
                pname = "NO RECORD";
                stocks = 0;
            }

            System.out.println("CATEGORY : " + Category.search(list[i], null).getCategoryName());
            System.out.println("[PRODUCT] "+pname + " : " + stocks);
            try {
                PrintWriter wrt = new PrintWriter(new BufferedWriter(new FileWriter("tempreport.txt",true)));

                wrt.println("CATEGORY : " + Category.search(list[i], null).getCategoryName());
                wrt.println("[PRODUCT] "+pname + " : " + stocks);
                
                wrt.close();
            }catch(IOException ioe) {
                System.err.println("Something went wrong!\n" + ioe.getMessage());
            }
        }
    }

    //write and view SORTED CATEGORIES
    public void viewSorted() {
        System.out.print("\u000C");
        System.out.println("=========================================");
        System.out.println("LISTING ALL CATEGORIES AND PRODUCTS");
        System.out.println("=========================================");
        Category [] catList = super.getCategoryList();
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
        try {
            PrintWriter wrt = new PrintWriter(new BufferedWriter(new FileWriter("tempproduct.txt",true)));

            for(int j=0;j<length;j++) {
                System.out.println("\nCATEGORY:"+ "[" +Catsorted[j] + "]"+ Category.search(Catsorted[j], null).getCategoryName());

                wrt.println("\nCATEGORY:"+ "[" +Catsorted[j] + "]"+ Category.search(Catsorted[j], null).getCategoryName());
                plist = Product.getAllProducts(Catsorted[j]);
                if(plist[0][0] == null) {
                    System.out.println("NO RECORD!");
                    wrt.println("NO RECORD!");
                }
                for(int x=0;x<plist.length;x++) {
                    if(plist[x][0] != null) {
                        System.out.println("[PRODUCT] " + plist[x][0]+":"+plist[x][1]);
                        wrt.println("[PRODUCT] " + plist[x][0]+":"+plist[x][1]);
                    }
                }
            }
            wrt.close();
        }catch(IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe.getMessage());
        }
        System.out.println("=========================================");
    }

    //use for writing a banner to textfile
    static void banner(String title) {
        try {
            PrintWriter wrt = new PrintWriter(new BufferedWriter(new FileWriter("tempreport.txt",true)));
            wrt.println("==========================================");
            wrt.println(title);
            wrt.println("==========================================");
            wrt.close();
        }catch(IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe.getMessage());
        }
    }


    //generate all the reports calculation algortihm
    public void generateReportTest(int month) {

        //write sorting
        viewSorted();

        banner("TOTAL PRODUCTION EACH CATEGORY");
        //find every category
        viewTotalProd(month);

        banner("AVERAGE WHOLE PRODUCTION");
        //find average
        viewAverage(month);

        banner("BULK VALUE EVERY PRODUCTS");
        //find bulk
        bulkList(month);

        banner("HIGHEST PRODCUTION EACH CATEGORY");
        //findHighest prodcution
        viewHighest(month);

        banner("LOWEST STOCKS EACH CATEGORY");
        //find Lowest
        viewLowest(month);
        
    }


    //generating report in pdf format
    public void generate2pdf(int month) {

        PDDocument doc = new PDDocument();
        PDPage blankPage = new PDPage();
        PDPage blankPage2 = new PDPage();

        try{
            doc.addPage(blankPage);
            doc.addPage(blankPage2);
            PDPageContentStream content = new PDPageContentStream(doc, blankPage);
            PDPageContentStream content2 = new PDPageContentStream(doc, blankPage2);
            
            //begin write text
            //title of report
            content.beginText();
            content.setFont(PDType1Font.TIMES_BOLD, 36);
            content.newLineAtOffset(30, 750);
            if(month > 0) {
                content.showText("MONTHLY REPORT");
            } else {
                content.showText("YEARLY REPORT");
            }
            content.endText();

            //company info
            content.beginText();
            content.setFont(PDType1Font.TIMES_BOLD, 13);
            content.newLineAtOffset(30, 730);
            content.showText(super.getCompanyInfo().getCompanyName());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.TIMES_BOLD, 13);
            content.newLineAtOffset(30, 715);
            content.showText(super.getCompanyInfo().getCompanyAddress());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.TIMES_BOLD, 13);
            content.newLineAtOffset(30, 700);
            content.showText(super.getCompanyInfo().getCompanyPhone());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.TIMES_BOLD, 13);
            content.newLineAtOffset(30, 685);
            content.showText(super.getCompanyInfo().getBusinessNumber());
            content.endText();
            
            String date = null; 
            content.beginText();
            content.setFont(PDType1Font.TIMES_BOLD, 13);
            content.newLineAtOffset(30, 660);
            if(month > 0) {
                date = "GENERATED FOR " + month + "/" + reportYear;
                content.showText(date);
            } else if(month == 0){
                date = "GENERATED FOR " + reportYear;
                content.showText(date);
            }
            content.endText();
            
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 11);
            content.newLineAtOffset(30, 550);
            content.showText("==========================================");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 11);
            content.newLineAtOffset(30, 535);
            content.showText("ALL CATEGORIES AND PRODUCTS");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 11);
            content.newLineAtOffset(30, 520);
            content.showText("==========================================");
            content.endText();

            //copy all the things in tempproduct.txt to pdf
            try {
                File tempfile = new File("tempproduct.txt");
                BufferedReader in = new BufferedReader(new FileReader("tempproduct.txt"));
                String dataLine = in.readLine();
                int yaxis = 505;
                while(dataLine != null) {
                    dataLine = dataLine.replaceAll("\t", "          ");//fix pdfbox not support the tab brrr
                    content.beginText();
                    content.setFont(PDType1Font.HELVETICA, 11);
                    content.newLineAtOffset(30, yaxis);
                    content.showText(dataLine);
                    content.endText();
                    dataLine = in.readLine();
                    yaxis -= 15;
                }
                in.close();
                tempfile.delete();
            }catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }

            //copy all the things in tempreport.txt to pdf
            try {
                File tempfile = new File("tempreport.txt");
                BufferedReader in = new BufferedReader(new FileReader("tempreport.txt"));
                String dataLine = in.readLine();
                int yaxis2 = 700;
                while(dataLine != null) {
                    dataLine = dataLine.replaceAll("\t", "          ");//fix pdfbox not support the tab brrr
                    content2.beginText();
                    if(dataLine.equals("==========================================")) {
                        content2.setFont(PDType1Font.HELVETICA_BOLD, 11);
                    } else {
                        content2.setFont(PDType1Font.HELVETICA, 11);
                    }
                    content2.newLineAtOffset(30, yaxis2);
                    content2.showText(dataLine);
                    content2.endText();
                    dataLine = in.readLine();
                    yaxis2 -= 15;
                }
                in.close();
                tempfile.delete();
            }catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
            content.close();
            content2.close(); //close the writing process
            String savefile = "report_" + month + "_" + reportYear + ".pdf";
            doc.save(savefile);
            doc.close();
            System.out.println("\nReport generated on " + savefile);
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }

    
    }

    public String toString() {
        return super.toString() + "\nReport Year: " + reportYear;
    }
}