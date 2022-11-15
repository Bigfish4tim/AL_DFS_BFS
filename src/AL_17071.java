import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_17071 {
    static int n;
    static int k;
    static int[] visit = new int[500001];

    public static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();

        visit[start] = 1;

        int Kcount = 0;
        int Kcoe = 1;

        if(start==end) {
            System.out.println(0);
            return;
        }

        q.offer(start);

        while (!q.isEmpty()) {
            int temp = q.poll();
            end = end + Kcoe;
            Kcoe++;

            for(int i=0; i<3; i++) {
                int next;

                if(i==0) {
                    next = temp+1;
                } else if(i==1) {
                    next = temp-1;
                } else {
                    next = temp*2;
                }

                if(next==end) {
                    System.out.println(visit[temp]);
                    return;
                }

                if(next >= 0 && next < visit.length && visit[next] == 0) {
                    q.offer(next);
                    visit[next] = visit[temp] + 1;
                }
            }
        }
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
