#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int idx[6][3] = {{9, 3, 1}, {9, 1, 3}, {3, 1, 9}, {3, 9, 1}, {1, 3, 9}, {1, 9, 3}};
int dp[61][61][61];
int scv[3];
int count = 0;
int idx2[2][2] = {{9, 3}, {3, 9}};
int go(int scv1, int scv2, int scv3)
{
    if (scv1 < 0)
        return go(0, scv2, scv3);
    if (scv2 < 0)
        return go(scv1, 0, scv3);
    if (scv3 < 0)
        return go(scv1, scv2, 0);
    if (scv1 == 0 && scv2 == 0 && scv3 == 0)
        return 0;
    if (dp[scv1][scv2][scv3] != -1)
        return dp[scv1][scv2][scv3];
    int ans = 100001;
    for (int i = 0; i < 6; i++)
    {
        if (ans > go(scv1 - idx[i][0], scv2 - idx[i][1], scv3 - idx[i][2]))
        {
            ans = go(scv1 - idx[i][0], scv2 - idx[i][1], scv3 - idx[i][2]);
        }
    }
    ans += 1;
    dp[scv1][scv2][scv3] = ans;
    return (dp[scv1][scv2][scv3]);
}
int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i <= 60; i++)
    {
        for (int j = 0; j <= 60; j++)
        {
            for (int k = 0; k <= 60; k++)
            {
                dp[i][j][k] = -1;
            }
        }
    }
    for (int i = 0; i < n; i++)
    {
        cin >> scv[i];
    }
    cout << go(scv[0], scv[1], scv[2]) << "\n";

    return 0;
}