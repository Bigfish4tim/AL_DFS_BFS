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


        if(Character.isUpperCase(map[x][y]) && !visit[x][y]) {
            doors.add(new Door(x, y, map[x][y]));
        } else if(Character.isLowerCase(map[x][y]) && !visit[x][y]) {
            keys.add(Character.toUpperCase(map[x][y]));
        } else if(map[x][y] == '$' && !visit[x][y]) {
            count++;
        }


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
                    keys.add(Character.toUpperCase(map[nextX][nextY]));

                    int doorIndex = 0;

                    do {
                        if(doors.size() == 0)
                            break;
                        if (doors.get(doorIndex).doorname == Character.toUpperCase(map[nextX][nextY])) {
                            q.add(new int[]{doors.get(doorIndex).x, doors.get(doorIndex).y});
                            map[doors.get(doorIndex).x][doors.get(doorIndex).y] = '.';
                            visit[doors.get(doorIndex).x][doors.get(doorIndex).y] = true;
                            doors.remove(doorIndex);
                        } else {
                            doorIndex++;
                        }
                    } while (doorIndex < doors.size());

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

            map = new char[h][w];
            visit = new boolean[h][w];

            keys = new ArrayList<>();
            doors = new ArrayList<>();

            Queue<int[]> startq = new LinkedList<>();

            count = 0;

            for(int j=0; j<h; j++) {
                line = br.readLine().toCharArray();
                if (w >= 0) System.arraycopy(line, 0, map[j], 0, w);
            }
            line = br.readLine().toCharArray();

            if(line[0] != '0') {
                for (char c : line) {
                    keys.add(Character.toUpperCase(c));
                }
            }

            for(int n=0; n<h; n++) {
                if(map[n][0] != '*') {
                    startq.add(new int[] {n, 0});
                }
                if(map[n][w-1] != '*') {
                    startq.add(new int[] {n, w-1});
                }
            }
            for(int n=0; n<w; n++) {
                if(map[0][n] != '*') {
                    startq.add(new int[] {0, n});
                }
                if(map[h-1][n] != '*') {
                    startq.add(new int[] {h-1, n});
                }
            }
            while (!startq.isEmpty()) {
                int[] temp = startq.poll();
                bfs(temp[0], temp[1]);
            }

            bw.write(count + "\n");
        }

        bw.flush();
        bw.close();
    }
}
