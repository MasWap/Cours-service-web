package org.example.models;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int horsePower;

    public Car() {
    }

    public Car(int id, String brand, String model, int horsePower) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

}
