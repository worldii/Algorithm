#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, k;
int count = 0;
vector<int> value;

int dp[10001];
int main(void)
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> n >> k;

    for (int i = 0; i < n; i++)
    {
        int m;
        cin >> m;
        value.push_back(m);
    }

    sort(value.begin(), value.end());
    dp[0] = 1;

    for (int j = 0; j < n; j++)
    {
        for (int i = 1; i <= k; i++)
        {
            if (i - value[j] >= 0)
                dp[i] += dp[i - value[j]];
        }
    }

    cout << dp[k];
}