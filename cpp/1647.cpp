#include <iostream>
using namespace std;
#include <vector>
#include <queue>
#include <algorithm>
int check[100001];
typedef struct _node
{
    /* data */
    int next;
    long long cost;
} Node;
vector<Node> graph[100001];
vector<long long> vec;
struct cmp
{
    bool operator()(Node e1, Node e2)
    {
        if (e1.cost > e2.cost)
            return true;
        else
            return false;
    }
};
priority_queue<Node, vector<Node>, cmp> pq;
long long sum = 0;
int last = -1;
void prim()
{
    while (!pq.empty())
    {
        Node temp = pq.top();
        int tempnext = temp.next;
        long long tempcost = temp.cost;
        pq.pop();
        if (check[tempnext])
            continue;
        check[tempnext] = 1;
        vec.push_back(tempcost);
        // sum += tempcost;
        // last = tempcost;
        auto list = graph[tempnext];
        for (int i = 0; i < list.size(); i++)
        {
            int next = list[i].next;
            long long cost = list[i].cost;
            pq.push({next, cost});
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
        int next = temp[i].next;
        long long cost = temp[i].cost;
        pq.push({next, cost});
    }
    prim();
    sort(vec.begin(), vec.end());
    long long sum = 0;
    for (int i = 0; i < vec.size() - 1; i++)
    {
        sum += vec[i];
        // cout << vec[i] << " ";
    }
    cout << sum;
    return 0;
}