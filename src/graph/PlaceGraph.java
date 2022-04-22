package graph;
import place.*;

public class PlaceGraph extends Graph<Place>{
    public PlaceGraph(int numPlace){
        super(numPlace);
    }
    public PlaceGraph(int numPlace, Place... place){
        super(numPlace, place);
    }
    public Place findPlace(String s){
        for(int i = 0; i < numVertex; i++){
            if(s.equals(graphList[i].element.name))
                return graphList[i].element;
        }
        return null;
    }
}
