package bean.comparator;

import bean.Car;

import java.util.Comparator;

public class CompareCarByColor implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getColor().compareTo(o2.getColor());
    }
}
