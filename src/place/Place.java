/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package place;
import link.*;
import person.*;
import time.Time;
/**
 *
 * @author haiji
 */
public class Place {
    public String name;
    private PersonLink pLink;
    
    public Place(){
        name = null;
        pLink = new PersonLink();
    }
    public Place(String s){
        name = s;
        pLink = new PersonLink();
    }
    public void recordPerson(Person ps, Time a, Time d){
        PersonNode psn = new PersonNode(ps, a, d);
        pLink.addPersonNode(psn);
    }
    public PersonNode getPersonNodeInfo(){
        return pLink.getPersonNode();
    }
    public Person getPersonInfo(){
        PersonNode temp = pLink.getPersonNode();
        if(temp != null)
            return temp.getPerson();
        else
            return null;
    }
    public void showPerson(){
        System.out.println(this.name + "¼ÇÂ¼£º");
        this.backFirstPerson();
        for (PersonNode pn = this.getPersonNodeInfo(); pn != null; pn = this.getPersonNodeInfo()){
            System.out.println(pn);
        }
    }
    public void backFirstPerson(){
        pLink.backFirst();
    }
    @Override
    public String toString(){
        return name;
    }
}
