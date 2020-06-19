package bean.comparator;

import bean.User;

import java.util.Comparator;

public class CompareUserByRole implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getRole().toString().compareTo(o2.getRole().toString());
    }
}
