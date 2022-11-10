import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2842 {
    static int N;
    static int[][] map;
    static String[][] s_Map;
    static boolean[][] v_Map;
    static int[] start;
    static int[] dx = { -1, 1, 0, 0, -1, 1, -1, 1 };
    static int[] dy = { 0, 0, -1, 1, -1, 1, 1, -1 };

    static Queue<int[]> k = new LinkedList<>();

    public static class Operator implements Comparable<Operator> {
        int x;
        int y;
        int h;

        public Operator(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }

        @Override
        public int compareTo(Operator o) {
            return this.h - o.h;
        }
    }

    public void dfs(int x, int y, int h) {
        v_Map[x][y] = true;

        PriorityQueue<Operator> pq = new PriorityQueue<>();

        for (int i=0; i<8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(isRange(nextX, nextY))
                continue;
            if(v_Map[nextX][nextY])
                continue;

            pq.offer(isK(nextX,nextY)? new Operator(nextX, nextY, -1) : new Operator(nextX, nextY, Math.abs(map[x][y] - map[nextX][nextY])));
        }

        while (!pq.isEmpty()) {
            Operator temp = pq.poll();
            if(isK(temp.x, temp.y))
                k.poll();
            dfs(temp.x, temp.y, temp.h);
        }
    }

    public boolean isK(int x, int y) {
        return Objects.equals(s_Map[x][y], "K");
    }

    public boolean isRange(int x, int y) {

        return x < 0 || y < 0 || x >= N || y >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        s_Map = new String[N][N];
        v_Map = new boolean[N][N];

        start = new int[3];

        StringTokenizer st;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                s_Map[i][j] = st.nextToken();
                if(Objects.equals(s_Map[i][j], "K"))
                    k.offer(new int[] {i, j});
            }
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(Objects.equals(s_Map[i][j], "P")) {
                    start[0] = i;
                    start[1] = j;
                    start[2] = map[i][j];
                }
            }
        }

    }
}
