package bean.comparator;

import bean.Order;

import java.util.Comparator;

public class CompareOrderByPrice implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getPrice() > o2.getPrice()){
            return 1;
        }else if(o2.getPrice() > o1.getPrice()){
            return -1;
        }else {
            return 0;
        }
    }
}
