import java.util.*;
import java.io.*;

public class KruskalAlgorithm {

    static class DSU {
        private final Map<String, String> parent = new HashMap<>();

        public DSU(List<String> vertices) {
            for (String v : vertices)
                parent.put(v, v);
        }

        public String find(String v) {
            if (!parent.get(v).equals(v))
                parent.put(v, find(parent.get(v)));
            return parent.get(v);
        }

        public void union(String a, String b) {
            a = find(a);
            b = find(b);
            if (!a.equals(b))
                parent.put(a, b);
        }
    }

    public static Map<String, Object> runKruskal(Graph graph) {
        long startTime = System.currentTimeMillis();
        int operations = 0;

        List<Edge> sortedEdges = new ArrayList<>(graph.edges);
        Collections.sort(sortedEdges);
        operations += sortedEdges.size();

        DSU dsu = new DSU(graph.vertices);
        List<Edge> mst = new ArrayList<>();

        for (Edge e : sortedEdges) {
            operations++;
            String root1 = dsu.find(e.from);
            String root2 = dsu.find(e.to);
            if (!root1.equals(root2)) {
                mst.add(e);
                dsu.union(root1, root2);
            }
        }

        long endTime = System.currentTimeMillis();
        int totalCost = mst.stream().mapToInt(x -> x.weight).sum();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("algorithm", "Kruskal");
        result.put("mst_edges", mst);
        result.put("total_cost", totalCost);
        result.put("vertices", graph.vertices.size());
        result.put("edges", graph.edges.size());
        result.put("operations", operations);
        result.put("execution_time_ms", endTime - startTime);

        return result;
    }
}
