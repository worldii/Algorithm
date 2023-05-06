#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
vector<int> v[32001];
int indegree[32001];
priority_queue<int, vector<int>, greater<int>> pq;
void bfs()
{

    while (!pq.empty())
    {
        int next = pq.top();
        cout << next << " ";
        pq.pop();
        for (int y : v[next])
        {
            int nextnode = y;
            indegree[nextnode] -= 1;
            if (indegree[nextnode] == 0)
            {

                pq.push(nextnode);
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
        int src, dest;
        cin >> src >> dest;
        v[src].push_back(dest);
        indegree[dest]++;
    }
    for (int i = 1; i <= n; i++)
    {
        if (indegree[i] == 0)
        {
            pq.push(i);
            //  cout << i << "\n";
        }
    }
    bfs();
    return 0;
}