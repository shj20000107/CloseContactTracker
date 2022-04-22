package graph;

class Edge{
    int from, to;          //ä»?ç¬? from ä¸????¹æ????ç¬? to ä¸?????
    Edge(){
        from = to = -1;
    }
    Edge(int f, int t){
        from = f;
        to = t;
    }
}
//
enum VMark{
    VISITED, UNVISITED
}
//
//
class LinkLine{
    int v;                  //¸Ã±ßÖ¸ÏòµÄ½áµã±àºÅ
    LinkLine next;          //Í¬Î²²¿µÄÏÂÒ»Ìõ±ß
    LinkLine(){
        v = -1;             
        next = null;
    }
    LinkLine(LinkLine lb){
        next = lb;
    }
    LinkLine(final int v, LinkLine next){
        this.v = v;
        this.next = next;
    }
}
//????
class LinkNode<T>{
    T element;       
    LinkLine head;   //ÒÔ¸Ã½ÚµãÎªÎ²²¿µÄµÚÒ»Ìõ±ß
    
    LinkNode(){
        element = null;
        head = new LinkLine();
    }
    LinkNode(T element){
        this.element = element;
        head = new LinkLine();
    }
}

public class Graph<T>{
    int numVertex;          //?¾ä¸­é¡¶ç?¹ä¸ª??
    int numEdge;            //?¾ä¸­è¾¹ç??ä¸???
    VMark[] Mark;           //??è®°å?¾ä¸­é¡¶ç?¹æ????è¢?è®¿é??è¿?
    int[] inDegree;         //å­??¾å?¾ä¸­é¡¶ç?¹ç???¥åº¦
    LinkNode<T>[] graphList;  //ä¿?å­?????è¡¨å¤´???°ç?
    Graph(int numVert){     //?????½æ??
        numVertex = numVert;
        numEdge = 0;
        inDegree = new int[numVertex];
        Mark = new VMark[numVertex];
        graphList = new LinkNode[numVertex];
        for(int i = 0; i < numVertex; i++){
            graphList[i] = new LinkNode();
            Mark[i] = VMark.UNVISITED;
            inDegree[i] = 0;
        }
    }
    Graph(int numVert, T... place){
        numVertex = numVert;
        numEdge = 0;
        inDegree = new int[numVertex];
        Mark = new VMark[numVertex];
        graphList = new LinkNode[numVertex];
        for(int i = 0; i < numVertex; i++){
            graphList[i] = new LinkNode(place[i]);
            Mark[i] = VMark.UNVISITED;
            inDegree[i] = 0;
        }
    }
    Edge firstEdge(int oneVertex){          //è¿??? ???? ç¬?ä¸??¡è¾¹
        Edge myEdge = new Edge();
        myEdge.from = oneVertex;
        LinkLine temp = graphList[oneVertex].head;
        if(temp.next != null)
            myEdge.to = temp.next.v;
        return myEdge;
    }
    Edge nextEdge(Edge preEdge){    //è¿??? ??è¾? ??ä¸?ä¸??¡è¾¹
        Edge myEdge = new Edge();
        myEdge.from = preEdge.from;
        LinkLine temp = graphList[preEdge.from].head;
        while(temp.next != null && temp.next.v <= preEdge.to)
            temp = temp.next;
        if(temp.next != null)
            myEdge.to = temp.next.v;
        return myEdge;
    }
    void setEdge(int from, int to){         //??å»ºç?¹å????è¾?
        LinkLine temp = graphList[from].head;
        while(temp.next != null && temp.next.v < to)
            temp = temp.next;
        if(temp.next == null){
            temp.next = new LinkLine(to, null);
            numEdge ++;
            inDegree[to] ++;
            return;
        }
        if(temp.next.v == to)
            return;
        if(temp.next.v > to){
            temp.next = new LinkLine(to, temp.next);
            numEdge ++;
            inDegree[to] ++;
            return;
        }
    }
    void delEdge(int from, int to){         //???¤ç?¹å??è¾?
        LinkLine temp = graphList[from].head;
        while(temp.next != null && temp.next.v < to)
            temp = temp.next;
        if(temp.next == null)
            return;
        if(temp.next.v > to)
            return;
        if(temp.next.v == to){
            temp.next = temp.next.next;
            numEdge --;
            inDegree[to] --;
            return;
        }
    }
    int verticesNum(){    //è¿????¾ä¸­é¡¶ç?¹ä¸ª??  
        return numVertex;
    }
    boolean isEdge(Edge oneEdge){   //oneEdge ??????è¾?
        return oneEdge.to >= 0;
    }
}
