import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdjacencyListGraph<T> implements Graph<T> {

    /**
     * список смежности.
     */
    private HashMap<T, HashMap<T, Integer>> adjList;

    /**
     * список вершин.
     */
    private HashMap<T, Vertex<T>> vertexList;

    /**
     * список ребер.
     */
    private ArrayList<Edge<T>> edgeList;

    /**
     * конструктор графа в виде списка смежности.
     */
    AdjacencyListGraph() {
        this.adjList = new HashMap<>();
        this.vertexList = new HashMap<>();
        this.edgeList = new ArrayList<>();
    }

    /**
     * возвращает список вершин.
     *
     * @return список вершин.
     */
    public HashMap<T, Vertex<T>> getVertexList() {
        return vertexList;
    }

    /**
     * возвращает список смежности.
     *
     * @return список смежности.
     */
    public HashMap<T, HashMap<T, Integer>> getAdjList() {
        return adjList;
    }

    /**
     * возвращает список ребер.
     *
     * @return список ребер.
     */
    public ArrayList<Edge<T>> getEdgeList() {
        return edgeList;
    }

    /**
     * добавляет вершину в граф.
     *
     * @param vertex веришина для добавления
     */
    public void addVertex(Vertex<T> vertex) {
        vertexList.put(vertex.getValue(), vertex);
        adjList.put(vertex.getValue(), new HashMap<>());
    }

    /**
     * удаляет вершину из графа.
     *
     * @param vertex вершина для удаления
     */
    public void removeVertex(Vertex<T> vertex) {
        //проверям есть ли такая вершина у нас
        if (vertexList.containsKey(vertex.getValue())) {
            vertexList.remove(vertex.getValue());
            adjList.remove(vertex.getValue());
            //ищем какие ребра надо удалить вместе с этой вершиной
            ArrayList<Edge<T>> toDelete = new ArrayList<>();
            for (Edge<T> curEdge : edgeList) {
                if (curEdge.getFrom().getValue() == vertex.getValue()) {
                    toDelete.add(curEdge);
                } else if (curEdge.getTo().getValue() == vertex.getValue()) {
                    toDelete.add(curEdge);
                }
            }
            //удаляем все ребра инцидентные этой вершине
            for (Edge<T> curEdge : toDelete) {
                edgeList.remove(curEdge);
            }
            //удаляем информацию о  вершине из списка смежности
            for (Map.Entry<T, HashMap<T, Integer>> entry : adjList.entrySet()) {
                HashMap<T, Integer> curList = entry.getValue();
                if (curList.containsKey(vertex.getValue())) {
                    curList.remove(vertex.getValue());
                }
            }
        }
    }

    /**
     * добавляет ребро.
     *
     * @param edge ребро для добавления.
     */
    public void addEdge(Edge<T> edge) {
        if (!vertexList.containsKey(edge.getFrom().getValue())) {
            return;
        }
        if (!vertexList.containsKey(edge.getTo().getValue())) {
            return;
        }
        this.edgeList.add(edge);
        HashMap<T, Integer> curList = adjList.get(edge.getFrom().getValue());
        if (!curList.containsKey(edge.getTo())) {
            curList.put(edge.getTo().getValue(), edge.getWeight());
        }
    }

    /**
     * удаляет ребро.
     *
     * @param edge ребро для удаления
     */
    public void removeEdge(Edge<T> edge) {
        if (!this.edgeList.contains(edge)) {
            return;
        }
        this.edgeList.remove(edge);
        HashMap<T, Integer> curList = adjList.get(edge.getFrom().getValue());
        curList.remove(edge.getTo().getValue());
    }

    /**
     * возвращает вершину по значению.
     *
     * @param value значение в вершине
     * @return вершина.
     */
    public Vertex<T> getVertexByValue(T value) {
        return vertexList.get(value);
    }

    /**
     * алгоритм дейкстры для данного графа.
     *
     * @param verS вершина с которой начать обход.
     * @return крастчайшие расстояния от данной вершины до других.
     */
    public HashMap<T, Integer> dijkstra(Vertex<T> verS) {
        int myInf = 1000000000;
        HashMap<T, Integer> dist = new HashMap<>();
        HashMap<T, Integer> mark = new HashMap<>();
        // 0 -> white, 1-grey, 2 black
        for (Map.Entry<T, Vertex<T>> entry : vertexList.entrySet()) {
            dist.put(entry.getKey(), myInf);
            mark.put(entry.getKey(), 0);
        }
        dist.put(verS.getValue(), 0);
        mark.put(verS.getValue(), 1);
        int min;
        Vertex<T> u;

        while (true) {
            min = myInf;
            u = null;
            for (Map.Entry<T, Integer> entry : dist.entrySet()) {
                if (mark.get(entry.getKey()) == 1 & entry.getValue() < min) {
                    u = vertexList.get(entry.getKey());
                    min = entry.getValue();
                }
            }
            if (min == myInf | u == null) {
                break;
            }
            mark.put(u.getValue(), 2);

            HashMap<T, Integer> curList = adjList.get(u.getValue());
            for (Map.Entry<T, Integer> entry : curList.entrySet()) {
                if (dist.get(entry.getKey()) > (dist.get(u.getValue()) + entry.getValue())) {
                    dist.put(entry.getKey(), dist.get(u.getValue()) + curList.get(entry.getKey()));
                    mark.put(entry.getKey(), 1);
                }
            }
        }
        return dist;
    }
}

