#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
// vector<int> cost;
int d[501][501];
int cost[501];
#define INF 987654321;
int main(void)
{
    int count;
    cin >> count;
    while (count--)
    {
        int n;
        cin >> n;
        for (int i = 1; i <= n; i++)
        {
            cin >> cost[i];
        }
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                d[i][j] = INF;
            }
        }
        for (int i = 1; i <= n; i++)
        {
            d[i][i] = 0;
        }
        for (int i = 1; i < n; i++)
        {
            d[i][i + 1] = cost[i] + cost[i + 1];
        }
        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                int k = j + i;
                if (k > n)
                    continue;
                int sum = 0;
                for (int t = j; t <= k; t++)
                {
                    sum += cost[t];
                }
                for (int t = j; t < k; t++)
                {

                    d[j][k] = min(d[j][k], d[j][t] + d[t + 1][k] + sum);
                }
            }
        }
        cout << d[1][n] << "\n";
    }
    return 0;
}