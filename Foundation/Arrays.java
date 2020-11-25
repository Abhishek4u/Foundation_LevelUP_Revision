public class Arrays {

    // 1. https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/bar-chart-official/ojquestion
    
    public static void barChart(int arr[], int max) {
        int temp = max;
        for (int i = 0; i < temp; i++) {
            for (int ele: arr) {
                if (ele >= max) System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
            max--;
        }
    }

    // 2. https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/sum-of-two-arrays-official/ojquestion

    public static void sumOf2Arrays(int arr1[], int arr2[], int n1, int n2) {

        int temp = 0;
        int i1 = n1 - 1, i2 = n2 - 1;
        int max = Math.max(n1,n2);
        
        int ans[] = new int[max+1];
        
        for(; max >= 0; max--) {
            
            int v1 = i1 >= 0 ? arr1[i1--] : 0;
            int v2 = i2 >= 0 ? arr2[i2--] : 0;
            
            int val = v1 + v2 + temp;
            if(val >= 10) {
                temp = val / 10;
                val = val % 10;
            } else temp = 0;
            ans[max] = val;
        }

        boolean came = false;
        for(int i = 0; i < ans.length; i++) {
            if(ans[i] == 0 && !came) continue;
            came = true;
            System.out.println(ans[i]);
        }

    }

    // 3. https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/difference-of-two-arrays-official/ojquestion

    public static void diffOf2Arrays(int arr1[], int arr2[], int n1, int n2) {

        int temp = 0;
        int i1 = n1 - 1, i2 = n2 - 1;
        int max = Math.max(n1,n2);
        
        int ans[] = new int[max+1];
        
        for(; max >= 0; max--) {
            
            int v1 = i1 >= 0 ? arr1[i1--] : 0;
            int v2 = i2 >= 0 ? arr2[i2--] : 0;
            
            int val = v2 - v1 - temp;
            if(val < 0) {
                temp = 1;
                val += 10;
            } else temp = 0;
            
            ans[max] = val;
        }

        boolean came = false;
        for(int i = 0; i < ans.length; i++) {
            if(ans[i] == 0 && !came) continue;
            came = true;
            System.out.println(ans[i]);
        }
    }

    // 4. https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/reverse-an-array-official/ojquestion

    public static void reverse(int[] a) {
        // write your code here
        int si = 0, ei = a.length - 1;
        
        while(si < ei) {
            int temp = a[si];
            a[si] = a[ei];
            a[ei] = temp;
            
            si++;
            ei--;
        }
    }

    // 5. https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/rotate-an-array-official/ojquestion
    
    public static void rotate(int[] a, int k) {
        // write your code here
        k %= a.length;
        if (k < 0) {
            k += a.length;
        }

        int n = a.length;

        reverse(a, 0, n - k - 1);
        reverse(a, n - k, n - 1);
        reverse(a, 0, n - 1);

    }

    public static void reverse(int arr[], int low, int high) {

        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;

            low++;
            high--;
        }

    }

    // 6. https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/inverse-of-an-array-official/ojquestion

    public static int[] inverse(int[] a) {
        // write your code here
        int n = a.length;
        int b[] = new int[n];
        
        for(int i = 0;i < n; i++) {
            b[a[i]] = i;
        }
        return b;
    }

    // 7. https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/subsets-of-array-official/ojquestion

    public static void printSubsets(int arr[], int n) {
        int max = 1 << n;

        for (int i = 0; i < max; i++) {

            for (int j = n - 1; j >= 0; j--) {

                int val = 1 << j;

                if ((val & i) != 0) {
                    System.out.print(arr[n - 1 - j] + "	");
                } else {
                    System.out.print("-	");
                }
            }
            System.out.println();
        }
    }

    // 8. https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/ceil-floor-official/ojquestion

    public static void ceilFloor(int arr[], int n) {
        int ceil = 0, floor = 0;
        int si = 0, ei = n - 1;
        
        while(si <= ei) {
            int mid = (si + ei) / 2;
            
            if(arr[mid] == ele) {
                ceil = ele;
                floor = ele;
                break;
                
            } else if(arr[mid] < ele) {
                floor = arr[mid];
                si = mid+1;
                
            } else if(arr[mid] > ele) {
                ceil = arr[mid];
                ei = mid-1;
            }
        }
        System.out.println(ceil + "\n" + floor);
    }

    // 9. https://www.pepcoding.com/resources/online-java-foundation/function-and-arrays/first-index-last-index-official/ojquestion

    public static void firstIndex(int arr[], int ele) {
        int index = -1;
        int si = 0, ei = arr.length - 1;
        
        while(si <= ei) {
            int mid = (si + ei) / 2;
            
            if(arr[mid] > ele) {
                ei = mid - 1;
                
            }  else if(arr[mid] < ele) {
                si = mid + 1;
                
            } else {
                index = mid;
                ei = mid - 1; // same elt can be on left side too
            }
        }
        System.out.println(index);
    }
    
    public static void lastIndex(int arr[], int ele) {
        int index = -1;
        int si = 0, ei = arr.length - 1;
        
        while(si <= ei) {
            int mid = (si + ei) / 2;
            
            if(arr[mid] > ele) {
                ei = mid - 1;
                
            }  else if(arr[mid] < ele) {
                si = mid + 1;
                
            } else {
                index = mid;
                si = mid + 1; // same elt can be on right side too
            }
        }
        System.out.println(index);
    }

    // 10. https://www.pepcoding.com/resources/online-java-foundation/2d-arrays/matrix-multiplication-official/ojquestion

    public void matrixMultiplication(int arr1[][], int n1, int m1, int arr2[][], int n2, int m2) {

        if (m1 != n2) {
            System.out.println("Invalid input");
            return;
        }

        int ans[][] = new int[n1][m2];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m2; j++) {
                for (int k = 0; k < n2; k++) {
                    ans[i][j] += (arr1[i][k] * arr2[k][j]);
                }
            }
        }
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m2; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 11. https://www.pepcoding.com/resources/online-java-foundation/2d-arrays/wave-traversal-official/ojquestion

    public static void waveTraversal(int arr[], int n, int m) {

        int dir = 1;
        for(int j = 0; j < m; j++) {
            int i = dir == 1 ? 0 : n-1;
            for(; dir == 1 ? i < n : i >= 0 ; i += dir) {
                System.out.println(arr[i][j]);
            }
            dir = dir == 1 ? -1 : 1;
        }
    }

    // 12. https://www.pepcoding.com/resources/online-java-foundation/2d-arrays/spiral-display-official/ojquestion#

    public static void spiralTraversal(int arr[][], int n, int m) {

        int rowmin = 0, colmin = 0, rowmax = n - 1, colmax = m - 1, count = 0;

        while (count < n * m) {
            // left wall
            for (int i = rowmin; i <= rowmax && count < n * m; i++) {
                System.out.println(arr[i][colmin]);
                count++;
            }
            colmin++;
            // bottom wall
            for (int j = colmin; j <= colmax && count < n * m; j++) {
                System.out.println(arr[rowmax][j]);
                count++;
            }
            rowmax--;
            // right wall
            for (int i = rowmax; i >= rowmin && count < n * m; i--) {
                System.out.println(arr[i][colmax]);
                count++;

            }
            colmax--;
            // top wall
            for (int j = colmax; j >= colmin && count < n * m; j--) {
                System.out.println(arr[rowmin][j]);
                count++;
            }
            rowmin++;
        }
    }

    // 13. https://www.pepcoding.com/resources/online-java-foundation/2d-arrays/exit-point-matrix-official/ojquestion

    public static void exitPoint(int arr[][], int n, int m) {
        int i = 0, j = 0;
        
        int dir[][] = { {0,1}, {1,0}, {0,-1}, {-1,0} };
        int currDir = 0; // current direction
        
        while( i >= 0 && j >= 0 && i < n && j < m) {
            
            if(arr[i][j] == 1) {
                currDir = (currDir + 1) % 4; 
            }
            
            i = dir[currDir][0] + i;
            j = dir[currDir][1] + j;
        }
        
        i = i - dir[currDir][0];
        j = j - dir[currDir][1];
        System.out.print(i + "\n" + j);
    }

    // 14. https://www.pepcoding.com/resources/online-java-foundation/2d-arrays/rotate-by-90-degree-official/ojquestion

    public static void rotate90(int arr[][], int n, int m) { 

        transposeArray(arr, n);
        reverseEltsRowWise(arr,n );
    }
    
    public static void transposeArray(int arr[][], int n) {
        
        for(int i= 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }
    
    public static void reverseEltsRowWise(int arr[][], int n) {
        
        for(int i= 0; i < n; i++) {
            for(int si = 0, ei = n-1; si < ei; si++, ei--) {
                int temp = arr[i][si];
                arr[i][si] = arr[i][ei];
                arr[i][ei] = temp;
            }
        }
    }
}