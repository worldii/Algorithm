#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>

using namespace std;
int a[1001][1001];
int check[1001][1001];
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};
int dist[1001][1001];
int n, m;
void bfs(int startx, int starty)
{
    queue<pair<int, int>> q;
    q.push(make_pair(startx, starty));
    check[startx][starty] = 1;
    while (!q.empty())
    {
        int newx = q.front().first;
        int newy = q.front().second;
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int x = newx + dx[i];
            int y = newy + dy[i];
            if (0 <= x && x < n)
            {
                if (0 <= y && y < m)
                {
                    if (!check[x][y] && a[x][y] == 1)
                    {
                        check[x][y] = 1;
                        dist[x][y] = dist[newx][newy] + 1;
                        q.push({x, y});
                    }
                }
            }
        }
    }
}
int main(void)
{
    int startx, starty;
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> a[i][j];
            if (a[i][j] == 2)
            {
                startx = i;
                starty = j;
            }
        }
    }
    bfs(startx, starty);
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (dist[i][j] == 0 && a[i][j] == 1)
                dist[i][j] = -1;
            cout << dist[i][j] << " ";
        }
        cout << "\n";
    }

    return 0;
}