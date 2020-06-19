package bean;

public class Car extends Entity{
    private String producer;
    private String model;
    private int year;
    private String fuel;
    private String color;
    private String transmission;

    public String getProducer() {
        return producer;
    }

    public Car setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Car setYear(int year) {
        this.year = year;
        return this;
    }

    public String getFuel() {
        return fuel;
    }

    public Car setFuel(String fuel) {
        this.fuel = fuel;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Car setColor(String color) {
        this.color = color;
        return this;
    }

    public String getTransmission() {
        return transmission;
    }

    public Car setTransmission(String transmission) {
        this.transmission = transmission;
        return this;
    }
}
