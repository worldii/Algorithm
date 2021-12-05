#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>
#define INF 987654321
using namespace std;
long long aa[6001];
typedef struct _edges
{
    int from;
    int to;
    int cost;
} Edges;
Edges edges[6001];

int main(void)
{
    int n, m;
    cin >> n >> m;
    for (int i = 1; i <= n; i++)
    {
        aa[i] = INF;
    }
    for (int i = 0; i < m; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        edges[i].from = a;
        edges[i].to = b;
        edges[i].cost = c;
    }
    aa[1] = 0;
    for (int j = 0; j < n; j++)
    {

        for (int i = 0; i < m; i++)
        {
            if (aa[edges[i].from] != INF && aa[edges[i].to] > aa[edges[i].from] + edges[i].cost)
            {
                aa[edges[i].to] = aa[edges[i].from] + edges[i].cost;
                if (j == n - 1)
                {
                    cout << -1;
                    return 0;
                }
            }
        }
    }

    for (int i = 2; i <= n; i++)
    {
        if (aa[i] >= INF)
        {
            aa[i] = -1;
        }
        cout << aa[i] << "\n";
    }

    return (0);
}