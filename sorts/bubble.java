import java.util.*;

public class bubble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        if (n <= 0) return;

        int[] a = new int[n];
        System.out.print("Enter elements separated by space: ");
        for (int i = 0; i < n; ++i) a[i] = scanner.nextInt();

        for (int i = 0; i < n - 1; ++i) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; ++j) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        System.out.print("Sorted array: ");
        for (int x : a) System.out.print(x + " ");
        System.out.println();
        scanner.close();
    }
}
