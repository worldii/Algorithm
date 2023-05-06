#include <iostream>
#include <vector>
#include <queue>
#include <stack>
using namespace std;
int edges[101][101];
long long d[101][101][101];
int nnext[101][101];

#define INF 987654321
void path(int x, int y)
{
    if (nnext[x][y] == -1)
    {
        printf("0\n");
        return;
    }
    queue<int> q;
    q.push(x);
    while (x != y)
    {
        x = nnext[x][y];
        q.push(x);
    }
    printf("%d ", q.size());
    while (!q.empty())
    {
        printf("%d ", q.front());
        q.pop();
    }
    printf("\n");
}
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
            {
                edges[i][j] = 0;
            }
            nnext[i][j] = -1;
        }
    }
    for (int i = 0; i < m; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        if (edges[a][b] != 0)
        {

            if (edges[a][b] > c)
            {
                edges[a][b] = c;
                nnext[a][b] = b;
            }
        }
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
                d[k][i][j] = d[k - 1][i][j];
                if (d[k][i][j] > d[k - 1][i][k] + d[k - 1][k][j])
                {
                    d[k][i][j] = d[k - 1][i][k] + d[k - 1][k][j];
                    nnext[i][j] = nnext[i][k];
                }
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

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            path(i, j);
        }
    }
    return 0;
}