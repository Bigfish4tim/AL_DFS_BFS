import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_14503 {
    static int N, M;
    static int[] start;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int count = 0;

    static void dfs(int x, int y, int d) {
        visit[x][y] = true;

        boolean[] check = new boolean[4];

        for(int i=0; i<4; i++) {
            int nextX = x+dx[(d+i+1)%4];
            int nextY = y+dy[(d+i+1)%4];

            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M)
                continue;
            if(visit[nextX][nextY] || map[nextX][nextY] == 1) {
                check[i] = true;
                continue;
            }

            visit[nextX][nextY] = true;
            dfs(nextX, nextY, (d+i+1)%4);
        }
        if(check[0] && check[1] && check[2] && check[3]) {
            dfs(x+dx[(d+2)%4], y+dy[(d+2)%4], d);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N][M];

        start = new int[3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
