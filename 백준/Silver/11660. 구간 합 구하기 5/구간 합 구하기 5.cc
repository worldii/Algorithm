#include <iostream>
using namespace std;
int a[1025][1025];
int d[1025][1025];

int main(void)
{

    cin.tie(NULL);
    ios::sync_with_stdio(false);
    cout.tie(NULL);
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> a[i][j];
        }
    }

    for (int i = 0; i < n; i++)
    {
        if (i >= 1)
            d[0][i] = d[0][i - 1];
        d[0][i] += a[0][i];
    }
    for (int i = 1; i < n; i++)
    {
        d[i][0] = d[i - 1][0];
        d[i][0] += a[i][0];
    }

    for (int i = 1; i < n; i++)
    {
        for (int j = 1; j < n; j++)
        {
            d[i][j] = d[i - 1][j] + d[i][j - 1] + a[i][j] - d[i - 1][j - 1];
        }
    }

    /*for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cout << d[i][j] << " ";
        }
        cout << "\n";
    }*/
    for (int i = 0; i < m; i++)
    {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        // 출력
        x1 -= 1;
        y1 -= 1;
        x2 -= 1;
        y2 -= 1;
        int result = d[x2][y2];
        if (x1 >= 1)
            result -= d[x1 - 1][y2];
        if (y1 >= 1)
            result -= d[x2][y1 - 1];
        if (x1 >= 1 && y1 >= 1)
            result += d[x1 - 1][y1 - 1];
        cout << result << "\n";
    }

    return 0;
}