#include <iostream>
#include <algorithm>
#include <cstdio>
using namespace std;
long long c[10001];
long long y[10001];
int main(void)
{
    int n, s;
    cin >> n >> s;
    for (int i = 0; i < n; i++)
    {
        cin >> c[i] >> y[i];
    }

    unsigned long long sum = 0;
    for (int i = 0; i < n; i++)
    {
        unsigned long long weeksum = 0;
        for (int j = 0; j <= i; j++)
        {
            unsigned long long tempsum = (c[j] + ((i - j) * s)) * y[i];
            if (weeksum == 0 || weeksum > tempsum)
                weeksum = tempsum;
        }
        sum += weeksum;
    }
    cout << sum;
    return 0;
}