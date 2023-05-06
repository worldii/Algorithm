#include <iostream>
using namespace std;
#include <algorithm>
int a[501][501];
int d[501][501];

int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j <= i; j++)
        {
            cin >> a[i][j];
        }
    }

    d[0][0] = a[0][0];
    for (int i = 1; i < n; i++)
    {
        for (int j = 0; j <= i; j++)
        {
            d[i][j] = a[i][j] + d[i - 1][j];
            if (j - 1 >= 0)
            {
                d[i][j] = max(d[i][j], a[i][j] + d[i - 1][j - 1]);
            }
        }
    }
    int max1 = d[n - 1][0];
    for (int i = 0; i < n; i++)
    {
        max1 = max(max1, d[n - 1][i]);
    }

    cout << max1;
    return 0;
}