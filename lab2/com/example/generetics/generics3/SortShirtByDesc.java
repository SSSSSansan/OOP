package lab2.com.example.generetics.generics3;

import java.util.Comparator;

public class SortShirtByDesc implements Comparator<Shirt> {
    public int compare(Shirt s1, Shirt s2) {
        return s1.getDescription().compareTo(s2.getDescription());
    }
}