#include <iostream>
#include <vector>
using namespace std;
vector<int> value;
int dp[10001];
int main(void)
{
    int n, k;
    cin >> n >> k;
    for (int i = 0; i < n; i++)
    {
        int m;
        cin >> m;
        value.push_back(m);
    }
    dp[0] = 0;
    for (int i = 1; i < 10001; i++)
    {
        dp[i] = 987654321;
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 1; j <= k; j++)
        {
            if (j - value[i] >= 0)
            {
                if (dp[j] == 987654321 || dp[j - value[i]] + 1 < dp[j])
                    dp[j] = dp[j - value[i]] + 1;
            }
        }
    }
    if (dp[k] >= 987654321)
        cout << -1;
    else
        cout << dp[k];
    return 0;
}