import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_17071 {
    static int n;
    static int k;
    static boolean[][] visited = new boolean[500001][2];


    public static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();

        int count = 0;
        visited[start][0] = true;

        if(start==end) {
            System.out.println(0);
            return;
        }
        q.offer(start);

        while (!q.isEmpty()) {
            if(end > 500000) {
                System.out.println(-1);
                return;
            }

            int odds = count % 2;

            if(visited[end][odds]) {
                System.out.println(count);
                return;
            }

            int size = q.size();

            for(int i=0; i<size; i++) {
                int temp = q.poll();
                int nextodds = (count + 1) % 2;

                int next = temp +1;
                if(next < 500001 && !visited[next][nextodds]) {
                    visited[next][nextodds] = true;
                    q.offer(next);
                }

                next = temp - 1;
                if(next > 0 && !visited[next][nextodds]) {
                    visited[next][nextodds] = true;
                    q.offer(next);
                }

                next = temp * 2;
                if(next < 500001 && !visited[next][nextodds]) {
                    visited[next][nextodds] = true;
                    q.offer(next);
                }
            }
            count++;
            end = end + count;
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs(n, k);
    }
}
