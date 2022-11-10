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

        if(!visit[x][y]) {
            visit[x][y] = true;
            count++;
        }

        boolean[] check = new boolean[4];
        int dir = d;

        for(int i=0; i<4; i++) {
            int nextD = (d+3)%4;
            int nextX = x+dx[nextD];
            int nextY = y+dy[nextD];

            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M)
                continue;
            if(visit[nextX][nextY] || map[nextX][nextY] == 1) {
                check[i] = true;
                continue;
            }

            dfs(nextX, nextY, nextD);
            d = nextD;
        }
        if(check[0] && check[1] && check[2] && check[3]) {
            int nextD = (dir+2)%4;
            int nextX = x+dx[nextD];
            int nextY = y+dy[nextD];
            if(!(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M)) {
                if(map[nextX][nextY] == 0)
                    dfs(nextX, nextY, dir);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N][M];
        map = new int[N][M];

        start = new int[3];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<3; i++) {
            start[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(start[0], start[1], start[2]);


        System.out.println(count);
    }
}
