/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closecontacttracker;
import java.util.Scanner;
import graph.*;
import link.*;
import person.*;
import place.*;
import time.*;
//文件相关包
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
/**
 *
 * @author haiji
 */
public class CloseContactTracker {
    public static PlaceGraph creatPlaceMap(){
        Place[] place = {
        new Place("一号楼"),
        new Place("二号楼"),
        new Place("三号楼"),
        new Place("四号楼"),
        new Place("五号楼"),
        new Place("六号楼"),
        new Place("七号楼"),
        new Place("八号楼")
        };
        PlaceGraph placeGraph = new PlaceGraph(8, place);
        return placeGraph;
    }
    public static PersonLink readNpcFile(PlaceGraph placeGraph, String inputFilePath){
        System.out.print("开始读取人物信息文件：");
        PersonLink npc = new PersonLink();
        try{
            File inputFile = new File(inputFilePath);                                    //要读取以上路径的input.txt文件
            System.out.println(inputFile);
            System.out.println("......");
            FileInputStream readerStream = new FileInputStream(inputFile);               //建立文件输入通道对象readerStream
            InputStreamReader reader = new InputStreamReader(readerStream, "utf-8");     //建立一个输入通道流对象reader
            BufferedReader br = new BufferedReader(reader);                              //建立一个缓冲区对象br
            for(String personName = br.readLine(); personName != null; personName = br.readLine()){
                System.out.println("姓名：" + personName);
                String addressOfWork = br.readLine();
                System.out.println("工作地：" + addressOfWork);
                String addressOfResidence = br.readLine();
                System.out.println("居住地：" + addressOfResidence);
                String phoneNumber = br.readLine();
                System.out.println("手机号：" + phoneNumber);
                boolean isProtected = br.readLine().equals("是");
                System.out.println("是否做好防护：" + isProtected);
                Person current = new Person(personName, addressOfWork, addressOfResidence, phoneNumber, isProtected);
                npc.addPerson(current);
                for(String placeName = br.readLine(); !placeName.equals("结束"); placeName = br.readLine()){
                    Time arrival, departure;
                    int day, hour, minuete;
                    day = Integer.parseInt(br.readLine());
                    hour = Integer.parseInt(br.readLine());
                    minuete = Integer.parseInt(br.readLine());
                    arrival = new Time(day, hour, minuete);
                    day = Integer.parseInt(br.readLine());
                    hour = Integer.parseInt(br.readLine());
                    minuete = Integer.parseInt(br.readLine());
                    departure = new Time(day, hour, minuete);
                    current.gotoPlace(placeGraph.findPlace(placeName), arrival, departure);
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return npc;
    }
    public static void findInfecterCloseContacter(PlaceGraph placeGraph){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入患者的姓名：");
        Person infecter = new Person(input.next());
        System.out.print("请输入患者到达的第一个地点名字：");
        for(String placeName = input.next(); !placeName.equals("无"); placeName = input.next()){
            Time arrival, departure;
            System.out.print("请输入患者到达该地点的时间，");
            arrival = new Time();
            System.out.print("请输入患者离开该地点的时间，");
            departure = new Time();
            System.out.println();
            infecter.gotoPlace(placeGraph.findPlace(placeName), arrival, departure);
            System.out.println();
            System.out.print("请输入患者到达的下一个地点的名字(若没有，输入“无”)：");
        }
        //查询输入的人物的密切接触者
        System.out.println("-------------------------------------------------");
        infecter.findCloseContacter();
    }
    
    public static void main(String[] args) {
        //创建地点-地图数据
        PlaceGraph placeGraph = creatPlaceMap();
        System.out.println("地点-地图已创建！");
        System.out.println("-------------------------------------------------");
        //读取人物数据
        PersonLink npc = readNpcFile(placeGraph, 
        "C:\\Users\\haiji\\Desktop\\DS_NTB\\CloseContactTracker\\input.txt");
        System.out.println("人群行动轨迹已创建！");
        //输入查询对象行动路线
        //System.out.println("-------------------------------------------------");
        //findInfecterCloseContacter(placeGraph);
        /*
        System.out.println("-------------------------------------------------");
        //按地点搜人员
        placeGraph.findPlace("一号楼").showPerson();
        placeGraph.findPlace("二号楼").showPerson();
        placeGraph.findPlace("三号楼").showPerson();
        placeGraph.findPlace("四号楼").showPerson();
        placeGraph.findPlace("五号楼").showPerson();
        */
        System.out.println("-------------------------------------------------");
        //按人员搜地点轨迹
        for(PersonNode pn = npc.getPersonNode(); pn != null; pn = npc.getPersonNode()){
            pn.getPerson().showRoute();
        }
        /*
        System.out.println("-------------------------------------------------");
        //按人员查密接
        xiaoming.findCloseContacter();
        xiaogang.findCloseContacter();
        xiaomei.findCloseContacter();
        System.out.println();
        */
    }
}