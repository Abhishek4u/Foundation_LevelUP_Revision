public class questions {

    // Leetcode 200. Number of Islands
    // https://leetcode.com/problems/number-of-islands/

    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        
        int n = grid.length, m = grid[0].length;
        int count = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    fillIsland(grid, i, j);
                }
            }
        }
        return count;
    }
    
    public void fillIsland(char[][] grid, int i, int j) {
        int dir[][] = { {1,0}, {-1,0}, {0,1}, {0,-1} };
        
        for(int d[] : dir) {
            int x = d[0] + i;
            int y = d[1] + j;
            
            if( x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1') {
                
                grid[x][y] = '2';
                fillIsland(grid, x, y);
            }
        }
    }

    // Leetcode 695. Max Area of Island
    // https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
            
        int n = grid.length, m = grid[0].length;        
        int ans = 0;
        // now find the island with max area
        for(int i = 0;i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    ans = Math.max(ans, fillIsland(grid, i, j));
                }
            }
        }
        return ans;
    }
    
    public int fillIsland(int[][] grid, int i, int j) {
        
        grid[i][j] = 2;
        
        int dir[][] = { {0,1}, {0,-1}, {-1,0}, {1,0} };
        int count = 0;
        for(int d[] : dir) {
            
            int x = d[0] + i;
            int y = d[1] + j;
            
            if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1) {
                count += fillIsland(grid, x, y);
            }
        }
        return count + 1; // count own value
    }

    

}