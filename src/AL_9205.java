import java.io.*;
import java.util.*;

public class AL_9205 {
    static int t;
    static int n;
    static int[] home;
    static int[][] store;
    static int[] rock;
    static boolean[] visit;

    static boolean bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] {x, y});

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            if(Math.abs(temp[0] - rock[0]) + Math.abs(temp[1] - rock[1]) <= 1000) {
                return true;
            }
            for (int i=0; i<n; i++) {
                if(visit[i])
                    continue;
                if(Math.abs(temp[0] - store[i][0]) + Math.abs(temp[1] - store[i][1]) <= 1000) {
                    visit[i] = true;
                    q.offer(new int[]{store[i][0], store[i][1]});
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++) {
            n = Integer.parseInt(br.readLine());
            home = new int[2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            store = new int[n][2];
            visit = new boolean[n];

            home[0] = Integer.parseInt(st.nextToken());
            home[1] = Integer.parseInt(st.nextToken());
            for(int j=0; j<n; j++) {
                st = new StringTokenizer(br.readLine());
                store[j][0] = Integer.parseInt(st.nextToken());
                store[j][1] = Integer.parseInt(st.nextToken());
            }
            rock = new int[2];
            st = new StringTokenizer(br.readLine());
            rock[0] = Integer.parseInt(st.nextToken());
            rock[1] = Integer.parseInt(st.nextToken());

            bw.write(bfs(home[0], home[1])? "happy\n" : "sad\n");
        }

        bw.flush();
        bw.close();
    }
}
