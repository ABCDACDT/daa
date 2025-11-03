import java.util.*;

public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        Set<Integer> s = new HashSet<>();
        for (int n : nums)
            s.add(n);
        int best = 0;
        for (int n : s) {
            if (!s.contains(n - 1)) {
                int cur = n;
                int len = 1;
                while (s.contains(cur + 1)) {
                    cur++;
                    len++;
                }
                best = Math.max(best, len);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = 1;
        if (sc.hasNextInt())
            t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            System.out.println(longestConsecutive(arr));
        }
        sc.close();
    }
}
