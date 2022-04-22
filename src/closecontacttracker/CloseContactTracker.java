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
//�ļ���ذ�
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
        new Place("һ��¥"),
        new Place("����¥"),
        new Place("����¥"),
        new Place("�ĺ�¥"),
        new Place("���¥"),
        new Place("����¥"),
        new Place("�ߺ�¥"),
        new Place("�˺�¥")
        };
        PlaceGraph placeGraph = new PlaceGraph(8, place);
        return placeGraph;
    }
    public static PersonLink readNpcFile(PlaceGraph placeGraph, String inputFilePath){
        System.out.print("��ʼ��ȡ������Ϣ�ļ���");
        PersonLink npc = new PersonLink();
        try{
            File inputFile = new File(inputFilePath);                                    //Ҫ��ȡ����·����input.txt�ļ�
            System.out.println(inputFile);
            System.out.println("......");
            FileInputStream readerStream = new FileInputStream(inputFile);               //�����ļ�����ͨ������readerStream
            InputStreamReader reader = new InputStreamReader(readerStream, "utf-8");     //����һ������ͨ��������reader
            BufferedReader br = new BufferedReader(reader);                              //����һ������������br
            for(String personName = br.readLine(); personName != null; personName = br.readLine()){
                System.out.println("������" + personName);
                String addressOfWork = br.readLine();
                System.out.println("�����أ�" + addressOfWork);
                String addressOfResidence = br.readLine();
                System.out.println("��ס�أ�" + addressOfResidence);
                String phoneNumber = br.readLine();
                System.out.println("�ֻ��ţ�" + phoneNumber);
                boolean isProtected = br.readLine().equals("��");
                System.out.println("�Ƿ����÷�����" + isProtected);
                Person current = new Person(personName, addressOfWork, addressOfResidence, phoneNumber, isProtected);
                npc.addPerson(current);
                for(String placeName = br.readLine(); !placeName.equals("����"); placeName = br.readLine()){
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
        System.out.print("�����뻼�ߵ�������");
        Person infecter = new Person(input.next());
        System.out.print("�����뻼�ߵ���ĵ�һ���ص����֣�");
        for(String placeName = input.next(); !placeName.equals("��"); placeName = input.next()){
            Time arrival, departure;
            System.out.print("�����뻼�ߵ���õص��ʱ�䣬");
            arrival = new Time();
            System.out.print("�����뻼���뿪�õص��ʱ�䣬");
            departure = new Time();
            System.out.println();
            infecter.gotoPlace(placeGraph.findPlace(placeName), arrival, departure);
            System.out.println();
            System.out.print("�����뻼�ߵ������һ���ص������(��û�У����롰�ޡ�)��");
        }
        //��ѯ�������������нӴ���
        System.out.println("-------------------------------------------------");
        infecter.findCloseContacter();
    }
    
    public static void main(String[] args) {
        //�����ص�-��ͼ����
        PlaceGraph placeGraph = creatPlaceMap();
        System.out.println("�ص�-��ͼ�Ѵ�����");
        System.out.println("-------------------------------------------------");
        //��ȡ��������
        PersonLink npc = readNpcFile(placeGraph, 
        "C:\\Users\\haiji\\Desktop\\DS_NTB\\CloseContactTracker\\input.txt");
        System.out.println("��Ⱥ�ж��켣�Ѵ�����");
        //�����ѯ�����ж�·��
        //System.out.println("-------------------------------------------------");
        //findInfecterCloseContacter(placeGraph);
        /*
        System.out.println("-------------------------------------------------");
        //���ص�����Ա
        placeGraph.findPlace("һ��¥").showPerson();
        placeGraph.findPlace("����¥").showPerson();
        placeGraph.findPlace("����¥").showPerson();
        placeGraph.findPlace("�ĺ�¥").showPerson();
        placeGraph.findPlace("���¥").showPerson();
        */
        System.out.println("-------------------------------------------------");
        //����Ա�ѵص�켣
        for(PersonNode pn = npc.getPersonNode(); pn != null; pn = npc.getPersonNode()){
            pn.getPerson().showRoute();
        }
        /*
        System.out.println("-------------------------------------------------");
        //����Ա���ܽ�
        xiaoming.findCloseContacter();
        xiaogang.findCloseContacter();
        xiaomei.findCloseContacter();
        System.out.println();
        */
    }
}