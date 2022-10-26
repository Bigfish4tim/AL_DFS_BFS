import java.util.*;

public class AL_1260 {

    static int[][] map;
    static boolean[] visit;

    public static void DFS(int i, boolean[] visit) {
        visit[i] = true;

        System.out.print(i + " ");

        for(int j=1; j<map.length; j++) {
            if(map[i][j] == 1 && !visit[j]) {
                DFS(j, visit);
            }
        }
    }

    public static void BFS(int i, boolean[] visit) {
        Queue<Integer> q = new LinkedList<>();

        visit[i] = true;

        q.offer(i);

        System.out.print(i + " ");

        while (!q.isEmpty()) {
            int temp = q.poll();
            for(int j=1; j<map.length; j++) {
                if(map[temp][j] == 1 && !visit[j]) {
                    visit[j] = true;
                    q.offer(j);
                    System.out.print(j + " ");
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, m, v;

        n = sc.nextInt();
        m = sc.nextInt();
        v = sc.nextInt();

        map = new int[n+1][n+1];

        visit = new boolean[n+1];

        for(int i=1; i<m+1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            map[x][y] = 1;
            map[y][x] = 1;
        }

        boolean[] visitClone1 = visit.clone();
        boolean[] visitClone2 = visit.clone();

        DFS(v, visitClone1);
        System.out.println();
        BFS(v, visitClone2);
    }
}
