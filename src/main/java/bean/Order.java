package bean;

import java.sql.Date;

public class Order extends Entity {
    private double price;
    private Date date;
    private Car car;
    private User user;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public String toString() {
        return "Чек:\n\n"
                + " Номер заказа:" + this.getId() +"\n"
                + " Дата:" + this.getDate()+"\n"
                + " Цена:" + this.getPrice()+"\n"
                + " Номер автомобиля:" + this.getCar().getId()+"\n"
                + " Производитель:" + this.getCar().getProducer()+"\n"
                + " Модель:" + this.getCar().getModel()+"\n"
                + " Год выпуска:" + this.getCar().getYear()+"\n"
                + " Цвет:" + this.getCar().getColor()+"\n"
                + " Тип топлива:" + this.getCar().getFuel()+"\n"
                + " Коробка передач:" + this.getCar().getTransmission()+"\n"
                + " Имя покупателя:" + this.getCustomer().getName()+"\n"
                + " Фамилия покупателя:" + this.getCustomer().getSurname()+"\n"
                + " Адрес покупателя:" + this.getCustomer().getAddress()+"\n"
                + " Номер продавца:" + this.getUser().getId()+"\n"
                + " Имя продавца:" + this.getUser().getName()+"\n"
                + " Фамилия продавца:" + this.getUser().getSurname()+"\n"
                + " Почта продавца:" + this.getUser().getEmail()+"\n"
                + " Телефон продавца:" + this.getUser().getTelephone()+"\n"
                + "-----------------------------------------\n"
                ;
    }

    public double getPrice() {
        return price;
    }

    public Order setPrice(double price) {
        this.price = price;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Order setDate(Date date) {
        this.date = date;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Order setCar(Car car) {
        this.car = car;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }
}
