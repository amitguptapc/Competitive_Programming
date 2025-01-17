package src.Graphs;

import java.util.ArrayList;

public class DFS<T> extends src.Graphs.Graph<T> {
    public ArrayList<T> visited = new ArrayList<>();

    public static void main(String[] args) {
        DFS<Integer> g = new DFS<>();
        g.addEdge(0, 1, true);
        g.addEdge(0, 4, false);
        g.addEdge(1, 2, true);
        g.addEdge(1, 3, false);
        g.addEdge(2, 3, false);
        g.addEdge(2, 4, true);
        g.addEdge(3, 4, true);
        System.out.println("Depth First Search : ");
        g.dfs(0);
    }

    void dfs(T node) {
        visited.add(node);
        System.out.print(node + " ");
        for (T neighbour : adjList.get(node))
            if (!visited.contains(neighbour))
                dfs(neighbour);
    }
}
