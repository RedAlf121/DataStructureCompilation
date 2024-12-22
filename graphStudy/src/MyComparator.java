import java.util.Comparator;

public class MyComparator<V,T> implements Comparator<Pair<Integer,T>>{

    @Override
    public int compare(Pair<Integer, T> o1, Pair<Integer, T> o2) {
        
        return o1.getKey().compareTo(o2.getKey());
    }

  
    
}
