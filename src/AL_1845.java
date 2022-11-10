import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class AL_1845 {
    static int N;
    static int[] map;
    static int[] target;

    static Queue<int[]> querys = new LinkedList<>();

    public static class query {
        int size;
        int[] range;
    }

    public static void mainFunc(int[] tempMap) {
        for(int i=0; i<N; i++) {
            int from = tempMap[i];
            if(Arrays.stream(target).anyMatch(x -> x == from)) {   /// from * to > 0인 경우
//                int toIndex = IntStream.range(0, target.length).filter(x -> from == target[x]).findFirst().orElse(-1);




                return;


                ////
            } else {  /// from * to < 0 인 경우
                int toIndex = IntStream.range(0, target.length).filter(x -> -from == target[x]).findFirst().orElse(-1); /// to index 탐색

                reverse(i, toIndex);
                querys.offer(new int[] {i, toIndex});
            }
                /////
        }
    }

    public static void minusFunc(int x, int y) {

    }

    public static void dfs() {

    }

    public static void reverse(int a, int b) {
        int range = (b-a+1);

        for(int i=a-1; i<range/2; i++) {
            int temp;
            temp = map[i];
            map[i] = -map[b-i-1];
            map[b-i-1] = -temp;
        }
    }

    public static boolean isc(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N];

        for(int i=0; i<N; i++) {
            map[i] = i+1;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }
    }
}
