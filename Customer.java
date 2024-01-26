
public class Customer extends Person {

    private String password;

    private int CustnumPurchasedCars;
    private int numBill;

    private Car[] CustpurchasedCars;
    private Bill[] custBill;

    

    // consturctor
    public Customer(){};

    public Customer(String CustName, String CustAddress, String CustIC, String CustPhone, String Password) {
        super(CustName, CustAddress, CustIC, CustPhone);
        this.password = Password;
        this.numBill = 0;
        this.CustnumPurchasedCars = 0;
        this.password = null;
    }

    // mutator

 
       public void setCustPassword(String Passowrd){
        this.password = Passowrd;
    }

    public void setCustomerData(String CustName, String CustAddress, String CustIC, String CustPhone,String Password) {
        this.Name = CustName;
        this.Address = CustAddress;
        this.IC = CustIC;
        this.Phone = CustPhone;
        this.password = Password;
        this.CustpurchasedCars = new Car[100];
        this.custBill = new Bill[100];
        this.numBill = 0;
        this.CustnumPurchasedCars = 0;
        this.Age = calcAge();
    }


    public String getCustPassword(){return this.password;}

    public Bill[] getCustBill(){return custBill;}
    public int getCustNumBill(){return numBill;}
    public Car[] getCustPurchasedCars() {return CustpurchasedCars;}


    // prcoessor

    public void addPurchasedCar(Car car){
        if( CustnumPurchasedCars < CustpurchasedCars.length){
            CustpurchasedCars[CustnumPurchasedCars] = car;
            CustnumPurchasedCars++;
        }
    }

    public void addBil(Bill bill){
        if( numBill < custBill.length){
            custBill[numBill] = bill;
            numBill++;
        }
    }

     public void removeBill(Bill bill,int index){
         for (int i = 0; i < custBill.length; i++) {
            if (custBill[i] == custBill[index]) {
                index = i;
                break;
            }
        }
            for(int i = index;i < custBill.length - 1 ;i++){
                custBill[i] = custBill[i + 1];
        }
        Bill[] newCustBill = new Bill[custBill.length - 1];
        System.arraycopy(custBill, 0, newCustBill, 0, newCustBill.length);

        custBill = newCustBill;

        numBill--;
    

    }

    


    // printer
    public String toString(){
        String info = "+---------------------------------+"+
                      "\n| Customer Name           : " + Name.toUpperCase() +
                      "\n| Customer IC             : " + IC +
                      "\n| Customer age            : " + calcAge() +
                      "\n| Customer Address        : " + Address.toUpperCase() +
                      "\n| Customer Phone number   : " + Phone +
                      "\n| Number of Car Purchased : " + CustnumPurchasedCars + " cars";
        return info;
    }
}
