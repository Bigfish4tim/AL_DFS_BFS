import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_7569 {
    static int m, n, h;
    static int[][][] map;
    static int[] dx = { -1, 1, 0, 0, 0, 0 };
    static int[] dy = { 0, 0, -1, 1, 0, 0 };
    static int[] dz = { 0, 0, 0, 0, -1, 1 };
    static int sum = 0;


    public static class Points {
        int x;
        int y;
        int z;
        public Points(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void bfs(Queue<Points> q) {

        while (!q.isEmpty()) {
            int size = q.size();

            for(int i=0; i<size; i++) {
                Points temp = q.poll();

                assert temp != null;
                int x = temp.x;
                int y = temp.y;
                int z = temp.z;

                for(int j=0; j<6; j++) {

                    int nowX = x+dx[j];
                    int nowY = y+dy[j];
                    int nowZ = z+dz[j];

                    if(nowX<0 || nowY<0 || nowZ<0 || nowX>=m || nowY>=n || nowZ>=h)
                        continue;

//                    if(x+dx[i] == 0 || y+dy[i] == 0 || z+dz[i] == 0) {
//                        q.offer(new Points(x+dy[i], y+dy[i], z+dz[i]));
//                        map[x+dx[i]][y+dy[i]][z+dz[i]] = 1;
//                    }
                    if(map[nowX][nowY][nowZ] == 0) {
                        q.offer(new Points(nowX, nowY, nowZ));
                        map[nowX][nowY][nowZ] = 1;
                    }
                }
            }

            sum++;
        }
    }

    public static void main(String[] args) throws IOException {
        Queue<Points> q = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        StringTokenizer st = new StringTokenizer(s);

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[m][n][h];

        for(int i=0; i<h; i++) {
            for(int j=0; j<n; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<m; k++) {
                    map[k][j][i] = Integer.parseInt(st.nextToken());
                    if(map[k][j][i] == 1) {
                        q.offer(new Points(k,j,i));
                    }
                }
            }
        }

        bfs(q);

        boolean trig = false;
        for(int i=0; i<h; i++) {
            for(int j=0; j<n; j++) {
                for(int k=0; k<m; k++) {
                    if(map[k][j][i] == 0) {
                        trig = true;
                    }
                }
            }
        }

        if(trig) {
            System.out.println(-1);
        } else {
            System.out.println(sum-1);
        }
    }
}
