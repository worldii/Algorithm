#include <iostream>
#include <queue>
#include <algorithm>
#include <cmath>
using namespace std;
int dx[9] = {-1, -1, -1, 0, 1, 1, 1, 0, 0};
int dy[9] = {-1, 0, 1, 1, 1, 0, -1, -1, 0};
pair<int, int> somi[2];
pair<int, int> dori[2];
int a[26][26];
typedef struct _cor
{
    int dorix;
    int doriy;
    int somix;
    int somiy;
} Cor;

int check[26][26][26][26];
int n;
int dist[26][26][26][26];
int bfs()
{
    queue<Cor> q;
    Cor temp2 = {dori[0].first, dori[0].second, somi[0].first, somi[0].second};
    q.push(temp2);
    check[dori[0].first][dori[0].second][somi[0].first][somi[0].second] = 1;
    while (!q.empty())
    {
        Cor temp = q.front();
        if (temp.dorix == dori[1].first && temp.doriy == dori[1].second && temp.somix == somi[1].first && temp.somiy == somi[1].second)
            return dist[temp.dorix][temp.doriy][temp.somix][temp.somiy];
        q.pop();
        for (int i = 0; i < 9; i++)
        {
            int tempdorix = dx[i] + temp.dorix;
            int tempdoriy = dy[i] + temp.doriy;
            if (tempdorix <= 0 || tempdorix > n || tempdoriy <= 0 || tempdoriy > n || a[tempdorix][tempdoriy] == 1)
                continue;
            for (int j = 0; j < 9; j++)
            {
                int tempsomix = dx[j] + temp.somix;
                int tempsomiy = dy[j] + temp.somiy;
                if (tempsomix <= 0 || tempsomix > n || tempsomiy <= 0 || tempsomiy > n || a[tempsomix][tempsomiy] == 1)
                    continue;
                if (abs(tempdorix - tempsomix) <= 1 && abs(tempdoriy - tempsomiy) <= 1)
                    continue;
                if (check[tempdorix][tempdoriy][tempsomix][tempsomiy] == 0)
                {
                    check[tempdorix][tempdoriy][tempsomix][tempsomiy] = 1;
                    dist[tempdorix][tempdoriy][tempsomix][tempsomiy] = dist[temp.dorix][temp.doriy][temp.somix][temp.somiy] + 1;
                    Cor newtemp = {tempdorix, tempdoriy, tempsomix, tempsomiy};
                    q.push(newtemp);
                }
            }
        }
    }
    return 0;
}
int main(void)

{
    cin >> n;
    cin >> dori[0].first >> dori[0].second >> dori[1].first >> dori[1].second;

    cin >> somi[0].first >> somi[0].second >> somi[1].first >> somi[1].second;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cin >> a[i][j];
        }
    }
    cout << bfs();
    return 0;
}