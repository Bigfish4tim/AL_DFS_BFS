import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_10026 {
    static int N;
    static char[][] map;
    static boolean[][] visit1;
    static boolean[][] visit2;
    static int count1, count2;
    static int[] dx = new int[] { -1, 1, 0, 0 };
    static int[] dy = new int[] { 0, 0, -1, 1 };

    public static void bfs(int x, int y) {
        if(visit1[x][y])
            return;

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {x,y});
        visit1[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
                    continue;
                if(visit1[nextX][nextY])
                    continue;
                if(map[x][y] != map[nextX][nextY])
                    continue;
                q.add(new int[] {nextX, nextY});
                visit1[nextX][nextY] = true;
            }
        }
        count1++;
    }

    public static void bfs2(int x, int y) {
        if(visit2[x][y])
            return;

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {x,y});
        visit2[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
                    continue;
                if(visit2[nextX][nextY])
                    continue;
                if(map[x][y] != map[nextX][nextY])
                    if(map[x][y] == 'B' || map[nextX][nextY] == 'B')
                        continue;
                q.add(new int[] {nextX, nextY});
                visit2[nextX][nextY] = true;
            }
        }
        count2++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visit1 = new boolean[N][N];
        visit2 = new boolean[N][N];
        count1 = 0;
        count2 = 0;

        char[] line;

        for(int i=0; i<N; i++) {
            line = br.readLine().toCharArray();
            if (N >= 0) System.arraycopy(line, 0, map[i], 0, N);
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                bfs(i,j);
                bfs2(i,j);
            }
        }
        System.out.println(count1 + " " + count2);
    }
}
