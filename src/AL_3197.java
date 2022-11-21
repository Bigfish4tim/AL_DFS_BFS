import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_3197 {
    static int R, C;
    static char[][] map;
    static int[][] Lpoints = new int[2][2];

    static Queue<int[]> StartPoint = new LinkedList<>();

    static boolean[][] visit;

    static boolean isFind;

    static int ans=0;

    static Queue<int[]> xq = new LinkedList<>();
    static Queue<int[]> xs = new LinkedList<>();

    static int[] dx = new int[] { -1, 1, 0, 0 };
    static int[] dy = new int[] { 0, 0, -1, 1 };

    public static Queue<int[]> bfs(Queue<int[]> q) {

        Queue<int[]> nextq = new LinkedList<>();

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            if (temp[0] == Lpoints[1][0] && temp[1] == Lpoints[1][1]) {
                isFind = true;
                break;
            }
            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C || visit[nextX][nextY])
                    continue;

                visit[nextX][nextY] = true;

                if(map[nextX][nextY] == 'X') {
                    nextq.offer(new int[] {nextX, nextY});
                    continue;
                }
                q.offer(new int[] {nextX, nextY});
            }
        }
        return nextq;
    }

    public static void findx() {
        int size = xq.size();
        for(int j=0; j<size; j++) {
            int[] temp = xq.poll();

            for(int i=0; i<4; i++) {
                assert temp != null;
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C)
                    continue;

                if(map[nextX][nextY] == 'X') {
                    map[nextX][nextY] = '.';
                    xq.offer(new int[] {nextX, nextY});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
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
        visit = new boolean[R][C];
        visit[Lpoints[0][0]][Lpoints[0][1]] = true;

        while (true) {
            StartPoint = bfs(StartPoint);
            if(isFind)
                break;
            findx();
            ans++;
        }

        System.out.println(ans);
    }
}
