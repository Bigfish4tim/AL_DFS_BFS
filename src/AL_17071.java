import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_17071 {
    static int n;
    static int k;
    static int[][] visit = new int[500001][2];
    static boolean[][] visited = new boolean[500001][2];

    public static class Operator {
        int x;
        int count;

        public Operator(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }

    public static int arithmetic(int n) {
        return n*(n+1)/2;
    }

    public static void bfs(int start, int end) {
        Queue<Operator> oq = new LinkedList<>();

        visited[start][1] = true;

        if(start==end) {
            System.out.println(0);
            return;
        }
        oq.offer(new Operator(start, 1));

        while (!oq.isEmpty()) {
            Operator tempq = oq.poll();

            int temp = tempq.x;

            int odds = tempq.count % 2;

            for(int i=0; i<3; i++) {
                int next;

                if(i==0) {
                    next = temp+1;
                } else if(i==1) {
                    next = temp-1;
                } else {
                    next = temp*2;
                }

                if(end+arithmetic(tempq.count) > 500000) {
                    System.out.println(-1);
                    return;
                }

                if(visited[end+arithmetic(tempq.count)][odds]) {
                    System.out.println(tempq.count);
                    return;
                }

                if(next >= 0 && next < visit.length && !visited[next][odds]) {
                    visited[next][odds] = true;
                    oq.offer(new Operator(next, tempq.count+1));
                }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

//        dfs(n, k, 0);

        bfs(n, k);
    }
}
