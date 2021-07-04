//Main Coder:Raihan

public class Company {

    private String companyName;
    private String companyPhone;
    private String companyAddress;
    private String businessNumber;

    //default constructor
    public Company(){

        this(null,null,null,null); //execute the normal constructor instead
    }

    //normal constructor
    public Company(String companyName, String companyPhone, String companyAddress, String businessNumber) {
        
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.companyAddress = companyAddress;
        this.businessNumber = businessNumber;

    }

    //mutator
    public void setAll(String companyName, String companyPhone, String companyAddress, String businessNumber) {
        
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyAddress = companyAddress;
        this.businessNumber = businessNumber;
    }

    //accessor
    public String getCompanyName() {return companyName;}
    public String getCompanyPhone() {return companyPhone;}
    public String getCompanyAddress() {return companyAddress;}
    public String getBusinessNumber() {return businessNumber;}

}