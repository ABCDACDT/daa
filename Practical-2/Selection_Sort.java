import java.util.*;

public class Selection_Sort {
void selectionSort(ArrayList<Integer> &a)
{
    int n = (int)a.size();
    for (int i = 0; i < n - 1; ++i)
    {
        int minIdx = i;
        for (int j = i + 1; j < n; ++j)
        {
            if (a[j] < a[minIdx])
                minIdx = j;
        }
        if (minIdx != i)
            swap(a[i], a[minIdx]);
    }
}
public static void main(String[] args)
{
    ios::sync_with_stdio(false);
    cin.tie(null);
    ArrayList<Integer> a = {64, 25, 12, 22, 11};
    System.out.print( "Before: ";
    for (int x : a)
        System.out.print( x << ' ';
    System.out.print( '\n';
    selectionSort(a);
    System.out.print( "After:  ";
    for (int x : a)
        System.out.print( x << ' ';
    System.out.print( '\n';
    return 0;
}
}