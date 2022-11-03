import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2573 {
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static Queue<Operator> q = new LinkedList<>();
    static boolean trig = true;
    static int sum=0;

    public static class Operator {
        int x;
        int y;

        public Operator(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int dfs(Operator o) {
        visit[o.x][o.y] = true;

        for(int i=0; i<4; i++) {
            int nextX = o.x + dx[i];
            int nextY = o.y + dy[i];

            if(nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length)
                continue;
            if(map[o.x][o.y] != 0 && map[nextX][nextY] == 0)
                q.offer(new Operator(o.x, o.y));
            if(visit[nextX][nextY])
                continue;
            if(map[nextX][nextY] > 0) {
                dfs(new Operator(nextX, nextY));
            }
        }

        return 1;
    }

    public static void counterMethod() {
        while (!q.isEmpty()) {
            Operator temp = q.poll();

            map[temp.x][temp.y]--;
            if(map[temp.x][temp.y] < 0)
                map[temp.x][temp.y] = 0;
        }
    }

    public static void bfs(Operator o) {
        Queue<Operator> qInt = new LinkedList<>();

        qInt.offer(new Operator(o.x, o.y));
        visit = new boolean[n][m];
        visit[o.x][o.y] = true;

        while (!qInt.isEmpty()) {
            Operator temp = qInt.poll();


            for(int i=0; i<4; i++) {
                int nextX = temp.x + dx[i];
                int nextY = temp.y + dy[i];

                if(nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length)
                    continue;
                if(visit[nextX][nextY])
                    continue;
                if(map[nextX][nextY] > 0) {
                    visit[nextX][nextY] = true;
                    qInt.offer(new Operator(nextX, nextY));
                }
            }
        }
    }

    public static void isMany() {
        int count=0;

        LoopA :
        for(int i=0; i< map.length; i++) {

            for(int j=0; j<map[0].length; j++) {
                if(map[i][j] != 0) {
                    bfs(new Operator(i,j));
                    break LoopA;
                }
            }
        }

        LoopB :
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                count += map[i][j];
                if (map[i][j] != 0 && !visit[i][j]) {
                    trig = false;
                    break LoopB;
                }
            }
        }
        if(count == 0) {
            trig = false;
            sum = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
//        visit = new boolean[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (trig) {
            visit = new boolean[n][m];

            LoopC :
            for(int i=1; i<map.length-1; i++) {
                for(int j=1; j<map[0].length; j++) {
                    if(map[i][j] != 0) {
                        visit = new boolean[n][m];
                        sum += dfs(new Operator(i,j));
                        counterMethod();
                        isMany();
                        if(!trig)
                            break LoopC;
                    }
                }
            }
        }

        System.out.println(sum);
    }
}
