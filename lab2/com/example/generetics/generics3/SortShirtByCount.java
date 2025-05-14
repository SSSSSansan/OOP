package lab2.com.example.generetics.generics3;

import java.util.Comparator;


public class SortShirtByCount implements Comparator<Shirt>{
    public int compare(Shirt s1, Shirt s2){
        return Integer.compare(s1.getCount(), s2.getCount());
    }
}