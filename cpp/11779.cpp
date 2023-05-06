#include <iostream>
#include <vector>
#include <queue>
#include <stack>
#define INF 987654321
using namespace std;
vector<pair<int, int>> s[1001];
long long dis[1001];
int parent[1001];
void dikstra(int src)
{
    priority_queue<pair<int, int>> q;
    q.push({src, 0});
    while (!q.empty())
    {
        int node = q.top().first;
        int distance = -q.top().second;
        q.pop();
        if (distance > dis[node])
            continue;
        auto list = s[node];
        for (int i = 0; i < list.size(); i++)
        {
            int nextnode = list[i].first;
            int nextdistance = list[i].second;
            if (distance + nextdistance < dis[nextnode])
            {
                dis[nextnode] = distance + nextdistance;
                q.push({nextnode, -dis[nextnode]});
                parent[nextnode] = node;
            }
        }
    }
}
int main(void)
{
    int n, m;
    cin >> n >> m;

    for (int i = 0; i < m; i++)
    {
        int src, dest, cost;
        cin >> src >> dest >> cost;
        s[src].push_back({dest, cost});
    }
    int finalsrc, finaldest;
    cin >> finalsrc >> finaldest;
    for (int i = 1; i <= n; i++)
    {
        if (i == finalsrc)
            dis[i] = 0;
        else
            dis[i] = INF;
    }
    dikstra(finalsrc);
    cout << dis[finaldest] << "\n";
    int x = finaldest;
    stack<int> temp;
    while (x != 0)
    {
        temp.push(x);
        x = parent[x];
    }
    cout << temp.size() << "\n";
    while (!temp.empty())
    {
        int tt = temp.top();
        cout << tt << " ";
        temp.pop();
    }
    return 0;
}