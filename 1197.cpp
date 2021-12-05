#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int parent[10001];

typedef struct _edges
{
    int src;
    int dest;
    int cost;
} Edges;
vector<Edges> edges;
bool compare(Edges e1, Edges e2)
{
    return e1.cost < e2.cost;
}
int getparent(int a)
{
    if (parent[a] != a)
    {
        return (parent[a] = getparent(parent[a]));
    }
    return a;
}
int sameparent(int a, int b)
{
    if (getparent(a) == getparent(b))
        return (1);
    else
        return 0;
}
void uunion(int a, int b)
{
    a = getparent(a);
    b = getparent(b);
    if (a != b)
    {
        parent[a] = b;
    }
}
int main(void)
{
    int v, e;
    cin >> v >> e;
    for (int i = 0; i < e; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;

        edges.push_back({a, b, c});
    }
    sort(edges.begin(), edges.end(), compare);
    for (int i = 1; i <= v; i++)
    {
        parent[i] = i;
    }
    long long sum = 0;
    for (int i = 0; i < edges.size(); i++)
    {
        // 같은 집합이 아니면 합친다.
        // sameparent 인지 확인
        // 아니면 union 하는 과정
        if (sameparent(edges[i].src, edges[i].dest) == 0)
        {
            sum += edges[i].cost;
            uunion(edges[i].src, edges[i].dest);
        }
    }
    cout << sum;
    return (0);
}