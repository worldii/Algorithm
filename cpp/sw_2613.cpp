#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int a[1001][1001];
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};
int check[1001][1001];
int dist[1001][1001];
vector<pair<int, int> > tomato;
int m, n;
queue<pair<int, int> > q;

void bfs()
{

    for (int i = 0; i < tomato.size(); i++)
    {
        q.push(make_pair(tomato[i].first, tomato[i].second));
        check[tomato[i].first][tomato[i].second] = 1;
    }
    while (!q.empty())
    {
        int tempx = q.front().first;
        int tempy = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++)
        {
            int newx = tempx + dx[i];
            int newy = tempy + dy[i];
            if (0 <= newx && newx < n && 0 <= newy && newy < m)
            {
                if (check[newx][newy] == 0 && a[newx][newy] == 0)
                {
                    check[newx][newy] = 1;
                    // a[newx][newy] = 1;
                    dist[newx][newy] = dist[tempx][tempy] + 1;
                    q.push(make_pair(newx, newy));
                }
            }
        }
    }
}
int main(void)
{
    cin >> m >> n;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> a[i][j];
            if (a[i][j] == 1)
                tomato.push_back(make_pair(i, j));
        }
    }
    bfs();
    int flag = 0;
    int maxsum = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            //  cout << a[i][j];
            if (a[i][j] == 0 && dist[i][j] == 0)
            {
                flag = 1;
            }
            if (maxsum < dist[i][j])
            {
                maxsum = dist[i][j];
            }
        }
        //   cout << "\n";
    }
    /* for (int i = 0; i < n; i++)
     {
         for (int j = 0; j < m; j++)
         {
             cout << dist[i][j];
         }
         cout << "\n";
     }*/
    if (flag == 1)
    {
        cout << -1;
        return 0;
    }
    cout << maxsum;

    return 0;
}
