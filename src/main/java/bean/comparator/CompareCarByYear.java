package bean.comparator;

import bean.Car;

import java.util.Comparator;

public class CompareCarByYear implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        if (o1.getYear() > o2.getYear()){
            return 1;
        }else if(o2.getYear() > o1.getYear()){
            return -1;
        }else {
            return 0;
        }
    }
}
