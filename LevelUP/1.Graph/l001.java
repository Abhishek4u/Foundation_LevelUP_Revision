import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ArrayDeque;

public class l001 {

    // u, v are vertices
    // w is the weight b/w two vertices (ie. known as edge)

    public static class Edge {
        int v;
        int w;

        Edge() {
        }

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        constructGraph();
        // solve();
        solve2();
    }

    public static void solve() {  

        // display();
        // // removeEdge(0,3);
        // removeVtx(3);
        // display();

        // System.out.println(hasPath(0, 6, new boolean[N]) );
        allPath(0, 6, new boolean[N], "0", 0);
        heavyPath();
    }

    public static void solve2() {

        // hamiltonian();
        // gcc();
        // BFS_01(0, new boolean[N]);
        BFS_LevelWise(0, new boolean[N]);
    }

    // --------------------------------------------------------GRAPH-CONTRUCTION---------------------------------------

    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void constructGraph() {

        // Note. For-Each loop does not works for graph initializing
        for(int i = 0; i < N; i++) {

            graph[i] = new ArrayList<> ();
        }

        // u v w
        addEdge(0,1,10);
        addEdge(0,3,10);
        addEdge(1,2,10);
        addEdge(2,3,40);
        addEdge(3,4,2);
        addEdge(4,5,2);
        addEdge(5,6,3);
        addEdge(4,6,8);

        addEdge(0,6,1);
        addEdge(2,5,1);
    }

    public static void addEdge(int u, int v, int w) {

        // Here we are using bi-directional graph so we will add edged 
        // in both the vertices

        graph[u].add( new Edge(v,w) );
        graph[v].add( new Edge(u,w) );

    }
    
