/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time;
import java.util.Scanner;
/**
 *
 * @author haiji
 */
public class Time {
    int day;
    int hour;
    int minuete;
    
    public Time(){
        System.out.print("天 时 分：");
        Scanner input = new Scanner(System.in);
        day = input.nextInt();
        hour = input.nextInt();
        minuete = input.nextInt();
    }
    public Time(int d, int h, int m){
        day = d;
        hour = h;
        minuete = m;
    }
    public boolean isEarlier(Time t){
        int myMinuetes, otherMinutes;
        myMinuetes = day * 24 * 60 + hour * 60 + minuete;
        otherMinutes = t.day * 24 * 60 + t.hour * 60 + t.minuete;
        return myMinuetes < otherMinutes;
    }
    public boolean isLater(Time t){
        int myMinuetes, otherMinutes;
        myMinuetes = day * 24 * 60 + hour * 60 + minuete;
        otherMinutes = t.day * 24 * 60 + t.hour * 60 + t.minuete;
        return myMinuetes > otherMinutes;
    }
    @Override
    public String toString(){
        return "第 " + day + " 天 " + hour + " : " + minuete;
    }
}
