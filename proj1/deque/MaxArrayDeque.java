package deque;


import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c){
        this.comparator = c;
    }
    public T max(){
        return max(comparator);}

    public T max(Comparator<T> c){
        if (isEmpty()){
            return null;
        }
        T result = get(0);
        for (int i = 0;i<length;i++){
            if (c.compare(get(i),result)>0){
                result = get(i);
            }
        }
        return result;
    }


}





