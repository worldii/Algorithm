#include <iostream>
#include <vector>
#include <queue>
#include <memory.h>

using namespace std;
typedef struct _Data
{
    int next;
    int cost;
} Data;

int dist[10001];
int check[10001];
vector<Data> node[10001];

void bfs(int n)
{
    queue<int> q;
    q.push(n);
    check[n] = 1;
    while (!q.empty())
    {
        int temp = q.front();
        q.pop();
        vector<Data> tempnode = node[temp];
        for (int i = 0; i < tempnode.size(); i++)
        {
            if (check[tempnode[i].next] == 0)
            {
                check[tempnode[i].next] = 1;
                dist[tempnode[i].next] = dist[temp] + tempnode[i].cost;
                q.push(tempnode[i].next);
            }
        }
    }
}
int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n - 1; i++)
    {
        int n1, n2, cost;
        cin >> n1 >> n2 >> cost;
        node[n1].push_back({n2, cost});
        node[n2].push_back({n1, cost});
    }
    memset(check, 0, sizeof(check));
    memset(dist, 0, sizeof(dist));
    bfs(1);
    /*for (int i = 1; i <= n; i++)
    {
        cout << dist[i] << " ";
    }*/
    int maxnode = 1;
    for (int i = 1; i <= n; i++)
    {
        if (dist[maxnode] < dist[i])
        {
            maxnode = i;
        }
    }
    // cout << maxnode;
    memset(check, 0, sizeof(check));
    memset(dist, 0, sizeof(dist));
    bfs(maxnode);
    /*for (int i = 1; i <= n; i++)
    {
        cout << dist[i] << " ";
    }*/

    maxnode = 1;
    for (int i = 1; i <= n; i++)
    {
        if (dist[maxnode] < dist[i])
            maxnode = i;
    }
    cout << dist[maxnode];
    return 0;
}