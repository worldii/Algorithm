#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
using namespace std;
#define INF 10000000;

typedef struct _cor
{

    int x;
    int y;
    int cost;
} Cor;

int n;
int a[101][101];
int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};
int check[101][101];
int dist[101][101];
void bfs(int startx, int starty)
{
    queue<Cor> q;
    q.push(Cor{startx, starty, 0});
    check[startx][starty] = 1;
    dist[startx][starty] = 0;
    while (!q.empty())
    {
        Cor temp = q.front();
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int tex = temp.x + dx[i];
            int tey = temp.y + dy[i];

            int tecost = temp.cost + a[temp.x][temp.y];
            if (dist[tex][tey] <= tecost)
                continue;
            if (0 <= tex && tex < n && 0 <= tey && tey < n)
            {

                // check[tex][tey] = 1;
                dist[tex][tey] = tecost;
                q.push(Cor{tex, tey, tecost});
            }
        }
    }
}

int main(void)
{
    int t;
    cin >> t;
    for (int tt = 1; tt <= t; tt++)
    {

        cin >> n;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                char temp;
                cin >> temp;
                a[i][j] = temp - '0';
            }
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                dist[i][j] = INF;
            }
        }
        bfs(0, 0);
        // cout << dist[n - 1][n - 1];

        // for (int i = 0; i < n; i++)
        // {
        //     for (int j = 0; j < n; j++)
        //     {
        //         cout << dist[i][j] << " ";
        //     }
        //     cout << "\n";
        // }

        cout << "#" << tt << " " << dist[n - 1][n - 1] << "\n";
    }
    return 0;
}