#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int check[1001];
int sum = 0;
vector<pair<int, int>> graph[1001];
typedef struct _edges
{
    int to;
    int cost;
} Edges;
struct cmp
{
    bool operator()(Edges e1, Edges e2)
    {
        if (e1.cost > e2.cost)
            return true;
        else
            return false;
    }
};
priority_queue<Edges, vector<Edges>, cmp> pq;
void prim()
{
    while (!pq.empty())
    {
        int node = pq.top().to;
        int cost = pq.top().cost;
        pq.pop();
        if (check[node] == 1)
            continue;
        check[node] = 1;
        sum += cost;
        auto next = graph[node];
        for (int i = 0; i < next.size(); i++)
        {
            int nextnode = next[i].first;
            int nextcost = next[i].second;
            pq.push({nextnode, nextcost});
        }
    }
}
int main(void)
{
    int n, m;

    cin >> n >> m;
    for (int i = 0; i < m; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        graph[a].push_back({b, c});
        graph[b].push_back({a, c});
    }
    check[1] = 1;
    auto temp = graph[1];
    for (int i = 0; i < temp.size(); i++)
    {
        int next = temp[i].first;
        int cost = temp[i].second;
        pq.push({next, cost});
    }
    prim();
    cout << sum;
    return (0);
}