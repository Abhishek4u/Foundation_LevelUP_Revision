import java.util.ArrayList;
public class l002_directedGraph {

    // In directed graph you need only neighbour vtx as weight does not matters here
    // So you do not need edge class here 

    public static void main(String[] args) {

        solve();
    }

    public static void solve() {

        constructGraph();
        display();
    }

    static int N = 7;
    static ArrayList<Integer>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v) {

        graph[u].add(v);
        // in directed graph there is edge on one side only
    }

    public static void constructGraph() {
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<> ();
        }

        addEdge(0,1);
        addEdge(0,3);
        addEdge(1,2);
        addEdge(2,3);
        addEdge(3,4);
        addEdge(4,5);
        addEdge(4,6);
        addEdge(5,6);
    }

    public static void display() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            sb.append(i + " -> ");
            for(int v : graph[i]){
                sb.append(v + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    

}