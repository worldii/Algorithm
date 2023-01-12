#include <iostream>
using namespace std;
int l, n;
int red[101];
int green[101];
int dist[101];
int main(void)
{
    cin >> n >> l;
    for (int i = 0; i < n; i++)
    {
        cin >> dist[i] >> red[i] >> green[i];
    }
    int ttime = dist[0];

    for (int i = 0; i < n; i++)
    {
        // 초록불 일때
        if (ttime % (red[i] + green[i]) - red[i] > 0)
            ;
        // 빨간불 일때
        else
        {
            ttime += (red[i] - ttime % (red[i] + green[i]));
        }
        if (i == n - 1)
            break;
        ttime += dist[i + 1] - dist[i];
    }
    cout << ttime + l - dist[n - 1];
    return 0;
}
