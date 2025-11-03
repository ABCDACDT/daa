import java.util.*;

public class meeting_rooms_3 {
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
    int mostBooked(int n, vector<ArrayList<Integer>>& meetings) {
        sort(meetings.begin(), meetings.end());
        priority_queue<int, ArrayList<Integer>, greater<int>> free;
        for (int i = 0; i < n; ++i)
            free.push(i);
        using T = pair<long, int>; 
        priority_queue<T, vector<T>, greater<T>> busy;
        ArrayList<Integer> cnt(n, 0);
        for (auto& m : meetings) {
            long s = m[0], e = m[1];

            while (!busy.empty() && busy.top().first <= s) {
                free.push(busy.top().second);
                busy.pop();
            }
            int room;
            long newEnd;
            if (!free.empty()) {
                room = free.top();
                free.pop();
                newEnd = e;
            } else {
                auto [endTime, r] = busy.top();
                busy.pop();
                room = r;
                newEnd = endTime + (e - s);
            }
            busy.emplace(newEnd, room);
            ++cnt[room];
        }
        return int(max_element(cnt.begin(), cnt.end()) - cnt.begin());
    }
};
}