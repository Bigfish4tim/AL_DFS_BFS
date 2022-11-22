import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_20304 {
    static int N;
    static int M;
    static boolean[] visit;
    static int[] password;

    public static ArrayList<Integer> binary(int x) {
        ArrayList<Integer> s = new ArrayList<>();
        while (x != 0) {
            s.add(x % 2);
            x = x/2;
        }
        return s;
    }

    public static void main(String[] args) throws IOException {

        ArrayList<ArrayList<Integer>> slist = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        password = new int[M];

        visit = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            password[i] = Integer.parseInt(st.nextToken());
            visit[password[i]] = true;
            slist.add(binary(password[i]));
        }
    }
}
