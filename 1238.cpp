#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int n, m, x;
#define INF 987654321
// x 번 마을
vector<pair<int, int>> edges[1001];
int dis[1001][1001];
void dikstra(int start)
{
    priority_queue<pair<int, int>> pq;
    pq.push({0, start});
    while (!pq.empty())
    {
        int node = pq.top().second;
        int tempdis = -pq.top().first;
        pq.pop();
        if (dis[start][node] < tempdis)
            continue;
        auto next = edges[node];
        for (int i = 0; i < next.size(); i++)
        {

            int nextdis = next[i].second;
            int nextnode = next[i].first;
            //cout << nextdis << " " << nextnode;
            if (dis[start][nextnode] > tempdis + nextdis)
            {
                dis[start][nextnode] = tempdis + nextdis;
                pq.push({-dis[start][nextnode], nextnode});
            }
        }
    }
}
int main(void)
{
    cin >> n >> m >> x;

    for (int i = 0; i < m; i++)
    {
        int src, dest, time;
        cin >> src >> dest >> time;
        edges[src].push_back({dest, time});
    }
    for (int i = 1; i < 1001; i++)
    {
        for (int j = 1; j < 1001; j++)
        {
            if (i == j)
                continue;
            dis[i][j] = INF;
        }
    }
    for (int i = 1; i <= n; i++)
    {
        dikstra(i);
    }
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cout << dis[i][j] << " ";
        }
        cout << "\n";
    }
    int mmax = -1;
    for (int i = 1; i <= n; i++)
    {
        if (dis[i][x] == INF || dis[x][i] == INF)
            continue;
        if (mmax == -1 || mmax < dis[i][x] + dis[x][i])
        {
            mmax = dis[i][x] + dis[x][i];
        }
    }
    cout << mmax;
}