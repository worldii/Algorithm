#include <iostream>
#include <vector>
#include <memory.h>
#include <cstdio>
#include <algorithm>
#include <queue>

using namespace std;

vector<int> graph[100001];
int checked[100001];
int dist[100001];

void dfs(int start, int parent)
{
    if (start == 1)
    {
        cout << parent << "\n";
        return;
    }
    checked[start] = 1;
    for (int i = 0; i < graph[start].size(); i++)
    {
        int next = graph[start][i];
        if (!checked[next])
        {
            // dist[next]= dist
        }
    }
}

void bfs(int start)
{
    queue<int> q;
    q.push(start);
    checked[start] = 1;
    while (!q.empty())
    {
        int temp = q.front();
        q.pop();
        for (int i = 0; i < graph[temp].size(); i++)
        {
            int next = graph[temp][i];
            if (!checked[next])
            {

                checked[next] = 1;
                q.push(next);
                dist[next] = dist[temp] + 1;
            }
        }
    }
}
void findparent(int start)
{

    memset(checked, 0, sizeof(checked));
    bfs(start);
}

int main(void)
{
    int m, n;
    cin >> n;

    for (int i = 0; i < n - 1; i++)
    {
        int a, b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    bfs(1);
    for (int i = 2; i <= n; i++)
    {
        int minidx = -1;
        for (int j = 0; j < graph[i].size(); j++)
        {
            if (minidx == -1 || dist[graph[i][j]] < dist[minidx])
            {
                minidx = graph[i][j];
            }
        }
        cout << minidx << "\n";
    }
    return 0;
}