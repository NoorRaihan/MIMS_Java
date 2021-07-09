public class ItemsInfo {

    private Company companyInfo;
    private Category [] catList;
    private String [][] prodList;

    //constructor
    public ItemsInfo() {
        this(null,null,null);
    }

    public ItemsInfo(Company companyInfo, Category [] catList, String[][] prodList) {

        this.companyInfo = companyInfo;
        this.catList = catList;
        this.prodList = prodList;
    }

    //accessor
    public Company getCompanyInfo() {return companyInfo;}
    public Category [] getCategoryList() {return catList;}
    public String [][] getProductList() {return prodList;} 

    
    //mutator
    public void setAll(Company companyInfo, Category [] catList, String[][] prodList) {

        this.companyInfo = companyInfo;
        this.catList = catList;
        this.prodList = prodList;
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
}