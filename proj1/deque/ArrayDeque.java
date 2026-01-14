package deque;

public class ArrayDeque<T>implements Deque<T> {
    private  T[] arr;
    private int size;
    private int first;
    private int last;
    private int length;

    public ArrayDeque(){
        arr = (T[]) new Object[8];
        this.size = 8;
        this.first = 3;
        this.last = 4;
        this.length = 0;
    }

    public void resize(int length,String action) {
        if (action.equals("up")) {
            size = size * 2;
            T[] arr1 = (T[]) new Object[size];
            System.arraycopy(arr,first+1,arr1,(size/2)-(length/2),length);
            first = (size/2)-(length/2)-1;
            last = first + length + 1;
            arr = arr1;
        } else if (action.equals("down")) {
            if (length <= size / 4 && size >= 32) {
                size = size / 2;
                T[] arr1 = (T[]) new Object[size];
                System.arraycopy(arr,first+1,arr1,(size/2)-(length/2),length);
                first = (size/2)-(length/2)-1;
                last = first + length + 1;
                arr = arr1;

            }
        }

    }

    public void addFirst(T item) {
        if (first <= 0){
        resize(length,"up");
        }
        arr[first] = item;
        first--;
        length++;
        }


    public void addLast(T item){
        if (last >= size - 1){
            resize(length,"up");
        }
        arr[last] = item;
        last++;
        length++;
    }

//    public boolean isEmpty(){
//        if (length == 0){
//            return true;
//        }
//        return false;
//    }

    public int size(){
        return length;
    }

    public void printDeque(){
        for (int i = first + 1;i <= last -1;i++){
            System.out.print(arr[i]);
            if(i!= last-1){
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public T removeFirst(){
        if (! isEmpty()){
            T result = arr[first+1];
            arr[first+1] = null;
            first ++;
            length--;
            resize(length,"down");
            return result;
        }
        return null;
    }

    public T removeLast(){
        if (! isEmpty()){
            T result = arr[last-1];
            arr[last-1] = null;
            last --;
            length--;
            resize(length,"down");
            return result;
        }
        return null;
    }

    public T get(int index){
        if (! isEmpty() && index < length){
            return arr[first+1+index];
        }
        return null;
    }

    public boolean equals(Object o){
        if (this == o) {return true;}
        if (!(o instanceof Deque)) {return false;}
        Deque<?> other = (Deque<?>) o;
        if (this.size() != other.size()) {return false;}

        for (int i = 0; i < this.size(); i++) {
            T a = this.get(i);
            Object b = other.get(i);

            if (a == null) {
                if (b != null) {
                    return false;
                }
            } else {
                if (!a.equals(b)) {
                    return false;
                }
            }
        }
        return true;
    }


}