    public static void display() {

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {

            sb.append(i + " -> ");
            for(Edge e : graph[i]) {
                sb.append( " (" + e.v + " @ " + e.w + ")," );
            }
            sb.setCharAt(sb.length() - 1, '.');
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int findEdge(int u, int v) {
        
        for(int i = 0; i < graph[u].size() ; i++) {
            Edge e = graph[u].get(i);

            if(e.v == v) {
                // found vertex so send the index so we can remove this edge
                return i;
            }
        }

        return -1; // no edge found 
    }

    public static void removeEdge(int u, int v) {

        int idx = findEdge(u, v); // find edge from u to v (means find in u's arraylist)

        if(idx != -1) graph[u].remove(idx); // remove(v from u's arraylist)

        idx = findEdge(v,u); // now find edge from v to u (in v's arrayList remove u)

        if(idx != -1) graph[v].remove(idx);
    }

    public static void removeVtx(int u) {

        // start removing vertexes from last to avoid shifting
        while(graph[u].size() != 0) {

            Edge e = graph[u].get(graph[u].size() - 1);
            removeEdge(u, e.v);
        }
    }

    // ---------------------------------------------------------------------------------------------------------

    // ----------------------------------------------------PATHS------------------------------------------------

    public static boolean hasPath(int src, int dest, boolean[] vis) {

        if(src == dest) {
            return true;
        }

        vis[src] = true; // mark visited source
        
        boolean found = false;

        for(Edge e : graph[src]) {

            if(!vis[e.v]) {
                found = found || hasPath(e.v, dest, vis);
            }
        }

        // no need to unmark source bcz  if we cannot find in this path
        // then if another node also comes to this path then that node 
        // also cannot reach destination

        return found;
    }

    public static void allPath(int src, int dest, boolean[] vis, String ans, int cost) {

        if(src == dest) {

            System.out.println(ans + " @ " + cost);
        }

        vis[src] = true;

        for(Edge e : graph[src]) {

            if(!vis[e.v]) {

                allPath(e.v, dest, vis, ans + " " + e.v, cost + e.w);
            }
        }
        vis[src] = false; // unmark after visiting so that we can print all the paths
        // which go from same vertex
    }

    public static class pair {

        int weight;
        String path;

        pair(int w, String p) {

            this.weight = w;
            this.path = p;
        }

        @Override
        public  String toString() {
            return path + " @ " + this.weight;
        }
    }

    public static void heavyPath() {

        pair ans = heavyWeightPath(0, 6, new boolean[N]);
        System.out.println(ans);
    }

    public static pair heavyWeightPath(int src, int dest, boolean[] vis) {

        if(src == dest) {
            return new pair(0, src + "");
        }

        pair myAns = new pair(0, "");
        vis[src] = true;

        for(Edge e : graph[src]) {

            if(!vis[e.v]) {

                pair recAns = heavyWeightPath(e.v, dest, vis);

                if(myAns.weight < recAns.weight + e.w) {
                    // check by adding your own value so that you can update 
                    // as we are adding own value in answer here
                    // and also in first comparison 0 < 0 does not works
                    myAns.weight = recAns.weight + e.w;
                    myAns.path = src + " " + recAns.path;
                }
            }
        }
        vis[src] = false;
        return myAns;
    }

    // ----------------------------------------------------------------------------------------------------------------------

    // --------------------------------------------------------HAMILTONIAN---------------------------------------------------
    
    // HAMILTONIAN PATH is a path through which we can visit all the vtx from 1 vtx without having a cycle from starting vtx to ending vtx
    // HAMILTONIAN CYCLE is a path through which we can visit all the vtx from 1 vtx and have a cycle in starting and ending vtx
    public static void hamiltonian() {

        int count = 0;
        
        for(int i = 0; i < graph.length; i++) {
            System.out.println("For Vtx " + i + " -> ");
            count += hamiltonianPath(i, i, 0, new boolean[N], i + " ");
            // src, overallSrc, vtxCount, vis[], ans
        }
        System.out.println("\nThere are total " + count + " hamiltonian paths and cycles in this graph");
    }

    public static int hamiltonianPath(int src, int oSrc, int vtxCount, boolean[] vis, String ans) {
        if(vtxCount == vis.length - 1) {

            int idx = findEdge(src, oSrc);
            if(idx != -1) {
                // there is an edge b/w src ans oSrc means there is a cycle
                System.out.println("Hamiltonian *Cycle* " + ans);

            } else {
                // there is no edge and we can traverse all the vtx means this path is hamiltonian path
                System.out.println("Hamiltonian #Path# " + ans);
            }
            return 1;
        }

        int count = 0;
        vis[src] = true;

        for(Edge e : graph[src]) {

            if(!vis[e.v]) {
                count += hamiltonianPath(e.v, oSrc, vtxCount + 1, vis, ans + e.v + " ");
            }
        }

        vis[src] = false;
        return count;
    }

    // -------------------------------------------------------------------------------------------------------

    // ----------------------------------------------DFS-AND-GCC----------------------------------------------

    public static int dfs(int src, boolean[] vis) {

        vis[src] = true;
        int count = 0;

        for(Edge e : graph[src]) {
            if(!vis[e.v]) {
                count += dfs(e.v, vis);
            }
        }

        return count + 1; // +1 for own count
    }

    // get connected components
    public static void gcc() {

        int count = 0; // no of components present in graph
        int totalArea = 0; // total no of vtx in whole graph

        boolean vis[] = new boolean[N];

        for(int i = 0; i < N; i++) {
            if(!vis[i]) {
                count++;
                totalArea += dfs(i, vis);
            }
        }

        System.out.println("There are total " + count + " components in graph");
        System.out.println("There are total " + totalArea + " vertices in graph");
    }

    // ------------------------------------------------------------------------

    // ------------------------------------------BFS------------------------------------

    // Normal BFS
    public static void BFS_01(int src, boolean[] vis) {

        LinkedList<Integer> que = new LinkedList<> ();
        que.addLast(src);

        while(que.size() != 0) {
            int size = que.size();

            while(size-- > 0) {
                int vtx = que.removeFirst();

                if(vis[vtx]) {
                    System.out.println("Cycle found at " + vtx + " vertex " );
                    continue;
                    // if you add again it will form the cycle
                }
                vis[vtx] = true;

                for(Edge e : graph[vtx]) {
                    if(!vis[e.v] ) {
                        que.addLast(e.v);
                    }
                }
            }
        }
    }

    //----NOTE : ARRAYDEQUE WORKS FASTER FOR INSERTION AND DELETION FROM BOTH ENDS THAN LINKEDLIST
    // BUT IT DOES NOT SUPPORTS NULL VALUE----

    // Level-wise BFS
    // To print level-wise use 2 while loops(one inside one)
    public static void BFS_LevelWise(int src, boolean[] vis) {

        ArrayDeque<Integer> que = new ArrayDeque<> ();
        que.addLast(src);

        int level = 1;
        while(que.size() != 0) {

            int size = que.size();
            System.out.print("Level " + level + " - > ");

            while(size-- > 0) {
                int vtx = que.remove();

                if(vis[vtx]) {
                    // cycle found because already visited
                    continue;
                }
                
                vis[vtx] = true;
                System.out.print(vtx + ", ");
                for(Edge e : graph[vtx]) {

                    if(!vis[e.v]) {
                        que.addLast(e.v);
                    }
                }
            }
            level++;
            System.out.println(".");
        }
    }

    // ----------------------------------------------------------------------------

}