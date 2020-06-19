package bean.comparator;

import bean.Car;

import java.util.Comparator;

public class CompareCarByProducer implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getProducer().compareTo(o2.getProducer());
    }
}
