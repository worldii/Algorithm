#include <iostream>
#include <algorithm>
using namespace std;
int a[2002][2002];
#define INF 987654321
int main(void)
{
    int n, m;
    cin >> n >> m;
    int ddest, ssrc;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if (i == j)
                a[i][j] = 0;
            else
                a[i][j] = INF;
        }
    }
    for (int i = 0; i < m; i++)
    {
        int src, dest, cost;
        cin >> src >> dest >> cost;
        a[src][dest] = min(a[src][dest], cost);
    }
    cin >> ssrc >> ddest;
    int count = n - 2;
    int check[1001] = {
        0,
    };

    while (1)
    {
        int min = INF;
        int minidx = -1;
        for (int i = 1; i <= n; i++)
        {
            if (a[ssrc][i] == INF)
                continue;
            if (check[i] == 0 && min > a[ssrc][i])
            {
                minidx = i;
                min = a[ssrc][i];
            }
        }
        //  cout << minidx << "min" << '\n';
        if (minidx == -1)
            break;
        check[minidx] = 1;
        for (int i = 1; i <= n; i++)
        {
            if (a[minidx][i] == INF)
                continue;
            if (a[ssrc][minidx] + a[minidx][i] < a[ssrc][i])
            {
                a[ssrc][i] = a[ssrc][minidx] + a[minidx][i];
            }
        }
    }
    cout << a[ssrc][ddest];
    /* for (int i = 1 ; i<= n ; i++) {
        cout<< a[ssrc][i]<<' ';

    }*/
}