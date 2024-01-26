public class Salesperson extends Person {
    
    private String SPID;
    private double SPcommissionRate;
    private double CommissionEarned;

    private Bill[] SPBill;
    private int numBill;

    private Customer[] servedCustomer;
    private int numCustomer;


    // constructor
    public Salesperson(){};
    public Salesperson(String name,String address,String ic, String phone, String SPID , double commissionRate) {
        super(name, address, ic, phone);
        this.SPID = SPID;
        this.SPcommissionRate = commissionRate;
        this.CommissionEarned = 0;
        this.numBill = 0;
        this.numCustomer = 0;
        this.SPBill = new Bill[100];
        this.servedCustomer = new Customer[100];
    }

    // setter

    public void setSalespersonData(String name,String address,String ic, String phone, String SPID , double commissionRate){
        Name = name;
        Address = address;
        IC = ic;
        Phone = phone;
        this.SPID = SPID;
        this.SPcommissionRate = commissionRate;
        this.CommissionEarned = 0;
        this.numBill = 0;
        this.numCustomer = 0;
        this.SPBill = new Bill[100];
        this.servedCustomer = new Customer[100];
    }

    


    public String getSPID(){return this.SPID;}
    public double getSPcommissionRate(){return this.SPcommissionRate;}


    // processor
       public void addBil(Bill bill){
        if( numBill < SPBill.length){
            SPBill[numBill] = bill;
            numBill++;
        }
    }
        public void addCustomer(Customer customer){
            if(numCustomer < servedCustomer.length){
                servedCustomer[numCustomer] = customer;
                numCustomer++;
            }
        }

        public void calcCommission(double earn){
            CommissionEarned += earn;
        }


    // printer

    public void PrintSalesPersonCommission(){
        System.out.println("+---------------------------------+");
        System.out.println("| Staff ID        : " + SPID);
        System.out.println("| Name            : " + Name);
        System.out.println("|");
        System.out.println("| Commission rate : " + (getSPcommissionRate()*100) + "%") ;
        System.out.println("| Commission      : RM" + CommissionEarned) ;
        System.out.println("| Customer Served : " + numCustomer);
        System.out.println("+---------------------------------+");
    }

    public void PrintSalesPersonInfo(){
                System.out.println("+---------------------------------+");
                System.out.println("| Name            : " + Name);
                System.out.println("| Address         : " + Address);
                System.out.println("| Phone           : " + Phone);
              System.out.println("|");
                System.out.println("| Staff IC        : " + IC);
                System.out.println("| Staff ID        : " + SPID);
                System.out.println("| Commission rate : " + (getSPcommissionRate()*100) + "%") ;
                System.out.println("| Commission      : RM" + CommissionEarned) ;
                System.out.println("| Customer Served : " + numCustomer);
                System.out.println("+---------------------------------+");
    }
}
