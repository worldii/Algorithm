#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;
vector<int> v[10001];
int indegree[10001];
int nodecost[10001];
queue<pair<int, int>> pq;
int a[10001];
long long sum = 0;
void bfs()
{
    while (!pq.empty())
    {
        int next = pq.front().second;
        long long cost = pq.front().first;
        //  cout << next << " " << cost << "\n";
        sum = max(sum, cost);
        pq.pop();
        for (int y : v[next])
        {
            int nextnode = y;
            indegree[nextnode] -= 1;
            if (cost + nodecost[nextnode] > a[nextnode])
            {
                a[nextnode] = cost + nodecost[nextnode];
            }
            if (indegree[nextnode] == 0)
            {

                pq.push({a[nextnode], nextnode});
            }
        }
    }
}
int main(void)
{
    int n;
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        int cost, count;
        cin >> cost >> count;
        nodecost[i] = cost;
        for (int j = 0; j < count; j++)
        {
            int dest;
            cin >> dest;
            v[dest].push_back(i);
            indegree[i]++;
        }
    }

    for (int i = 1; i <= n; i++)
    {
        if (indegree[i] == 0)
        {
            pq.push({nodecost[i], i});
            a[i] = nodecost[i];
        }
    }
    bfs();
    cout << sum;
    return 0;
}