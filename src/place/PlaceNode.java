/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package place;
import time.Time;
/**
 *
 * @author haiji
 */
public class PlaceNode {
    private Place place;    //????
    private Time arrival;   //??
    private Time departure; //??
    public PlaceNode next; 
    
    public PlaceNode(){
        place = null;
        arrival = departure = null;
        next = null;
    }
    public PlaceNode(Place p, Time a, Time d){
        place = p;
        arrival = a;
        departure = d;
        next = null;
    }
    public Place getPlace(){
        return place;
    }
    public Time getArrival(){
        return arrival;
    }
    public Time getDeparture(){
        return departure;
    }
    @Override
    public String toString(){
        return place + "，" + "到达时间：" + arrival + "  离开时间：" + departure;
    }
}
