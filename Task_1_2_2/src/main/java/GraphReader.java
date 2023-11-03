import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * класс для считывания графов с файла.
 */
public class GraphReader {

    /**
     * метод для считывания файла в виде списка смежности.
     *
     * @param filename имя файла.
     * @return граф в виде списка смежности.
     */
    public static AdjacencyListGraph<String> readAdjacencyList(String filename) {
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        AdjacencyListGraph<String> g1 = new AdjacencyListGraph<String>();

        int numVer = 0;
        int numEdge = 0;
        String[] temp = fileScanner.nextLine().split(" ");
        numVer = Integer.parseInt(temp[0]);
        numEdge = Integer.parseInt((temp[1]));

        Vertex<String> tempVert;
        String lineTemp;

        for (int i = 0; i < numVer; i++) {
            lineTemp = fileScanner.nextLine().replaceAll("\\r\\n", "");
            tempVert = new Vertex<String>(lineTemp);
            g1.addVertex(tempVert);
        }

        Edge<String> edgeTemp;
        Vertex<String> v1;
        Vertex<String> v2;
        int weight;
        for (int i = 0; i < numEdge; i++) {
            lineTemp = fileScanner.nextLine().replaceAll("\\r\\n", "");
            temp = lineTemp.split(" ");
            v1 = g1.getVertexByValue(temp[0]);
            v2 = g1.getVertexByValue(temp[1]);
            weight = Integer.parseInt((temp[2]));
            edgeTemp = new Edge<>(v1, v2, weight);
            g1.addEdge(edgeTemp);
        }
        return g1;
    }

    /**
     * метод для считывания файла в виде матрицы смежности.
     *
     * @param filename имя файла.
     * @return граф в виде матрицы смежности.
     */
    public static AdjacencyMatrixGraph<String> readAdjacencyMatrix(String filename) {
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        AdjacencyMatrixGraph<String> g1 = new AdjacencyMatrixGraph<String>();

        int numVer = 0;
        int numEdge = 0;
        String[] temp = fileScanner.nextLine().split(" ");
        numVer = Integer.parseInt(temp[0]);
        numEdge = Integer.parseInt((temp[1]));

        Vertex<String> tempVert;
        String lineTemp;

        for (int i = 0; i < numVer; i++) {
            lineTemp = fileScanner.nextLine().replaceAll("\\r\\n", "");
            tempVert = new Vertex<String>(lineTemp);
            g1.addVertex(tempVert);
        }

        Edge<String> edgeTemp;
        Vertex<String> v1;
        Vertex<String> v2;
        int weight;
        for (int i = 0; i < numEdge; i++) {
            lineTemp = fileScanner.nextLine().replaceAll("\\r\\n", "");
            temp = lineTemp.split(" ");
            v1 = g1.getVertexByValue(temp[0]);
            v2 = g1.getVertexByValue(temp[1]);
            weight = Integer.parseInt((temp[2]));
            edgeTemp = new Edge<>(v1, v2, weight);
            g1.addEdge(edgeTemp);
        }
        return g1;
    }

    /**
     * метод для считывания файла в виде матрицы инцедентности.
     *
     * @param filename имя файла.
     * @return граф в виде матрицы инцедентности.
     */
    public static IncidenceMatrixGraph<String> readIncidenceMatrix(String filename) {
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        IncidenceMatrixGraph<String> g1 = new IncidenceMatrixGraph<>();

        int numVer = 0;
        int numEdge = 0;
        String[] temp = fileScanner.nextLine().split(" ");
        numVer = Integer.parseInt(temp[0]);
        numEdge = Integer.parseInt((temp[1]));

        Vertex<String> tempVert;
        String lineTemp;

        for (int i = 0; i < numVer; i++) {
            lineTemp = fileScanner.nextLine().replaceAll("\\r\\n", "");
            tempVert = new Vertex<String>(lineTemp);
            g1.addVertex(tempVert);
        }

        Edge<String> edgeTemp;
        Vertex<String> v1;
        Vertex<String> v2;
        int weight;
        for (int i = 0; i < numEdge; i++) {
            lineTemp = fileScanner.nextLine().replaceAll("\\r\\n", "");
            temp = lineTemp.split(" ");
            v1 = g1.getVertexByValue(temp[0]);
            v2 = g1.getVertexByValue(temp[1]);
            weight = Integer.parseInt((temp[2]));
            edgeTemp = new Edge<>(v1, v2, weight);
            g1.addEdge(edgeTemp);
        }
        return g1;
    }
}
