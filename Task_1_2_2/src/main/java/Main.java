import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        AdjacencyList<String> g1 = GraphReader.readAdjacencyList("src/main/java/adjanecylist.txt");
        System.out.print(g1.getEdgeList()+"\n");
        System.out.print(g1.getVertexList()+"\n");
        System.out.print(g1.getAdjList()+"\n");
        System.out.print(g1.dijkstra(g1.getVertexByValue("v0")));

    }

}

//        Vertex<String> v0 = new Vertex<String>("v0");
//        Vertex<String> v1 = new Vertex<String>("v1");
//        Vertex<String> v2 = new Vertex<String>("v2");
//        Vertex<String> v3 = new Vertex<String>("v3");
//        Vertex<String> v4 = new Vertex<String>("v4");
//        Edge<String> e1 = new Edge<String>(v0, v1, 2);
//        Edge<String> e2 = new Edge<String>(v1, v2, 3);
//        Edge<String> e3 = new Edge<String>(v0, v4, 10);
//        Edge<String> e4 = new Edge<String>(v4, v2, 6);
//        Edge<String> e5 = new Edge<String>(v2, v3, 4);
//        Edge<String> e6 = new Edge<String>(v3, v4, 5);
//
//        AdjacencyList<String> g1 = new AdjacencyList<String>();
//        g1.addVertex(v0);
//        g1.addVertex(v1);
//        g1.addVertex(v2);
//        g1.addVertex(v3);
//        g1.addVertex(v4);
//        g1.addEdge(e1);
//        g1.addEdge(e2);
//        g1.addEdge(e3);
//        g1.addEdge(e4);
//        g1.addEdge(e5);
//        g1.addEdge(e6);
//
//        HashMap<String, Integer> distance = g1.dijkstra(v0);
//        System.out.print(distance + "\n");
//        for (Map.Entry<String, Vertex<String>> entry : g1.getVertexList().entrySet()) {
//            System.out.print(entry.getKey()+"\n");
//        }
//        System.out.print(g1.getEdgeList()+"\n");

//        System.out.print(g1.getAdjList()+"\n");
//        System.out.print(g1.getEdgeList()+"\n");
//        System.out.print(g1.getVertexList()+"\n");
//        System.out.print(g1.getAdjList()+"\n");
