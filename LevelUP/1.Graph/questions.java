public class questions {

    // Leetcode 200. Number of Islands
    // https://leetcode.com/problems/number-of-islands/

    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        
        int n = grid.length, m = grid[0].length;
        int count = 0;
        int dir[][] = { {1,0}, {-1,0}, {0,1}, {0,-1} };

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    fillIsland(grid, i, j, dir);
                }
            }
        }
        return count;
    }
    
    public void fillIsland(char[][] grid, int i, int j, int dir[][]) {
        grid[i][j] = '2';
        
        for(int d[] : dir) {
            int x = d[0] + i;
            int y = d[1] + j;
            
            if( x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1') {
                
                fillIsland(grid, x, y, dir);
            }
        }
    }

    // Leetcode 695. Max Area of Island
    // https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
            
        int n = grid.length, m = grid[0].length;   
        int ans = 0;  
        
        int dir[][] = { {0,1}, {0,-1}, {-1,0}, {1,0} };
        // now find the island with max area
        for(int i = 0;i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    ans = Math.max(ans, fillIsland(grid, i, j,dir));
                }
            }
        }
        return ans;
    }
    
    public int fillIsland(int[][] grid, int i, int j, int dir[][]) {
        
        grid[i][j] = 2;
        
        int count = 0;
        for(int d[] : dir) {
            
            int x = d[0] + i;
            int y = d[1] + j;
            
            if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1) {
                count += fillIsland(grid, x, y, dir);
            }
        }
        return count + 1; // count own value
    }

    // 463. Island Perimeter
    // https://leetcode.com/problems/island-perimeter/

    // 1. Using floodFill
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        
        int dir[][] = { {0,1}, {0,-1}, {1,0}, {-1,0} };
        
        for(int i = 0;i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    return totalPerimeter(grid, i, j, dir);
                }
            }
        }
        return 0;
    }
    
    public int totalPerimeter(int[][] grid, int i, int j, int dir[][]) {
        grid[i][j] = 2;
        
        int count = 4;
        for(int d[] : dir) {
            int x = d[0] + i;
            int y = d[1] + j;
            
            if( x >= 0 && y >= 0 && x < grid.length && y < grid[0].length) {
                if( grid[x][y] == 1 ) {
                    // connected with unvisited box so decrement edge
                    count--;
                    count += totalPerimeter(grid,x, y, dir);
                
                } else if( grid[x][y] == 2) {
                    // connected with visited box so decrement edge
                    count--;
                }                
            }
        }
        return count;
    }

    // Using normal logic
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        
        int dir[][] = { {0,1}, {0,-1}, {1,0}, {-1,0} };
        
        int count = 0, nbrs = 0;
        // count is number of islands and nbrs is count of neighbours
        for(int i = 0;i < n; i++) {
            for(int j = 0; j < m; j++) {
                
                if(grid[i][j] == 1) {
                    count++;
                    
                    // now count the neighbours
                    for(int d[] : dir) {
                        int x = d[0] + i;
                        int y = d[1] + j;
                        
                        if( x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                            nbrs++;
                        }                       
                   }
                }
            }
        }        
        int totalPerimeter = count*4 - nbrs;
        // decrement the connected boundaries (ie. count of neighbours)
        // because connected edges does not contribute in perimeter
        return totalPerimeter;
    }

    // 130. Surrounded Regions
    // https://leetcode.com/problems/surrounded-regions/

    public void solve(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return;
        
        int n = grid.length, m = grid[0].length;
        
        int dir[][] = { {0,1} , {0,-1} , {1,0}, {-1,0} };
        
        for(int i = 0;i < n; i++) {            
            for(int j = 0; j < m; j++) {
                if(i == 0 || j == 0 || i == n-1 || j == m-1) {
                    // fill false info on border elts
                    if(grid[i][j] == 'O') {
                        capture(grid, i, j, 'O', '#', dir);
                    }
                }                
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 'O') {
                    // capture the remaining zeroes
                    capture(grid, i, j, 'O', 'X', dir);                    
                }                
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // correct the false info
                if(grid[i][j] == '#') {
                    capture(grid, i, j, '#', 'O', dir);                    
                }                
            }
        }
    }
    
    public void capture(char[][] grid, int i, int j, char a, char b, int[][] dir) { 
        grid[i][j] = b;
        
        for(int d[] : dir) {
            
            int x = d[0] + i;
            int y = d[1] + j;
            
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                if(grid[x][y] == a) {
                    capture(grid, x, y, a, b, dir);
                }
            }
        }
    }

    // 785. Is Graph Bipartite?
    // https://leetcode.com/problems/is-graph-bipartite/

    public boolean isBipartite(int[][] graph) {        
        int n = graph.length, m = graph[0].length;
        
        int color[] = new int[n];
        // will work as visited array and also as bipartite colors
        
        Arrays.fill(color, -1);
        // -1 : unvisited, 0 : red color, 1 : green color
        
        // will call for all components in graph
        for(int i = 0; i < n; i++) {
            // visit unvisited components
            if( color[i] == -1) {
                if( !bfsTraverse(graph, i, color) ) { 
                    // current component of graph is not bipartite
                    return false;
                }
            }
        }        
        return true;
    }
    
    public boolean bfsTraverse(int[][] graph, int src, int[] color) {
        
        ArrayDeque<Integer> que = new ArrayDeque<> ();
        que.add(src);
        
        int currentColor = 0; // 0 : red, 1 : green
        
        while(que.size() != 0) {            
            int size = que.size();
            
            while(size-- > 0) {
                int vtx = que.remove();
                
                if(color[vtx] != -1) {
                    // means current vertex is already visited 
                    // so check for the color and if different than 
                    // current color means graph is not bipartite
                    if(color[vtx] != currentColor) {
                        return false;
                    }
                    else continue;
                    // do not add already visited vertices
                }
                
                color[vtx] = currentColor; // mark color of current vertex
                for(int v : graph[vtx]) {

                    if(color[v] == -1) {
                        // add unvisited vertexes 
                        que.add(v);
                    }                        
                }
            }
            currentColor = (currentColor + 1) % 2;
            // to toggle the color
        }
        return true;
    }

    // 994 Rotting Oranges
    
    public int orangesRotting(int[][] grid) {

        int n = grid.length, m = grid[0].length;
        ArrayDeque<Integer> que = new ArrayDeque<> ();

        int freshOranges = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                
                if(grid[i][j] == 2) {
                    que.addLast( i*m + j);

                } else if(grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        if(que.size() == 0) {
            // no oranges are rotten
            return -1;
        }
        int dir[][] = { {0,1}, {-1,0}, {1,0}, {0,-1} };

        int time = 0;
        while(que.size() != 0) {
            int size = que.size();

            while(size-- > 0) {
                int loc = que.removeFirst();

                int x = loc / m;
                int y = loc % m;

                for(int d[] : dir) {
                    int i = d[0] + x;
                    int j = d[1] + y;

                    if( i >= 0 && j >= 0 && i < n && j < m && grid[i][j] == 1) {
                        grid[i][j] = 2; // now its rotten 
                        // updating bcz it can be added again and can create loop

                        que.addLast(i*m + j);
                        freshOranges--;
                    }
                }
            }
            time++;
        }
        return freshOranges == 0 ? time : -1;
        // if all freshOranges are not rotten that means ans not possible
    }

    // 1091. Shortest Path in Binary Matrix
    // https://leetcode.com/problems/shortest-path-in-binary-matrix/

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return -1;
        
        int n = grid.length, m = grid[0].length;
        
        if(grid[0][0] != 0 || grid[n-1][m-1] != 0) return -1;
        if(n == 1 && m == 1 && grid[0][0] == 0) return 1;
        
        ArrayDeque<Integer> que = new ArrayDeque<> ();
        que.add(0);
        
        int dir[][] = { {0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {-1,-1}, {1,-1}, {-1,1} };
        int level = 0;
        
        while(que.size() != 0) {            
            int size = que.size() ;
            level++;
            
            while(size-- > 0) {
                int loc = que.removeFirst();
                int x = loc/m;
                int y = loc%m;
             
                if(grid[x][y] == -1) continue;
                // already visited
                
                grid[x][y] = -1; // make it visited
                
                for(int d[] : dir) {
                    int i = d[0] + x;
                    int j = d[1] + y;
                    
                    if( i >= 0 && j >= 0 && i < n && j < m && grid[i][j] == 0 ) {
                        if(i == n-1 && j == m-1) return level+1;
                        que.addLast(i*m + j);
                    }
                }
            }
        }
        return -1;// if not possible then return -1
    }
    
}