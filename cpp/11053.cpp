#include <vector>
#include <iostream>

using namespace std;
int a[1001];
int d[1001];
int main(void)
{

    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++)
    {
        d[i] = 1;
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < i; j++)
        {
            if (a[i] > a[j] && d[i] < d[j] + 1)
            {
                d[i] = d[j] + 1;
            }
        }
    }
    int max = d[0];
    for (int i = 0; i < n; i++)
    {
        if (max < d[i])
            max = d[i];
        // cout << d[i] << " ";
    }
    cout << max;
    return 0;
}