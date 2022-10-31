import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2644 {
    static int count;
    static int[][] map;
    static int mcount;
    static boolean[] visit;
    static int sum=-1;

    public static void bfs(int start, int end) {
        visit[start] = true;

        Queue<Integer> q = new LinkedList<>();

        q.offer(start);

        while (!q.isEmpty()) {
            int temp = q.poll();

            for(int i=0; i< map.length; i++) {
                if(visit[i])
                    continue;
                if(map[temp][i] == 0)
                    continue;

                visit[i] = true;
                q.offer(i);
                sum++;
            }
        }
    }

    public static void dfs(int start, int end, int total, boolean trig) {
        visit[start] = true;
        int temp = 0;

        if(start == end) {
            sum = total;
            trig = true;
            return;
        }
        if(trig) {
            return;
        }

        for(int i=1; i<map.length; i++) {
            if(visit[i])
                continue;
            if(map[start][i] == 0)
                continue;

            temp = total+1;
            dfs(i, end, temp, trig);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        count = Integer.parseInt(br.readLine());
        map = new int[count+1][count+1];
        visit = new boolean[count+1];
        visit[0] = true;

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] input = new int[2];
        for(int i=0; i<2; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        mcount = Integer.parseInt(br.readLine());

        for(int i=0; i<mcount; i++) {
            StringTokenizer stt = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stt.nextToken());
            int y = Integer.parseInt(stt.nextToken());

            map[x][y] = 1;
            map[y][x] = 1;
        }

        dfs(input[0], input[1], 0, false);

        System.out.println(sum);
    }

}
