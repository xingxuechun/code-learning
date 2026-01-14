package deque;


import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c){
        this.comparator = c;
    }
    public T max(){
        return max(comparator);}

    public T max(Comparator<T> c){
        if (isEmpty()){
            return null;
        }
        T best = get(0);          // 因为非空，所以 get(0) 不为 null
        for (int i = 1; ; i++) {
            T cur = get(i);
            if (cur == null) {    // i 越界了，结束
                break;
            }
            if (c.compare(cur, best) > 0) {
                best = cur;
            }
        }
        return best;
    }


}





