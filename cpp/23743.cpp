#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

typedef struct _tempp
{
    int src;
    int des;
    int cost;
} Gr;
vector<Gr> ggraph[200002];
vector<Gr> edges;
bool compare(Gr a, Gr b)
{
    return a.cost < b.cost;
}
int parent[200003];
int find_parent(int x)
{
    if (parent[x] == x)
        return x;
    else
        return parent[x] = find_parent(parent[x]);
}
void uunion(int x, int y)
{
    x = find_parent(x);
    y = find_parent(y);
    if (x != y)
    {
        parent[y] = x;
    }
}
bool sameparent(int x, int y)
{
    x = find_parent(x);
    y = find_parent(y);
    if (x == y)
        return true;
    else
        return false;
}

int main(void)
{
    int n, m;
    cin >> n >> m;
    for (int i = 0; i <= 200002; i++)
    {
        parent[i] = i;
    }
    for (int i = 0; i < m; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        ggraph[a].push_back(Gr{a, b, c});
        ggraph[b].push_back(Gr{b, a, c});
        edges.push_back(Gr{a, b, c});
    }

    for (int i = 0; i < n; i++)
    {
        int temp;
        cin >> temp;
        ggraph[200001].push_back(Gr{200001, i + 1, temp});
        ggraph[i + 1].push_back(Gr{i + 1, 200001, temp});
        edges.push_back(Gr{200002, i + 1, temp});
    }
    sort(edges.begin(), edges.end(), compare);
    int sum = 0;
    for (int i = 0; i < edges.size(); i++)
    {
        if (sameparent(edges[i].src, edges[i].des) == false)
        {
            uunion(edges[i].src, edges[i].des);
            sum += edges[i].cost;
        }
    }
    cout << sum;
    return (0);
}