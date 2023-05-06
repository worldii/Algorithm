#include <iostream>
#include <queue>
using namespace std;

double sum = 0;
#include <cmath>
using namespace std;
typedef struct _edge
{
    int next;
    double cost;
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
    /* data */
};

priority_queue<Edges, vector<Edges>, cmp> pq;

int check[1001];

double getdis(double x, double y, double x2, double y2)
{
    return sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
}
vector<pair<double, double>> star;
vector<Edges> graph[1001];

void prim()
{
    while (!pq.empty())
    {
        auto tempnode = pq.top();
        // cout << tempnode.cost << " " << tempnode.next << "\n";
        pq.pop();
        if (check[tempnode.next])
            continue;
        sum += tempnode.cost;

        check[tempnode.next] = 1;
        auto templist = graph[tempnode.next];
        for (int i = 0; i < templist.size(); i++)
        {
            pq.push(Edges{templist[i].next, templist[i].cost});
        }
    }
}
int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        double x, y;
        cin >> x >> y;
        star.push_back({x, y});
    }
    for (int i = 0; i < star.size(); i++)
    {
        for (int j = i + 1; j < star.size(); j++)
        {
            double dis = getdis(star[i].first, star[i].second, star[j].first, star[j].second);
            graph[i].push_back(Edges{j, dis});
            graph[j].push_back(Edges{i, dis});
        }
    }
    check[0] = 1;
    auto temp = graph[0];
    for (int i = 0; i < temp.size(); i++)
    {
        pq.push(Edges{temp[i].next, temp[i].cost});
    }
    prim();
    cout << fixed;
    cout.precision(2);
    cout << sum;
    return 0;
}