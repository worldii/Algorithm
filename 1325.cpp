#include <iostream>
#include <vector>
#include <cstdio>
#include <memory.h>
#include <algorithm>
#include <queue>

using namespace std;

vector<int> graph[10001];
int checked[10001];
int cnt[10001];

void dfs(int start, int parent)
{

    for (int i = 0; i < graph[start].size(); i++)
    {
        if (!checked[graph[start][i]])
        {
            cnt[parent]++;
            checked[graph[start][i]] = 1;
            dfs(graph[start][i], parent);
        }
    }
}

int main(void)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int m, n;
    cin >> n;
    cin >> m;

    for (int i = 0; i < m; i++)
    {
        int a, b;
        cin >> a >> b;
        graph[b].push_back(a);
    }

    int max1 = -1;
    for (int i = 1; i <= n; i++)
    {
        memset(checked, 0, sizeof(checked));
        checked[i] = 1;
        dfs(i, i);

        max1 = max(max1, cnt[i]);
    }

    for (int i = 1; i <= n; i++)
    {
        if (max1 == cnt[i])
        {
            cout << i << " ";
        }
    }
    return 0;
}