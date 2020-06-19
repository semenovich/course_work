package bean.comparator;

import bean.Car;

import java.util.Comparator;

public class CompareCarByFuel implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getFuel().compareTo(o2.getFuel());
    }
}
