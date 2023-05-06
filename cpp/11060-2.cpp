#include <iostream>
using namespace std;
int a[2001];

int d[2001];
int main(void)
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }
    if (n == 1)
    {
        cout << 0;
        return 0;
        ;
    }
    for (int i = 0; i < n; i++)
    {
        d[i] = -1;
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 1; j <= a[i]; j++)
        {
            if (i + j >= n)
                continue;
            if (d[i] == -1 && i != 0)
                continue;
            if (d[i + j] == -1 || d[i + j] > d[i] + 1)
                d[i + j] = d[i] + 1;
        }
    }

    if (d[n - 1] == -1)
        cout << -1;
    else
        cout << d[n - 1] + 1;
}