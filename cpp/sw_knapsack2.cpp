#include <iostream>
#include <algorithm>

using namespace std;
int n, w;
int weight[1001];
int price[1001];
int d[1001][10001];
int main(void)
{
    cin >> n >> w;
    for (int i = 1; i <= n; i++)
    {
        cin >> weight[i] >> price[i];
    }

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= w; j++)
        {
            d[i][j] = d[i - 1][j];
            if (j >= weight[i])
                d[i][j] = max(d[i][j], d[i - 1][j - weight[i]] + price[i]);
        }
    }
    cout << d[n][w];
    return 0;
}