import java.text.DecimalFormat;

public class Bill {
    
    private String DateGenerated;

    private Salesperson SalesStaff;
    private Car SelectedCar;
    private Customer Customer;

    private int lengthOfLoan;

    private double totalPayment;
    private double monthlyPayment;
    private double paymentLeft;
    private double carDownpayment;

    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    // default constructor
    public Bill(){}

    // normal constructor;
    public Bill(String Date , Salesperson salesStaff, Car selectCar, Customer customer,int YearPayment){
        this.DateGenerated = Date;
        this. SalesStaff = new Salesperson();
        this.SelectedCar = new Car();   
        this.Customer = new Customer();
        this.lengthOfLoan = YearPayment;

    }

    // setter

    public void setTotalPayment(double totalPayment){
        this.totalPayment = totalPayment;
    }
    public void setMonthlyPayment(double MP){
        this.monthlyPayment = MP;
    }

    public void setBill(String Date , Salesperson salesperson, Car selectCar, Customer customer,int YearPayment){
        this.DateGenerated = Date;
        this. SalesStaff = salesperson;
        this.SelectedCar = selectCar;
        this.Customer = customer;
        this.lengthOfLoan = YearPayment;
        this.paymentLeft = payBill(0);
        this.totalPayment = calcTotalPayment(selectCar);
        this.carDownpayment = calcDownpayment(selectCar);
        this.monthlyPayment = calcMonthlyPayment();
        
    }

    // processor

    public int calcMonthOfPayment(){
        int month = 0;

        month = lengthOfLoan * 12;

        return month;
    }

    public double calcTotalPayment(Car car){
        double TotalPayment = 0.00;

        TotalPayment = car.calcCarPrice();

        setTotalPayment(TotalPayment);

        return TotalPayment;
    }
    public double calcMonthlyPayment(){
        double MPayment = 0.00;

        MPayment = ((paymentLeft - carDownpayment ) / calcMonthOfPayment());

        setMonthlyPayment(MPayment);

        return MPayment;
    }


    public double payBill(double totalToPay){
        paymentLeft = paymentLeft - totalToPay;

        if(paymentLeft < 0){
                    System.out.println("+---------------------------------+");
                    System.out.println("| Balance of RM"+Math.abs(paymentLeft) + " will be returned.");
                    System.out.println("+---------------------------------+");

        }

        return totalPayment;

    }
    
    public double calcDownpayment(Car car){
        double downpayment = 0.00;

        downpayment = .1 * car.calcCarPrice();

        paymentLeft = paymentLeft-carDownpayment;

        return downpayment;
    }




    // getter
    public Salesperson getsalesPerson(){return this.SalesStaff;}
    public Car getSelectedCar(){return this.SelectedCar;}
    public Customer getCustomer(){return this.Customer;}
    public double getPaymentLeft(){return this.paymentLeft;}

    // printer

    public String SimplifiedBill(){
        String str = "+---------------------------------+" +
                   "\n|               BILL              |" + 
                   "\n+---------------------------------+" +
                   "\n+  SALESPERSON NAME  : "+ getsalesPerson().getPersonName() +
                   "\n+  CAR MODEL         : "+ getSelectedCar().getCarModel() +
                   "\n+  CAR BRAND         : "+ getSelectedCar().getCarBrand() +
                   "\n|                                  " +
                   "\n|  PAYMENT LEFT DUE  : RM"+ paymentLeft +
                   "\n+---------------------------------+";
        return str;
    }
    
    public String toString(){
      String str = "+---------------------------------+" +
                   "\n|               BILL              |" + 
                   "\n+---------------------------------+" +
                   "\n+  DATE GENERATED    : "+ DateGenerated +
                   "\n|                                  " +
                   "\n+  SALESPERSON NAME  : "+ getsalesPerson().getPersonName() +
                   "\n+  CUSTOMER NAME     : "+ getCustomer().getPersonName() +
                   "\n|                                  " +
                   "\n+  CAR MODEL         : "+ getSelectedCar().getCarModel() +
                   "\n+  CAR BRAND         : "+ getSelectedCar().getCarBrand() +
                   "\n|                                  " +
                   "\n|  TOTAL PAYMENT     : RM"+ totalPayment   +
                   "\n|                                  " +
                   "\n|  LOAN YEARS TIME   : "+lengthOfLoan+" years" +  
                   "\n|  LOAN MONTHLY TIME : "+calcMonthOfPayment()+" month"+
                   "\n|                                  " +
                   "\n|  CAR DOWNPAYMENT   : RM"+carDownpayment+ 
                   "\n|  PAYMENT PER MONTH : RM"+monthlyPayment+
                   "\n|                                  " +
                   "\n|  PAYMENT LEFT DUE  : RM"+ paymentLeft +
                   "\n+---------------------------------+";
        return str;
    }


}





