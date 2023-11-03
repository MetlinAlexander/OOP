/**
 * класс для описывающий вершину.
 *
 * @param <T> тип данных в вершине.
 */
public class Vertex<T> {
    /**
     * значение в вершине.
     */
    private T value;

    /**
     * конструктор.
     *
     * @param value значение в вершине
     */
    Vertex(T value) {
        this.value = value;
    }

    /**
     * возвращает значение в вершине.
     *
     * @return значение вершины.
     */
    public T getValue() {
        return this.value;
    }
}
