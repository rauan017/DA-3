import java.io.*;
import java.util.*;
import com.google.gson.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();

        // Read JSON input
        Reader reader = new FileReader("input_example.json");
        Map<String, Object> data = gson.fromJson(reader, Map.class);
        reader.close();

        List<String> vertices = (List<String>) data.get("vertices");
        List<Map<String, Object>> edgesData = (List<Map<String, Object>>) data.get("edges");
        List<Edge> edges = new ArrayList<>();

        for (Map<String, Object> e : edgesData) {
            edges.add(new Edge(
                    (String) e.get("from"),
                    (String) e.get("to"),
                    ((Double) e.get("weight")).intValue()));
        }

        Graph graph = new Graph(vertices, edges);

        Map<String, Object> primResult = PrimAlgorithm.runPrim(graph);
        Map<String, Object> kruskalResult = KruskalAlgorithm.runKruskal(graph);

        try (FileWriter fw1 = new FileWriter("output_prim.json")) {
            gson.toJson(primResult, fw1);
        }
        try (FileWriter fw2 = new FileWriter("output_kruskal.json")) {
            gson.toJson(kruskalResult, fw2);
        }

        System.out.println("✅ Prim and Kruskal results saved successfully!");
    }
}
