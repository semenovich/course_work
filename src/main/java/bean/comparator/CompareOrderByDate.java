package bean.comparator;

import bean.Order;

import java.util.Comparator;

public class CompareOrderByDate implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
