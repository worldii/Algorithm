#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>
#include <queue>
#include <deque>
using namespace std;
int check[100001];
int dist[100001];
void bfs(int n, int k)
{
    deque<int> q;
    q.push_back(n);
    while (!q.empty())
    {
        int temp = q.front();
        q.pop_front();
        if (temp == k)
            break;
        if (0 <= temp * 2 && temp * 2 <= 100000 && check[temp * 2] == 0)
        {
            check[temp * 2] = 1;
            dist[temp * 2] = dist[temp];
            q.push_front(temp * 2);
        }
        if (0 <= temp - 1 && temp - 1 <= 100000 && check[temp - 1] == 0)
        {
            check[temp - 1] = 1;
            dist[temp - 1] = 1 + dist[temp];
            q.push_back(temp - 1);
        }
        if (0 <= temp + 1 && temp + 1 <= 100000 && check[temp + 1] == 0)
        {
            check[temp + 1] = 1;
            dist[temp + 1] = 1 + dist[temp];
            q.push_back(temp + 1);
        }
    }
}
int main(void)
{
    int n, k;
    cin >> n >> k;
    bfs(n, k);
    cout << dist[k];
    return 0;
}