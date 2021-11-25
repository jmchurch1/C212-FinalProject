package edu.iu.c212.models;

public enum Item {;

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
