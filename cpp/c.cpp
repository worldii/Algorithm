#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
vector<vector<int>> t;
vector<int> le;
int check[1001][1001];
void bfs(int src, int depth)
{
    queue<pair<int, int>> q;
    q.push({src, 1});
    check[src][1] = 1;
    int count = 1;
    while (!q.empty())
    {
        int temp = q.front().first;
        int tempcount = q.front().second;
        if (tempcount > depth)
            break;
        q.pop();
        vector<int> tt = t[temp];
        for (int i = 0; i < tt.size(); i++)
        {
            if (check[tt[i]][tempcount+1] ==1) continue;
            check[tt[i]][tempcount+1] = 1;
            q.push({tt[i], tempcount + 1});
            if (tempcount == depth)
            {
                le.push_back(tt[i]);
            }
        }
    }
    while (!q.empty())
    {
        q.pop();
    }
    sort(le.begin(), le.end());
    if (le.size() == 0)
    {
        cout << -1;
        return;
    }
    int temp = le[0];
    cout << temp << ' ';
    for (int i = 1; i < le.size(); i++)
    {
        if (temp == le[i])
            continue;
        cout << le[i] << ' ';
        temp = le[i];
    }
}

int main(void)
{
    int n, m, x, y;
    cin >> n >> m >> x >> y;
    for (int i = 0; i < n; i++)
    {
        vector<int> s;
        t.push_back(s);
    }
    for (int i = 0; i < m; i++)
    {
        int src, des;
        cin >> src >> des;
        t[src].push_back(des);
        t[des].push_back(src);
    }
    bfs(x, y);
    return (0);
}