#include <iostream>
#include <queue>
#include <vector>

using namespace std;
int sum = 0;
#define CNT 20000
int parent[CNT + 2];
// int tree[10001];
vector<int> graph[CNT + 2];

int checked[CNT + 2];

void bfs(int start)
{
    queue<int> q;
    q.push(start);
    checked[start] = 1;
    sum++;
    while (!q.empty())
    {
        int temp = q.front();
        q.pop();
        for (int i = 0; i < graph[temp].size(); i++)
        {
            if (!checked[graph[temp][i]])
            {

                checked[graph[temp][i]] = 1;
                q.push(graph[temp][i]);
                sum++;
            }
        }
    }
}
int getDepth(int start)
{
    int sum = 0;

    while (start != 1)
    {
        start = parent[start];
        sum++;
    }
    return sum;
}
int main(void)
{
    int t;
    // cin >> t;
    // for (int tt = 1; tt <= t; tt++)
    // {
    for (int i = 0; i < CNT; i++)
    {
        graph[i].clear();
        parent[i] = 0;
        sum = 0;
        checked[i] = 0;
    }

    int v, e, v1, v2;
    cin >> v >> e >> v1 >> v2;
    for (int i = 0; i < e; i++)
    {
        int a, b;
        cin >> a >> b;
        parent[b] = a;
        graph[a].push_back(b);
    }

    // 1 까지 depth 구하기
    int depth_v1 = getDepth(v1);
    int depth_v2 = getDepth(v2);
    if (depth_v1 - depth_v2 > 0)
    {
        int cnt = depth_v1 - depth_v2;
        while (cnt--)
        {
            v1 = parent[v1];
        }
    }
    else
    {
        int cnt = depth_v2 - depth_v1;
        while (cnt--)
        {
            v2 = parent[v2];
        }
    }
    // cout << v1 << v2 << "\n";
    while (v1 != v2)
    {
        v1 = parent[v1];
        v2 = parent[v2];
    }
    // cout << "#" << tt << " " << v1 << " ";
    bfs(v1);
    cout << sum << "\n";
    //}
    return 0;
}