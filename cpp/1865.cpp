#include <iostream>
using namespace std;
typedef struct _edges
{
    int from;
    int to;
    int cost;
} Edges;
#define INF 987654321
long long aa[1001];
//Edges edges[10000];

int main(void)
{
    int count;
    cin >> count;

    for (int i = 0; i < count; i++)
    {
        int n, m, w;
        int idx = 0;
        Edges edges[10000];
        cin >> n >> m >> w;
        for (int i = 1; i <= n; i++)
        {
            aa[i] = 0;
        }
        aa[1] = 0;
        for (int j = 0; j < m; j++)
        {
            int s, e, t;
            cin >> s >> e >> t;
            edges[idx].from = s;
            edges[idx].to = e;
            edges[idx].cost = t;
            idx++;
            edges[idx].from = e;
            edges[idx].to = s;
            edges[idx].cost = t;
            idx++;
        }
        for (int j = 0; j < w; j++)
        {
            int s, e, t;
            cin >> s >> e >> t;
            edges[idx].from = s;
            edges[idx].to = e;
            edges[idx].cost = -t;
            // cout << -t;
            idx++;
        }
        int flag = 0;
        for (int j = 0; j < n; j++)
        {
            for (int k = 0; k < idx; k++)
            {
                if (aa[edges[k].from] != INF && aa[edges[k].to] > aa[edges[k].from] + edges[k].cost)
                {
                    aa[edges[k].to] = aa[edges[k].from] + edges[k].cost;
                    if (j == n - 1)
                        flag = 1;
                }
            }
        }
        if (flag == 1)
            cout << "YES";
        else
            cout << "NO";
        cout << "\n";
    }
    return 0;
}