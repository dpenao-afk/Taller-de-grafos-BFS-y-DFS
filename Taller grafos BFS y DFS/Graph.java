import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class Graph {
    private final boolean[][] adjacencyMatrix;

    public Graph(int vertexCount) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("La cantidad de vertices no puede ser negativa.");
        }
        adjacencyMatrix = new boolean[vertexCount][vertexCount];
    }

    public int getVertexCount() {
        return adjacencyMatrix.length;
    }

    public void addEdge(int first, int second) {
        validateVertex(first);
        validateVertex(second);
        adjacencyMatrix[first][second] = true;
        adjacencyMatrix[second][first] = true;
    }

    public void removeEdge(int first, int second) {
        validateVertex(first);
        validateVertex(second);
        adjacencyMatrix[first][second] = false;
        adjacencyMatrix[second][first] = false;
    }

    public boolean hasEdge(int first, int second) {
        validateVertex(first);
        validateVertex(second);
        return adjacencyMatrix[first][second];
    }

    public void printAdjacencyMatrix() {
        System.out.print("   ");
        for (int vertex = 0; vertex < getVertexCount(); vertex++) {
            System.out.printf("%2d ", vertex);
        }
        System.out.println();

        for (int row = 0; row < getVertexCount(); row++) {
            System.out.printf("%2d ", row);
            for (int column = 0; column < getVertexCount(); column++) {
                System.out.printf("%2d ", adjacencyMatrix[row][column] ? 1 : 0);
            }
            System.out.println();
        }
    }

    public List<Integer> depthFirstSearch(int start) {
        validateVertex(start);

        List<Integer> traversal = new ArrayList<>();
        boolean[] visited = new boolean[getVertexCount()];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (visited[current]) {
                continue;
            }

            visited[current] = true;
            traversal.add(current);

            // Se apilan en orden inverso para visitar primero el vecino menor.
            for (int neighbor = getVertexCount() - 1; neighbor >= 0; neighbor--) {
                if (adjacencyMatrix[current][neighbor] && !visited[neighbor]) {
                    stack.push(neighbor);
                }
            }
        }

        return traversal;
    }

    public List<Integer> breadthFirstSearch(int start) {
        validateVertex(start);

        List<Integer> traversal = new ArrayList<>();
        boolean[] visited = new boolean[getVertexCount()];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.remove();
            traversal.add(current);

            for (int neighbor = 0; neighbor < getVertexCount(); neighbor++) {
                if (adjacencyMatrix[current][neighbor] && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return traversal;
    }

    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= getVertexCount()) {
            throw new IndexOutOfBoundsException(
                    "Vertice fuera de rango: " + vertex + ". El rango valido es 0 a "
                            + (getVertexCount() - 1) + ".");
        }
    }
}
