import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphTests {

    @Test
    public void testAdjacencyList() {
        AdjacencyList<String> g1 = GraphReader.readAdjacencyList("src/main/java/graph.txt");
        HashMap<String, Integer> distance = g1.dijkstra(g1.getVertexByValue("v0"));
        Vertex<String> v5 = new Vertex<>("v5");
        g1.addVertex(v5);
        Edge<String> e5 = new Edge<>(v5, g1.getVertexByValue("v1"), 10);
        g1.addEdge(e5);
        Edge<String> e6 = new Edge<>(g1.getVertexByValue("v4"), v5, 2);
        g1.addEdge(e6);
        g1.removeEdge(e5);
        g1.removeVertex(g1.getVertexByValue("v5"));
        HashMap<String, Vertex<String>> verList = g1.getVertexList();
        ArrayList<Edge<String>> edList = g1.getEdgeList();
        HashMap<String, HashMap<String, Integer>> adjList = g1.getAdjList();
        distance.get("v0") ;
        Assertions.assertEquals(distance.get("v0"), 0);
        Assertions.assertEquals(distance.get("v1"), 2);
        Assertions.assertEquals(distance.get("v2"), 5);
        Assertions.assertEquals(distance.get("v3"), 9);
        Assertions.assertEquals(distance.get("v4"), 10);
    }

    @Test
    public void testAdjacencyMatrix() {
        AdjacencyMatrix<String> g1 = GraphReader.readAdjacencyMatrix("src/main/java/graph.txt");
        HashMap<String, Integer> distance = g1.dijkstra(g1.getVertexByValue("v0"));
        Vertex<String> v5 = new Vertex<>("v5");
        g1.addVertex(v5);
        Edge<String> e5 = new Edge<>(v5, g1.getVertexByValue("v1"), 10);
        g1.addEdge(e5);
        Edge<String> e6 = new Edge<>(g1.getVertexByValue("v4"), v5, 2);
        g1.addEdge(e6);
        g1.removeEdge(e5);
        g1.removeVertex(g1.getVertexByValue("v5"));
        HashMap<String, Vertex<String>> verList = g1.getVertexList();
        ArrayList<Edge<String>> edList = g1.getEdgeList();
        HashMap<String, HashMap<String, Integer>> adjList = g1.getAdjMatrix();
        distance.get("v0") ;
        Assertions.assertEquals(distance.get("v0"), 0);
        Assertions.assertEquals(distance.get("v1"), 2);
        Assertions.assertEquals(distance.get("v2"), 5);
        Assertions.assertEquals(distance.get("v3"), 9);
        Assertions.assertEquals(distance.get("v4"), 10);
    }

    @Test
    public void testIncidenceMatrix() {
        IncidenceMatrix<String> g1 = GraphReader.readIncidenceMatrix("src/main/java/graph.txt");
        HashMap<String, Integer> distance = g1.dijkstra(g1.getVertexByValue("v0"));
        Vertex<String> v5 = new Vertex<>("v5");
        g1.addVertex(v5);
        Edge<String> e5 = new Edge<>(v5, g1.getVertexByValue("v1"), 10);
        g1.addEdge(e5);
        Edge<String> e6 = new Edge<>(g1.getVertexByValue("v4"), v5, 2);
        g1.addEdge(e6);
        g1.removeEdge(e5);
        g1.removeVertex(g1.getVertexByValue("v5"));
        HashMap<String, Vertex<String>> verList = g1.getVertexList();
        ArrayList<Edge<String>> edList = g1.getEdgeList();
        HashMap<String, HashMap<Edge<String>, Integer>> adjList = g1.getIncMatrix();
        distance.get("v0") ;
        Assertions.assertEquals(distance.get("v0"), 0);
        Assertions.assertEquals(distance.get("v1"), 2);
        Assertions.assertEquals(distance.get("v2"), 5);
        Assertions.assertEquals(distance.get("v3"), 9);
        Assertions.assertEquals(distance.get("v4"), 10);
    }
}
