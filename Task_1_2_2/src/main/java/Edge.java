/**
 * класс для работы с ребром.
 *
 * @param <T> тип данных ребре
 */
public class Edge<T> {
    /**
     * weight of edge.
     */
    private int weight = -1;
    /**
     * вершина их которой исходит.
     */
    private Vertex<T> from;
    /**
     * вершина куда входит.
     */
    private Vertex<T> to;

    /**
     * constructor.
     *
     * @param v1 вершина из которой исходит ребро
     * @param v2 врешина в которую входит ребро
     * @param weight вес ребра
     */
    Edge(Vertex<T> v1, Vertex<T> v2, int weight) {
        this.from = v1;
        this.to = v2;
        this.weight = weight;
    }

    /**
     * возвращает вес ребра.
     *
     * @return вес ребра
     */
    public int getWeight() {
        return this.weight;
    }
    /**
     * получаем вершину из которой исходит.
     *
     * @return вершина из которой исходит
     */

    public Vertex<T> getFrom() {
        return this.from;
    }
    /**
     * получаем вершину из которой исходит.
     *
     * @return вершина из которой исходит
     */

    public Vertex<T> getTo() {
        return this.to;
    }

}
