public class Car {
    
    private String carBrand;
    private String carModel;
    private String carType;
    private double carPrice;




    // constructor
    public Car(){}
    public Car(String carBrand, String carModel, String carType, double carPrice) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carType = carType;
        this.carPrice = carPrice;
    }
        
    // getter
    public String getCarBrand(){return this.carBrand;}
    public String getCarModel(){return this.carModel;}
    public double getCarPrice(){return this.carPrice;}

    // processor

    public double calcCarPrice(){

        double price = 0.00;
        
        price = getCarPrice() + (getCarPrice() * .15);

        return price;
    }
    // printer
    @Override
    public String toString(){
        String info = "+---------------------------------+" +
                    "\n| Car Brand         : " + carBrand +
                    "\n| Car Model         : " + carModel +
                    "\n| Car Type          : " + carType +
                    "\n|" +
                    "\n| Car Price         : RM" + calcCarPrice() +
                    "\n+---------------------------------+";
        return info;
    }

    public String WriteString(){
        String write = carBrand + ";" + carModel + ";" + carType + ";" + carPrice;
        return write;
    }

}
