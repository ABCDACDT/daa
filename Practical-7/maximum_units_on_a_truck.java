import java.util.*;

public class maximum_units_on_a_truck {
public static void main(String[] args)
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int t;
    scanner.nextInt() t;
    while (t--)
    {
        
    }
    return 0;
}
class Solution {
public:
    int maximumUnits(vector<ArrayList<Integer>>& boxTypes, int truckSize) {
        int answer = 0;
        int length = boxTypes.size();
        sort(boxTypes.begin(), boxTypes.end(),
             [](const auto& a, const auto& b) { return a[1] > b[1]; });
        for (int i = 0; i < length; i++) {
            if ((truckSize - boxTypes[i][0]) >= 0) {
                answer += (boxTypes[i][0] * boxTypes[i][1]);
                truckSize -= boxTypes[i][0];
            } else {
                answer += (truckSize * boxTypes[i][1]);
                break;
            }
        }
        return answer;
    }
};
}