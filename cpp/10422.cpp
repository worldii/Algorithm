#include <iostream>
using namespace std;
int t;
long long dp[5001];
#define rem 1000000007
int main(void)
{
    dp[0] = 0;
    dp[1] = 0;
    dp[2] = 1;
    for (int s = 3; s <= 5000; s++)
    {
        dp[s] = 0;
        for (int i = 2; i <= s; i++)
        {
            if (i - 2 != 0 && s - i != 0)
                dp[s] += dp[i - 2] * dp[s - i];
            else if (i - 2 == 0)
                dp[s] += dp[s - i];
            else if (s - i == 0)
                dp[s] += dp[i - 2];
            dp[s] %= rem;
        }
        dp[s] %= rem;
    }

    cin >> t;
    while (t--)
    {
        int m;
        cin >> m;
        cout << dp[m] % rem << "\n";
    }
    return 0;
}