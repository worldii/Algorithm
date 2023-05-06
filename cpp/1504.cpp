#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#define INF 987654321

using namespace std;
vector<pair<int, int>> edges[801];
long long  dis[1001][1001];
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
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n, e;
    cin >> n >> e;
    for (int i = 0; i < e; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        edges[a].push_back({b, c});
        edges[b].push_back({a, c});
    }

    int tempa, tempb;
    cin >> tempa >> tempb;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
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

    /*for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cout << dis[i][j] << " ";
        }
        cout << "\n";
    }*/
    long long sum1 = dis[1][tempa] + dis[tempa][tempb] + dis[tempb][n];
    long long sum2 = dis[1][tempb] + dis[tempb][tempa] + dis[tempa][n];
    //   cout << sum1 << sum2 << " ";

    sum1 = min(sum1, sum2);
    if (sum1 >= INF)
        sum1 = -1;
    cout << sum1;
    return (0);
}
