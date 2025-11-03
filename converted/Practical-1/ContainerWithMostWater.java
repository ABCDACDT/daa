import java.util.*;

public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, currentArea);
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = 1;
        if (sc.hasNextInt())
            t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] h = new int[n];
            for (int i = 0; i < n; i++)
                h[i] = sc.nextInt();
            System.out.println(maxArea(h));
        }
        sc.close();
    }
}
