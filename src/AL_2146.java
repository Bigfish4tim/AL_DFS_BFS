import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2146 {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int num;
    static int[] dx = new int[] { -1, 1, 0, 0 };
    static int[] dy = new int[] { 0, 0, -1, 1 };
    static int count = Integer.MAX_VALUE;
    static Queue<int[]> brig = new LinkedList<>();

    public static void bfs(int x, int y) {
        if(visit[x][y] || map[x][y] == 0)
            return;

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {x,y});
        visit[x][y] = true;
        map[x][y] += num;

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
                    continue;
                if(visit[nextX][nextY])
                    continue;
                if(map[nextX][nextY] == 0) {
                    brig.add(new int[] {temp[0], temp[1], 0});
                    continue;
                }

                q.add(new int[] {nextX, nextY});
                visit[nextX][nextY] = true;
                map[nextX][nextY] += num;
            }
        }
        num++;
    }

    public static void bfs2(int[] point) {
        Queue<int[]> q = new LinkedList<>();

        q.add(point);
        visit[point[0]][point[1]] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N)
                    continue;
                if(visit[nextX][nextY])
                    continue;
                if(map[nextX][nextY] > 0) {
                    if(map[nextX][nextY] == map[point[0]][point[1]])
                        continue;
                    if(map[nextX][nextY] != map[point[0]][point[1]])
                        if(temp[2] <= count) {
                            count = temp[2];
                            continue;
                        }
                }

                q.add(new int[] {nextX, nextY, temp[2]+1});
                visit[nextX][nextY] = true;

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visit = new boolean[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        num = 1;

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                bfs(i,j);

        while (!brig.isEmpty()) {
            visit = new boolean[N][N];
            int[] temp = brig.poll();
            bfs2(temp);
        }

        System.out.println(count);
    }
}
