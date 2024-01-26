import java.util.*;
import java.io.*;

public class CarDealershipApp { 

    private static final String CAR_FILE = "Car.txt";
    public static int Size = 100;
    public static void main(String[] args) throws Exception{ 
        // Create a new Dealership
        Dealership dealership = new Dealership("KAMAL CAR SOLUTION SDN BHD");

        Customer[] customer = new Customer[Size];
        Salesperson[] SalesStaff = new Salesperson[3];
        Car[] cars = new Car[Size];

        // add salestaff
        SalesStaff[0] = new Salesperson("SITI BINTI HALIM","NO 30, JALAN RIMAU 16, TAMAN RIMAU,43650, BANDAR BARU BANGI","650525035450","011-2345 6789","SP01", 0.1);
        SalesStaff[1] = new Salesperson("AHMAD BIN MUHAMMAD","NO 25, JALAN EMPAYAN 4,TAMAN EMPAYAN,43650, BANDAR BARU BANGI","650525035450","011-2345 6789","SP02", 0.15);
        SalesStaff[2] = new Salesperson("ABU BIN KASIM","NO 1, JALAN EMPAYAN 20, TAMAN EMPAYAN,43650, BANDAR BARU BANGI","650525035450","012-4363 5764","SP03", 0.12);

        for(int i = 0; i < SalesStaff.length; i++){
            dealership.hireSaleStaff(SalesStaff[i]);
        }
        // get customer data
        Scanner input = new Scanner(System.in);
        String optionTemp;
        char option;

        readFile(cars, dealership);
        do{
            
        dealership.printMainMenu();

        System.out.print("| Choose one to continue : ");
         optionTemp = input.next();
        option = optionTemp.toUpperCase().charAt(0);

        if(option == 'C'){
            Scanner customerin = new Scanner(System.in);
            String optionCust = "E";
            while(optionCust.toUpperCase().charAt(0) != 'M'){
             System.out.println("+---------------------------------+");
             System.out.println("|              LOGIN              |");
             System.out.println("+---------------------------------+");
             System.out.println("|    [L] = Login to account       |");
             System.out.println("|    [R] = Register an account    |");
             System.out.println("|    [M] = Back to menu           |");
             System.out.println("+---------------------------------+");
            System.out.print("| Choose : ");
             optionCust = customerin.next();
             if(optionCust.toUpperCase().charAt(0) == 'R'){
                RegisterCustomerProcess(customer, dealership);
                }
             else if(optionCust.toUpperCase().charAt(0) == 'L'){
                int attempt = 3;
                boolean success = false;
                Scanner customerLogin = new Scanner(System.in);
                if(dealership.getNumCustomer() == 0){
                System.out.println("+---------------------------------+");
                System.out.println("|    ERROR, NO CUSTOMER DATA      |");
                System.out.println("|    PLEASE CREATE AN ACCOUNT     |");
                System.out.println("+---------------------------------+");
                break;
                }
                else{
                System.out.println("+---------------------------------+");
                System.out.println("|              LOGIN              |");
                System.out.println("+---------------------------------+");
                }
                
                while(attempt != 0 && success == false){
                    
                    System.out.print("| IC NUMBER [without -] : ");
                    String IC = customerLogin.nextLine();
                    System.out.print("| PASSWORD :");
                    String pass = customerLogin.nextLine();
            
                    int indexofCustomer = 0;
        
            
            for(int j = 0 ; j<dealership.getNumCustomer();j++){
                
            if(customer[j].getCustPassword().equals(pass) && customer[j].getPersonIC().equals(IC)){
                String optionCust2 = "Meee";
                    success = true;
                    System.out.println("+---------------------------------+");
                    System.out.println("|             SUCCESS             |");
                    System.out.println("+---------------------------------+");
                    indexofCustomer = j;
                     do{
                Scanner customerMenu = new Scanner(System.in);
                dealership.printCustomerMenu();
               System.out.print("| Choose : ");
            optionCust2 = customerMenu.nextLine();
            if(optionCust2.toUpperCase().charAt(0) == 'C'){
            if(dealership.getNumCars() == 0){
            System.out.println("+---------------------------------+");
                System.out.println("|         UNFORTUNATELY           |");
                System.out.println("|     WE HAVE RAN OUT OF CAR      |");
                System.out.println("|     PLEASE COME AGAIN SOON      |");
                System.out.println("+---------------------------------+");
        }
        else{
        System.out.println("+---------------------------------+");
        System.out.println("|          Available Car          |");
        System.out.println("+---------------------------------+");

        dealership.PrintCarList(cars);
         while(true){
        System.out.println("+---------------------------------+");
        System.out.println("|           Choose a car          |");
        System.out.println("+---------------------------------+");
        System.out.print("|  car number : ");
        int carChoice = customerin.nextInt();

        if(carChoice >=1 && carChoice <= cars.length){
            Car selectedCar = cars[ carChoice - 1];

            //code index to randomly assign a salesperson to a customer
            int startIndex = 1;
            int endIndex = dealership.getNumSalesStaff();
            Random random = new Random();
            int randomIndex = random.nextInt(endIndex - startIndex ) + startIndex;

            
            System.out.println("+---------------------------------+");
            System.out.println("| Mr." + SalesStaff[randomIndex].getPersonName() + "( ID: " + SalesStaff[randomIndex].getSPID() + " ) " + "will handle\n| your purchase transaction.");
            System.out.println("+---------------------------------+");
            System.out.println("| do you wish to proceed and buy the following vehicle :");
            System.out.println(selectedCar);
            System.out.print("| Answer Y/N : ");
            String answer = customerin.next();
            SalesStaff[randomIndex].addCustomer(customer[indexofCustomer]);
            if(answer.toUpperCase().charAt(0) == 'Y'){
                System.out.println("+---------------------------------+");
                System.out.println("|       Choose Loan Length        |");
                System.out.println("+---------------------------------+");
                System.out.println("|   [6] = 6 Year Loan             |");
                System.out.println("|   [9] = 9 Year Loan             |");
                System.out.println("+---------------------------------+");
                System.out.print("| Answer : ");
                String paymentAnswer = customerin.next();
                int loanLength = 0;
                
                Bill bills = new Bill();
                String date = dealership.GenerateDate();
                
                if(paymentAnswer.charAt(0) == '6'){
                loanLength = 6;
                bills.calcTotalPayment(selectedCar);
                bills.setBill(date, SalesStaff[randomIndex], selectedCar, customer[indexofCustomer],loanLength);
                bills.payBill(bills.calcDownpayment(selectedCar));
                customer[indexofCustomer].addPurchasedCar(cars[carChoice - 1]);
                SalesStaff[randomIndex].addBil(bills);
                customer[indexofCustomer].addBil(bills);
                deleteCarData(cars, dealership, (carChoice-1));
                dealership.sellCar(carChoice-1,cars[carChoice-1], SalesStaff[randomIndex], customer[indexofCustomer],bills);
                
                System.out.println(dealership.getNumbills());
                break;
            }
                else if(paymentAnswer.charAt(0) == '9'){
                loanLength = 9;
                bills.calcTotalPayment(selectedCar);
                bills.setBill(date, SalesStaff[randomIndex], selectedCar, customer[indexofCustomer],loanLength);
                bills.payBill(bills.calcDownpayment(selectedCar));
                customer[indexofCustomer].addPurchasedCar(cars[carChoice - 1]);
                SalesStaff[randomIndex].addBil(bills);
                customer[indexofCustomer].addBil(bills);
                deleteCarData(cars, dealership, (carChoice-1));
                dealership.sellCar(carChoice-1,cars[carChoice-1], SalesStaff[randomIndex], customer[indexofCustomer],bills);
                System.out.println(dealership.getNumbills());
                break;
                }

            }
            else{
                System.out.println("+---------------------------------+");
                System.out.println("|       Choose another car ?      |");
                System.out.println("+---------------------------------+");
                System.out.print("| Answer Y/N : ");
                answer = customerin.next();
                if(answer.toUpperCase().charAt(0) == 'Y'){
                    dealership.PrintCarList(cars);
                }
                else{
                    System.out.println("+---------------------------------+");
                    System.out.println("|    Thank you for using us !     |");
                    System.out.println("+---------------------------------+");
                    break;
                }
                


            }

        }
        else {
            System.out.println("|  Invalid car choice.");
        }
    }
        
        }
        // opt user to choose which car to buyE
            }
            else if(optionCust2.toUpperCase().charAt(0) == 'I'){
            // display all available car list
            System.out.println("+---------------------------------+");
            System.out.println("|          Available Car          |");
            System.out.println("+---------------------------------+");

            dealership.PrintCarList(cars);
            }
            else if(optionCust2.toUpperCase().charAt(0) == 'B'){

            Scanner optionbillCust = new Scanner(System.in);

            int repeat5 = 0;
            do{
             System.out.println("+---------------------------------+");
             System.out.println("|            BILL MENU            |");
             System.out.println("+---------------------------------+");
             System.out.println("|    [P] = Pay Bill               |");
             System.out.println("|    [L] = List of Bill           |");
             System.out.println("|    [B] = Back to Menu           |");
             System.out.println("+---------------------------------+");
            System.out.print("| Choose : ");
            String optionBill = optionbillCust.next();
            Bill[] customerbill = customer[indexofCustomer].getCustBill();
            if(optionBill.toUpperCase().charAt(0) == 'P'){
                    int chosenBill = 0;
                    double amountToPay = 0;
                if(customer[indexofCustomer].getCustNumBill() == 0) {
                        System.out.println("| NO BILL AVAILABLE");
                }
                else {
                    for(int i =0;i<customer[indexofCustomer].getCustNumBill(); i++){
                    
                    
                    Bill bill = customerbill[i];
                    System.out.println("| BILL NUMBER " + (i+1));
                    System.out.println(bill.SimplifiedBill());
                    
                }
                    System.out.print("| Choose number of bills : ");
                    chosenBill = optionbillCust.nextInt();
                    int indexofBill = chosenBill - 1;
                    if(indexofBill > 99 || indexofBill < 0){
                        System.out.println("| ERROR : Bill Not Found.");
                        System.out.println("| Going back to menu.");
                        break;
                    }
                    Bill bill = customerbill[indexofBill];

                    if(bill != null){
                    System.out.println("+---------------------------------+");
                    System.out.println("|          BILL NUMBER "+chosenBill+"          |");
                    System.out.println("|         HAS BEEN CHOSEN         |");
                    System.out.println("+---------------------------------+");
                    System.out.println(bill);
                    System.out.println("| How much would you like to pay ?");
                    System.out.print("| RM");
                    amountToPay = optionbillCust.nextDouble();
                    bill.payBill(amountToPay);
                    System.out.println("+---------------------------------+");
                    System.out.println("|        PAYMENT SUCCESSFUL       |");
                    System.out.println("+---------------------------------+");


                    if(bill.getPaymentLeft() == 0 || bill.getPaymentLeft() < 0){
                        customer[indexofCustomer].removeBill(bill, indexofBill);
                    System.out.println("+---------------------------------+");
                    System.out.println("|         BILL FULLY PAID         |");
                    System.out.println("|         TERMINATING BILL        |");
                    System.out.println("+---------------------------------+");
                    }
                    }
                    else{
                        System.out.println("| ERROR, NO BILL FOUND");
                    }
                }
                
            }
           else if(optionBill.toUpperCase().charAt(0) == 'L'){
                for(int i = 0; i < customer[indexofCustomer].getCustNumBill(); i++){
                    Bill bill = customerbill[i];
                    System.out.println(bill.SimplifiedBill());
                }
                if(customer[indexofCustomer].getCustNumBill() == 0) {
                        System.out.println("| NO BILL AVAILABLE");
                }
            }
            else if(optionBill.toUpperCase().charAt(0) == 'B'){
                repeat5 = 1;
            }
            }while(repeat5 == 0);

            }
            else if(optionCust2.toUpperCase().charAt(0) == 'U'){
                System.out.println(customer[indexofCustomer]);
            }
            else if(optionCust2.toUpperCase().charAt(0) == 'L'){
                // memang kosong sebab taknak buat apa apa sebenarnya
            }
            else{
                System.out.println("| ERROR : Wrong Code Entered");
            }
              


            }while(optionCust2.toUpperCase().charAt(0) != 'L');
        
                }  
            else{
                System.out.println("+---------------------------------+");
                System.out.println("|              ERROR              |");
                System.out.println("|     YOU HAVE "+(attempt-1)+" ATTEMPT LEFT     |");
                System.out.println("+---------------------------------+");
                    attempt--;
            }
            }
           
        }
         }
        }
    }
        else if(option == 'A'){
            Scanner admin = new Scanner(System.in);
            int attemptleft = 3; 
            boolean success = false;
            System.out.println("+---------------------------------+");
            System.out.println("|           ADMIN PAGE            |");
            System.out.println("+---------------------------------+");
            System.out.println("|          PLEASE LOGIN           |");
            System.out.println("|        YOU HAVE 3 ATTEMPT       |");
            System.out.println("+---------------------------------+");
            while(attemptleft != 0 && success == false){
                System.out.print("| Username : ");
                String username = admin.nextLine();
                System.out.print("| Password : ");
                String Password = admin.nextLine();
            if(username.equals("staffKamalSolution") && Password.equals("admin123")){
                success = true;
            int repeat2 = 0;
            while(repeat2 != 1){
            dealership.PrintStaffMainMenu();
            System.out.print("| Choose one to continue : ");
            String option2 = input.next();

            if(option2.toUpperCase().charAt(0) == 'P'){
                dealership.PrintTotalProfit();
            }
            else if(option2.toUpperCase().charAt(0) == 'I'){

                Scanner adminIO = new Scanner(System.in);
                
                String optionIO = "C";
                while(optionIO.toUpperCase().charAt(0) != 'B'){
                dealership.printIOmenu();
                System.out.print("| Choose : ");
                optionIO = adminIO.next();

                if(optionIO.toUpperCase().charAt(0) == 'I'){
                        importCar(cars, dealership,"ImportCar.txt");
                }else{

                }
            }
            }
            else if(option2.toUpperCase().charAt(0) == 'C'){

                Scanner Manage = new Scanner(System.in);
                String carOption = "G";

                while(carOption.toUpperCase().charAt(0) != 'M'){
                dealership.PrintCarManagement();
                System.out.print("| Choose : ");
                carOption = Manage.nextLine();
                 if(carOption.toUpperCase().charAt(0) == 'A'){
                addNewCar(cars, dealership);
                }
 
                else if(carOption.toUpperCase().charAt(0) == 'R'){
                    RemoveCar(cars, dealership);
                }               
                else if(carOption.toUpperCase().charAt(0) == 'I'){
                System.out.println("+---------------------------------+");
                System.out.println("|            CAR STOCK            |");
                System.out.println("+---------------------------------+");
                dealership.PrintCarList(cars);
            }
            else {

            }
            }  


            }
            else if(option2.toUpperCase().charAt(0) == 'S'){
            Scanner Manage = new Scanner(System.in);
            String staffOption = "G";
            while(staffOption.toUpperCase().charAt(0) != 'M'){

            dealership.PrintStaffManagement();
            System.out.print("| Choose : ");
            staffOption = Manage.nextLine();

                if(staffOption.toUpperCase().charAt(0) == 'C'){
                    dealership.printStaffCommission(SalesStaff);
                }
                else if(staffOption.toUpperCase().charAt(0) == 'I'){
                    dealership.printStaffInformation(SalesStaff);
                }
                else{
                }
            }  

            }
            else if(option2.toUpperCase().charAt(0) == 'A'){
                dealership.PrintSystemAnalytics();
            }
            else if(option2.toUpperCase().charAt(0) == 'M'){
                repeat2++;
            }
            }
        }
            else {
                attemptleft--;
                System.out.println("+---------------------------------+");
                System.out.println("|              ERROR              |");
                System.out.println("|     YOU HAVE "+attemptleft+" ATTEMPT LEFT     |");
                System.out.println("+---------------------------------+");
            }

        }
        }
        else if(option == 'E'){
            System.out.println("+---------------------------------+");
            System.out.println("|    THE SYSTEM WILL EXIT NOW     |");
            System.out.println("+---------------------------------+");

        }
        }while(option != 'E');

        
    }
    public static void RegisterCustomerProcess(Customer[] customer,Dealership dealership){
                int j = 0;
                while(j < 1){
                    Scanner CustomerRegist = new Scanner(System.in);

                    customer[dealership.getNumCustomer()] = new Customer();

                    System.out.println("+---------------------------------+");
                    System.out.println("|      Fill you information       |");
                    System.out.println("+---------------------------------+");
                      System.out.print("| Full Name      : ");
                    String name = CustomerRegist.nextLine();    
                      System.out.print("| NRIC [no - ]   : ");
                    String ic = CustomerRegist.nextLine();
                      System.out.print("| Set a Password : ");
                    String password = CustomerRegist.nextLine();
                      System.out.print("| Home Address   : ");
                    String address = CustomerRegist.nextLine();
                      System.out.print("| Phone Number   : ");
                    String phone = CustomerRegist.nextLine();       

                    name = name.toUpperCase();
                    address = address.toUpperCase();
                        customer[dealership.getNumCustomer()].setCustomerData(name, address, ic, phone, password);

                    System.out.print(customer[dealership.getNumCustomer()]);
                    System.out.println("\n+---------------------------------+");
                    System.out.println("|   is the information correct ?  |");
                    System.out.println("+    [Y] = Yes   ||   [N] = NO    +");
                    System.out.println("+---------------------------------+");
                      System.out.print("| Answer : ");
                    String answer = CustomerRegist.nextLine();
                    
                    
                    if(answer.toUpperCase().charAt(0) == 'Y'){

                        dealership.addCustomer(customer[dealership.getNumCustomer()]);
                        
                        System.out.println(customer[dealership.getNumCustomer() - 1]);
                        System.out.println(dealership.getNumCustomer());
                        System.out.println(customer.length);
                        j++;
                    }
                    else{
                        System.out.println("| Please fill the the form again.");
                    }       
        }
   }
    public static void addNewCar(Car[] cars, Dealership dealership) throws Exception {
            System.out.println("+---------------------------------+");
            System.out.println("|          ADD NEW CAR            |");
            System.out.println("+---------------------------------+");
                Scanner adminCar = new Scanner(System.in);
                System.out.println("+---------------------------------+");
                System.out.print("| Car Brand : ");
                String carB = adminCar.nextLine();
                System.out.print("| Car Model : ");
                String carM = adminCar.nextLine();
                System.out.print("| Car Type  : ");
                String carT = adminCar.nextLine();
                System.out.print("| Car Price : RM");
                double carp = adminCar.nextDouble();

                cars[dealership.getNumCars()] = new Car(carB, carM, carT, carp);

                 dealership.addCarToInventory(cars[dealership.getNumCars()]);
            try{
                writeFile(cars, dealership);
            }
            catch(Exception ex){}
            
                System.out.println("+---------------------------------+");
                System.out.println("| Car Added Successfully.");
                System.out.println("+---------------------------------+");

    }
    public static void RemoveCar(Car[] cars, Dealership dealership) throws FileNotFoundException, Exception{
        Scanner adminCar = new Scanner(System.in);
        int error = 1;
        while(error == 1){
        if(dealership.getNumCars() == 0){
        System.out.println("+---------------------------------+");
        System.out.println("|       NO CARS IN INVENTORY      |");
        System.out.println("+---------------------------------+");
            break;
        }

        System.out.println("+---------------------------------+");
        System.out.println("|         REMOVE A CAR            |");
        System.out.println("+---------------------------------+");
        dealership.PrintCarList(cars);
        System.out.println("+---------------------------------+");
        System.out.println("|      Choose car to remove       |");
        System.out.println("+---------------------------------+");
        System.out.print("|  car number : ");
        int indextoRemove = adminCar.nextInt();
        indextoRemove = indextoRemove - 1;

        if(cars[indextoRemove] == null){
        System.out.println("+---------------------------------+");
        System.out.println("|   A     CAR DOES NOT EXIST       |");
        System.out.println("+---------------------------------+");
        }

        else {
        Car carToRemove = cars[indextoRemove];
        dealership.removeCarFromInventory(carToRemove, indextoRemove);

        System.out.println("+---------------------------------+");
        System.out.println("|     CAR REMOVED SUCCESSFULLY    |");
        System.out.println("+---------------------------------+");
        error = 0;

        deleteCarData(cars, dealership, indextoRemove);
        }
        
        }

    } 
    public static void importCar(Car[] cars, Dealership dealership,String Filename) throws Exception {

        File CarImport = new File(Filename);
        FileReader CarReader = new FileReader(CarImport);
        BufferedReader bufferRead = new BufferedReader(CarReader);
        

        int carnum = 0;
            String line;
            while((line = bufferRead.readLine()) != null){
                StringTokenizer tokenizer = new StringTokenizer(line,";");

                if(tokenizer.countTokens() == 4){
                    carnum++;
                    String Brand = tokenizer.nextToken();
                    String Model = tokenizer.nextToken();
                    String Type = tokenizer.nextToken();
                    double price = Double.parseDouble(tokenizer.nextToken());

                    cars[dealership.getNumCars()] = new Car(Brand, Model, Type, price);

                    dealership.addCarToInventory(cars[dealership.getNumCars()]);

                    try{
                        writeFile(cars, dealership);
                    }catch(Exception ex){}
                }
            }
                        System.out.println("| "+carnum+" Car(s) Imported.");
                        System.out.println("| Car Imported Successfully.");
                        bufferRead.close();

            clearFile(Filename);
  } 
  public static void writeFile(Car[] cars, Dealership dealership) throws Exception {
        try{
                        // EXPORT CAR 
                        File ExportCar = new File(CAR_FILE);
                        FileWriter WriteCar = new FileWriter(ExportCar);
                        PrintWriter PrintCar = new PrintWriter(WriteCar);
                        for(int i =0;i<cars.length;i++){
                            if(cars[i] != null){
                                Car[] carData = dealership.getCarInvetory();
                                PrintCar.println(carData[i].WriteString());
                            }
                        }
                        PrintCar.close();
        }   
        catch(Exception ex){}
    }
    public static void readFile(Car[] cars, Dealership dealership) throws Exception {
        try{
            File CarImport = new File(CAR_FILE);
            FileReader CarReader = new FileReader(CarImport);
            BufferedReader bufferRead = new BufferedReader(CarReader);

            int carnum = 0;
                        String line;
                        while((line = bufferRead.readLine()) != null){
                            StringTokenizer tokenizer = new StringTokenizer(line,";");

                            if(tokenizer.countTokens() == 4){
                                carnum++;
                                String Brand = tokenizer.nextToken();
                                String Model = tokenizer.nextToken();
                                String Type = tokenizer.nextToken();
                                double price = Double.parseDouble(tokenizer.nextToken());
                                cars[dealership.getNumCars()] = new Car(Brand, Model, Type, price);
                                dealership.addCarToInventory(cars[dealership.getNumCars()]);
                            }
                        }
                        bufferRead.close();
        }
        catch(Exception ex){}
    }
    public static void clearFile(String fileName){

        try {
            // CLEAR OUT IMPORt.TXT
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
    }

    } 
    public static void deleteCarData(Car[] cars, Dealership dealership,int indextoRemove) throws FileNotFoundException, Exception {
            
        File OriginalFile = new File(CAR_FILE);
        File tempFile = new File("tempt.txt");

            try {
                
                BufferedReader reader = new BufferedReader(new FileReader(OriginalFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String currentLine;
                int CurrentLineNumber = 1;
                int LineToDelete = (indextoRemove+1);

                while((currentLine = reader.readLine()) != null){
                    if(CurrentLineNumber != LineToDelete){
                        writer.write(currentLine);
                        writer.newLine();
                    }
                    CurrentLineNumber++;
                }

                 writer.close();
                reader.close();

                boolean successful = OriginalFile.delete();
                boolean successful2 = tempFile.renameTo(OriginalFile);

                System.out.println(successful);
                System.out.println(successful2);

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
