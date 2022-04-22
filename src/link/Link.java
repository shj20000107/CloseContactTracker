package link;

public class Link<T> {
    protected T head;           //
    protected T tail;           //
    protected T current;        //
    protected int total;        //
    protected int position;     //
    
    public Link(){
        head = null;
        tail = null;
        current = null;
        total = 0;
        position = 0;
    }
}
