import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_3197 {
    static int R, C;
    static char[][] map;
    static int[][] Lpoints = new int[2][2];

    static Queue<int[]> StartPoint = new LinkedList<>();

    static boolean[][] Lastvisit;
    static boolean[][] visit;

    static boolean isFind;

    static int ans=0;

    static Queue<int[]> xq = new LinkedList<>();
    static Queue<int[]> xs = new LinkedList<>();

    static int[] dx = new int[] { -1, 1, 0, 0 };
    static int[] dy = new int[] { 0, 0, -1, 1 };

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] {x, y});
        visit[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C)
                    continue;
                if(visit[nextX][nextY])
                    continue;
                if(map[nextX][nextY] == 'X') {
                    StartPoint.offer(new int[] {temp[0], temp[1]});
                    continue;
                }
                if (nextX == Lpoints[1][0] && nextY == Lpoints[1][1]) {
                    isFind = true;
                    break;
                }
                q.offer(new int[] {nextX, nextY});
                visit[nextX][nextY] = true;
            }

        }
    }

    public static void findx() {
        int size = xq.size();
        for(int j=0; j<size; j++) {
            int[] temp = xq.poll();

            for(int i=0; i<4; i++) {
                assert temp != null;
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) {
                    if(i==3)
                        xq.offer(temp);
                    continue;
                }
                if(map[nextX][nextY] == 'X') {
                    if(i==3)
                        xq.offer(temp);
                    continue;
                }
                xs.offer(temp);
                break;
            }
        }

        while (!xs.isEmpty()) {
            int[] temp = xs.poll();
            map[temp[0]][temp[1]] = '.';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        Lastvisit = new boolean[R][C];
        visit = new boolean[R][C];

        int Lcount = 0;
        for(int i = 0 ; i < R ; ++i) {
            char[] line = br.readLine().toCharArray();
            for(int j = 0 ; j < C ; ++j) {
                map[i][j] = line[j];
                if(map[i][j] == 'L') {
                    Lpoints[Lcount][0] = i;
                    Lpoints[Lcount][1] = j;
                    Lcount++;
                }
                if(map[i][j] == 'X') {
                    xq.offer(new int[] {i, j});
                }
            }
        }
        StartPoint.offer(new int[] {Lpoints[0][0], Lpoints[0][1]});

        while (!StartPoint.isEmpty()) {
            visit = new boolean[R][C];
            int[] starter = StartPoint.poll();
            bfs(starter[0], starter[1]);
            if(isFind)
                break;
            findx();
            ans++;
        }

        System.out.println(ans);
    }
}
