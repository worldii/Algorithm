#include <iostream>
#include <algorithm>
using namespace std;
int d[101][101];
int a[101][101];
#define INF 987654321
int main(void)
{
    int n, m;
    cin >> n >> m;
    for (int i = 0; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if (i == j)
            {
                a[i][j] = 0;
                d[i][j] = 0;
            }
            else
            {
                a[i][j] = INF;
                d[i][j] = INF;
            }
        }
    }
    for (int i = 0; i < m; i++)
    {
        int src, dest;
        cin >> src >> dest;
        d[src][dest] = 1;
        d[dest][src] = 1;
    }

    for (int k = 1; k <= n; k++)
    {
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                d[i][j] = min(d[i][j], d[i][k] + d[k][j]);
            }
        }
    }
    int final = -1;
    int finalidx = -1;
    for (int i = 1; i <= n; i++)
    {
        int sum = 0;
        for (int j = 1; j <= n; j++)
        {
            sum += d[i][j];
        }
        if (final == -1 || sum < final)
        {
            final = sum;
            finalidx = i;
        }
    }
    cout << finalidx;
}