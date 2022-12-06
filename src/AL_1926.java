import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_1926 {
    static int n,m;
    static int[][] map;
    static boolean[][] visit;
    static int count;
    static int size;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        visit[x][y] = true;

        int picsize = 1;

        q.add(new int[] {x, y});

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
                    continue;
                if(map[nextX][nextY] == 0)
                    continue;
                if(visit[nextX][nextY])
                    continue;

                visit[nextX][nextY] = true;
                q.add(new int[] {nextX, nextY});
                picsize++;
            }
        }
        count++;
        if(picsize > size)
            size = picsize;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        count = 0;
        size = 0;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 0)
                    continue;
                if(visit[i][j])
                    continue;
                bfs(i,j);
            }
        }

        System.out.println(count);
        System.out.println(size);
    }
}
