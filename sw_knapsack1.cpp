#include <iostream>
#include <algorithm>

using namespace std;
int n, w;
int weight[1001];
int price[1001];
int d[10001];
int main(void)
{
    cin >> n >> w;
    for (int i = 1; i <= n; i++)
    {
        cin >> weight[i] >> price[i];
    }
    d[0] = 0;
    for (int i = 1; i <= w; i++)
    {
        d[i] = d[i - 1];

        for (int j = 1; j <= n; j++)
        {

            if (i >= weight[j])
                d[i] = max(d[i], d[i - weight[j]] + price[j]);
        }
    }

    cout << d[w];
    return 0;
}