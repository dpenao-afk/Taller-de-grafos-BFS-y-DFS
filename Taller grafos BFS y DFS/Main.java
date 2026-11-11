public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);

        System.out.println("Matriz de adyacencia:");
        graph.printAdjacencyMatrix();
        System.out.println("DFS desde 0: " + graph.depthFirstSearch(0));
        System.out.println("BFS desde 0: " + graph.breadthFirstSearch(0));
    }
}
