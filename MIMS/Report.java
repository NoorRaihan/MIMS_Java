import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

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
    public void setAll(int reportYear) {
        this.reportYear = reportYear;
    }

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
        System.out.println("--------------------TOTAL PRODUCTION EACH CATEGORIES----------------------");
        Category [] catList = super.getCategoryList();
        int length = ItemsInfo.checkLength(catList);
        for(int i=0;i<length;i++) {
            String cid = catList[i].getCategoryID();
            String cname = catList[i].getCategoryName();
            int production = calculateCategories(cid, month);
            System.out.println(cname + ":"  + production);
        }
    }

    //calculate total whole production average
    public double calculateAverage(int month) {
        Category [] data = super.getCategoryList();
        int length = ItemsInfo.checkLength(data);
        int totalProduction = 0;
        double average;

        for(int i=0; i<length; i++) {
            totalProduction += data[i].calcQuantity(month, reportYear);
        }

        average = totalProduction/length;
        return average;
    }

    //calculate lowest stocks
    public String [] findLowestStocks(String id,int month) { //receive category id
        String [][] prodList = super.getProductList();
        Product [] prodData = null;
        int stocks = -1;
        int lowest = -1;
        int yearCount = 0;
        String lowid = null;
        String tempId = null;
        String [] lowData = new String [2];

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
                                tempId = prodData[j].getProductID();
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
                        tempId = prodData[yearCount-1].getProductID();
                    } 
                }
                
                //comparing the lowest
                if(lowest<0) {
                    // System.out.println("execute3");
                    lowest = stocks;
                    lowid = tempId;
                } else if (stocks <= lowest) {
                    // System.out.println("execute4");
                    lowest = stocks;
                    lowid = tempId;
                } 
             } else {
                 continue;
             }
             yearCount = 0;
        }
        lowData[0] = lowid;
        lowData[1] = Integer.toString(lowest);
        return lowData;
    }

    //calculate highest production
    public String [] findHighestProduction(String id,int month) {
        String [][] prodList = super.getProductList();
        Product [] prodData = null;
        int tempProduction = 0;
        int lowest = -1;
        int yearCount = 0;
        String lowid = null;
        String tempId = null;
        String [] lowData = new String [2];

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
                            tempId = pid;
                        }else if(month > 0){
                            if(month == mm) {
                                tempProduction = prodData[j].getProductQuantity();
                                tempId = pid;
                                break;
                            }
                        }
                    }
                    if(lowest < 0) {
                        lowest = tempProduction;
                        lowid = tempId;
                    } else if(tempProduction >= lowest) {
                        lowest = tempProduction;
                        lowid = tempId;
                    }
                }
            } else {
                continue;
            }
            if(month == 0) {
                tempProduction = 0;
            }
        }
        lowData[0] = lowid;
        lowData[1] = Integer.toString(lowest);
        return lowData;
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

    public void bulkList(int month) {
        System.out.println("--------------------DEBUG CALCULATE BULK VALUE ALL PRODUCTS----------------------");
        String [][] prodList = super.getProductList(); //get the product id and name list
        String id;
        int bulk;

        for(int i=0;i<prodList.length;i++) {

            if(prodList[i][0] != null) {
                id = prodList[i][0];
                bulk = calcBulk(id, month);
                System.out.println(id + " : " + bulk);
            }
       }
    }

    public void viewHighest(int month) {
        System.out.println("------------------DEBUG CHECK HIGHEST PRODUCTION EACH CATEGORY---------------");
        Category [] catList = super.getCategoryList();
        int length = ItemsInfo.checkLength(catList);

        for(int i=0;i<length;i++) {
            String [] data = findHighestProduction(catList[i].getCategoryID(),month);
            String pname = data[0];
            int stocks = Integer.parseInt(data[1]);

            if(pname == null) {
                pname = "NO RECORD";
            }
            System.out.println(catList[i].getCategoryName()+":"+ pname + "=" + stocks);
        }
    }

    public void viewLowest(int month) {
        System.out.println("------------------DEBUG CHECK LOWEST STOCKS EACH CATEGORY---------------");
        Category [] catList = super.getCategoryList();
        int length = ItemsInfo.checkLength(catList);
        for(int i=0;i<length;i++) {
            String [] data = findLowestStocks(catList[i].getCategoryID(),month);
            String pname = data[0];
            int stocks = Integer.parseInt(data[1]);

            if(pname == null && stocks == -1) {
                pname = "NO RECORD";
                stocks = 0;
            }

            System.out.println(catList[i].getCategoryName()+":"+ pname + "=" + stocks);
        }
    }

    //testing all the reports calculation algortihm
    public void generateReportTest(int month) {

        //find every category
        viewTotalProd(month);

        //find average
        System.out.println("Total whole average: " + calculateAverage(month));

        //find bulk
        bulkList(month);
       //-----------------------------------------------------------------------

       //findHighest
        viewHighest(month);
        //-------------------------------------------------------------

        //find Lowest
        viewLowest(month);
        //-----------------------------------------------------------------


    }
}