import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_5014 {
    static int F;
    static int S;
    static int G;
    static int U;
    static int D;
    static int[] visit;

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();

        q.offer(S);
        visit[S] = 1;

        if(S==G) {
            System.out.println(0);
            return;
        }

        while (!q.isEmpty()) {
            int temp = q.poll();

            for(int i=0; i<2; i++) {
                int next;
                if(i==0) {
                    next = temp + U;
                } else {
                    next = temp - D;
                }
                if(next == G) {
                    System.out.println(visit[temp]);
                    return;
                }

                if(next <= F && next > 0  && visit[next] == 0) {
                    q.offer(next);
                    visit[next] = visit[temp] + 1;
                }
            }
        }

        System.out.println("use the stairs");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        visit = new int[F+1];

        visit[0] = 1;

        bfs();
    }
}
