import java.io.*;
import java.util.*;

public class AL_9328 {
    static int T;
    static int h, w;
    static char[][] map;
    static boolean[][] visit;
    static ArrayList<Character> keys;

    static ArrayList<Door> doors;

    static int count;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static class Door {
        int x;
        int y;
        char doorname;

        public Door(int x, int y, char doorname) {
            this.x = x;
            this.y = y;
            this.doorname = doorname;
        }
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {x,y});
        visit[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for(int i=0; i<4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if(isRange(nextX, nextY))
                    continue;
                if(visit[nextX][nextY])
                    continue;
                if(map[nextX][nextY] == '*')
                    continue;

                if(Character.isUpperCase(map[nextX][nextY])) {
                    if(keys.contains(map[nextX][nextY])) {
                        q.add(new int[] {nextX, nextY});
                        map[nextX][nextY] = '.';
                        visit[nextX][nextY] = true;
                    } else {
                        doors.add(new Door(nextX, nextY, map[nextX][nextY]));
                    }
                } else if(Character.isLowerCase(map[nextX][nextY])) {
                    q.add(new int[] {nextX, nextY});
                    keys.add(map[nextX][nextY]);
                    for(int j=0, size=doors.size(); j<size; j++) {
                        if(doors.get(j).doorname == Character.toUpperCase(map[nextX][nextY])) {
                            q.add(new int[] {doors.get(j).x, doors.get(j).y});
                            visit[doors.get(j).x][doors.get(j).y] = true;
                            doors.remove(j);
                        }
                    }
                    map[nextX][nextY] = '.';
                    visit[nextX][nextY] = true;
                } else if(map[nextX][nextY] == '$') {
                    count++;
                    q.add(new int[] {nextX, nextY});
                    map[nextX][nextY] = '.';
                    visit[nextX][nextY] = true;
                } else {
                    q.add(new int[] {nextX, nextY});
                    visit[nextX][nextY] = true;
                }
            }
        }
    }

    public static boolean isRange(int x, int y) {
        return x < 0 || y < 0 || x >= h || y >= w;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));



        T = Integer.parseInt(br.readLine());

        char[] line;

        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            visit = new boolean[h][w];

            keys = new ArrayList<>();
            doors = new ArrayList<>();

            count = 0;

            for(int j=0; j<h; j++) {
                line = br.readLine().toCharArray();
                if (w >= 0) System.arraycopy(line, 0, map[j], 0, w);
            }
            line = br.readLine().toCharArray();
            for (char c : line) {
                keys.add(Character.toUpperCase(c));
            }
        }




    }
}
