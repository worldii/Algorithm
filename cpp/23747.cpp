#include <iostream>
#include <queue>
#include <string>
using namespace std;
char a[2001][2001];
string temp;
int check[2001][2001];
typedef struct _cor
{
    int x;
    int y;
} Cor;
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};
int r, c;
void bfs(int x, int y)
{
    check[x][y] = 1;
    queue<Cor> q;
    q.push(Cor{x, y});
    while (!q.empty())
    {
        int tempx = q.front().x;
        int tempy = q.front().y;
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int tx = tempx + dx[i];
            int ty = tempy + dy[i];
            //   cout << tx << ty << '\n';

            if (1 <= tx && tx <= r && 1 <= ty && ty <= c)
            {
                if (check[tx][ty] == 0 && a[tempx][tempy] == a[tx][ty])
                {
                    //         cout << "hell" << tx << ty << '\n';
                    check[tx][ty] = 1;
                    q.push(Cor{tx, ty});
                }
            }
        }
    }
}
int main(void)
{

    cin >> r >> c;
    int tempr, tempc;

    for (int i = 1; i <= r; i++)
    {
        for (int j = 1; j <= c; j++)
        {
            cin >> a[i][j];
        }
    }
    cin >> tempr >> tempc;
    cin >> temp;
    for (int i = 0; i < temp.length(); i++)
    {
        if (temp[i] == 'U')
        {
            if (tempr - 1 >= 1)
            {
                tempr -= 1;
            }
        }
        else if (temp[i] == 'D')
        {
            if (tempr + 1 <= r)
            {
                tempr += 1;
            }
        }
        else if (temp[i] == 'L')
        {
            if (tempc - 1 >= 1)
            {
                tempc -= 1;
            }
        }
        else if (temp[i] == 'R')
        {
            if (tempc + 1 <= c)
            {
                tempc += 1;
            }
        }
        else if (temp[i] == 'W')
        {
            //  cout << tempr << tempc;
            bfs(tempr, tempc);
        }
    }
    if (tempr + 1 <= r)
        check[tempr + 1][tempc] = 1;
    if (tempr - 1 >= 1)
        check[tempr - 1][tempc] = 1;
    if (tempc + 1 <= c)
        check[tempr][tempc + 1] = 1;
    if (tempc - 1 >= 1)
        check[tempr][tempc - 1] = 1;
    check[tempr][tempc] = 1;
    for (int i = 1; i <= r; i++)
    {
        for (int j = 1; j <= c; j++)
        {
            if (check[i][j] == 1)
            {
                cout << '.';
            }
            else
                cout << '#';
        }
        cout << "\n";
    }

    return 0;
}