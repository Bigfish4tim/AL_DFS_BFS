import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2468 {
    static int n;
    static int[][] map;
    static int max=0;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static boolean[][] visit;

    public static class Operator {
        int x;
        int y;
        public Operator(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs(int[][] pmap, int h, boolean[][] visited) {
        Queue<Operator> q = new LinkedList<>();

        int total = 0;

        for(int i=0; i<pmap.length; i++) {
            for(int j=0; j< pmap.length; j++) {
                if(pmap[i][j] > h && !visited[i][j]) {
                    q.offer(new Operator(i, j));
                    visited[i][j] = true;
                    total++;
                }
                while (!q.isEmpty()) {
                    Operator temp = q.poll();
                    for(int k=0; k<4; k++) {
                        int nextX = temp.x + dx[k];
                        int nextY = temp.y + dy[k];

                        if(nextX < 0 || nextX >= pmap.length || nextY < 0 || nextY >= pmap.length || pmap[nextX][nextY] <= h || visited[nextX][nextY]) {
                            continue;
                        }
                        q.offer(new Operator(nextX, nextY));
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
        return total;
    }

    public static int dfs(Operator o, int h, boolean[][] visited, int total) {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map.length; j++) {
                if(map[i][j] < h || visited[i][j])
                    continue;

                total = total + dfs2(new Operator(i,j),h,visited);

            }
        }

        return total;
    }
    public static int dfs2(Operator o, int h, boolean[][] visited) {
        visited[o.x][o.y] = true;
        for(int k=0; k<4; k++) {
            int nextX = o.x + dx[k];
            int nextY = o.y + dy[k];

            if(nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map.length || map[nextX][nextY] <= h || visited[nextX][nextY])
                continue;

//                    visited[nextX][nextY] = true;

            dfs2(new Operator(nextX, nextY), h, visited);
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visit = new boolean[n][n];

        int maxh = 0;

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(maxh < map[i][j]) {
                    maxh = map[i][j];
                }
            }
        }

        for(int i=1; i<maxh; i++) {
//            int h = bfs(map, i, visit);
            int h = dfs(new Operator(0, 0), i, visit, 0);

            if(max < h)
                max = h;

            visit = new boolean[n][n];
        }

        System.out.println(max);
    }
}
