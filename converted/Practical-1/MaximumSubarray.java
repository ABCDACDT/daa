import java.util.*;

public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int maximum = nums[0];
        for (int v : nums) {
            sum += v;
            maximum = Math.max(maximum, sum);
            if (sum < 0)
                sum = 0;
        }
        return maximum;
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
            System.out.println(maxSubArray(arr));
        }
        sc.close();
    }
}
