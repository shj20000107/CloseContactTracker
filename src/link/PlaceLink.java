package link;
import place.*;

public class PlaceLink extends Link<PlaceNode>{
    
    public PlaceLink(){
        super();
        head = new PlaceNode();
        current = tail = head;
        total = 1;
    }
    public PlaceLink(PlaceNode n){
        super();
        head = new PlaceNode();
        current = head;
        head.next = n;
        tail = head.next;
        total = 1;
    }
    public void addPlaceNode(PlaceNode n){
        tail.next = n;
        tail = tail.next;
        total ++;
    }
    public PlaceNode getPlaceNode(){
        if(current == tail)
            return null;
        current = current.next;
        position ++;
        return current;
    } 
    public void backFirst(){
        if(position == 0)
            return;
        current = head;
        position = 0;
    }
}
