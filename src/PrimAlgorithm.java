import java.util.*;
import java.io.*;

public class PrimAlgorithm {

    public static Map<String, Object> runPrim(Graph graph) {
        long startTime = System.currentTimeMillis();
        int operations = 0;

        Map<String, List<Edge>> adj = graph.getAdjacencyList();
        Set<String> visited = new HashSet<>();
        List<Edge> mst = new ArrayList<>();

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // Start from first vertex
        String start = graph.vertices.get(0);
        visited.add(start);
        pq.addAll(adj.get(start));
        operations++;

        while (!pq.isEmpty() && mst.size() < graph.vertices.size() - 1) {
            Edge e = pq.poll();
            operations++;
            if (visited.contains(e.to)) continue;
            visited.add(e.to);
            mst.add(e);

            for (Edge next : adj.get(e.to)) {
                if (!visited.contains(next.to)) {
                    pq.add(next);
                    operations++;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        int totalCost = mst.stream().mapToInt(x -> x.weight).sum();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("algorithm", "Prim");
        result.put("mst_edges", mst);
        result.put("total_cost", totalCost);
        result.put("vertices", graph.vertices.size());
        result.put("edges", graph.edges.size());
        result.put("operations", operations);
        result.put("execution_time_ms", endTime - startTime);

        return result;
    }
}
