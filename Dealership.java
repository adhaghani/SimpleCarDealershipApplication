import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dealership {
    

    private Salesperson[] SalesStaff;
    private Car[] CarInventory;
    private Customer[] CustomerData;
    private Bill[] bills;


    private int numSalesStaff;
    private int numCustomer;
    private int numCars;
    private int numBills;

    private Double TotalProfit;
    private Double TotalPaidCommission;
    private double ProfitRate;

    // constructor
    public Dealership(String DealershipName){
        this.SalesStaff = new Salesperson[4];  //4 is max number of staff
        this.CarInventory = new Car[100]; //100 car is limit
        this.CustomerData = new Customer[200]; //200 customer per system
        this.bills = new Bill[500]; //500 bill is limit per system run
        this.numSalesStaff = 0; //start with 0 staff
        this.numCustomer = 0;   //start with 0 customer
        this.numCars = 0; // start w    ith 0 car
        this.numBills = 0;
        this.TotalProfit = 0.00; // start with 0 profit
        this.TotalPaidCommission = 0.00; // start with 0 paid commission
        this.ProfitRate = 0.15; //15% profit rate
        
    }
    // getter
    public int getNumSalesStaff(){ return this.numSalesStaff;}
    public int getNumCustomer() { return this.numCustomer; }
    public int getNumCars() { return this.numCars; }
    public int getNumbills() { return this.numBills; }
    public double getProfitRate(){return this.ProfitRate;}
    public double getTotalProfit(){return this.TotalProfit;}
    public double getTotalPaidCommission(){return this.TotalPaidCommission;}
    public Salesperson[] getSalesStaff() {return this.SalesStaff;}
    public Car[] getCarInvetory(){return this.CarInventory;}
    public Customer[] getCustomerData(){return this.CustomerData;}


    // adding customer to database
    public void addCustomer(Customer customer){
                if(numCustomer < CustomerData.length){
                CustomerData[numCustomer] = customer;
            numCustomer++;
        }
        else{
            System.out.println("Cannot add more Customer. Limit is reached");
        }
    }
    public void addBill(Bill bill){
        if(numBills < bills.length){
            bills[numBills] = bill;
            numBills++;
        }
                else{
            System.out.println("Cannot add more bill. Limit is reached");
        }
    }
    // hiring staff
    public void hireSaleStaff(Salesperson salesperson){
        if(numSalesStaff < SalesStaff.length){
            SalesStaff[numSalesStaff] = salesperson;
            numSalesStaff++;
        }
        else{
            System.out.println("Cannot add more staff. Limit is reached");
        }
    }
     public void removeCarFromInventory(Car car,int index){
         for (int i = 0; i < CarInventory.length; i++) {
            if (CarInventory[i] == CarInventory[index]) {
                index = i;
                break;
            }
        }
            for(int i = index;i < CarInventory.length - 1 ;i++){
                CarInventory[i] = CarInventory[i + 1];
        }
        Car[] newCarArray = new Car[CarInventory.length - 1];
        System.arraycopy(CarInventory, 0, newCarArray, 0, newCarArray.length);

        CarInventory = newCarArray;

        numCars--;
    }
    public void addCarToInventory(Car car){
                if(numCars < CarInventory.length){
                    CarInventory[numCars] = car;
                    numCars++;
            }
               else{
                 System.out.println("Cannot add more car.Inventory is full.");
                }
    }
    public String GenerateDate(){
        String date = "";

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = currentDate.format(formatter);

        return date;
    }

    // calculating profit per car
    public double calcProfit(Car car){
        double profit = 0.00;
        profit = car.calcCarPrice() - car.getCarPrice();
        return profit;
    }
    // calculation commission per car that goes to salesPerson
    public double calcCommission(Car car, Salesperson salesperson){
        double Commission = 0.00;
        Commission = (calcProfit(car) * salesperson.getSPcommissionRate()) ;
        return Commission;
    }
    // calculating profit per car that goes to dealership
    public double calcProfitToDealership(Car car, Salesperson salesperson){
        double profit = 0.00;
        profit = calcProfit(car) - calcCommission(car, salesperson);
        return profit;
    }
    // calculating the total profit earn to Dealership;
    public double calcTotalProfit(Car car, Salesperson salesperson){

        TotalProfit += calcProfitToDealership(car, salesperson);

        return TotalProfit;
    }
    // calculating totalCommission paid to SP;
    public double calcTotalPaidCommission(Car car, Salesperson salesperson){
        TotalPaidCommission += calcCommission(car, salesperson);
        return TotalPaidCommission;
    }
    public void sellCar(int index,Car car, Salesperson salesperson, Customer customer,Bill bills){
        calcCommission(car, salesperson);
        addBill(bills);
        calcProfit(car);
        calcTotalProfit(car,salesperson);
        calcTotalPaidCommission(car, salesperson);
        removeCarFromInventory(car, index);
        salesperson.calcCommission(calcCommission(car, salesperson));
        System.out.println("+---------------------------------+");
         System.out.println("| Car sold to " + customer.getPersonName() + " by " + salesperson.getPersonName() +
         " for RM" + car.calcCarPrice());
         System.out.println("+ Revenue Gained : RM" + calcProfit(car));
         System.out.println("+ Commission to " + salesperson.getPersonName()+"    : RM" + calcCommission(car, salesperson));
         System.out.println("| profit gain    : RM" + (calcProfit(car)-calcCommission(car, salesperson)));
         System.out.println("+---------------------------------+");
         System.out.println(bills.toString());
    }
    public void printStaffCommission(Salesperson salesStaff[]){
        for(int i = 0;i<salesStaff.length;i++){
            salesStaff[i].PrintSalesPersonCommission();
        }
        System.out.println("+---------------------------------+");
         System.out.println("| Total Commission paid to Staff: RM" + getTotalPaidCommission());
        System.out.println("+---------------------------------+");
    }
    public void printStaffInformation(Salesperson salesStaff[]){
        for(int i = 0;i<salesStaff.length;i++){
            salesStaff[i].PrintSalesPersonInfo();
        }
    }
    public void PrintCarList(Car car[]){

        for(int i=0;i<numCars;i++){
                if(car[i] != null){
                    System.out.println("| Car Number : " + (i+1));
                    System.out.println(CarInventory[i]);
                }

    }
        if(numCars == 0 ){
        System.out.println("+---------------------------------+");
                System.out.println("|         UNFORTUNATELY           |");
                System.out.println("|     WE HAVE RAN OUT OF CAR      |");
                System.out.println("|     PLEASE COME AGAIN SOON      |");
                System.out.println("+---------------------------------+");
                }
        }
    public void printMainMenu(){
        System.out.println("+---------------------------------+");
        System.out.println("|  WELCOME TO KAMAL CAR SOLUTION  |");
        System.out.println("+---------------------------------+");
        System.out.println("|          [C] = Customer         |");
        System.out.println("+                                 +");
        System.out.println("|          [A] = Admin            |");
        System.out.println("+                                 +");
        System.out.println("|          [E] = exit             |");
        System.out.println("+---------------------------------+");
    }
    public void printCustomerMenu(){
            System.out.println("+---------------------------------+");
             System.out.println("|               MENU              |");
             System.out.println("+---------------------------------+");
             System.out.println("|    [C] = Choose a car to buy    |");
             System.out.println("|    [I] = Car Invenotry          |");
             System.out.println("|    [B] = BIll sectcion          |");
             System.out.println("|    [U] = User Profile           |");
             System.out.println("|    [L] = Log Out                |");
             System.out.println("+---------------------------------+");
    }
    public void PrintStaffMainMenu(){
                       // start of admin page
        System.out.println("+---------------------------------+");
        System.out.println("|           STAFF PAGE            |");
        System.out.println("+---------------------------------+");
        System.out.println("|     [P] = Total Profit          |");
        System.out.println("|     [I] = Import Data           |");
        System.out.println("+     [S] = Staff Management      +");
        System.out.println("|     [C] = Car Management        |");
        System.out.println("|     [A] = System Data           |");
        System.out.println("|     [M] = Main Menu             |");
        System.out.println("+---------------------------------+"); 
    }
    public void PrintStaffManagement(){
                       // start of admin page
        System.out.println("+---------------------------------+");
        System.out.println("|     STAFF MANAGEMENT PAGE       |");
        System.out.println("+---------------------------------+");
        System.out.println("|     [C] = staff Commission      |");
        System.out.println("|     [I] = Staff information     |");
        System.out.println("|     [M] = Main Menu             |");
        System.out.println("+---------------------------------+"); 
    }
    public void PrintCarManagement(){
                       // start of admin page
        System.out.println("+---------------------------------+");
        System.out.println("|       CAR MANAGEMENT PAGE       |");
        System.out.println("+---------------------------------+");
        System.out.println("|     [A] = Add New Car           |");
        System.out.println("|     [R] = Remove Car            |");
        System.out.println("|     [I] = Car Inventory         |");
        System.out.println("|     [M] = Main Menu             |");
        System.out.println("+---------------------------------+"); 
    }
    public void PrintTotalProfit(){
            System.out.println("+---------------------------------+");
            System.out.println("|          TOTAL PROFIT           |");
            System.out.println("+---------------------------------+");
            System.out.println("| Profit Percentage : " + (getProfitRate()*100) + "%");
            System.out.println("| Total Profit  : RM" + getTotalProfit());
            System.out.println("+---------------------------------+"); 
    }
    public void printIOmenu(){
        System.out.println("+---------------------------------+");
        System.out.println("|              IMPORT             |");
        System.out.println("+---------------------------------+");
        System.out.println("|           [I] IMPORT            |");
        System.out.println("|           [B] BACK TO MENU      |");
        System.out.println("+---------------------------------+");
    }
    public void PrintSystemAnalytics(){
        System.out.println("+---------------------------------+");
        System.out.println("|        SYSTEM ANALYTICS         |");
        System.out.println("+---------------------------------+");
        System.out.println("|  NUMBER OF CUSTOMER           : " + numCustomer);
        System.out.println("|  NUMBER OF STAFF              : " + numSalesStaff );
        System.out.println("|  NUMBER OF CAR IN INVENTORY   : " + numCars);
        System.out.println("|  EMPTY SPACE LEFT FOR CARS    : " + (100 - numCars));
        System.out.println("|  NUMBER OF CAR SOLD           : " + numBills);
        System.out.println("|  NMBER OF BILLS GENERATED     : " + numBills);
        System.out.println("+---------------------------------+");
    }
}
