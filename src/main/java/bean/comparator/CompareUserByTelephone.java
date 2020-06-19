package bean.comparator;

import bean.User;

import java.util.Comparator;

public class CompareUserByTelephone implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
            return o1.getTelephone().compareTo(o2.getTelephone());
    }

}
