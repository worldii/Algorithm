#include <iostream>
#include <vector>
using namespace std;
int edges[101][101];
long long d[101][101][101];
#define INF 987654321
int main(void)
{
    int n, m;
    cin >> n >> m;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            edges[i][j] = INF;
            if (i == j)
                edges[i][j] = 0;
        }
    }
    for (int i = 0; i < m; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        if (edges[a][b] != 0)
            edges[a][b] = min(c, edges[a][b]);
    }
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            d[0][i][j] = edges[i][j];
        }
    }

    for (int k = 1; k <= n; k++)
    {
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (i == j)
                    continue;

                d[k][i][j] = min(d[k - 1][i][j], d[k - 1][i][k] + d[k - 1][k][j]);
            }
        }
    }
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if (d[n][i][j] == INF)
                d[n][i][j] = 0;
            cout << d[n][i][j] << " ";
        }
        cout << "\n";
    }
    return 0;
}