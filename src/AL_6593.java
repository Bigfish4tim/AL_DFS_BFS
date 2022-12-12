import java.io.*;
import java.util.*;

public class AL_6593 {
    static int L,R,C;
    static char[][][] map;
    static boolean[][][] visit;
    static int[] start;
    static int count;
    static boolean trig;

    static int[] dx = new int[] { -1, 1, 0, 0, 0, 0 };
    static int[] dy = new int[] { 0, 0, -1, 1, 0, 0 };
    static int[] dz = new int[] { 0, 0, 0, 0, -1, 1 };

    public static void bfs(int x, int y, int z) {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {x,y,z,0});

        visit[x][y][z] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for(int i=0; i<6; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];
                int nextZ = temp[2] + dz[i];

                if(nextX < 0 || nextY < 0 || nextZ < 0 || nextX >= L || nextY >= R || nextZ >= C)
                    continue;
                if(visit[nextX][nextY][nextZ])
                    continue;
                if(map[nextX][nextY][nextZ] == '#')
                    continue;
                if(map[nextX][nextY][nextZ] == 'E') {
                    if(count >= temp[3]+1)
                        count = temp[3]+1;
                    trig = true;
                    continue;
                }
                q.add(new int[] {nextX, nextY, nextZ, temp[3]+1});
                visit[nextX][nextY][nextZ] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L==0 && R==0 && C==0)
                break;

            map = new char[L][R][C];
            visit = new boolean[L][R][C];

            char[] line;
            char[] empty = new char[] {};

            count = Integer.MAX_VALUE;

            for(int i=0; i<L; i++) {
                for(int j=0; j<R; j++) {
                    line = br.readLine().toCharArray();
                    if(L==0 && R==0 && C==0) {
                        return;
                    }
                    for(int k=0; k<C; k++) {
                        map[i][j][k] = line[k];
                        if(map[i][j][k] == 'S')
                            start = new int[] {i, j, k};
                    }
                }
                line = br.readLine().toCharArray();
            }

            bfs(start[0], start[1], start[2]);

//            if(trig)
//                bw.write("Escaped in " + count + " minute(s).");
//            else
//                bw.write("Trapped!");

            bw.write(trig? "Escaped in " + count + " minute(s).\n" : "Trapped!\n");
        }

        bw.flush();
        bw.close();
    }
}
