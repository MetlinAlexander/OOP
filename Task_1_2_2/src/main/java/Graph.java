import java.util.HashMap;

/**
 * interface для работы с графом.
 *
 * @param <T> тип данных хранящийся в вершинах и ребрах
 */
public interface Graph<T> {
    /**
     * добавляет новую вершину в графю.
     *
     * @param vertex веришина для добавления
     */
    void addVertex(Vertex<T> vertex);

    /**
     * удаляет вершину из графа.
     *
     * @param vertex вершина для удаления
     */
    void removeVertex(Vertex<T> vertex);

    /**
     * добавляет ребро в граф.
     *
     * @param edge ребро для добавления.
     */
    void addEdge(Edge<T> edge);

    /**
     * удаляет ребро из графа.
     *
     * @param edge ребро для удаления
     */
    void removeEdge(Edge<T> edge);

    /**
     * возвращает обЪект вершину по значению.
     *
     * @param value значение в вершине
     * @return объект вершину
     */
    Vertex<T> getVertexByValue(T value);

    /**
     * алгоритм дейкстры.
     *
     * @param vertex вершина с которой начать обход.
     * @return hashmap с расстояниями до всех вершин.
     */
    HashMap<T, Integer> dijkstra(Vertex<T> vertex);

}
