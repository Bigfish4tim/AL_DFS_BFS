import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_3197 {
    static int R, C;
    static char[][] map;
    static int[] dx = new int[] { -1, 1, 0, 0 };
    static int[] dy = new int[] { 0, 0, -1, 1 };

    public static void bfs() {

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

//        for(int i=0; i<R; i++) {
//            st = new StringTokenizer(br.readLine());
//            for(int j=0; j<C; j++) {
//                map[i][j] = st.nextToken();
//            }
//        }

        for(int i = 0 ; i < R ; ++i) {
            char[] line = br.readLine().toCharArray();
            for(int j = 0 ; j < C ; ++j) {
                map[i][j] = line[j];
//                if(map[i][j] == 'L') {
//                    swan[swanIdx++] = new Node(r, c);
//                }
//                if(map[r][c] != 'X') {
//                    waterQ.offer(new Node(r, c));
//                }
            }
        }

    }
}
