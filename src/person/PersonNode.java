/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person;
import time.Time;
/**
 *
 * @author haiji
 */
public class PersonNode {
    private Person person;      //
    private Time arrival;       //
    private Time departure;     //
    public PersonNode next;    
    
    public PersonNode(){
        person = null;
        arrival = departure = null;
        next = null;
    }
    public PersonNode(Person p){
        person = p;
        arrival = departure = null;
        next = null;
    }
    public PersonNode(Person p, Time a, Time d){
        person = p;
        arrival = a;
        departure = d;
        next = null;
    }
    public Person getPerson(){
        return person;
    }
    public Time getArrival(){
        return arrival;
    }
    public Time getDeparture(){
        return departure;
    }
    @Override
    public String toString(){
        return person + "ï¼?" + "?°è¾¾?¶é?´ï?" + arrival + "  ç¦»å??¶é?´ï?" + departure;
    }
}
