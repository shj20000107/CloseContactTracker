/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person;
import java.util.Scanner;
import link.*;
import place.*;
import time.Time;

/**
 *
 * @author haiji
 */
public class Person {
    public String name;                 //姓名
    private String addressOfWork;       //工作地
    private String addressOfResidence;  //居住地
    private String phoneNumber;         //联系方式（手机号）
    private boolean isProtected;        //是否做好防护 
    private boolean isInfected;         //是否被感染
    private PlaceLink pLink;
    
    public Person(){
        
    }
    public Person(String name){
        this.name = name;
        this.isInfected = false;
        pLink = new PlaceLink();
    }
    public Person(String name, String addressOfWork, String addressOfResidence, String phoneNumber, boolean isProtected){
        this.name = name;
        this.addressOfWork = addressOfWork;
        this.addressOfResidence = addressOfResidence;
        this.phoneNumber = phoneNumber;
        this.isProtected = isProtected;
        this.isInfected = false;
        pLink = new PlaceLink();
    }
    public void setProtected(boolean b){
        isProtected = b;
    }
    public void gotoPlace(Place pl, Time a, Time d){
        PlaceNode pln = new PlaceNode(pl, a, d);
        pLink.addPlaceNode(pln);
        pl.recordPerson(this, a, d);
        System.out.println(this.name + " 去 " + pl + " 停留时间：" + a + " 至 " + d);
    }
    public PlaceNode getPlaceNodeInfo(){
        return pLink.getPlaceNode();
    }
    public Place getPlaceInfo(){
        PlaceNode temp = pLink.getPlaceNode();
        if(temp != null)
            return temp.getPlace();
        else
            return null;
    }
    public void showRoute(){
        System.out.println(this.name + "记录：");
        this.backFirstPlace();
        Scanner input = new Scanner(System.in);
        for (PlaceNode pn = this.getPlaceNodeInfo(); pn != null; pn = this.getPlaceNodeInfo()){
            System.out.print("任意输入字符或数字：");
            if(input.next() != null){
                System.out.println(pn);
            }
        }
    }
    public void backFirstPlace(){
        pLink.backFirst();
    }
    public void findCloseContacter(){
        System.out.println(this.name + " 的密切接触者有:" );
        this.backFirstPlace();
        for (PlaceNode pln = this.getPlaceNodeInfo(); pln != null; pln = this.getPlaceNodeInfo()){
            Place pl = pln.getPlace();
            pl.backFirstPerson();
            for (PersonNode psn = pl.getPersonNodeInfo(); psn != null; psn = pl.getPersonNodeInfo()){
                if(!(psn.getDeparture().isEarlier(pln.getArrival()) || psn.getArrival().isLater(pln.getDeparture()))
                && psn.getPerson().isProtected == false
                && psn.getPerson().isInfected == false
                && !psn.getPerson().name.equals(this.name)){
                    System.out.println(psn.getPerson());
                    psn.getPerson().isInfected = true;
                    //psn.getPerson().isInfected = true;
                }
            }
        }
    }
    @Override
    public String toString(){
        return name;
    }
}
