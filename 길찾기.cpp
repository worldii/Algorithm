#include <iostream>
#include <vector>
#include <queue>
#include <cstdio>
#include <memory.h>
using namespace std;
int check[100];
void bfs(int start, int end, vector<int> graph[100])
{
    queue<int> q;
    check[start] = 1;
    q.push(start);
    while (!q.empty())
    {
        int temp = q.front();
        q.pop();
        // cout << temp << "\n";
        auto nextlist = graph[temp];
        for (int i = 0; i < nextlist.size(); i++)
        {
            if (check[nextlist[i]] == 0)
            {
                check[nextlist[i]] = 1;
                q.push(nextlist[i]);
            }
        }
    }
}
int main(void)
{
    int tc, count;
    int testcase;
    for (testcase = 1; testcase <= 10; testcase++)
    {
        cin >> tc >> count;
        memset(check, 0, sizeof(check));
        vector<int> graph[100];

        for (int i = 0; i < count; i++)
        {
            int a, b;
            cin >> a >> b;
            graph[a].push_back(b);
        }
        bfs(0, 99, graph);
        cout << "#" << tc << " " << check[99] << "\n";
    }
    return 0;
}