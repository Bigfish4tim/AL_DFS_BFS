import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_2667 {

    static int[][] map;
    static int n;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 }; //x방향배열-상하
    static int[] dy = { 0, 0, -1, 1 }; //y방향배열-좌우
    static int total = 0;

    public static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs(int x, int y) {
        visited[x][y] = true;

        int count = 1;

        Queue<Coordinate> q = new LinkedList<>();

        q.offer(new Coordinate(x, y));

        while (!q.isEmpty()) {
            Coordinate temp = q.poll();
            map[temp.x][temp.y] = 0;

            for(int i=0; i<4; i++) {
                Coordinate next = new Coordinate(temp.x + dx[i], temp.y + dy[i]);

                if(next.x < 0 || next.y < 0 || next.x >= map.length || next.y >= map.length)
                    continue;
                if(visited[next.x][next.y] || map[next.x][next.y] == 0)
                    continue;

                q.offer(next);
                visited[next.x][next.y] = true;
                count++;
            }
        }
        total++;

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i=0; i<n; i++) {
            String s = br.readLine();
            for(int j=0; j<n; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        for(int i=0; i< map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                if(map[i][j] == 0)
                    continue;

                pq.offer(bfs(i, j));
            }
        }

        System.out.println(total);
        int size = pq.size();
        for(int i=0; i<size; i++) {
            System.out.println(pq.poll());
        }
    }
}
