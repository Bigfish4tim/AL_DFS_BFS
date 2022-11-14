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

    public static int bfs(Operator o, int h) {
        Queue<Operator> q = new LinkedList<>();

        q.offer(o);
        visit[o.x][o.y] = true;

        while (!q.isEmpty()) {
            Operator temp = q.poll();

            for(int i=0; i<4; i++) {
                int nextX = temp.x + dx[i];
                int nextY = temp.y + dy[i];

                if(nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map.length)
                    continue;
                if(visit[nextX][nextY])
                    continue;
                if(map[nextX][nextY] <= h)
                    continue;

                q.offer(new Operator(nextX, nextY));
                visit[nextX][nextY] = true;
            }
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

        for(int k=0; k<maxh; k++) {
            visit = new boolean[n][n];
            int count = 0;
            for(int i=0; i<map.length; i++) {
                for(int j=0; j<map.length; j++) {
                    if(map[i][j] <= k)
                        continue;
                    if(visit[i][j])
                        continue;

                    count += bfs(new Operator(i,j), k);
                }
            }
            if(max < count)
                max = count;
        }

        System.out.println(max);
    }
}
