package Exercise2;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addNode(new Node('A')); //0
        graph.addNode(new Node('B')); //1
        graph.addNode(new Node('C')); //2
        graph.addNode(new Node('D')); //3
        graph.addNode(new Node('E')); //4

        graph.addEdge(0, 1); // A and B
        graph.addEdge(0, 2); // A and C
        graph.addEdge(0, 4); // A and E
        graph.addEdge(1, 0); // B and A
        graph.addEdge(1, 2); // B and C
        graph.addEdge(2, 0); // C and A
        graph.addEdge(2, 1); // C and B
        graph.addEdge(2, 3); // C and D
        graph.addEdge(3, 2); // D and C
        graph.addEdge(4, 0); // E and A

        graph.print();
        System.out.println("Check cac dinh lien thong voi B:");
        graph.breathFirstSearch(1); //check B
        System.out.println();
        System.out.println("Graph co lien thong khong? ");
        graph.isConnected(0);
        System.out.println("Degree of the vertex A: " + graph.degree(0));






    }
}
