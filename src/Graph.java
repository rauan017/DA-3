import java.util.*;

public class Graph {
    public List<String> vertices;
    public List<Edge> edges;

    public Graph(List<String> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String v : vertices)
            adj.put(v, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.from).add(e);
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight)); // undirected
        }
        return adj;
    }
}
