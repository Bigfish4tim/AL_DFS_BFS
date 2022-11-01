import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_1697 {
    static int n;
    static int k;
    static int sum=Integer.MAX_VALUE;
    static int[] visit = new int[100001];

    public static class Operator {
        int p;
        int m;
        int d;

        public Operator(int p, int m, int d) {
            this.p = p;
            this.m = m;
            this.d = d;
        }
    }

    public static void dfs(int start, int end, int total) {
        int temp = total;

        if(start==end) {
            if(total < sum) {
                sum = total;
            }
            return;
        }

        if(start > end) {
            int gap = start-end;
            temp = temp + gap;
            if(temp < sum) {
                sum = temp;
            }
            return;
        }
        dfs(start*2, end, ++temp);
        dfs(start+1, end, ++temp);
        if(start-1 > 0) {
            dfs(start-1, end, ++temp);
        }
    }

    public static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();

        visit[start] = 1;

        if(start==end) {
            System.out.println(0);
            return;
        }

        q.offer(start);

        while (!q.isEmpty()) {
            int temp = q.poll();

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
