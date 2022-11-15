import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class AL_1845 {
    static int N;
    static int[] map;
    static int[] target;
    static int total = 501;
    static Queue<int[]> querys = new LinkedList<>();

    public static void dfs(int[] tempMap, int start, Queue<int[]> queue) {
        if(isc(tempMap, target)) {
            if(total > queue.size()) {
                total = queue.size();
                querys = new LinkedList<>(queue);
            }

            return;
        }

        int from = tempMap[start];
        int to = target[start];

        if(from == target[start]) {
            dfs(tempMap, start+1, queue);
        } else if(Arrays.stream(tempMap).anyMatch(x -> x == to)) {
            int toIndex = IntStream.range(0, tempMap.length).filter(x -> to == tempMap[x]).findFirst().orElse(-1);

            for(int i=start+1; i<N; i++) {
                if(start==toIndex)
                    continue;
                Queue<int[]> tempQ = new LinkedList<>(queue);
                int[] temp = tempMap.clone();

                reverse(temp, Integer.min(i, toIndex), Integer.max(i, toIndex));
                tempQ.offer(new int[] {Integer.min(i, toIndex)+1, Integer.max(i, toIndex)+1});
                reverse(temp, start, i);
                tempQ.offer(new int[] {start+1, i+1});
                if(tempQ.size() < total)
                    dfs(temp, start+1, tempQ);
            }
        } else { /// from * to < 0 인 경우
            int toIndex = IntStream.range(0, tempMap.length).filter(x -> -to == tempMap[x]).findFirst().orElse(-1); /// to index 탐색

            Queue<int[]> tempQ = new LinkedList<>(queue);
//            querys.offer(new int[] {start, toIndex});
            tempQ.offer(new int[] {start+1, toIndex+1});
            int[] temp = tempMap.clone();
            temp = reverse(temp, start, toIndex).clone();
            if(tempQ.size() < total)
                dfs(temp, start+1, tempQ);
        }
    }

    public static int[] reverse(int[] pmap, int a, int b) {
        int range = (b-a+1);
        int size = (int) Math.ceil((double) range / 2);

        for(int i=0; i<size; i++) {
            int temp;
            temp = pmap[a+i];
            pmap[a+i] = -pmap[b-i];
            pmap[b-i] = -temp;
        }

        return pmap;
    }

    public static boolean isc(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N];
        target = new int[N];

        for(int i=0; i<N; i++) {
            map[i] = i+1;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        Queue<int[]> queue = new LinkedList<>();

        dfs(map, 0,queue);

        System.out.println(querys.size());
        int size = querys.size();
        for(int i=0; i<size; i++) {
            int[] temp = querys.poll();
            assert temp != null;
            System.out.println(temp[0] + " " + temp[1]);
        }
    }
}
