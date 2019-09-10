import java.util.*;

public class VivekNparty {
    private static HashMap<String, ArrayList<String>> adjList;
    private static HashMap<String, Integer> ordering; // to maintain the order in which the input is given

    private static ArrayList<String> toplogicalSort() {
        PriorityQueue<String> q = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return ordering.get(o1) - ordering.get(o2);
            }
        });
        ArrayList<String> topoSort = new ArrayList<>();
        HashMap<String, Integer> inDegree = new HashMap<>();
        for (String s : adjList.keySet())
            inDegree.put(s, 0);
        for (String node : adjList.keySet()) {
            for (String neighbour : adjList.get(node))
                inDegree.put(neighbour, inDegree.get(neighbour) + 1);
        }
        for (String node : adjList.keySet()) {
            if (inDegree.get(node) == 0)
                q.add(node);
        }
        while (!q.isEmpty()) {
            String node = q.poll();
            topoSort.add(node);
            for (String neighbour : adjList.get(node)) {
                inDegree.put(neighbour, inDegree.get(neighbour) - 1);
                if (inDegree.get(neighbour) == 0)
                    q.add(neighbour);
            }
        }
        return topoSort;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = 1;
        ordering = new HashMap<>();
        while (sc.hasNext()) {
            int n = sc.nextInt();
            adjList = new HashMap<>();
            String s;
            for (int i = 0; i < n; i++) {
                s = sc.next();
                adjList.put(s, new ArrayList<>());
                ordering.put(s, i);
            }
            int m = sc.nextInt();
            String u, v;
            for (int i = 0; i < m; i++) {
                u = sc.next();
                v = sc.next();
                ArrayList<String> temp = adjList.get(u);
                temp.add(v);
            }
            System.out.print("Case #" + (t++) + ": Vivek should drink beverages in this order:");
            ArrayList<String> list = toplogicalSort();
            for (String str : list)
                System.out.print(" " + str);
            System.out.println(".\n");
        }
    }
}