#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;
int a[51][51];
typedef struct _cor
{
    int x;
    int y;
    int cost;
} Cor;
int a2[51][51];
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};
int r, c, t;
queue<Cor> q;
int check2[51][51];
Cor mise1;

void bfs()
{
    memset(check2, 0, sizeof(check2));

    while (!q.empty())
    {
        int tempx = q.front().x;
        int tempy = q.front().y;
        q.pop();
        int cnt = 0;
        for (int i = 0; i < 4; i++)
        {
            int corx = tempx + dx[i];
            int cory = tempy + dy[i];

            if (0 <= corx && corx < r && 0 <= cory && cory < c)
            {
                if (a[corx][cory] != -1)
                {

                    check2[corx][cory] += (a[tempx][tempy] / 5);
                    cnt++;
                }
            }
        }
        check2[tempx][tempy] -= cnt * (a[tempx][tempy] / 5);
    }
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            a[i][j] += check2[i][j];
        }
    }
    /* cout << '\n';
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            cout << a[i][j] << ' ';
        }
        cout << '\n';
    }*/
    memcpy(a2, a, sizeof(a));

    for (int i = c - 2; i >= 0; i--)
    {
        a[mise1.x][i + 1] = a2[mise1.x][i];
        a[mise1.x + 1][i + 1] = a2[mise1.x + 1][i];
    }

    for (int i = 0; i <= c - 2; i++)
    {
        a[0][i] = a2[0][i + 1];
        a[r - 1][i] = a2[r - 1][i + 1];
    }
    for (int i = 0; i < mise1.x; i++)
    {
        a[i][c - 1] = a2[i + 1][c - 1];
        a[i + 1][0] = a2[i][0];
    }
    for (int i = r - 1; i > mise1.x + 1; i--)
    {
        a[i][c - 1] = a2[i - 1][c - 1];
        a[i - 1][0] = a2[i][0];
    }
    a[mise1.x][mise1.y] = -1;
    a[mise1.x + 1][mise1.y] = -1;
    a[mise1.x][mise1.y + 1] = 0;
    a[mise1.x + 1][mise1.y + 1] = 0;

    /*  cout << '\n';
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            cout << a[i][j] << ' ';
        }
        cout << '\n';
    }*/
}
int main(void)
{

    cin >> r >> c >> t;
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            cin >> a[i][j];

            if (a[i][j] == -1 && mise1.cost == 0)
            {
                mise1.x = i;
                mise1.y = j;
                mise1.cost = 1;
            }
        }
    }
    while (t--)
    {
        q = queue<Cor>();
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                if (a[i][j] != -1 && a[i][j] != 0)
                    q.push(Cor{i, j});
            }
        }
        bfs();
    }
    int sum = 0;
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            if (a[i][j] > 0)
            {
                sum += a[i][j];
            }
        }
    }
    cout << sum;
    return 0;
}