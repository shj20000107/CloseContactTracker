package link;
import person.*;

public class PersonLink extends Link<PersonNode>{
    
    public PersonLink(){
        super();
        head = new PersonNode();
        current = tail = head;
        total = 1;
    }
    public PersonLink(PersonNode n){
        super();
        head = new PersonNode();
        current = head;
        head.next = n;
        tail = head.next;
        total = 1;
    }
    public void addPerson(Person p){
        tail.next = new PersonNode(p);
        tail = tail.next;
        total ++;
    }
    public void addPersonNode(PersonNode n){
        tail.next = n;
        tail = tail.next;
        total ++;
    }
    public PersonNode getPersonNode(){
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
