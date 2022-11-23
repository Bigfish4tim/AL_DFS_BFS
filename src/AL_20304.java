import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AL_20304 {
    static int N;
    static int M;
    static boolean[] visit;
    static int[] password;

    public static int bisearch(int x) {
        int b = 2;
        int count = 1;

        while (x >= b) {
            b = b*2;
            count++;
        }
        return count;
    }

    public static ArrayList<Integer> binary(int x) {
        ArrayList<Integer> s = new ArrayList<>();
        while (x != 0) {
            s.add(x % 2);
            x = x/2;
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        password = new int[M];
        visit = new boolean[N];

        int count = bisearch(N);
        int max=0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            password[i] = Integer.parseInt(st.nextToken());
            if(password[i] > max)
                max = password[i];
        }

        int x = 0;
        for(int i=0; i<M; i++) {
            x = x | password[i];
        }

        int reverse = (int) Math.pow(2, count) -1;

        int ans = reverse-x;

        while (ans > N) {
            count--;
            ans = ans - (int) Math.pow(2, count);
        }

        ArrayList<Integer> ansList = binary(ans);
        ArrayList<Integer> maxList = binary(max);

        int maxsize = Math.max(ansList.size(), maxList.size());

        if(maxsize > ansList.size()) {
            for(int i=0, size=maxsize-ansList.size(); i<size; i++) {
                ansList.add(0);
            }
        }

        ArrayList<ArrayList<Integer>> asList = new ArrayList<>();

        for(int i=0; i<M; i++) {
            ArrayList<Integer> temp = binary(password[i]);
            int size = maxsize - temp.size();
            for(int j=0; j<size; j++) {
                temp.add(0);
            }
            asList.add(temp);
        }

        int ansbinary = Integer.MAX_VALUE;

        for(int i=0; i<M; i++) {
            ArrayList<Integer> temp = asList.get(i);
            int trueCount = 0;
            for(int j=0; j<temp.size(); j++) {
                if(!Objects.equals(temp.get(j), ansList.get(j)))
                    trueCount++;
            }
            if(trueCount < ansbinary)
                ansbinary = trueCount;
        }

        System.out.println(ansbinary);
    }
}
