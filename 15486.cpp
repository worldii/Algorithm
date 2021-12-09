#include <iostream>
#include <algorithm>
using namespace std;
int a[15000002];
int p[15000002];

int t[15000002];

int main(void)
{
    int n;
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        cin >> a[i] >> p[i];
        ;
    }

    for (int i = 1; i <= n; i++)
    {
        // 상담할 때 ,
        if (i + a[i] <= n + 1)
        {
            t[i + a[i]] = max(t[i + a[i]], t[i] + p[i]);
        }
        // 상담 안할때,
        if (i + 1 <= n + 1)
        {
            t[i + 1] = max(t[i + 1], t[i]);
        }
    }
    cout << t[n + 1];
    return 0;
}