package graph;

class Edge{
    int from, to;          //�?�? from �????��????�? to �?????
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
    int v;                  //�ñ�ָ��Ľ����
    LinkLine next;          //ͬβ������һ����
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
    LinkLine head;   //�Ըýڵ�Ϊβ���ĵ�һ����
    
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
    int numVertex;          //?�中顶�?�个??
    int numEdge;            //?�中边�??�???
    VMark[] Mark;           //??记�?�中顶�?��????�?访�??�?
    int[] inDegree;         //�??��?�中顶�?��???�度
    LinkNode<T>[] graphList;  //�?�?????表头???��?
    Graph(int numVert){     //?????��??
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
    Edge firstEdge(int oneVertex){          //�??? ???? �?�??�边
        Edge myEdge = new Edge();
        myEdge.from = oneVertex;
        LinkLine temp = graphList[oneVertex].head;
        if(temp.next != null)
            myEdge.to = temp.next.v;
        return myEdge;
    }
    Edge nextEdge(Edge preEdge){    //�??? ??�? ??�?�??�边
        Edge myEdge = new Edge();
        myEdge.from = preEdge.from;
        LinkLine temp = graphList[preEdge.from].head;
        while(temp.next != null && temp.next.v <= preEdge.to)
            temp = temp.next;
        if(temp.next != null)
            myEdge.to = temp.next.v;
        return myEdge;
    }
    void setEdge(int from, int to){         //??建�?��????�?
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
    void delEdge(int from, int to){         //???��?��??�?
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
    int verticesNum(){    //�????�中顶�?�个??  
        return numVertex;
    }
    boolean isEdge(Edge oneEdge){   //oneEdge ??????�?
        return oneEdge.to >= 0;
    }
}
