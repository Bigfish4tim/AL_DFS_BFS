import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_1600 {
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visit;
    static int count = Integer.MAX_VALUE;
    static int[] dx = new int[] { -1, 1, 0, 0 };
    static int[] dy = new int[] { 0, 0, -1, 1 };
    static int[] hdx = new int[] { -2, -1, 2, 1, -2, -1, 2, 1 };
    static int[] hdy = new int[] { -1, -2, -1, -2, 1, 2, 1, 2 };

    public static class Node {
        int x;
        int y;
        int k;
        int counter;

        public Node(int x, int y, int k, int counter) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.counter = counter;
        }
    }

    public static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(0,0,K,0));
        visit[0][0][K] = true;

        while (!q.isEmpty()) {
            Node temp = q.poll();
            if(temp.x == H-1 && temp.y == W-1) {
                return temp.counter;
            }

            for(int i=0; i<4; i++) {
                int nextX = temp.x + dx[i];
                int nextY = temp.y + dy[i];

                if(nextX >= 0 && nextY >= 0 && nextX < H && nextY < W && !visit[nextX][nextY][temp.k] && map[nextX][nextY] == 0) {
                    visit[nextX][nextY][temp.k] = true;
                    q.offer(new Node(nextX, nextY, temp.k, temp.counter+1));
                }
            }
            if(temp.k > 0) {
                for(int i=0; i<8; i++) {
                    int nextX = temp.x + hdx[i];
                    int nextY = temp.y + hdy[i];

                    if(nextX >= 0 && nextY >= 0 && nextX < H && nextY < W && !visit[nextX][nextY][temp.k] && map[nextX][nextY] == 0) {
                        visit[nextX][nextY][temp.k-1] = true;
                        q.offer(new Node(nextX, nextY, temp.k-1, temp.counter+1));
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visit = new boolean[H][W][K+1];

        for(int i=0; i<H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = bfs(0,0);

        if(count == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(count);
    }
}
