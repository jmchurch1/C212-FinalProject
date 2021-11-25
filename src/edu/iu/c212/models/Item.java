package edu.iu.c212.models;

public enum Item {

    TEDDYBEAR ("Teddy Bear", 2.00),
    BALL ("Ball", 3.50),
    BABYYODA ("Baby Yoda", 6.00),
    FLASHLIGHT ("Flashlight", 2.00),
    KAYAK ("Kayak", 599.99),
    BASEBALL ("Baseball", 5.00),
    BAT ("Baseball Bat", 15.00),
    KEYCHAIN ("Keychain", 1.50),
    PENCIL ("Pencil", 2.00),
    WHOOPEE ("Whoopee Cushion", 4.00),
    BUBBLES ("Bubbles", 3.00);


    // private instance variables
    double value;
    String readableName;

    // arguments
    // String readableName
    // double value
    Item(String readableName, double value){
        this.readableName = readableName;
        this.value = value;
    }

    @Override
    public String toString(){
        // print out readable name and value
        return String.format("Name: %s, Value: $%s",readableName,value);
    }

    // setters
    public void setReadableName(String readableName) {
        this.readableName = readableName;
    }

    public void setValue(double value) {
        this.value = value;
    }

    // getters
    public String getReadableName() {
        return readableName;
    }

    public double getValue() {
        return value;
    }
}
