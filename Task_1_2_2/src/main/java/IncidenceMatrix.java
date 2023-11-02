import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IncidenceMatrix<T> implements Graph<T> {
    /**
     * матрица инцедентности.
     * если из вершины v1 выходит ребро e1,
     * то в матрице инцедентности будет вес этого ребра,
     * если нет, то будет -1;
     */
    private HashMap<T, HashMap<Edge<T>, Integer>> incMatrix;

    /**
     * список вершин.
     */
    private HashMap<T, Vertex<T>> vertexList;

    /**
     * список ребер.
     */
    private ArrayList<Edge<T>> edgeList;

    /**
     * конструктор графа в виде матрицы инцедентности.
     */
    IncidenceMatrix() {
        this.incMatrix = new HashMap<>();
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
     * возвращает матрицу инцедентности.
     *
     * @return список смежности.
     */
    public HashMap<T, HashMap<Edge<T>, Integer>> getIncMatrix() {
        return incMatrix;
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
        incMatrix.put(vertex.getValue(), new HashMap<>());
        for (int i = 0; i < edgeList.size(); i++) {
            incMatrix.get(vertex.getValue()).put(edgeList.get(i), -1);
        }
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
            incMatrix.remove(vertex.getValue());
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
        for (Map.Entry<T, HashMap<Edge<T>, Integer>> entry : incMatrix.entrySet()) {
            if (entry.getKey() == edge.getFrom().getValue()) {
                entry.getValue().put(edge, edge.getWeight());
            } else {
                entry.getValue().put(edge, -1);
            }
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
        for (Map.Entry<T, HashMap<Edge<T>, Integer>> entry : incMatrix.entrySet()) {
            if (entry.getValue().containsKey(edge)) {
                entry.getValue().remove(edge);
            }
        }
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
//        // 0 -> white, 1-grey, 2 black
        for (Map.Entry<T, Vertex<T>> entry : vertexList.entrySet()) {
            dist.put(entry.getKey(), myInf);
            mark.put(entry.getKey(), 0);
        }

        dist.put(verS.getValue(), 0);
        mark.put(verS.getValue(), 1);
        int min;
        Vertex<T> u;
        T tempVertex;
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

            HashMap<Edge<T>, Integer> curList = incMatrix.get(u.getValue());
            for (Map.Entry<Edge<T>, Integer> entry : curList.entrySet()) {
                tempVertex = entry.getKey().getTo().getValue();
                if (entry.getValue() == -1) {
                    continue;
                }
                if (dist.get(tempVertex) > (dist.get(u.getValue()) + entry.getValue())) {
                    dist.put(tempVertex, dist.get(u.getValue()) + entry.getValue());
                    mark.put(tempVertex, 1);
                }
            }
        }
        return dist;
    }
}
