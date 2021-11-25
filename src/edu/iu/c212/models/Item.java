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
        return String.format("%s, %s",readableName,value);
    }

}
