import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2606 {

    private static int[][] map;
    private static boolean[] visit;

    public static void bfs(int i) {
        Queue<Integer> q = new LinkedList<>();
        visit[i] = true;

        q.offer(i);

        while(!q.isEmpty()) {
            int temp = q.poll();
            for(int j=1; j<map.length; j++) {
                if(map[temp][j] == 1 && !visit[j]) {
                    q.offer(j);
                    visit[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];
        visit = new boolean[n+1];
        visit[0] = true;
        int x, y;

        for(int i=1; i<m+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
            map[y][x] = 1;
        }

        int sum = 0;

        bfs(1);

        for (boolean b : visit) {
            if (b) {
                sum++;
            }
        }

        System.out.println(sum-2);
    }
}
