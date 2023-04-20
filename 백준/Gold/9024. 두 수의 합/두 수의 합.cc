#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

vector<int> s;

int main(void)
{
    ios::sync_with_stdio(false);

    cin.tie(NULL);
    cout.tie(NULL);
    int t;
    cin >> t;
    for (int c = 1; c <= t; c++)
    {
        int n, k;
        s.clear();
        cin >> n >> k;
        for (int i = 0; i < n; i++)
        {
            int a;
            cin >> a;
            s.push_back(a);
        }
        sort(s.begin(), s.end());

        int diff = 1000000000;
        int cnt = 0;
        int start = 0;
        int end = n - 1;

        while (start < end)
        {
            int sum = s[start] + s[end];
            if (sum <= k)
            {
                if (abs(sum - k) < diff)
                {
                    diff = abs(sum - k);
                    cnt = 1;
                }
                else if (abs(sum - k) == diff)
                {
                    cnt++;
                }
                start++;
            }
            else if (sum > k)
            {
                if (abs(sum - k) < diff)
                {
                    diff = abs(sum - k);
                    cnt = 1;
                }
                else if (abs(sum - k) == diff)
                {
                    cnt++;
                }
                end--;
            }
        }
        cout << cnt << "\n";
    }
    return 0;
}